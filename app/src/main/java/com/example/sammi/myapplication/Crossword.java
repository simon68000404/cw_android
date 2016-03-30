package com.example.sammi.myapplication;

import android.util.Log;

import java.util.Arrays;
//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;
//import org.json.simple.parser.ParseException;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONTokener;

/**
 * Created by Sammi on 14/03/2016.
 */
public class Crossword
{
    String testPuzzle = "{\n" +
            "\t\"width\" : 11,\n" +
            "\t\"height\" : 7,\n" +
            "\t\"words\" : \n" +
            "\t[\n" +
            "\t\t{\n" +
            "\t\t\t\"value\" : \"harbour\",\n" +
            "\t\t\t\"direction\" : \"vertical\",\n" +
            "\t\t\t\"x\" : 9,\n" +
            "\t\t\t\"y\" : 0\n" +
            "\t\t},\n" +
            "\t\t{\n" +
            "\t\t\t\"value\" : \"dog\",\n" +
            "\t\t\t\"direction\" : \"vertical\",\n" +
            "\t\t\t\"x\" : 3,\n" +
            "\t\t\t\"y\" : 1\n" +
            "\t\t},\n" +
            "\t\t{\n" +
            "\t\t\t\"value\" : \"beach\",\n" +
            "\t\t\t\"direction\" : \"vertical\",\n" +
            "\t\t\t\"x\" : 6,\n" +
            "\t\t\t\"y\" : 0\n" +
            "\t\t},\n" +
            "\t\t{\n" +
            "\t\t\t\"value\" : \"fundamental\",\n" +
            "\t\t\t\"direction\" : \"horizontal\",\n" +
            "\t\t\t\"x\" : 0,\n" +
            "\t\t\t\"y\" : 1\n" +
            "\t\t},\n" +
            "\t\t{\n" +
            "\t\t\t\"value\" : \"singer\",\n" +
            "\t\t\t\"direction\" : \"horizontal\",\n" +
            "\t\t\t\"x\" : 4,\n" +
            "\t\t\t\"y\" : 6\n" +
            "\t\t}\n" +
            "\t]\n" +
            "}";
    Crossword(String jsonPuzzleStr)
    {
        //JSONParser parser = new JSONParser();
        int horizontalNum = 0;
        int verticalNum = 0;
        try {
            JSONObject jsonObj = (JSONObject) new JSONTokener(jsonPuzzleStr).nextValue();

            //horizontal
            JSONArray hJSonArray = (JSONArray)jsonObj.get("horizontal");
            horizontalNum = hJSonArray.length();
            mHorizontalWords = new Word[horizontalNum];

            for (int i = 0; i < horizontalNum; i++)
            {
                JSONObject wordObj = (JSONObject)hJSonArray.get(i);
                mHorizontalWords[i] = new Word(wordObj.getString("value"),wordObj.getString("hint"), wordObj.getInt("x"), wordObj.getInt("y"), Word.Direction.HORIZONTAL, i);
                Log.d("test", mHorizontalWords[i].getValue());
            }
            //vertical
            JSONArray vJSonArray = (JSONArray)jsonObj.get("vertical");
            verticalNum = vJSonArray.length();
            mVerticalWords = new Word[verticalNum];

            for (int i = 0; i < verticalNum; i++)
            {
                JSONObject wordObj = (JSONObject)vJSonArray.get(i);
                mVerticalWords[i] = new Word(wordObj.getString("value"),wordObj.getString("hint"), wordObj.getInt("x"), wordObj.getInt("y"), Word.Direction.VERTICAL, i);
                Log.d("test", mVerticalWords[i].getValue());
            }


            mWidth = jsonObj.getInt("width");
            mHeight = jsonObj.getInt("height");


        } catch (JSONException e) {
            e.printStackTrace();
        }

        mLetterTable = new Letter[mHeight][mWidth];
        mFillingTable = new Letter[mHeight][mWidth];

        for (int i = 0; i < mHeight; i++)
        {
            for(int j = 0; j < mWidth; j++)
            {
                char zero = 0;
                mLetterTable[i][j] = new Letter(zero, i, j);
                mFillingTable[i][j] = new Letter(zero, i, j);
            }
        }

        for (int i = 0; i < horizontalNum; i++)
        {
            Letter.Pos pos = mHorizontalWords[i].getHeadPos();
            int wordLength = mHorizontalWords[i].getValue().length();
            for (int j = 0; j < wordLength; j++)
            {
                Letter letter = mLetterTable[pos.r][pos.c + j];
                letter.setChar(mHorizontalWords[i].getValue().charAt(j));
                letter.addWordBelongTo(mHorizontalWords[i]);
                mHorizontalWords[i].addLetter(letter);
            }
        }

        for (int i = 0; i < verticalNum; i++)
        {
            Letter.Pos pos = mVerticalWords[i].getHeadPos();
            int wordLength = mVerticalWords[i].getValue().length();
            for (int j = 0; j < wordLength; j++)
            {
                Letter letter = mLetterTable[pos.r + j][pos.c];
                letter.setChar(mVerticalWords[i].getValue().charAt(j));
                letter.addWordBelongTo(mVerticalWords[i]);
                mVerticalWords[i].addLetter(letter);
            }
        }
    }

    Crossword()
    {
//        String s = "[0,{\"1\":{\"2\":{\"3\":{\"4\":[5,{\"6\":7}]}}}}]";
//        JSONParser parser = new JSONParser();
//        try{
//            Object obj = parser.parse(testPuzzle);
////            JSONArray array = (JSONArray)obj;
//            JSONObject jsonObj = (JSONObject)obj;
//
//            System.out.println("The 2nd element of array");
//            System.out.println("width:" + jsonObj.get("width"));
//            System.out.println();
//
////            JSONObject obj2 = (JSONObject)array.get(1);
////            System.out.println("Field \"1\"");
////            System.out.println(obj2.get("1"));
////
////            s = "{}";
////            obj = parser.parse(s);
////            System.out.println(obj);
////
////            s = "[5,]";
////            obj = parser.parse(s);
////            System.out.println(obj);
////
////            s = "[5,,2]";
////            obj = parser.parse(s);
////            System.out.println(obj);
//        }catch(ParseException pe){
//
//            System.out.println("position: " + pe.getPosition());
//            System.out.println(pe);
//        }
    }

    private Word[] mHorizontalWords;
    private Word[] mVerticalWords;

    public int getWidth() {
        return mWidth;
    }

    public int getHeight() {
        return mHeight;
    }

    private int mWidth;
    private int mHeight;

    public Letter[][] getLetterTable() {
        return mLetterTable;
    }

    private Letter[][] mLetterTable;
    private Letter[][] mFillingTable;
}
