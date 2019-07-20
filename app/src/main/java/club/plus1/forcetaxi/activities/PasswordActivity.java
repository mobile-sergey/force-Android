package club.plus1.forcetaxi.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import club.plus1.forcetaxi.R;

public class PasswordActivity extends AppCompatActivity {

    Button buttonChangePassword;
    View.OnClickListener listenerChangePassword = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(PasswordActivity.this, RecoveryActivity.class);
            startActivity(intent);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.password);

        buttonChangePassword = findViewById(R.id.buttonChangePassword);
        buttonChangePassword.setOnClickListener(listenerChangePassword);
    }
}
