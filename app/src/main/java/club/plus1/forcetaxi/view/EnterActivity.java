package club.plus1.forcetaxi.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import club.plus1.forcetaxi.R;

public class EnterActivity extends AppCompatActivity {

    Button buttonEnter;
    TextView linkRegistration;
    TextView linkRecovery;

    View.OnClickListener listenerEnter = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(EnterActivity.this, EnterResultActivity.class);
            startActivity(intent);
        }
    };

    View.OnClickListener listenerRegistraiton = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(EnterActivity.this, RegistrationActivity.class);
            startActivity(intent);
        }
    };

    View.OnClickListener listenerRecovery = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(EnterActivity.this, RecoveryActivity.class);
            startActivity(intent);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enter);

        buttonEnter = findViewById(R.id.buttonEnter);
        buttonEnter.setOnClickListener(listenerEnter);

        linkRegistration = findViewById(R.id.linkRegister);
        linkRegistration.setOnClickListener(listenerRegistraiton);

        linkRecovery = findViewById(R.id.linkRecovery);
        linkRecovery.setOnClickListener(listenerRecovery);
    }
}
