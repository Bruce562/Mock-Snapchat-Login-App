package snapchat.example.snapchat.NetworkRequests;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class EmailTakenRequest extends StringRequest {
    private static final String EMAIL_REQUEST_URL = "https://notsilent-cs.000webhostapp.com/EmailTaken.php";
    private Map<String, String> params;

    public EmailTakenRequest(String email, Response.Listener<String> listener){
        super(Method.POST, EMAIL_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("email", email);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
