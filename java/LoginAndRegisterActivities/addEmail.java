package snapchat.example.snapchat.LoginAndRegisterActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import snapchat.example.snapchat.mainCamera;
import snapchat.example.snapchat.NetworkRequests.EmailTakenRequest;
import snapchat.example.snapchat.NetworkRequests.UpdateEmailRequest;
import com.example.snapchatstartscreen.R;

import org.json.JSONException;
import org.json.JSONObject;

public class addEmail extends AppCompatActivity {

    private EditText mEditTextEmail;
    private TextView availability;
    private Button bContinue;
    private String username;
    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_email);
        availability = findViewById(R.id.textView_email_text4);
        mEditTextEmail = findViewById(R.id.editText_Email);
        bContinue = findViewById(R.id.continue_button);
        Intent intent = getIntent();
        username = intent.getStringExtra("username");
        addEmail();
    }

    //To phoneNum activity
    public void addPhone(View view) {
        Intent intent = new Intent(this, addPhoneNum.class);
        intent.putExtra("username", username);
        startActivity(intent);
    }

    //Checks if valid email (contains @ and domain)
    public boolean isValid(String email){
        if(email.contains("@") &&
                                   (email.contains(".com") ||
                                    email.contains(".net") ||
                                    email.contains(".co.uk") ||
                                    email.contains(".fr") ||
                                    email.contains(".co.in") ||
                                    email.contains(".de") ||
                                    email.contains(".ru") ||
                                    email.contains(".it") ||
                                    email.contains(".br") ||
                                    email.contains(".es") )){
            return true;
        }
        else{
            availability.setTextColor(Color.rgb(255,0,0));
            availability.setText("Please enter a valid email address");
            bContinue.setEnabled(false);
            bContinue.setBackgroundResource(R.drawable.rounded_button);
        }
        return false;
    }

    //Checks when editTextEmail changes
    public void addEmail(){
        mEditTextEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                    availability.setText("");
                    bContinue.setBackgroundResource(R.drawable.rounded_button2);
                    bContinue.setEnabled(true);
            }
        });
    }

    //Checks if email is available
    public void checkTaken(View view){
        email = mEditTextEmail.getText().toString();
        if(isValid(email)){
            Response.Listener<String> responseListener = new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject jsonResponse = new JSONObject(response);
                        boolean success = jsonResponse.getBoolean("success");
                        if(success){
                            updateEmail(email, username);
                        }
                        else{
                            availability.setTextColor(Color.rgb(255,0,0));
                            availability.setText("Email is already taken");
                            bContinue.setEnabled(false);
                            bContinue.setBackgroundResource(R.drawable.rounded_button);
                        }
                    }
                    catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            };
            EmailTakenRequest emailTakenRequest = new EmailTakenRequest(email, responseListener);
            RequestQueue queue = Volley.newRequestQueue(addEmail.this);
            queue.add(emailTakenRequest);
        }
    }

    //Adds the email to the database
    public void updateEmail(String email, String username){
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");
                    if(success){
                        toCamera();
                    }
                }
                catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        UpdateEmailRequest updateEmailRequest = new UpdateEmailRequest(email, username, responseListener);
        RequestQueue queue = Volley.newRequestQueue(addEmail.this);
        queue.add(updateEmailRequest);
    }

    //Goes to camera activity
    public void toCamera(){
        Intent intent = new Intent(addEmail.this, mainCamera.class);
        startActivity(intent);
    }
}