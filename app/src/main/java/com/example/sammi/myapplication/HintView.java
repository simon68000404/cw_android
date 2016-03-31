package com.example.sammi.myapplication;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Sammi on 16/03/2016.
 */
public class HintView extends TextView
{
    public HintView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void updateText(String text)
    {
        this.setText(text);
    }
}
