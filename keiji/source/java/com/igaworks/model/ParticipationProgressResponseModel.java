package com.igaworks.model;

import java.util.List;

public class ParticipationProgressResponseModel {
    private List<ParticipationProgressModel> Data;
    private boolean Result;
    private int ResultCode;
    private String ResultMessage;

    public boolean isResult() {
        return this.Result;
    }

    public void setResult(boolean z) {
        this.Result = z;
    }

    public int getResultCode() {
        return this.ResultCode;
    }

    public void setResultCode(int i) {
        this.ResultCode = i;
    }

    public void setResultMessage(String str) {
        this.ResultMessage = str;
    }

    public List<ParticipationProgressModel> getData() {
        return this.Data;
    }

    public void setData(List<ParticipationProgressModel> list) {
        this.Data = list;
    }
}
