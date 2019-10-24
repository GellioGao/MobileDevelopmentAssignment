package com.gao.yingjian.mobiledevelopmentassignmentone.AsyncTasks;

import android.os.AsyncTask;

import com.gao.yingjian.mobiledevelopmentassignmentone.Interfaces.IProgressReportable;

public class SurveySubmissionAsyncTask extends AsyncTask<String,Integer,Boolean> {
    private IProgressReportable activity;

    public SurveySubmissionAsyncTask(IProgressReportable activity){
        this.activity = activity;
    }

    @Override
    protected void onPreExecute() {
        this.activity.setStart();
    }

    @Override
    protected void onPostExecute(Boolean succeeded) {
        if(succeeded){
            this.activity.succeeded();
        }else{
            this.activity.failed();
        }
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        this.activity.setProgress(values[0]);
    }

    @Override
    protected Boolean doInBackground(String... strings) {
        for(int i = 0; i <= 100; i+=10){
            try {
                publishProgress(i);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return true;
    }
}
