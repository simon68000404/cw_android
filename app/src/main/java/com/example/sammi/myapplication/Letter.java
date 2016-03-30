package com.example.sammi.myapplication;

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

    private char mChar;
    private Pos mPos;
    private ArrayList<Word> mWordsBelongTo = new ArrayList<Word>();

    public static class Pos
    {
        Pos(int x, int y)
        {
            this.x = x;
            this.y = y;
        }
        public int x;
        public int y;
    }
}
