package com.example.testjavagame;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public class Coins implements GameObject{

    private Rect rectangle;
    private int color;

    public Coins(Rect rectangle, int color) {
        this.rectangle = rectangle;
        this.color = color;
    }

    public Rect getRectangle() {
        return rectangle;
    }

    public boolean coinCollide(RectPlayer player) {
        return Rect.intersects(rectangle, player.getRectangle());
    }

    @Override
    public void draw(Canvas canvas) {
        Paint paint = new Paint ();
        paint.setColor(color);
        canvas.drawRect(rectangle, paint);
    }

    @Override
    public void update() {
        int l,t,r,b;
        l = (int)(Math.random()*(Constants.SCREEN_WIDTH - 80));
        t = (int)(Math.random()*(Constants.SCREEN_HEIGHT - 280));
        r = l + 80;
        b = t + 80;
        rectangle.set(l,t,r,b);
    }
}
