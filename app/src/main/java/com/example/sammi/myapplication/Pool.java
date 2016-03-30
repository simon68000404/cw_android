package com.example.sammi.myapplication;

import java.util.ArrayList;

/**
 * Created by Sammi on 16/03/2016.
 */
public class Pool
{
    public Pool(Word w)
    {
        mWord = w;
        String str = w.getValue();
        int i = str.length() - 1;
        while (i >= 0)
        {
            mCandidates.add(str.charAt(i));
            i--;
        }
    }
    public ArrayList<Character> getCandidates() {
        return mCandidates;
    }

    private ArrayList<Character> mCandidates = new ArrayList<Character>();
    private Word mWord;
}
