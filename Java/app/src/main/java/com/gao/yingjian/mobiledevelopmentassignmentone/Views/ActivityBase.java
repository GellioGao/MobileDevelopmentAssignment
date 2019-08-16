package com.gao.yingjian.mobiledevelopmentassignmentone.Views;

import android.content.Intent;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ActivityBase extends AppCompatActivity {
    protected String getMessage(CharSequence delimiter, ArrayList<Integer> ids){
        if(delimiter == null || ids == null){
            return "";
        }
        ArrayList<String> messages = getMessages(ids);
        return String.join(delimiter, messages);
    }
    protected ArrayList<String> getMessages(ArrayList<Integer> ids){
        ArrayList<String> messages = new ArrayList<>();
        if(ids == null){
            return messages;
        }
        for (int id:ids) {
            String message = getString(id);
            messages.add(message);
        }
        return messages;
    }

    protected void showIndicator(ProgressBar progressBar, View... views){
        progressBar.setVisibility(View.VISIBLE);
        for (View view: views) {
            view.setEnabled(false);
        }
    }

    protected void hideIndicator(ProgressBar progressBar, View... views){
        progressBar.setVisibility(View.INVISIBLE);
        for (View view: views) {
            view.setEnabled(true);
        }
    }

    protected void jumpTo(Class<?> cls){
        Intent intent = new Intent(this, cls);

        startActivity(intent);
    }
}
