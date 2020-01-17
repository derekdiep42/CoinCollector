package com.example.testjavagame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {

    private MainThread thread;
    private RectPlayer player;
    private Point playerPoint;
    private Coins coin;
    private Score score;


    private boolean canMove = false;

    public GameView(Context context) {
        super(context);

        getHolder().addCallback(this);

        thread = new MainThread(getHolder(), this);
        player = new RectPlayer(new Rect(100,100,220,220), Color.BLUE);
        playerPoint = new Point(160,160);
        coin = new Coins(new Rect (200,400,280,480), Color.YELLOW);
        score = new Score(0);

        setFocusable (true);
    }

    @Override
    public void surfaceChanged (SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceCreated (SurfaceHolder holder) {
        thread.setRunning(true);
        thread.start();
    }

    @Override
    public void surfaceDestroyed (SurfaceHolder holder) {
        boolean retry = true;
        while (retry) {
            try {
                thread.setRunning(false);
                thread.join();
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
            retry = false;
        }
    }

    @Override
    public boolean onTouchEvent (MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (player.getRectangle().contains((int)event.getX(), (int)event.getY()))
                    canMove = true;
                break;
            case MotionEvent.ACTION_MOVE:
                if (canMove)
                    playerPoint.set((int)event.getX(), (int)event.getY());
                break;
            case MotionEvent.ACTION_UP:
                canMove = false;
                break;
        }

        return true;
        //return super.onTouchEvent(event);
    }

    public void update() {
        player.update(playerPoint);
        if (coin.coinCollide(player)) {
            coin.update();
            score.update();
        }
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        canvas.drawColor(Color.BLACK);
        player.draw(canvas);
        coin.draw(canvas);
        score.draw(canvas);
    }

}
