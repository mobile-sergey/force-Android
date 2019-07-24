package club.plus1.forcetaxi.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import club.plus1.forcetaxi.R;

public class RegistrationActivity extends AppCompatActivity {

    Button butonRegistration;
    View.OnClickListener listenerRegistraiton = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(RegistrationActivity.this, VerificationActivity.class);
            startActivity(intent);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);

        butonRegistration = findViewById(R.id.buttonRegistration);
        butonRegistration.setOnClickListener(listenerRegistraiton);

    }
}
