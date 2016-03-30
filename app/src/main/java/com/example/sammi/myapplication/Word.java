package com.example.sammi.myapplication;

import java.util.ArrayList;

/**
 * Created by Sammi on 14/03/2016.
 */
public class Word
{
    public enum Direction
    {
        HORIZONTAL,
        VERTICAL
    }
    private String mTheWord;
    private Letter.Pos mHeadPos;
    private Letter.Pos mRearPos;
    private int mWordLength;
    private Direction mDirection;
    private int mIndex;
    private String mHint;
    private Pool mPool;

    public ArrayList<Letter> getLetters() {
        return mLetters;
    }

    public void setLetters(ArrayList<Letter> letters) {
        this.mLetters = letters;
    }

    public void addLetter(Letter letter) {
        this.mLetters.add(letter);
    }

    public void setPool(Pool p)
    {
        mPool = p;
    }
    public Pool getPool()
    {
        return mPool;
    }

    private ArrayList<Letter> mLetters;

    Word(String word, String hint, int x0, int y0, String direction, int index)
    {
        mWordLength = word.length();
        mHeadPos = new Letter.Pos(x0, y0);
        mTheWord = word;
        mHint = hint;
        mIndex = index;
        int x1 = 0;
        int y1 = 0;
        if (direction == "Horizontal")
        {
            mDirection = Direction.HORIZONTAL;
            x1 = mHeadPos.x + mWordLength - 1;
            y1 = mHeadPos.y;
        }
        else
        {
            mDirection = Direction.VERTICAL;
            x1 = mHeadPos.x;
            y1 = mHeadPos.y + mWordLength - 1;
        }

        mRearPos = new Letter.Pos(x1, y1);
        mLetters = new ArrayList<Letter>(mWordLength);
//        char[] chars = {'a','a','a','a','a','a','a','a','a','a','a','a'};
//        mPool = new Pool();
//        mPool.setLetters(chars);
    }

    Word(String word, String hint, int x0, int y0, Direction direction, int index)
    {
        mWordLength = word.length();
        mHeadPos = new Letter.Pos(x0, y0);
        mTheWord = word;
        mHint = hint;
        mIndex = index;
        int x1 = 0;
        int y1 = 0;
        if (direction == Direction.HORIZONTAL)
        {
            mDirection = Direction.HORIZONTAL;
            x1 = mHeadPos.x + mWordLength - 1;
            y1 = mHeadPos.y;
        }
        else
        {
            mDirection = Direction.VERTICAL;
            x1 = mHeadPos.x;
            y1 = mHeadPos.y + mWordLength - 1;
        }

        mRearPos = new Letter.Pos(x1, y1);
        mLetters = new ArrayList<Letter>(mWordLength);
        mPool = new Pool(this);

//        char[] chars = {'a','a','a','a','a','a','a','a','a','a','a','a'};
//        mPool = new Pool();
//        mPool.setLetters(chars);
    }
    public String getValue()
    {
        return mTheWord;
    }

    public Letter.Pos getHeadPos()
    {
        return mHeadPos;
    }

    public Letter.Pos getRearPos()
    {
        return mRearPos;
    }

    public Direction getDirection()
    {
        return mDirection;
    }

    public int getIndex() { return mIndex; }

    public String getHint() { return mHint; }
}


