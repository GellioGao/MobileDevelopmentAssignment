package com.gao.yingjian.mobiledevelopmentassignmentone.Interfaces;

public interface IProgressReportable {
    void succeeded();
    void failed();
    void setStart();
    void setProgress(Integer value);
}
