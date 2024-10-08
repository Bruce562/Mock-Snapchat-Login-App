package snapchat.example.snapchat.LoginAndRegisterActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import snapchat.example.snapchat.NetworkRequests.LoginRequest;
import snapchat.example.snapchat.mainCamera;
import com.example.snapchatstartscreen.R;

import org.json.JSONException;
import org.json.JSONObject;

public class logIn extends AppCompatActivity {

    private static final String LOG_TAG = logIn.class.getSimpleName();
    private Button bLogin;
    private EditText mUsername;
    private EditText mPassword;
    private TextView loginFailed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        mUsername = findViewById(R.id.editText_Username);
        mPassword = findViewById(R.id.editText_Password);
        bLogin = findViewById(R.id.continue_button);
        loginFailed = findViewById(R.id.textView_password_text4);
        checkBlank();
        bLogin.setOnClickListener(view -> {
            String username = mUsername.getText().toString();
            String password = mPassword.getText().toString();
            Response.Listener<String> responseListener = response -> {
                Log.d(LOG_TAG, response);
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");
                    Log.i(LOG_TAG, "response" + response);
                    if(success)
                    {
                        Intent intent = new Intent(logIn.this, mainCamera.class);
                        startActivity(intent);
                    }
                    else
                        loginFailed.setText("Oops! Your username/email or password is incorrect.");
                }
                catch (JSONException e) {
                    e.printStackTrace();
                }
            };
            if(username.contains("@")) {
                String email = username;
                LoginRequest loginRequest = new LoginRequest("", email, password, responseListener);
                RequestQueue queue = Volley.newRequestQueue(logIn.this);
                queue.add(loginRequest);
            }
            else {
                LoginRequest loginRequest = new LoginRequest(username, "", password, responseListener);
                RequestQueue queue = Volley.newRequestQueue(logIn.this);
                queue.add(loginRequest);
            }
        });
    }

    //textListener
    public void checkBlank(){
        mUsername.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                String username = mUsername.getText().toString();
                loginFailed.setText("");
                if(username != null && !passwordCheck()) {
                    bLogin.setBackgroundResource(R.drawable.rounded_button2);
                    bLogin.setEnabled(true);
                }
                if(username.isEmpty() || passwordCheck()) {
                    bLogin.setBackgroundResource(R.drawable.rounded_button);
                    bLogin.setEnabled(false);
                }
            }
        });
        mPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                String password = mPassword.getText().toString();
                loginFailed.setText("");
                if(password != null && !userCheck()) {
                    bLogin.setBackgroundResource(R.drawable.rounded_button2);
                    bLogin.setEnabled(true);

                }
                if(password.isEmpty() || userCheck()) {
                    bLogin.setBackgroundResource(R.drawable.rounded_button);
                    bLogin.setEnabled(false);
                }
            }
        });
    }

    //Checks if editTextUsername is empty
    public boolean userCheck(){
        String username = mUsername.getText().toString();
        if(username.isEmpty())
            return true;
        else
            return false;
    }

    //Checks if editTextPassword is empty
    public boolean passwordCheck(){
        String password = mPassword.getText().toString();
        if(password.isEmpty())
            return true;
        else
            return false;
    }

}