package com.example.sammi.myapplication;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Sammi on 16/03/2016.
 */
public class PoolView extends LinearLayout
{
    public PoolView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView tv = (TextView) view;
                tv.setBackgroundColor(Color.MAGENTA);

            }
        };
    }

    public void updatePool(Pool pool)
    {
        this.removeAllViews();
        ArrayList<Character> candidates = pool.getCandidates();
        for (int i = 0; i < candidates.size(); i++)
        {
            TextView tv = new TextView(getContext());
            tv.setText(Character.toString(candidates.get(i)));
            tv.setGravity(Gravity.CENTER);
            tv.setBackgroundResource(R.drawable.background);
            tv.setOnClickListener(mListener);
            //tv.setX(i * 20);
            tv.setWidth(20);
            LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            llp.setMargins(10, 10, 10, 10);
            tv.setLayoutParams(llp);

            this.addView(tv);
        }
    }

    private View.OnClickListener mListener;
}
