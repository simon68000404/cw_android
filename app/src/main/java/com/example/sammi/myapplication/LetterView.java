package com.example.sammi.myapplication;

import android.content.Context;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Sammi on 16/03/2016.
 */
public class LetterView extends TextView
{
    public LetterView(Context context) {
        super(context);
    }

    public boolean check()
    {
        boolean res = mLetter.check(this.getText().charAt(0));
        if (res == false)
        {
            this.setTextColor(0xFFFF0000);
        }
        return res;
    }
    public void setLetter(Letter l)
    {
        mLetter = l;
    }
    public Letter getLetter()
    {
        return mLetter;
    }

    private Letter mLetter = new Letter('0', 0, 0);
}
