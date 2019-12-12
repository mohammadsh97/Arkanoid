package com.mohammadSharabati_ahmadSharabati.ex2;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.widget.Toast;

import static android.content.ContentValues.TAG;

public class Paddle {
    private float top, bottom, right, left;
    public Paint p;
    private int moveRight = 0, moveLeft = 0;

    public Paddle(float left, float top, float right, float bottom) {
        this.left = left;
        this.top = top;
        this.bottom = bottom;
        this.right = right;
        this.p = new Paint(Paint.ANTI_ALIAS_FLAG);
        this.p.setColor(Color.RED);
    }

    public void moveRight(float w) {//to make the paddle move right
        if (this.getRight() <= w) {
            this.right ++;//= moveRight;
            this.left ++;//= moveRight;
        }
    }

    public void moveLeft() {//to make the paddle move left
        if (this.getLeft() > 0) {
            this.left --;//= moveLeft;
            this.right --;//= moveLeft;
        }
    }

    public void setLeft(float left) {
        this.left = left;
    }

    public float getLeft() {
        return left;
    }

    public void setRight(float right) {
        this.right = right;
    }

    public float getRight() {
        return right;
    }

    public void draw(Canvas canvas) {
        canvas.drawRect(left, top, right, bottom, p);
    }

    void didCollide(Ball ball) {//to check collision from top

        float marginy = (bottom - top);

        if (ball.getX() + ball.getRadius() > left && ball.getX() - ball.getRadius() < right) {
            if (ball.getY() + ball.getRadius() > top && ball.getY() + ball.getRadius() < bottom + marginy) {
                if (ball.getY() >= 0) {
                    if (ball.movY < 0)
                        ball.movY = (ball.movY * -1);
                }

            }
        }

    }
}