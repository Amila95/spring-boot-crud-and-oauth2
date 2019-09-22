package com.codetech.oauth2.validator;

import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Amila Wickramarathne.
 * Date: 9/5/2019
 * Time: 4:17 PM
 */
@Component
public class MiddleNameValidator {
    String regex = "^(?=.*[a-zA-Z])(?=\\S+$).{3,40}+$";
    Pattern pattern = Pattern.compile(regex);
    boolean decision = true;
    public boolean validMiddleName(String name) {

            Matcher matcher = pattern.matcher(name);

            if (!matcher.matches()) {
                decision = false;
                return decision;

            }

        return decision;
    }
}
