package club.plus1.forcetaxi.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import club.plus1.forcetaxi.R;

public class PhoneVerificationActivity extends AppCompatActivity {

    Button butonVerification;
    View.OnClickListener listenerVerification = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(PhoneVerificationActivity.this, RegistrationFinishedActivity.class);
            startActivity(intent);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.phone_verification);

        butonVerification = findViewById(R.id.buttonVerification);
        butonVerification.setOnClickListener(listenerVerification);
    }
}
