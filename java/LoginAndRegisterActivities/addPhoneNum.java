package snapchat.example.snapchat.LoginAndRegisterActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import snapchat.example.snapchat.NetworkRequests.UpdatePhoneRequest;
import com.example.snapchatstartscreen.R;

import org.json.JSONException;
import org.json.JSONObject;

public class addPhoneNum extends AppCompatActivity {

    private static final String LOG_TAG = addPhoneNum.class.getSimpleName();

    private EditText mEditTextPhoneNum;
    private Button bContinue;
    private String username;
    private String phoneNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_phonenum);
        mEditTextPhoneNum = findViewById(R.id.editText_Phone);
        bContinue = findViewById(R.id.continue_button);
        Intent intent = getIntent();
        username = intent.getStringExtra("username");
        checkPhoneChange();
    }

    //Goes to email activity
    public void addEmail(View view) {
        Intent intent = new Intent(this, addEmail.class);
        intent.putExtra("username", username);
        startActivity(intent);
    }

    //Checks if editTextPhoneNum changes
    public void checkPhoneChange() {
        mEditTextPhoneNum.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (mEditTextPhoneNum.getText().toString().length() >= 4) {
                    bContinue.setBackgroundResource(R.drawable.rounded_button2);
                    bContinue.setEnabled(true);
                } else {
                    bContinue.setBackgroundResource(R.drawable.rounded_button);
                    bContinue.setEnabled(false);
                }
            }
        });
    }

    //Adds the phoneNum to database
    public void addPhone(View view){
        phoneNum = mEditTextPhoneNum.getText().toString();
        Log.i(LOG_TAG, phoneNum );
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");
                    if(success)
                        toSMSPhoneVerification();
                }
                catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        UpdatePhoneRequest updatePhoneRequest = new UpdatePhoneRequest(phoneNum, username, responseListener);
        RequestQueue queue = Volley.newRequestQueue(addPhoneNum.this);
        queue.add(updatePhoneRequest);
    }

    //Goes to smsPhoneVerification activity
    public void toSMSPhoneVerification(){
        Intent intent = new Intent(addPhoneNum.this, smsPhoneVerification.class);
        Log.i(LOG_TAG, phoneNum );
        intent.putExtra("phoneNum", phoneNum);
        startActivity(intent);
    }
}