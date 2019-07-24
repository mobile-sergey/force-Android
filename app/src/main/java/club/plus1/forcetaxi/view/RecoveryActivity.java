package club.plus1.forcetaxi.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import club.plus1.forcetaxi.R;

public class RecoveryActivity extends AppCompatActivity {

    Button buttonRecovery;
    View.OnClickListener listenerRecovery = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(RecoveryActivity.this, RecoveryEmailActivity.class);
            startActivity(intent);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recovery);

        buttonRecovery = findViewById(R.id.buttonRecovery);
        buttonRecovery.setOnClickListener(listenerRecovery);
    }
}
