package snapchat.example.snapchat.NetworkRequests;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import java.util.HashMap;
import java.util.Map;

public class UsernameTakenRequest extends StringRequest {
    private static final String LOGIN_REQUEST_URL = "https://notsilent-cs.000webhostapp.com/UsernameTaken.php";
    private Map<String, String> params;

    public UsernameTakenRequest(String username, Response.Listener<String> listener){
        super(Method.POST, LOGIN_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("username", username);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
