package club.plus1.forcetaxi.view;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import club.plus1.forcetaxi.R;
import club.plus1.forcetaxi.databinding.RecoveryEmailBinding;
import club.plus1.forcetaxi.viewmodel.RecoveryViewModel;

public class RecoveryEmailActivity extends AppCompatActivity {

    private RecoveryViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("Force", "RecoveryEmailActivity::onCreate()");
        super.onCreate(savedInstanceState);
        RecoveryEmailBinding binding = DataBindingUtil.setContentView(this, R.layout.recovery_email);
        viewModel = RecoveryViewModel.getInstance(this);
        binding.setViewModel(viewModel);
    }
}
