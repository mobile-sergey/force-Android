package club.plus1.forcetaxi.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import club.plus1.forcetaxi.R;

public class SetPINActivity extends AppCompatActivity {

    Button butonNext;
    View.OnClickListener listenerNext = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(SetPINActivity.this, ConfirmPINActivity.class);
            startActivity(intent);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_pin);

        butonNext = findViewById(R.id.buttonNext);
        butonNext.setOnClickListener(listenerNext);
    }
}