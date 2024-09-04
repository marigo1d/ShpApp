package com.example.shpapp.Activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.shpapp.api.Api;
import com.example.shpapp.api.ApiConfig;
import com.example.shpapp.api.TtitCallback;
import com.example.shpapp.databinding.ActivityRegisterBinding;
import com.example.shpapp.util.StringUtils;

import java.util.HashMap;

public class RegisterActivity extends BaseActivity {

    private ActivityRegisterBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String account = binding.registerUsernameInput.getText().toString().trim();
                String pwd = binding.registerPwdInput.getText().toString().trim();
                String rptPwd = binding.registerPwdRptInput.getText().toString().trim();
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

//        String hashedPassword = BCrypt.withDefaults().hashToString(12, pwd.toCharArray());

        HashMap<String, Object> m = new HashMap<>();
        m.put("Username", account);
//        m.put("Password", hashedPassword);
        m.put("Password", pwd);
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