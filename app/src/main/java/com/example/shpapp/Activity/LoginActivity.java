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
import com.example.shpapp.databinding.ActivityLoginBinding;
import com.example.shpapp.databinding.ActivityMainBinding;

import java.util.HashMap;

public class LoginActivity extends BaseActivity {

    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        etAccount = findViewById(R.id.login_username_input);
        etPwd = findViewById(R.id.login_password_input);
        btnLogin = findViewById(R.id.login_button);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String account = etAccount.getText().toString().trim();
                String pwd = etPwd.getText().toString().trim();
                login(account, pwd);
            }
        });
        btnRegister = findViewById(R.id.register_button);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigateTo(RegisterActivity.class);
            }
        });
    }

    private void login(String account, String pwd) {
        if(StringUtils.isEmpty(account)) {
            showToast("Please Input Username~");
            return;
        }
        if(StringUtils.isEmpty(pwd)) {
            showToast("Please Input Password~");
            return;
        }

        String hashedPassword = BCrypt.withDefaults().hashToString(12, pwd.toCharArray());

        HashMap<String, Object> m = new HashMap<>();
        m.put("Username", account);
        m.put("Password", hashedPassword);
        Api.config(ApiConfig.LOGIN_URL, m);
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