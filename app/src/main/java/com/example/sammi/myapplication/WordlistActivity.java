package com.example.sammi.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class WordlistActivity extends AppCompatActivity {

    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wordlist);

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

        listView = (ListView) findViewById(R.id.wordlist_view);
        String[] values = new String[] { "word 1",
                "word 2",
                "word 3",
                "word 4",
//                "word 5",
//                "word 6",
//                "word 7",
//                "word 8",
        };
        WordlistAdapter adapter = new WordlistAdapter();
        for (int i = 0; i < values.length; i++)
        {
            String str[] = {values[i], "aaa"};
            adapter.addItem(str);
        }

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


    }
    private class WordlistAdapter extends BaseAdapter
    {
        private ArrayList<String[]> mData = new ArrayList<String[]>();
        public WordlistAdapter() {

        }
        @Override
        public String[] getItem(int position) {
            return mData.get(position);
        }
        @Override
        public long getItemId(int position) {
            return position;
        }
        @Override
        public int getCount() {
            return mData.size();
        }

        public void addItem(final String[] item) {
            mData.add(item);
            notifyDataSetChanged();
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v = convertView;
            if (v == null)
            {
                LayoutInflater vi;
                vi = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                //vi = LayoutInflater.from(getApplicationContext());
                v = vi.inflate(R.layout.list_item_word_def, null);
            }

            String[] strs = getItem(position);
            if (strs != null)
            {
                TextView tt1 = (TextView) v.findViewById(R.id.word_spelling);
                TextView tt2 = (TextView) v.findViewById(R.id.word_definition);
                if (tt1 != null)
                {
                    tt1.setText(strs[0]);
                }
                if (tt2 != null)
                {
                    tt2.setText(strs[1]);
                }
            }
            return v;
        }

    }
}
