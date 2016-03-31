package com.example.sammi.myapplication;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

//        TextView text = (TextView) findViewById(R.id.samplegrid);
//        text.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Log.d("info", "yeah");
//                Snackbar.make(view, "n.幸福", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//
//
//            }
//        });

        String testPuzzle = "{\n" +
                "\t\"width\" : 11,\n" +
                "\t\"height\" : 7,\n" +
                "\t\"vertical\" : \n" +
                "\t\t[\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"value\" : \"harbour\",\n" +
                "\t\t\t\t\"x\" : 9,\n" +
                "\t\t\t\t\"y\" : 0,\n" +
                "\t\t\t\t\"hint\" : \"port, dock\"\n" +
                "\t\t\t},\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"value\" : \"dog\",\n" +
                "\t\t\t\t\"x\" : 3,\n" +
                "\t\t\t\t\"y\" : 1,\n" +
                "\t\t\t\t\"hint\" : \"Human's best friend\"\n" +
                "\t\t\t},\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"value\" : \"beach\",\n" +
                "\t\t\t\t\"x\" : 6,\n" +
                "\t\t\t\t\"y\" : 0,\n" +
                "\t\t\t\t\"hint\" : \"a pebbly or sandy shore\"\n" +
                "\t\t\t}\n" +
                "\t\t],\n" +
                "\t\"horizontal\" : \n" +
                "\t\t[\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"value\" : \"fundamental\",\n" +
                "\t\t\t\t\"x\" : 0,\n" +
                "\t\t\t\t\"y\" : 1,\n" +
                "\t\t\t\t\"hint\" : \"forming a necessary base or core; of central importance.\"\n" +
                "\t\t\t},\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"value\" : \"singer\",\n" +
                "\t\t\t\t\"x\" : 4,\n" +
                "\t\t\t\t\"y\" : 6,\n" +
                "\t\t\t\t\"hint\" : \"a person who sings, especially professionally.\"\n" +
                "\t\t\t}\n" +
                "\t\t]\n" +
                "}";
        Crossword crossword = new Crossword(testPuzzle);

        Letter[][] table = crossword.getLetterTable();
//        String a = "";
//        for (int i = 0; i < crossword.getHeight(); i++)
//        {
//            for (int j = 0; j < crossword.getWidth(); j++)
//            {
//                a += table[i][j].getChar();
//            }
//        }
//        Log.d("info", a);
        CrosswordView crosswordView = new CrosswordView(this, crossword);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,//no use
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.BELOW, R.id.hint_view);
        crosswordView.setLayoutParams(params);//no use

        RelativeLayout contentLayout = (RelativeLayout) findViewById(R.id.content_layout);
        contentLayout.addView(crosswordView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
