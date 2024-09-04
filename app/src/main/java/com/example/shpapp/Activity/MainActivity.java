package com.example.shpapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.shpapp.api.Api;
import com.example.shpapp.api.ApiConfig;
import com.example.shpapp.api.TtitCallback;
import com.example.shpapp.databinding.ActivityLoginBinding;
import com.example.shpapp.util.StringUtils;

import java.util.HashMap;

public class MainActivity extends BaseActivity {

    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.loginBtn.setOnClickListener(view -> {
            String account = binding.loginUsernameInput.getText().toString().trim();
            String pwd = binding.loginPwdInput.getText().toString().trim();
            login(account, pwd);
        });

        binding.loginRegisterBtn.setOnClickListener(view -> {
            startActivity(new Intent(this, RegisterActivity.class));
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

//        String hashedPassword = BCrypt.withDefaults().hashToString(12, pwd.toCharArray());

        HashMap<String, Object> m = new HashMap<>();
        m.put("Username", account);
//        m.put("Password", hashedPassword);
        m.put("Password", pwd);
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