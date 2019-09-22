package com.codetech.oauth2.validator;

import org.springframework.stereotype.Component;


/**
 * Created by Supun Dilshan.
 * Date: 09/03/2019
 * Time: 10:48 PM
 */

@Component
public class IsNullable {
    public static boolean isNullable(final String idName) {

        String id= idName.toLowerCase();

        // null or empty

        if(id.equals("null")|| id.equals("empty") || id.equals("empty")){
            return true;
        }


        return false;

    }
}
