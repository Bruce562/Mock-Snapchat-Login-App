package snapchat.example.snapchat.LoginAndRegisterActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.snapchatstartscreen.R;

public class Homescreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //To signUp path
    public void toName(View view){
        Intent intent = new Intent(this, setName.class);
        startActivity(intent);
    }

    //To login path
    public void toLogIn(View view){
        Intent intent = new Intent(this, logIn.class);
        startActivity(intent);
    }
}