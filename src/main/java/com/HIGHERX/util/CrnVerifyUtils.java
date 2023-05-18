package com.HIGHERX.util;

import org.springframework.beans.factory.annotation.Value;

public class CrnVerifyUtils {

    // TODO 설정에서 관리하기
    @Value("${auth.key}")
    private static int authenticationKeyOrigin;

    public static boolean crnVerify(String crn) {

        int authenticationKey = authenticationKeyOrigin;

        // 1.
        int number = Integer.parseInt(crn.replace("-", "").substring(0, 9));

        // 2.
        int sum = sumCrnOperation(authenticationKey, number);

        // 3.
        int lastValueResult = lastValueOperation(authenticationKey, number);

        // 4.
        sum += lastValueResult;

        // 5.
        int division = sum % 10;

        // 6.
        int result = 10 - division;

        if (result == 1) return true;

        return false;
    }

    public static int lastValueOperation(int authenticationKey, int number) {
        int result = 0;

        int lastAuthKey = authenticationKey % 10;
        int lastNumber = number % 10;

        return (int) lastAuthKey * lastNumber / 10;
    }

    public static int sumCrnOperation(int authenticationKey, int number) {
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
