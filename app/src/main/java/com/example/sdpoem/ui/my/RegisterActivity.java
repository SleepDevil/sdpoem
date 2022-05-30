package com.example.sdpoem.ui.my;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sdpoem.R;

public class RegisterActivity extends AppCompatActivity {
    private SharedPreferences userPref;
    private SharedPreferences.Editor editor;
    private EditText username;
    private EditText account;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);
        username = findViewById(R.id.username);
        account = findViewById(R.id.account);
        password = findViewById(R.id.password);
        userPref = getSharedPreferences("userPref", MODE_PRIVATE);
        editor = userPref.edit();
        TextView toLogin = findViewById(R.id.toLogin);
        toLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        Button registerBtn = findViewById(R.id.registerBtn);
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.putString("username", username.getText().toString()).apply();
                editor.putString("account", account.getText().toString()).apply();
                editor.putString("password", password.getText().toString()).apply();
                Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
            }
        });
    }
}