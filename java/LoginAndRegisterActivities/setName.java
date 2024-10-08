package snapchat.example.snapchat.LoginAndRegisterActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.snapchatstartscreen.R;

public class setName extends AppCompatActivity {

    private static final String LOG_TAG = setName.class.getSimpleName();
    private EditText mEditTextFirstName;
    private EditText mEditTextLastName;
    private Button bContinue;
    private String firstName;
    private String lastName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setname);
        mEditTextFirstName = findViewById(R.id.editText_Username);
        mEditTextLastName = findViewById(R.id.editText_Password);
        bContinue = findViewById(R.id.continue_button);
        checkBlank();
    }

    //Checks if either of the editTexts have changed
    public void checkBlank(){
        mEditTextFirstName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                firstName = mEditTextFirstName.getText().toString();
                if(firstName != null) {
                    bContinue.setBackgroundResource(R.drawable.rounded_button2);
                    bContinue.setEnabled(true);
                }
                if(firstName.isEmpty()) {
                    bContinue.setBackgroundResource(R.drawable.rounded_button);
                    bContinue.setEnabled(false);
                }
            }
        });
        mEditTextLastName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                lastName = mEditTextLastName.getText().toString();
                if(lastName != null) {
                    bContinue.setBackgroundResource(R.drawable.rounded_button2);
                    bContinue.setEnabled(true);
                }
                if(lastName.isEmpty()) {
                    bContinue.setBackgroundResource(R.drawable.rounded_button);
                    bContinue.setEnabled(false);
                }
            }
        });
    }

    //Goes to birthday activity
    public void toBirthday(View view) {
        if(mEditTextFirstName.getText().toString().length() == 0)
            firstName = "";
        if(mEditTextLastName.getText().toString().length() == 0)
            lastName = "";
        Intent intent = new Intent(this, setBirthday.class);
        intent.putExtra("firstName", firstName);
        intent.putExtra("lastName", lastName);
        startActivity(intent);
    }
}