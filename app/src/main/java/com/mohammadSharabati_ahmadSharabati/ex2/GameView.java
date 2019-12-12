package com.mohammadSharabati_ahmadSharabati.ex2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import java.util.Random;

public class GameView extends View {

    private int score = 0, lives = 3, WinScore = 5 * 35;
    private static final int ROWS = 5;
    private static final int COLS = 7;
    private float getHeight, getWidth;
    private float fx, fy;
    BrickCollection B;
    private boolean didColide = false;
    private MediaPlayer colsn;//for the sound
    int k ;
    enum State {GET_READY, PLAYING, GAME_OVER};
    public State state;
    private Paint pInformation, pMessage;
    Paddle paddle;
    private Ball ball;
    private int movePaddle = 0;


    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    private void threadForBrick() {
        Thread task = new Thread() {
            @Override
            public void run() {
                didColide = B.checkCollision(ball);
                paddle.didCollide(ball);
            }
        };

        task.start();
    }

    private void forMessege() {
        pMessage = new Paint(Paint.ANTI_ALIAS_FLAG);
        pMessage.setTextAlign(Paint.Align.CENTER);
        pMessage.setColor(Color.BLACK);
//        pMessage.setStyle(Paint.Style.STROKE);
        pMessage.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
        pMessage.setTextSize(55);

    }

    private void forInformation() {
        pInformation = new Paint(Paint.ANTI_ALIAS_FLAG);
        pInformation.setColor(Color.BLACK);
        pInformation.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
        pInformation.setTextSize(60);
    }

    private void startGame() {
        forInformation();
        forMessege();
        state = State.GET_READY;
        B = new BrickCollection(getHeight, getWidth, ROWS, COLS);
        colsn = MediaPlayer.create(getContext(), R.raw.impact);
        paddle = new Paddle((getWidth / 2) - 200f, getHeight - 50f, (getWidth / 2) + 200f, getHeight - 20f);
        ball = new Ball(((getWidth / 2) - 200f) + 200f, getHeight - 90f, 30);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(k==0) {
            startGame();
            k++;
        }

        B.draw(canvas);
        paddle.draw(canvas);
        paddle.didCollide(ball);
        threadForBrick();
        ball.draw(canvas);
        pInformation.setTextAlign(Paint.Align.RIGHT);
        canvas.drawText("Lives: " + lives, getWidth - 50, 70, pInformation);
        pInformation.setTextAlign(Paint.Align.LEFT);
        canvas.drawText("Scores: " + score, 50, 70, pInformation);
        ball.ballMov(getWidth, getHeight);
        k=1;

        switch (state) {
            case GET_READY:
                canvas.drawText("Click to PLAY!", getWidth / 2, getHeight / 2, pMessage);
                break;

            case PLAYING:

                // move the jumpingBall
                ball.ballMov(getWidth, getHeight);
                if (didColide) {
                    score = B.counterColsions * 5;
                    colsn.start();
                }
                if (B.counterColsions == WinScore)//incase of winning
                    state = State.GAME_OVER;

                // check if the ball droped out of height
                if (ball.outOfHeight == true) {
                    if (lives > 1) {
                        lives--;
                        ball.outOfHeight = false;
                        ball.setX(((getWidth / 2) - 200f) + 200f);
                        ball.setY(getHeight - 90f);
                        paddle.setLeft((getWidth / 2) - 200f);
                        paddle.setRight((getWidth / 2) + 200f);
                        state = State.GET_READY;
                        ball.movX = 0;
                        ball.movY = 0;
                    } else {
                        state = State.GAME_OVER;
                    }
                }
                if (score == WinScore) {
                    canvas.drawText("YOU WIN!", getWidth / 2, getHeight / 2, pMessage);
                    lives++;
                    score = 0;
                    state = State.GET_READY;
                    startGame();
                }
                break;
            case GAME_OVER:
                if (score == WinScore)
                    canvas.drawText("YOU WIN!", getWidth / 2, getHeight / 2, pMessage);
                else {
                    canvas.drawText("GAME OVER - You Loss!", getWidth / 2, getHeight / 2, pMessage);
                    ball.movX = 0;
                    ball.movY = 0;
                }
                break;
        }
        invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        fx = event.getX();
        fy = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (state == State.GET_READY) {
                    ball.movX = new Random().nextInt(3) + 1;
                    ball.movY = new Random().nextInt(3) + 1;
                    state = State.PLAYING;
                } else {
                    if (state == State.PLAYING) {
                    } else if (state == state.GAME_OVER) {
                        lives = 3;
                        score = 0;
                        B.counterColsions = 0;
                        ball.setX(((getWidth / 2) - 200f) + 200f);
                        ball.setY(getHeight - 90f);
                        paddle.setLeft((getWidth / 2) - 200f);
                        paddle.setRight((getWidth / 2) + 200f);
                        ball.outOfHeight = false;
                        B.newGame();
                        state = state.GET_READY;
                    } else {
                        startGame();
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                if (state == State.PLAYING)
                    movePaddle = 0;
                break;
        }
        invalidate();
        return true;
    }

    @Override
    protected void onSizeChanged(int width, int height, int oldw, int oldh) {
        super.onSizeChanged(width, height, oldw, oldh);
        getWidth = width;
        getHeight = height;

    }

}