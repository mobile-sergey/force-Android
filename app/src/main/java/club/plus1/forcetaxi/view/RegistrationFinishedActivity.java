package club.plus1.forcetaxi.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import club.plus1.forcetaxi.R;

public class RegistrationFinishedActivity extends AppCompatActivity {

    TextView buttonSetPIN;
    View.OnClickListener listenerSetPIN = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(RegistrationFinishedActivity.this, PINSetActivity.class);
            startActivity(intent);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_finished);

        buttonSetPIN = findViewById(R.id.linkSetPIN);
        buttonSetPIN.setOnClickListener(listenerSetPIN);
    }
}
