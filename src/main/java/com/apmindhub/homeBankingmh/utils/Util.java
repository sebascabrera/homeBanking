package com.apmindhub.homeBankingmh.utils;

public class Util {
    public static int getRandomNumber(Integer min, Integer max)  {
        return (int) ((Math.random() * (max - min)) + min);
    }
    public static int getRandomNumberCvv (int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
    public static String randomCardNumber(){
        Integer numCard, max=9999, min = 1;
        String numCardString="";
        for (int i=0; i<4 ;i++){
            numCard = (int)((Math.random() * (max - min)) + min);
            if (i ==0 ){
                numCardString =  numCard.toString();
            }else {
                numCardString += ("-" + numCard.toString());
            }
        }
        return numCardString;
    }
}
