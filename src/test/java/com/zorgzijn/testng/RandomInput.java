package com.zorgzijn.testng;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Random;

public class RandomInput {

    public static String phoneNumber() {

        Random random = new Random();
        String countryCode = "+31";
        StringBuilder phoneNumber = new StringBuilder(countryCode);

        int digitsCount = 9;
        for (int i = 0; i < digitsCount; i++) {
            phoneNumber.append(random.nextInt(10));
        }

        return phoneNumber.toString();
    }

    public static String kvkNumber() {
        Random random = new Random();
        int[] digits = new int[8];

        // Generate the first 7 digits randomly
        for (int i = 0; i < 7; i++) {
            digits[i] = random.nextInt(10); // Random digit between 0-9
        }

        // Calculate the checksum using Modulo 11
        int checksum = 0;
        for (int i = 0; i < 7; i++) {
            checksum += digits[i] * (8 - i);
        }
        checksum %= 11;

        // If the checksum is 10, regenerate (10 is invalid as per Modulo 11 rules)
        if (checksum == 10) {
            return kvkNumber();
        }

        // Add the checksum as the 8th digit
        digits[7] = checksum;

        // Convert the digits array to a string
        StringBuilder kvkNumber = new StringBuilder();
        for (int digit : digits) {
            kvkNumber.append(digit);
        }

        return kvkNumber.toString();
    }

    public static String birthday() {
        Random random = new Random();

        int startYear = 1950;
        int endYear = 2005;

        int year = startYear + random.nextInt(endYear - startYear + 1);
        int month = random.nextInt(12);

        GregorianCalendar calendar = new GregorianCalendar(year, month, 1);
        int day = 1 + random.nextInt(calendar.getActualMaximum(GregorianCalendar.DAY_OF_MONTH));

        calendar.set(year, month, day);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        return dateFormat.format(calendar.getTime());
    }

    public static String feePerHour() {
        Random random = new Random();

        int number = 100 + random.nextInt(900);

        return String.valueOf(number);
    }
}
