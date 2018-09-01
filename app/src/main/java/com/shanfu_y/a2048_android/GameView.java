package com.shanfu_y.a2048_android;

import android.content.Context;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridLayout;

import java.util.ArrayList;
import java.util.List;

public class GameView extends GridLayout {

    private Card[][] cards = new Card[4][4];
    private List<Point> emptyPoints = new ArrayList<>();

    public GameView(Context context) {
        super(context);
        init();
    }

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public GameView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public GameView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {

        setColumnCount(4);
        setBackgroundColor(0x123456);
        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                float startX = 0, startY = 0, offsetX = 0, offsetY = 0;

                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        startX = motionEvent.getX();
                        startY = motionEvent.getY();
                        break;
                    case MotionEvent.ACTION_UP:
                        offsetX = motionEvent.getX() - startX;
                        offsetY = motionEvent.getY() - startY;

                        if (Math.abs(offsetX) > Math.abs(offsetY)) {
                            if (offsetX < -5) {
                                swipeLeft();
                            } else if (offsetX > 5) {
                                swipeRight();
                            }
                        } else {
                            if (offsetY < -5) {
                                swipeUp();
                            } else if (offsetY > 5) {
                                swipeDown();
                            }
                        }
                        break;
                    default:
                }
                return true;
            }
        });
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        int cardWidth = (Math.min(w, h) - 10) / 4;

        addCard(cardWidth, cardWidth);

        startGame();
    }

    private void swipeLeft() {

    }

    private void swipeRight() {

    }

    private void swipeUp() {

    }

    private void swipeDown() {

    }

    private void addRandomNumber() {
        emptyPoints.clear();

        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                if (cards[x][y].getNum() <= 0) {
                    emptyPoints.add(new Point(x, y));
                }
            }
        }

        Point p = emptyPoints.remove((int) (Math.random() * emptyPoints.size()));
        cards[p.x][p.y].setNum(Math.random() > 0.1 ? 2 : 4);
    }

    private void startGame() {
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                cards[x][y].setNum(0);
            }
        }
        addRandomNumber();
        addRandomNumber();
    }

    private void addCard(int cardWidth, int cardHeight) {
        Card card;
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                card = new Card(getContext());
                card.setNum(2);
                addView(card, cardWidth, cardHeight);
                cards[x][y] = card;
            }
        }
    }
}
