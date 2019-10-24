package com.gao.yingjian.mobiledevelopmentassignmentone.ViewModels;

import com.gao.yingjian.mobiledevelopmentassignmentone.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LoginViewModelTest {
    private static final String EMPTY_STRING = "";
    private static final String USERNAME_LESS_THAN_5_CHARACTERS = "less";
    private static final String USERNAME_MORE_THAN_5_CHARACTERS = "morethan5";
    private static final String PASSWORD_LESS_THAN_8_CHARACTERS = "less8";
    private static final String PASSWORD_MORE_THAN_8_AND_LESS_THAN_32_CHARACTERS = "morethan8CHARACTERS";
    private static final String PASSWORD_MORE_THAN_32_CHARACTERS = "MORETHAN32MORETHAN32MORETHAN32MORETHAN32";
    private LoginViewModel target;
    @Before
    public void setUp() throws Exception{
        target = new LoginViewModel();
    }

    @After
    public void tearDown() throws Exception{
        target = null;
    }

    @Test
    public void verifyUsername_less_5_characters_is_not_valid() {
        boolean expected = false;
        boolean actual = target.verifyUsername(USERNAME_LESS_THAN_5_CHARACTERS).isValid();
        assertEquals(expected,actual);
    }
    @Test
    public void verifyUsername_empty_message() {
        int expected = R.string.login_username_error_message_empty_string;
        int actual = target.verifyUsername(EMPTY_STRING).getMessageIDList().get(0);
        assertEquals(expected,actual);
    }
    @Test
    public void verifyUsername_less_5_characters_message() {
        int expected = R.string.login_username_error_message_too_short_string;
        int actual = target.verifyUsername(USERNAME_LESS_THAN_5_CHARACTERS).getMessageIDList().get(0);
        assertEquals(expected,actual);
    }
    @Test
    public void verifyUsername_valid() {
        boolean expected = true;
        boolean actual = target.verifyUsername(USERNAME_MORE_THAN_5_CHARACTERS).isValid();
        assertEquals(expected,actual);
    }
    @Test
    public void verifyPassword_less_8_characters_is_not_valid() {
        boolean expected = false;
        boolean actual = target.verifyPassword(PASSWORD_LESS_THAN_8_CHARACTERS).isValid();
        assertEquals(expected,actual);
    }
    @Test
    public void verifyPassword_empty_message() {
        int expected = R.string.login_password_error_message_empty_string;
        int actual = target.verifyPassword(EMPTY_STRING).getMessageIDList().get(0);
        assertEquals(expected,actual);
    }
    @Test
    public void verifyPassword_less_8_characters_message() {
        int expected = R.string.login_password_error_message_too_short_string;
        int actual = target.verifyPassword(PASSWORD_LESS_THAN_8_CHARACTERS).getMessageIDList().get(0);
        assertEquals(expected,actual);
    }
    @Test
    public void verifyPassword_more_32_characters_message() {
        int expected = R.string.login_password_error_message_too_long_string;
        int actual = target.verifyPassword(PASSWORD_MORE_THAN_32_CHARACTERS).getMessageIDList().get(0);
        assertEquals(expected,actual);
    }
    @Test
    public void verifyPassword_valid() {
        boolean expected = true;
        boolean actual = target.verifyPassword(PASSWORD_MORE_THAN_8_AND_LESS_THAN_32_CHARACTERS).isValid();
        assertEquals(expected,actual);
    }
}