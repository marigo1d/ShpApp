package com.example.shpapp.Activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.shpapp.R;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {

    private EditText etAccount;
    private EditText etPwd;
    private EditText etRptPwd;
    private Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        etAccount = findViewById(R.id.register_username_input);
        etPwd = findViewById(R.id.register_password_input);
        etRptPwd = findViewById(R.id.register_password_rpt_input);
        btnRegister = findViewById(R.id.register_button);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String account = etAccount.getText().toString().trim();
                String pwd = etPwd.getText().toString().trim();
                String rptPwd = etRptPwd.getText().toString().trim();
                Register(account, pwd, rptPwd);
            }
        });
    }

    private void Register(String account, String pwd, String rptPwd) {
        if(StringUtils.isEmpty(account)) {
            showToast("Please Input Username~");
            return;
        }
        if(StringUtils.isEmpty(pwd)) {
            showToast("Please Input Password~");
            return;
        }
        if (!rptPwd.equals(pwd)) {
            showToast("Inconsistency in passwords entered twice >-<");
            return;
        }

        String hashedPassword = BCrypt.withDefaults().hashToString(12, pwd.toCharArray());

        HashMap<String, Object> m = new HashMap<>();
        m.put("Username", account);
        m.put("Password", hashedPassword);
        Api.config(ApiConfig.REGISTER_URL, m);
        Api.postRequest(new TtitCallback() {
            @Override
            public void onSuccess(String response) {
                Log.i("onSuccess", response);
            }

            @Override
            public void onFailure(String response) {
                Log.e("onFailure", response);
            }
        });

    }
}