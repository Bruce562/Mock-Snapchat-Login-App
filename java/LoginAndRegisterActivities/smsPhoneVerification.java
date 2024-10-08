package snapchat.example.snapchat.LoginAndRegisterActivities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.telephony.SmsManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import snapchat.example.snapchat.ExtraClasses.PinEntryEditText;
import snapchat.example.snapchat.mainCamera;
import com.example.snapchatstartscreen.R;

public class smsPhoneVerification extends AppCompatActivity{

    private static final String LOG_TAG = smsPhoneVerification.class.getSimpleName();
    final int SEND_SMS_PERMISSION_REQUEST_CODE = 1;

    private PinEntryEditText txtPinEntry;
    private CountDownTimer countDownTimer;
    private long timeLeftInMilliseconds;
    private TextView textInfo;
    private TextView verify;
    private Button resend;
    private String phoneNum;
    private int smsCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms_phone_verification);
        txtPinEntry = findViewById(R.id.txt_pin_entry);
        verify = findViewById(R.id.textView_phoneVerification_text4);
        resend = findViewById(R.id.button_resend);
        textInfo = findViewById(R.id.textView_phoneVerification_text2);
        txtPinEntry = findViewById(R.id.txt_pin_entry);
        Intent intent = getIntent();
        phoneNum = intent.getStringExtra("phoneNum");
        formatPhoneNum(phoneNum);
        sendSMS();
        resendTimer();
        if(!checkPermission(Manifest.permission.SEND_SMS))
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, SEND_SMS_PERMISSION_REQUEST_CODE);
        txtPinEntry.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().equals(smsCode + "")) {
                    Intent intent = new Intent(smsPhoneVerification.this, mainCamera.class);
                    startActivity(intent);
                } else if (!s.toString().equals(smsCode + "") && txtPinEntry.getText().toString().length() == 6) {
                    verify.setText("That's not the right code!");
                }
                verify.setText("");
            }
        });
    }

    //Sends sms message
    public void sendSMS(){
        Intent intent = getIntent();
        phoneNum = intent.getStringExtra("phoneNum");
        smsCode = (int) (Math.random() * 999999-100000) + 100000;
        String message = "Snapchat code: " + smsCode + ". Do not share it or use it elsewhere!";
        if(checkPermission(Manifest.permission.SEND_SMS)) {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNum, null, message, null, null);
        }
    }

    //Creates timer to resend SMS
    public void resendTimer(){
        timeLeftInMilliseconds = 60000;
        countDownTimer = new CountDownTimer(timeLeftInMilliseconds, 1000) {
            @Override
            public void onTick(long l) {
                timeLeftInMilliseconds = l;
                int seconds = (int) (timeLeftInMilliseconds / 1000);
                String timeLeftText = "";
                timeLeftText += seconds;
                resend.setText("Resend " + timeLeftText);
            }

            @Override
            public void onFinish() {
                resend.setText("Resend ");
                resend.setEnabled(true);
                resend.setBackgroundResource(R.drawable.rounded_button2);
                smsCode = 0;
            }
        }.start();
    }

    //Formats phoneNum into (xxx) xxx-xxxx
    public void formatPhoneNum(String phoneNumber){
        String temp = phoneNumber;
        String phoneNumFormatted = "(" + temp.substring(0,3) + ") " + temp.substring(3,6) + "-" + temp.substring(6);
        String message = "Enter the code we sent to " + phoneNumFormatted;
        textInfo.setText(message);
    }

    //Resends sms
    public void resendSMS(View view) {
        resend.setBackgroundResource(R.drawable.rounded_button);
        resend.setEnabled(false);
        resendTimer();
        sendSMS();

    }

    //Checks sms permissions
    public boolean checkPermission(String permission){
        int check = ContextCompat.checkSelfPermission(this, permission);
        return(check == PackageManager.PERMISSION_GRANTED);
    }
}