package club.plus1.forcetaxi.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import club.plus1.forcetaxi.R;

public class RecoveryPhoneActivity extends AppCompatActivity {

    Button butonConfirm;
    View.OnClickListener listenerConfirm = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(RecoveryPhoneActivity.this, RecoveryActivity.class);
            startActivity(intent);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recovery_phone);

        butonConfirm = findViewById(R.id.buttonConfirm);
        butonConfirm.setOnClickListener(listenerConfirm);
    }
}
