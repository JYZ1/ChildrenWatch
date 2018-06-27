package com.jyz.childrenwatch.json;

/**
 * Created by Administrator on 2018/6/22.
 */

public class SmsResponse extends CommResponse{
    public static class Result{
        String vCode;
        String isRegister;

        public String getvCode() {
            return vCode;
        }

        public void setvCode(String vCode) {
            this.vCode = vCode;
        }

        public String getIsRegister() {
            return isRegister;
        }

        public void setIsRegister(String isRegister) {
            this.isRegister = isRegister;
        }
    }

    Result result;

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }
}
