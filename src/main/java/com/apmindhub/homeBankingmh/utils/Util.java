package com.apmindhub.homeBankingmh.utils;

public class Util {
    public static int getRandomNumber(Integer min, Integer max)  {
        return (int) ((Math.random() * (max - min)) + min);
    }
    public static int getRandomNumberCvv (int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

}
