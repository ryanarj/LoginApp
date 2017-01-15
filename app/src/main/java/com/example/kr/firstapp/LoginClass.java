package com.example.kr.firstapp;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * This class retrieves the login data from the database and if found logs the user in.
 **/

public class LoginClass extends StringRequest {
    private static final String REGISTER_REQUEST_URL =
            "http://karshan.000webhostapp.com/Login.php";
    private Map<String, String> params;

    public LoginClass(String username, String password, Response.Listener<String> listener){
        super(Method.POST, REGISTER_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("username", username);
        params.put("password", password);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}