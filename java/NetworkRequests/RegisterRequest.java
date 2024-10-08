package snapchat.example.snapchat.NetworkRequests;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import java.util.HashMap;
import java.util.Map;

public class RegisterRequest extends StringRequest{

    private static final String REGISTER_REQUEST_URL = "https://notsilent-cs.000webhostapp.com/Register.php";
    private Map<String, String> params;

    public RegisterRequest(String name, String birthday, String username, String password, int phoneNum, String email, Response.Listener<String> listener){
        super(Method.POST, REGISTER_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("name", name);
        params.put("birthday", birthday);
        params.put("username", username);
        params.put("password", password);
        params.put("phoneNum", phoneNum + "");
        params.put("email", email);

    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }

}
