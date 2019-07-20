package club.plus1.forcetaxi.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import club.plus1.forcetaxi.R;

public class RecoveryEmailActivity extends AppCompatActivity {

    View layout;

    View.OnClickListener listenerLayout = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(RecoveryEmailActivity.this, RecoveryActivity.class);
            startActivity(intent);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recovery_email);

        layout = findViewById(R.id.layout);
        layout.setOnClickListener(listenerLayout);
    }
}
