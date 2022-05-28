package com.example.sdpoem.ui.shicidb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.sdpoem.R;

public class ShowPoemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_poem);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();
        String author = intent.getStringExtra("author");
        String paragraphs = intent.getStringExtra("paragraphs");
        String title = intent.getStringExtra("title");
        TextView TitleText = findViewById(R.id.poemTitle);
        TextView ContentText = findViewById(R.id.poemContent);
        TextView AuthorText = findViewById(R.id.poemAuhthor);
        TitleText.setText(title);
        ContentText.setText(paragraphs);
        AuthorText.setText(author);
    }
}