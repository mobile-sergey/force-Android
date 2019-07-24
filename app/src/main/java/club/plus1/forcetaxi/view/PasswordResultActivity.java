package club.plus1.forcetaxi.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import club.plus1.forcetaxi.R;

public class PasswordResultActivity extends AppCompatActivity {

    View layout;

    View.OnClickListener listenerLayout = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(PasswordResultActivity.this, EnterActivity.class);
            startActivity(intent);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.password_result);

        layout = findViewById(R.id.layout);
        layout.setOnClickListener(listenerLayout);
    }
}
