package com.codetech.oauth2.exceptions;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Amila Wickramarathne.
 * Date: 8/19/2019
 * Time: 4:01 PM
 */
@Getter
@Setter
public class ValidationError {
    private String path;
    private String message;
}
