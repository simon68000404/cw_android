package com.example.sammi.myapplication;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Sammi on 16/03/2016.
 */
public class CrosswordView extends RelativeLayout
{
    public CrosswordView(Context context, Crossword crossword)
    {
        super(context);
        this.setId(R.id.the_crossword_view);
        //this.setBackgroundResource(R.drawable.background);
        mLetterViews = new LetterView[crossword.getHeight()][crossword.getWidth()];
        mCrossword = crossword;
        mSideLength = 70;
        Letter[][] table = mCrossword.getLetterTable();
        int n = 0;
        for (int i = 0; i < mCrossword.getHeight(); i++)
        {
            for (int j = 0; j < mCrossword.getWidth(); j++)
            {
                if (table[i][j].getChar() != 0)
                {
                    LetterView lv = new LetterView(context);
                    lv.setLetter(table[i][j]);
//                    lv.setTranslationX(mSideLength * j);
//                    lv.setTranslationY(mSideLength * i);
//                    lv.setWidth(mSideLength);
//                    lv.setHeight(mSideLength);
                    //lv.setText(Character.toString(table[i][j].getChar()));
                    lv.setGravity(Gravity.CENTER);
                    lv.setBackgroundResource(R.drawable.background);
                    lv.setOnClickListener(new View.OnClickListener(){
                        @Override
                        public void onClick(View view)
                        {
                            CrosswordView cView = (CrosswordView)view.getParent();
                            LetterView clickedLV = (LetterView)view;
                            cView.setCurrentLetterView(clickedLV);
                            cView.setCurrentWord(clickedLV.getLetter().getWordsBelongTo().get(0));
                            ArrayList<Word> wordsThisLetterBelongTo = clickedLV.getLetter().getWordsBelongTo();
                            if (wordsThisLetterBelongTo.size() == 2)
                            {
                                Log.d("cross", wordsThisLetterBelongTo.get(0).getValue() + " and " + wordsThisLetterBelongTo.get(1).getValue() + " hint: " + wordsThisLetterBelongTo.get(0).getHint());
                                Log.d("cross", wordsThisLetterBelongTo.get(0).getPool().getCandidates().toString());
                            }
                            else
                            {
                                Log.d("normal", wordsThisLetterBelongTo.get(0).getValue() + " hint: " + wordsThisLetterBelongTo.get(0).getHint());
                            }
                            Activity act = (Activity)getContext();
                            PoolView pv = (PoolView) act.findViewById(R.id.pool_view);
                            Log.d("pv", pv.toString());
                            pv.updatePool(wordsThisLetterBelongTo.get(0).getPool());
                            HintView hv = (HintView) act.findViewById(R.id.hint_view);
                            hv.updateText(wordsThisLetterBelongTo.get(0).getHint());
                        }
                    });

                    mLetterViews[i][j] = lv;
                    this.addView(lv);
                }
            }
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int width = widthSize;
        int height = (int)(heightSize * 0.7f);
        setMeasuredDimension(width, height);
    }
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom)
    {
        super.onLayout(changed, left, top, right, bottom);
        int tableWidth = mCrossword.getWidth();
        int tableHeight = mCrossword.getHeight();
        int gridWidthPixels = (right - left)/tableWidth;
        int gridHeightPixels = (bottom - top)/tableHeight;
        Letter[][] table = mCrossword.getLetterTable();
        mSideLength = gridHeightPixels > gridWidthPixels ? gridWidthPixels : gridHeightPixels;
        for (int i = 0; i < tableHeight; i++) {
            for (int j = 0; j < tableWidth; j++) {
                if (table[i][j].getChar() != 0) {
                    //Log.d("letter view num", mLetterViews.toString());
                    LetterView lv = mLetterViews[i][j];
                    lv.setTranslationX((mSideLength - 1) * j);
                    lv.setTranslationY((mSideLength - 1) * i);
                    lv.setWidth(mSideLength);
                    lv.setHeight(mSideLength);
                    lv.layout(lv.getLeft(), lv.getTop(), lv.getRight(), lv.getBottom());
                }
            }
        }
    }

    public void setCurrentWord(Word currentWord) {
        this.mCurrentWord = currentWord;
    }
    public Word getCurrentWord() {
        return mCurrentWord;
    }

