package com.java.thread.enums;

public enum CountryEnum {
    ONE(1, "齐"),
    TWO(2, "楚"),
    THREE(3, "燕"),
    FOUR(4, "赵"),
    FIVE(5, "魏"),
    SIX(6, "韩");

    private int code;
    private String describe;

    public int getCode() {
        return code;
    }

    public String getDescribe() {
        return describe;
    }

    CountryEnum(int code, String describe) {
        this.code = code;
        this.describe = describe;
    }

    public static CountryEnum getByCode(int code) {
        CountryEnum[] array = CountryEnum.values();
        for (CountryEnum countryEnum : array) {
            if (code == countryEnum.getCode()) {
                return countryEnum;
            }
        }
        return null;
    }
}
