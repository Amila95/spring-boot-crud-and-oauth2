package com.codetech.oauth2.validator;

import org.springframework.stereotype.Component;

/**
 * Created by Amila Wickramarathne.
 * Date: 8/19/2019
 * Time: 4:13 PM
 */
@Component
public class IsNumeric {

    public static boolean isNumeric(final String str) {

        // null or empty
        if (str == null || str.length() == 0) {
            return false;
        }

        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }

        return true;

    }
}
