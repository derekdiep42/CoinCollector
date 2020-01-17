package com.example.testjavagame;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

public class Score implements GameObject {

    private int score;
    private Rect rectangle;

    public Score (int score) {
        this.score = score;
    }

    @Override
    public void draw(Canvas canvas) {
        Paint paint = new Paint ();
        paint.setColor(Color.GRAY);
        Paint paint2 = new Paint ();
        paint2.setColor(Color.BLUE);

        canvas.drawRect(new Rect (0, Constants.SCREEN_HEIGHT - 200, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT), paint);
        paint2.setTextSize(90);
        canvas.drawText("Score: " + score, 100, Constants.SCREEN_HEIGHT - 75, paint2);
    }

    @Override
    public void update() {
        this.score++;
    }
}