    public void setCalculatedCurrentLetterView()
    {
        Log.d("abcd", mCurrentLetterView.getLetter().getChar() + "");
        Letter.Pos pos = mCurrentLetterView.getLetter().getPos().clone();
        Word currentWord = this.getCurrentWord();
        if (currentWord.getDirection() == Word.Direction.HORIZONTAL)
        {
            if (pos.c < currentWord.getRearPos().c)
            {
                pos.c += 1;
            }
            else
            {
                return;
            }

        }
        else
        {
            if (pos.r < currentWord.getRearPos().r)
            {
                pos.r += 1;
            }
            else
            {
                return;
            }
        }
        this.setCurrentLetterView(mLetterViews[pos.r][pos.c]);
    }
    public void setCurrentLetterView(LetterView currentLetterView) {
        if (this.mCurrentLetterView == currentLetterView)
        {
            return;
        }

        if (this.mCurrentLetterView != null)
        {
            //this.mCurrentLetterView.setBackgroundResource(R.drawable.background);
            ArrayList<Word> preWordList = mCurrentLetterView.getLetter().getWordsBelongTo();
            if (preWordList.size() == 1) {
                Word preWord = preWordList.get(0);
                int length = preWord.getValue().length();
                Letter.Pos headPos = preWord.getHeadPos();
                if (preWord.getDirection() == Word.Direction.HORIZONTAL) {
                    for (int i = 0; i < length; i++) {
                        this.mLetterViews[headPos.r][headPos.c + i].setBackgroundResource(R.drawable.background);
                    }
                } else {
                    for (int i = 0; i < length; i++) {
                        this.mLetterViews[headPos.r + i][headPos.c].setBackgroundResource(R.drawable.background);
                    }
                }
            }
            else
            {
                Word hWord = preWordList.get(0);
                Word vWord = preWordList.get(1);
                int hLength = hWord.getValue().length();
                int vLength = vWord.getValue().length();
                Letter.Pos hHeadPos = hWord.getHeadPos();
                Letter.Pos vHeadPos = vWord.getHeadPos();
                for (int i = 0; i < hLength; i++) {
                    this.mLetterViews[hHeadPos.r][hHeadPos.c + i].setBackgroundResource(R.drawable.background);
                }
                for (int i = 0; i < vLength; i++) {
                    this.mLetterViews[vHeadPos.r + i][vHeadPos.c].setBackgroundResource(R.drawable.background);
                }
            }
        }

        ArrayList<Word> wordList = currentLetterView.getLetter().getWordsBelongTo();
        this.mCurrentLetterView = currentLetterView;
        //Log.d("aaaaaaaaaa", word.getValue() + " " + word.getHeadPos().x + " " + word.getHeadPos().y);
        if (wordList.size() == 1) {
            Word word = wordList.get(0);
            int length = word.getValue().length();

            Letter.Pos headPos = word.getHeadPos();
            if (word.getDirection() == Word.Direction.HORIZONTAL) {
                for (int i = 0; i < length; i++) {
                    this.mLetterViews[headPos.r][headPos.c + i].setBackgroundColor(0xFF00FF00);
                }
            } else {
                for (int i = 0; i < length; i++) {
                    this.mLetterViews[headPos.r + i][headPos.c].setBackgroundColor(0xFF00FF00);
                }
            }
        }
        else
        {
            Word hWord = wordList.get(0);
            Word vWord = wordList.get(1);
            int hLength = hWord.getValue().length();
            int vLength = vWord.getValue().length();
            Letter.Pos hHeadPos = hWord.getHeadPos();
            Letter.Pos vHeadPos = vWord.getHeadPos();
            for (int i = 0; i < hLength; i++) {
                this.mLetterViews[hHeadPos.r][hHeadPos.c + i].setBackgroundColor(0xFF00FF00);
            }
            for (int i = 0; i < vLength; i++) {
                this.mLetterViews[vHeadPos.r + i][vHeadPos.c].setBackgroundColor(0xFF00FF00);
            }
        }
        this.mCurrentLetterView.setBackgroundColor(Color.BLUE);
    }
    public LetterView getCurrentLetterView() {
        return mCurrentLetterView;
    }

    private Crossword mCrossword;
    private LetterView[][]  mLetterViews;
    private HintView mHintView;
    private PoolView mPoolView;
    private Word mCurrentWord;
    private LetterView mCurrentLetterView;
    private int mSideLength;
}
