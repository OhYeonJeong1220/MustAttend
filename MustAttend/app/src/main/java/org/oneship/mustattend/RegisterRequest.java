package org.oneship.mustattend;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegisterRequest extends StringRequest {
    final static private String URL="http://10.0.2.2:80/Register.php";
    private Map<String, String> parameters;


    public RegisterRequest(String userEmail, String userPassword, String userPhone, String userBirth,
                           Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);
        parameters = new HashMap<>();
        parameters.put("userEmail",userEmail);
        parameters.put("userPassword",userPassword);
        parameters.put("userPhone",userPhone);
        parameters.put("userBirth",userBirth);
    }

    protected Map<String, String> getParms() throws AuthFailureError {
        return parameters;
    }
}
