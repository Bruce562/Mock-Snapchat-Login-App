package snapchat.example.snapchat.LoginAndRegisterActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import snapchat.example.snapchat.NetworkRequests.RegisterRequest;
import com.example.snapchatstartscreen.R;

public class setPassword extends AppCompatActivity {

    private static final String LOG_TAG = setPassword.class.getSimpleName();
    private EditText mEditTextPassword;
    private TextView textInfo;
    private TextView visibility;
    private Button bContinue;
    private String firstName = "";
    private String lastName = "";
    private int birthdayYear;
    private int birthdayMonth;
    private int birthdayDay;
    private String username;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setpassword);
        mEditTextPassword = findViewById(R.id.editText_Password);
        textInfo = findViewById(R.id.textView_password_text4);
        visibility = findViewById(R.id.textView_password_visibility);
        bContinue = findViewById(R.id.continue_button);
        gatherInfo();
        checkPassword();
    }

    //Gathers info from username activity
    public void gatherInfo(){
        Intent intent = getIntent();
        firstName = intent.getStringExtra("firstName");
        lastName = intent.getStringExtra("lastName");
        birthdayYear = intent.getIntExtra("birthYear", 0);
        birthdayMonth = intent.getIntExtra("birthMonth", 0);
        birthdayDay = intent.getIntExtra("birthDay", 0);
        username = intent.getStringExtra("username");
    }

    //Checks if editTextPassword changes and is a length of 8+
    public void checkPassword(){
        mEditTextPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(mEditTextPassword.getText().toString().length() >= 8) {
                    password = mEditTextPassword.getText().toString();
                    bContinue.setBackgroundResource(R.drawable.rounded_button2);
                    bContinue.setEnabled(true);
                    textInfo.setText("");
                }
                else {
                    textInfo.setText("Your password must be at least 8 characters.");
                    textInfo.setTextColor(Color.rgb(255,0,0));
                    bContinue.setBackgroundResource(R.drawable.rounded_button);
                    bContinue.setEnabled(false);
                }
            }
        });
    }

    //Show or hide password
    public void visibility(View view) {
        if(visibility.getText().toString().equals("Show")){
            mEditTextPassword.setTransformationMethod(null);
            visibility.setText("Hide");
        }
        else if(visibility.getText().toString().equals("Hide")){
            mEditTextPassword.setTransformationMethod(new PasswordTransformationMethod());
            visibility.setText("Show");
        }
    }

    //Registers account
    public void registerAccount(View view){
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Intent intent = new Intent(setPassword.this, addPhoneNum.class);
                intent.putExtra("username", username);
                setPassword.this.startActivity(intent);
                finish();
            }
        };

        RegisterRequest registerRequest = new RegisterRequest(firstName + " " + lastName, birthdayYear + "-" + (birthdayMonth+1) + "-" + birthdayDay, username, password, 0, "", responseListener);
        RequestQueue queue = Volley.newRequestQueue(setPassword.this);
        queue.add(registerRequest);
    }

}