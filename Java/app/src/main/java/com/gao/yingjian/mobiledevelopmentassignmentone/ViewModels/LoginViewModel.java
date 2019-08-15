package com.gao.yingjian.mobiledevelopmentassignmentone.ViewModels;

import com.gao.yingjian.mobiledevelopmentassignmentone.Models.LoginResult;
import com.gao.yingjian.mobiledevelopmentassignmentone.Models.UserEntity;
import com.gao.yingjian.mobiledevelopmentassignmentone.Models.VerificationResult;
import com.gao.yingjian.mobiledevelopmentassignmentone.R;

public class LoginViewModel {
    public VerificationResult verifyUsername(String username) {
        if (username.length() <= 0) {
            return new VerificationResult(false, R.string.login_username_error_message_empty_string);
        }
        if (username.length() <= 5) {
            return new VerificationResult(false, R.string.login_username_error_message_too_short_string);
        }
        return new VerificationResult();
    }


    public VerificationResult verifyPassword(String password) {
        if (password.length() <= 0) {
            return new VerificationResult(false, R.string.login_password_error_message_empty_string);
        }
        if (password.length() < 8) {
            return new VerificationResult(false, R.string.login_password_error_message_too_short_string);
        }
        if (password.length() > 32) {
            return new VerificationResult(false, R.string.login_password_error_message_too_long_string);
        }
        return new VerificationResult();
    }

    public LoginResult Login() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LoginResult result = new LoginResult(true, new UserEntity(1, "sss", "Yingjian", "Gao"));
        return result;
    }
}
