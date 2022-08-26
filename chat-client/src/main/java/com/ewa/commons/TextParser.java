package com.ewa.commons;

import org.apache.commons.lang3.StringUtils;

import javax.inject.Singleton;

@Singleton
public class TextParser {

    public static int parseLastNumber(String text, String separator){
        Integer number = 0;
        String num = getLastPart(text, separator);
        try {
            number = Integer.parseInt(num);
        } catch (NumberFormatException nfe) {
        }
        return number;
    }

    public static String getFirstPart(String text, String separator){
        return StringUtils.substringBefore(text, separator);
    }
    public static String getLastPart(String text, String separator){
        return StringUtils.substringAfter(text, separator).trim();
    }
}
