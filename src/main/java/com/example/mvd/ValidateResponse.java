package com.example.mvd;

public class ValidateResponse {
    private boolean isValid;

    public ValidateResponse(boolean isValid){
        this.isValid = isValid;
    }
    public boolean isValid() {
        return isValid;
    }

    public void setIsValid(boolean isValid) {
        this.isValid = isValid;
    }
}
