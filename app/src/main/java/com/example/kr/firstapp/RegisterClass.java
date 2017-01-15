package com.example.kr.firstapp;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 *  This class is the register of the connection between the Register fields and the Database
 **/

public class RegisterClass extends StringRequest {

    // This is the URL of the database php file, which handles the transfer of data
    private static final String REGISTER_REQUEST_URL =
            "http://karshan.000webhostapp.com/Register.php";
    private Map<String, String> params;

    // The fields are passed here and a hashmap is the dictionary of the objects
    public RegisterClass(String name, String username, int age, String password, Response.Listener<String> listener){
        super(Method.POST, REGISTER_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("name", name);
        params.put("username", username);
        params.put("password", password);
        params.put("age", age + "");
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
