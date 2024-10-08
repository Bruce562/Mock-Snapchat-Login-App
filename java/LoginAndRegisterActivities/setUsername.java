package snapchat.example.snapchat.LoginAndRegisterActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import snapchat.example.snapchat.NetworkRequests.UsernameTakenRequest;
import com.example.snapchatstartscreen.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class setUsername extends AppCompatActivity {

    private static final String LOG_TAG = setUsername.class.getSimpleName();
    private EditText mEditTextUsername;
    private TextView availability;
    private Button bContinue;
    private String firstName;
    private String lastName;
    private String username;
    private int birthdayYear;
    private int birthdayMonth;
    private int birthdayDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setusername);
        mEditTextUsername = findViewById(R.id.editText_Username);
        bContinue = findViewById(R.id.continue_button);
        availability = findViewById(R.id.textView_email_text4);
        gatherInfo();
        mEditTextUsername.setText(username);
        changeUsername();
    }

    //Gathers info from birthday activity
    public void gatherInfo(){
        Intent intent = getIntent();
        firstName = intent.getStringExtra("firstName");
        lastName = intent.getStringExtra("lastName");
        username = intent.getStringExtra("username");
        birthdayYear = intent.getIntExtra("birthYear", 0);
        birthdayMonth = intent.getIntExtra("birthMonth", 0);
        birthdayDay = intent.getIntExtra("birthDay", 0);
    }

    //Checks when username changes and launches isValid()
    public void changeUsername(){
        mEditTextUsername.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(isValid(mEditTextUsername.getText().toString())){
                    availability.setText("");
                    bContinue.setBackgroundResource(R.drawable.rounded_button2);
                    bContinue.setEnabled(true);
                }
                if(!isValid(mEditTextUsername.getText().toString())){
                    availability.setTextColor(Color.rgb(255,0,0));
                    bContinue.setEnabled(false);
                    bContinue.setBackgroundResource(R.drawable.rounded_button);
                }
            }
        });
    }

    //Checks if username falls under guidelines
    public Boolean isValid(String username){
        Pattern special = Pattern.compile ("[!@#$%&*()+=|<>?{}\\[\\]~]");
        Matcher hasSpecial = special.matcher(username);
        if(username.length() < 3){
            availability.setText("Oops! Usernames must be at least 3 chracters");
            return false;
        }
        if(!Pattern.matches("[a-zA-Z]", username.substring(0, 1))){
            availability.setText("Oops! Usernames must start with a letter");
            return false;
        }
        if(mEditTextUsername.getText().toString().length() > 15)
        {
            availability.setText("Oops! Usernames cannot be longer than 15 characters");
            return false;
        }
        if(!Pattern.matches("[a-zA-Z]", username.substring(username.length()-1)) && !Pattern.matches("[0-9]",username.substring(username.length()-1))){
            availability.setText("Oops! Usernames must end in a letter or number");
            return false;
        }
        if(hasSpecial.find()){
              availability.setText("Oops! Usernames can only include latin letters, numbers, and one of -,_, or . but no special characters!");
              return false;
        }
        if(countChar(username, '-') >= 2 || countChar(username, '_') >= 2 || countChar(username, '.') >= 2 ){
            availability.setText("Oops! Usernames can only include one -,_, or . You've got too many");
            return false;
        }
        if(!Pattern.matches("[a-zA-Z]", username.substring(username.length()-1)) && !Pattern.matches("[0-9]",username.substring(username.length()-1))){
            availability.setText("Oops! Usernames must end in a letter or number");
            return false;
        }

        return true;
    }

    //Counts how many of a certain char there is in a String
    public int countChar(String str, char c) {
        int count = 0;
        for(int i=0; i < str.length(); i++){
            if(str.charAt(i) == c)
            count++;
        }
        return count;
    }

    //Checks if username is taken and launches toPassword() if not taken
    public void checkTaken(View view){
        username = mEditTextUsername.getText().toString();
        Response.Listener<String> responseListener = response -> {
            try {
                JSONObject jsonResponse = new JSONObject(response);
                boolean success = jsonResponse.getBoolean("success");
                Log.d(LOG_TAG, "Success:" + success);
                if(success)
                    toPassword();
                else
                {
                    availability.setTextColor(Color.rgb(255,0,0));
                    availability.setText(username + " is already taken!");
                    bContinue.setEnabled(false);
                    bContinue.setBackgroundResource(R.drawable.rounded_button);
                }
            }
            catch (JSONException e) {
                e.printStackTrace();
            }
        };
        UsernameTakenRequest usernameTakenRequest = new UsernameTakenRequest(username, responseListener);
        RequestQueue queue = Volley.newRequestQueue(setUsername.this);
        queue.add(usernameTakenRequest);
    }

    //Goes to password activity
    public void toPassword(){
        Intent intent = new Intent(setUsername.this, setPassword.class);
        intent.putExtra("firstName", firstName);
        intent.putExtra("lastName", lastName);
        intent.putExtra("birthYear", birthdayYear);
        intent.putExtra("birthMonth", birthdayMonth);
        intent.putExtra("birthDay", birthdayDay);
        intent.putExtra("username", username);
        startActivity(intent);
    }
}