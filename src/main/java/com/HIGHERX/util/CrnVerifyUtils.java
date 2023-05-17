package com.HIGHERX.util;

public class CrnVerifyUtils {

    private final int authenticationKey = 137137135;

    boolean crnVerify(String crn) {

        // 1.
        int number = Integer.parseInt(crn.replace("-", "").substring(0, 9));

        // 2.
        int sum = sum(authenticationKey, number);

        // 3.
        int lastValueResult = lastValueOperation(authenticationKey, number);

        // 4.
        sum += lastValueResult;

        // 5.
        int division = sum % 10;

        // 6.
        int result = 10 - division;

        if(result == 1) return true;

        return false;
    }

    public int lastValueOperation(int authenticationKey, int number) {
        int result = 0;

        int lastAuthKey = authenticationKey % 10;
        int lastNumber = number % 10;

        return (int) lastAuthKey * lastNumber / 10;
    }

    public int sum(int authenticationKey, int number) {
        int result = 0;

        while (authenticationKey > 0) {


            int authKey = authenticationKey % 10;
            int num = number % 10;

            result += (authKey * num);

            authenticationKey /= 10;
            number /= 10;
        }

        return result;
    }

}
