package snapchat.example.snapchat.NetworkRequests;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class UpdateEmailRequest extends StringRequest {
    private static final String EMAIL_REQUEST_URL = "https://notsilent-cs.000webhostapp.com/UpdateEmail.php";
    private Map<String, String> params;

    public UpdateEmailRequest(String email, String username, Response.Listener<String> listener){
        super(Request.Method.POST, EMAIL_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("email", email);
        params.put("username", username);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
