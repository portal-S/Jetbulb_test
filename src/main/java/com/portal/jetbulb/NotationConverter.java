package com.portal.jetbulb;

public class NotationConverter {
    public int toArabic(String romanNotation) throws InvalidValueException {
        if (romanNotation == null || romanNotation.equals("") || (!romanNotation.replaceAll("[IVXLCDM]", "").equals(""))) throw new InvalidValueException("String must contain only valid roman numerals [I, V, X, L, C, D, M]");

        char[] chars = romanNotation.toCharArray();
        int sum = 0;
        for (int i = chars.length - 1; i >= 0; i-=2){
            int val1 = getValByChar(chars[i]);
            if(i == 0){
                sum+=val1;
                break;
            }
            int val2 = getValByChar(chars[i - 1]);
            if(val1 > val2){
                sum+= val1 - val2;
            } else sum+= val1 + val2;
        }
        return sum;
    }

    public int getValByChar(char c){
        return RomanNum.valueOf(String.valueOf(c)).getVal();
    }

}
