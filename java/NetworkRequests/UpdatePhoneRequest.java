package snapchat.example.snapchat.NetworkRequests;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class UpdatePhoneRequest extends StringRequest {
    private static final String PHONE_REQUEST_URL = "https://notsilent-cs.000webhostapp.com/UpdatePhone.php";
    private Map<String, String> params;

    public UpdatePhoneRequest(String phoneNum, String username, Response.Listener<String> listener){
        super(Request.Method.POST, PHONE_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("phoneNum", phoneNum);
        params.put("username", username);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
