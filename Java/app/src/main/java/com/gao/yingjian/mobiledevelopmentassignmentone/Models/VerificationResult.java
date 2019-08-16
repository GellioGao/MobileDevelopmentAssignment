package com.gao.yingjian.mobiledevelopmentassignmentone.Models;

import java.util.ArrayList;

public class VerificationResult {
    boolean valid;
    ArrayList<Integer> messageIDList;
    public VerificationResult(){
        this(true);
    }
    public VerificationResult(boolean valid, Integer... ids){
        this.valid = valid;
        this.messageIDList = new ArrayList();
        if(ids == null){
            return;
        }
        for (int m: ids) {
            this.messageIDList.add(m);
        }
    }

    public boolean isValid() {
        return valid;
    }

    public ArrayList<Integer> getMessageIDList() {
        return messageIDList;
    }
}
