package com.example.sammi.myapplication;

import android.app.Activity;
import android.content.Context;
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
                    lv.setText(Character.toString(table[i][j].getChar()));
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

    public void setCurrentLetterView(LetterView currentLetterView) {
        if (this.mCurrentLetterView != null)
        {
            this.mCurrentLetterView.setBackgroundColor(0xFFFFFFFF);
        }

        this.mCurrentLetterView = currentLetterView;
        this.mCurrentLetterView.setBackgroundColor(0xFF00FF00);
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
