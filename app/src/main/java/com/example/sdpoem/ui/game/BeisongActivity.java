package com.example.sdpoem.ui.game;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.sdpoem.R;
import com.example.sdpoem.ui.game.ui.Beisong.BeisongFragment;

public class BeisongActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, BeisongFragment.newInstance())
                    .commitNow();
        }
    }
}