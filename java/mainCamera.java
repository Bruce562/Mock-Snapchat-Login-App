package snapchat.example.snapchat;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.snapchatstartscreen.R;

public class mainCamera extends AppCompatActivity {

    private TextView textInfo1;
    private TextView textInfo2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_camera);
        textInfo1 = findViewById(R.id.textView_mainCamera_textInfo1);
        textInfo2 = findViewById(R.id.textView_mainCamera_textInfo2);
    }

    public void finishedProject(View view) {
        textInfo1.setText("Thank you!");
    }
}