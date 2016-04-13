package com.example.sammi.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

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

        Button playButton = (Button) findViewById(R.id.play_button);
        Button wordlistsButton = (Button) findViewById(R.id.wordlists_button);
        Button missedwordsButton = (Button) findViewById(R.id.missedwords_button);
        Button settingsButton = (Button) findViewById(R.id.settings_button);


    }

    public void goToPlay(View view)
    {
        Intent intent = new Intent(this, GamePlayActivity.class);
        startActivity(intent);
    }

    public void goToWordLists(View view)
    {
        Intent intent = new Intent(this, WordlistListActivity.class);
        startActivity(intent);
    }

    public void goToMissedWords(View view)
    {
        Intent intent = new Intent(this, MissedWordsActivity.class);
        startActivity(intent);
    }

    public void goToSettings(View view)
    {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }
}
