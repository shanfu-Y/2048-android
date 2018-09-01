package com.shanfu_y.a2048_android;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.TextView;

public class Card extends FrameLayout {

    private TextView mLable;

    private int mNum;

    public Card(@NonNull Context context) {
        super(context);

        mLable = new TextView(context);
        mLable.setTextSize(40);
        mLable.setGravity(Gravity.CENTER);

        LayoutParams layoutParams = new LayoutParams(-1, -1);
        layoutParams.setMargins(10, 10, 0, 0);

        addView(mLable, layoutParams);

        setNum(0);
    }

    public int getNum() {
        return mNum;
    }

    public void setNum(int num) {
        this.mNum = num;
        String value;
        if (num <= 0) {
            value = "";
        } else {
            value = "" + num;
        }
        mLable.setText(value);
    }

    public boolean equals(Card card) {
        return getNum() == card.getNum();
    }
}
