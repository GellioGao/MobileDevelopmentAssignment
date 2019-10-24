package com.gao.yingjian.mobiledevelopmentassignmentone.Views;

import androidx.appcompat.widget.AppCompatButton;

import android.annotation.SuppressLint;
import android.database.sqlite.SQLiteDatabase;
import android.nfc.Tag;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.gao.yingjian.mobiledevelopmentassignmentone.DAOs.LogInfo;
import com.gao.yingjian.mobiledevelopmentassignmentone.Models.LoginResult;
import com.gao.yingjian.mobiledevelopmentassignmentone.Models.VerificationResult;
import com.gao.yingjian.mobiledevelopmentassignmentone.R;
import com.gao.yingjian.mobiledevelopmentassignmentone.ViewModels.LoginViewModel;
import com.gao.yingjian.mobiledevelopmentassignmentone.ViewModels.ViewModelFactory;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.litepal.LitePal;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class LoginActivity extends ActivityBase {
    private static final String TAG = "LoginActivity";

    private TextInputLayout tilUsername;
    private TextInputLayout tilPassword;
    private TextInputEditText tieUsername;
    private TextInputEditText tiePassword;
    private AppCompatButton btnLogin;
    private TextView txvSignUp;
    private ProgressBar progressBar;

    private LoginViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        LitePal.initialize(this);
        SQLiteDatabase db = LitePal.getDatabase();
        Log.d(TAG,db.getPath());

        viewModel = ViewModelFactory.getLoginViewModel();
        String lastUser = getLastLoginUser();

        tilUsername = findViewById(R.id.tilUsername);
        tilPassword = findViewById(R.id.tilPassword);
        tieUsername = findViewById(R.id.tieUsername);
        tiePassword = findViewById(R.id.tiePassword);
        btnLogin = findViewById(R.id.btnLogin);
        txvSignUp = findViewById(R.id.txvSignUp);
        progressBar = findViewById(R.id.progressBar);

        tieUsername.setText(lastUser);
        tieUsername.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                tilUsername.setErrorEnabled(false);
                return false;
            }
        });
        tiePassword.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                tilPassword.setErrorEnabled(false);
                if(keyEvent.getKeyCode() == KeyEvent.KEYCODE_ENTER){
                    login();
                }
                return false;
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
        txvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signUp();
            }
        });
    }

    private void login() {
        Log.d(TAG, "Login");
        if (!checkCredential()) {
            return;
        }
        String username = tieUsername.getText().toString();
        String password = tiePassword.getText().toString();
        @SuppressLint("StaticFieldLeak") AsyncTask<Void,Void,LoginResult> task = new AsyncTask<Void, Void, LoginResult>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                showIndicator(progressBar, btnLogin, tilUsername, tilPassword, txvSignUp);
            }

            @Override
            protected LoginResult doInBackground(Void... voids) {
                return viewModel.Login(username, password);
            }

            @Override
            protected void onPostExecute(LoginResult result) {
                super.onPostExecute(result);
                if (!result.isLogin()) {
                    Log.d(TAG, "Login Finished!");
                    onLoginFailed();
                    hideIndicator(progressBar, btnLogin, tilUsername, tilPassword, txvSignUp);
                    return;
                }
                onLoginSuccess(result);
                hideIndicator(progressBar, btnLogin, tilUsername, tilPassword, txvSignUp);
            }
        };
        task.execute();
    }

    private boolean checkCredential(){
        boolean isUsernameOk = checkUsername();
        boolean isPasswordOk = checkPassword();

        return isUsernameOk && isPasswordOk;
    }

    private boolean checkUsername(){
        String username = tieUsername.getText().toString();
        VerificationResult result = viewModel.verifyUsername(username);
        boolean isValid = result.isValid();
        if(!isValid) {
            String message = super.getMessage(",", result.getMessageIDList());
            tilUsername.setError(message);
        }
        return isValid;
    }

    private boolean checkPassword(){
        String password = tiePassword.getText().toString();
        VerificationResult result = viewModel.verifyPassword(password);
        boolean isValid = result.isValid();
        if(!isValid) {
            String message = super.getMessage(",", result.getMessageIDList());
            tilPassword.setError(message);
        }
        return isValid;
    }

    private void onLoginSuccess(LoginResult result){
        Log.d(TAG, "Login Success");
        tiePassword.setText("");
        saveLastLoginUser(result.getUser().getUsername());
        HashMap<String, String> map = new HashMap<>();
        map.put(HomeNavigationDrawerActivity.INTENT_EXTRA_USER, result.getUser().getFirstName());
        Map.Entry<String, String>[] entries = new Map.Entry[map.size()];
        map.entrySet().toArray(entries);
        jumpTo(HomeNavigationDrawerActivity.class, entries);
        Log.d(TAG, String.format("jump to: %s", new Date().toString()));
    }

    private void onLoginFailed(){
        Log.d(TAG, "Login Failed");
        Toast.makeText(this, getString(R.string.login_failed_message_string), Toast.LENGTH_SHORT).show();
    }

    private void signUp(){
        Log.d(TAG, "Sign Up");
        Toast.makeText(this, getString(R.string.login_sign_up_message_string), Toast.LENGTH_SHORT).show();
    }

    private String getLastLoginUser(){
        LogInfo info = LitePal.order("lastLoginTime").findLast(LogInfo.class);
        if(info == null){
            return null;
        }
        return info.getUserName();
    }

    private void saveLastLoginUser(String user){
        LogInfo info = LitePal.where("userName = ?", user).findFirst(LogInfo.class);
        if(info == null){
            info = new LogInfo();
            info.setUserName(user);
        }
        info.loginSucceed();
        info.save();
    }
}
