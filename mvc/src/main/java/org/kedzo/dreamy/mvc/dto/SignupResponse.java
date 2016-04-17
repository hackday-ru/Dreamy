package org.kedzo.dreamy.mvc.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class SignupResponse {

    private String status;



    public enum SignupError {
        DUPLICATE_EMAIL, PASSWORD_TOO_SHORT
    }
}
