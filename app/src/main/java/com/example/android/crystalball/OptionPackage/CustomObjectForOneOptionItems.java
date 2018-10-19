package com.example.android.crystalball.OptionPackage;

public class CustomObjectForOneOptionItems {
    private String oneOption;
    private int oneOptionId;

    public CustomObjectForOneOptionItems(int oneOptionId, String oneOption) {
        this.oneOption = oneOption;
        this.oneOptionId = oneOptionId;
    }

    public String getOneOption() {
        return oneOption;
    }

    public void setOneOption(String oneOption) {
        this.oneOption = oneOption;
    }

    public int getOneOptionId() {
        return oneOptionId;
    }

    public void setOneOptionId(int oneOptionId) {
        this.oneOptionId = oneOptionId;
    }
}
