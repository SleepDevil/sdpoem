package com.example.sdpoem.ui.my;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sdpoem.R;
import com.example.sdpoem.ui.shicidb.ShicidbFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class LoginActivity extends AppCompatActivity {
    private SharedPreferences userPref;
    private SharedPreferences.Editor editor;
    private EditText account;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        userPref = getSharedPreferences("userPref", MODE_PRIVATE);
        editor = userPref.edit();

        account = findViewById(R.id.account);
        password = findViewById(R.id.password);

        TextView toRegister = findViewById(R.id.toRegister);
        Button loginBtn = findViewById(R.id.loginBtn);
        toRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(i);
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (account.getText().toString().equals(userPref.getString("account", "")) && password.getText().toString().equals(userPref.getString("password", ""))) {
                    // 登录成功
                    Toast.makeText(LoginActivity.this, "登陆成功", Toast.LENGTH_SHORT).show();
                    editor.putBoolean("loggedin", true).apply();
                    Intent intent = new Intent();
                    intent.putExtra("changedata", "true");
                    setResult(Activity.RESULT_OK, intent);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "账号或密码错误，请重试", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}