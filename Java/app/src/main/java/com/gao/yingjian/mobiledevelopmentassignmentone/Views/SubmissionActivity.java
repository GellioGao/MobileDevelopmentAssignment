package com.gao.yingjian.mobiledevelopmentassignmentone.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.gao.yingjian.mobiledevelopmentassignmentone.AsyncTasks.SurveySubmissionAsyncTask;
import com.gao.yingjian.mobiledevelopmentassignmentone.Interfaces.IProgressReportable;
import com.gao.yingjian.mobiledevelopmentassignmentone.R;

import at.grabner.circleprogress.CircleProgressView;

public class SubmissionActivity extends AppCompatActivity implements IProgressReportable {

    private CircleProgressView clvSubmissionProgress;
    private Button btnSubmitSurvey;

    private Boolean isSubmissionInProgressing = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submission);

        clvSubmissionProgress = findViewById(R.id.clvSubmissionProgress);
        btnSubmitSurvey = findViewById(R.id.btnSubmitSurvey);
    }

    @Override
    public void onBackPressed() {
        if(!isSubmissionInProgressing){
            finish();
        }
    }

    public void submitSurvey(View view) {
        btnSubmitSurvey.setEnabled(false);
        isSubmissionInProgressing = true;
        new SurveySubmissionAsyncTask(this).execute();
    }

    public void succeeded() {
        setResult(SurveyDetailActivity.RESULT_CODE_SUBMITTED, null);
        finish();
    }

    public void failed(){
        btnSubmitSurvey.setEnabled(true);
        isSubmissionInProgressing = false;
    }

    public void setStart() {
        clvSubmissionProgress.setVisibility(View.VISIBLE);
        clvSubmissionProgress.setValue(0);

    }

    public void setProgress(Integer value) {
        clvSubmissionProgress.setValue(value);
    }
}
