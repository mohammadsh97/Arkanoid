package com.mohammadSharabati_ahmadSharabati.ex2;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Brick {

    private float top, bottom, right, left;
    public Paint p;
    boolean isDisabled;

    public Brick(float left, float top, float right, float bottom) {
        this.left = left;
        this.top = top;
        this.bottom = bottom;
        this.right = right;
        this.p = new Paint(Paint.ANTI_ALIAS_FLAG);
        this.p.setColor(Color.RED);
        this.isDisabled = false;
    }

    public String didCollide(Ball ball) {//to check collision
        if (isDisabled == true)
            return "none";
        float marginy = (bottom - top) * 7 / 20;
        float margin = (right - left) * 7 / 20;

        float balltopy = ball.getY() - ball.getRadius();
        if (ball.getX() + ball.getRadius() > left && ball.getX() - ball.getRadius() < right) {
            if (balltopy < bottom && balltopy > bottom - marginy) {
                return "bottom";
            } else if (ball.getY() + ball.getRadius() > top && ball.getY() + ball.getRadius() < top + marginy) {
                return "top";
            }
        }

        if (ball.getY() + ball.getRadius() > top && ball.getY() - ball.getRadius() < bottom) {
            if (ball.getX() + ball.getRadius() > left && ball.getX() + ball.getRadius() < left + margin) {
                return "Left";
            } else if (ball.getX() - ball.getRadius() < right && ball.getX() - ball.getRadius() > right - margin) {
                return "right";
            }
        }
        return "none";
    }
    public void draw(Canvas canvas) {
        if (isDisabled == false)
            canvas.drawRect(left, top, right, bottom, p);
    }

}
