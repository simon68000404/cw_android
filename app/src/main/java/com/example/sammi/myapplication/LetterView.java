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

    public void setLetter(Letter l)
    {
        mLetter = l;
    }
    public Letter getLetter()
    {
        return mLetter;
    }

    private Letter mLetter;
}