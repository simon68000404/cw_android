package com.example.sammi.myapplication;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Sammi on 18/03/2016.
 */
public class Letter {

    Letter(char c, int row, int column)
    {
        mChar = c;
        mPos = new Pos(row, column);
    }
    Letter(Letter l)
    {
        mChar = l.mChar;
        mPos = l.mPos.clone();
    }

    public Letter clone()
    {
        return new Letter(this);
    }

    public boolean check()
    {
        //Log.d("ccc", ans.equals(mChar) + " " + ans + " " + mChar);
        mCorrect = mUserAnswer.equals(mChar);
        return mCorrect;
    }
    public boolean getCheckResult()
    {
        return mCorrect;
    }

    public char getChar()
    {
        return mChar;
    }

    public void setChar(char c)
    {
        mChar = c;
    }

    public void addWordBelongTo(Word word)
    {
        mWordsBelongTo.add(word);
    }

    public ArrayList<Word> getWordsBelongTo()
    {
        return mWordsBelongTo;
    }

    public Pos getPos() {
        return mPos;
    }

    public void setPos(Pos pos) {
        this.mPos = pos;
    }

    private Character mChar;
    private Pos mPos;
    private ArrayList<Word> mWordsBelongTo = new ArrayList<Word>();

    public static class Pos
    {
        Pos(int r, int c)
        {
            this.c = c;
            this.r = r;
        }
        Pos(Pos pos)
        {
            this.c = pos.c;
            this.r = pos.r;
        }
        public Pos clone()
        {
            return new Pos(this);
        }
        public int c;
        public int r;
    }
    private boolean mCorrect = false;

    public Character getUserAnswer() {
        return mUserAnswer;
    }

    public void setUserAnswer(Character userAnswer) {
        this.mUserAnswer = userAnswer;
    }

    private Character mUserAnswer = null;
}
