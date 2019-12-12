package com.mohammadSharabati_ahmadSharabati.ex2;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;


import androidx.annotation.Nullable;

public class BrickCollection {
    int counterColsions = 0;
    float getHeight, getWidth;
    int row, col;
    Brick[] AllOfbricks;
    int counter = 0;

    public BrickCollection(float getHeight, float getWidth, int row, int col) {
        counter = 0;
        this.getHeight = getHeight;
        this.getWidth = getWidth;
        this.row = row;
        this.col = col;
        AllOfbricks = new Brick[row * col];
        float paddingX = 4;
        float paddingY = 4;
        float x = ((getWidth - (4 * 6) + 22) / 7);
        float y = (((getHeight - (4 * 4)) / 5) / 3);
        Paint p = new Paint();
        p.setColor(Color.RED);
        p.setStyle(Paint.Style.FILL);
        for (int i = 2; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                if (j == 0 && (i -2) >= 0)
                    AllOfbricks[counter] = new Brick(j * x, (i * y) + paddingY, (j + 1) * x, (i + 1) * y);
                else
                    AllOfbricks[counter] = new Brick((j * x) + paddingX, (i * y) + paddingY, (j + 1) * x, (i + 1) * y);
                counter++;
                if (counter == row * col)
                    break;
            }
        }
        counter = 0;
    }

    public void draw(Canvas canvas) {
        counter = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                AllOfbricks[counter].draw(canvas);
                counter++;
                if (counter == row * col)
                    break;
            }
        }
        counter = 0;
    }

public boolean checkCollision(Ball ball) {//to check collision
    String result;

    for (int i = 0; i < row * col; i++) {
        result = AllOfbricks[i].didCollide(ball);
        if (!result.equalsIgnoreCase("none")) {
            if (result.equalsIgnoreCase("top")) {
                counterColsions++;
                AllOfbricks[i].isDisabled = true;
                if (ball.getY() <= 0)
                    ball.movY = ball.movY * -1;

                return true;

            } else if (result.equalsIgnoreCase("bottom")) {
                AllOfbricks[i].isDisabled = true;
                counterColsions++;
                if (ball.getY() >= 0)
                    ball.movY = ball.movY * -1;
                return true;

            } else if (result.equalsIgnoreCase("left")) {
                AllOfbricks[i].isDisabled = true;
                counterColsions++;
                if (ball.getX() <= 0)
                    ball.movX = ball.movX * -1;
                return true;

            } else if (result.equalsIgnoreCase("right")) {
                AllOfbricks[i].isDisabled = true;
                counterColsions++;
                if (ball.getX() >= 0)
                    ball.movX = ball.movX * -1;
                return true;
            }
        }
    }
    return false;
}

    public void newGame() {//incase of game-over set isDisabled to false.
        for (int i = 0; i < row * col; i++) {
            AllOfbricks[i].isDisabled = false;
            AllOfbricks[i].p.setColor(Color.RED);
            AllOfbricks[i].p.setStyle(Paint.Style.FILL);
        }
    }
}
