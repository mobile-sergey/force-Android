package club.plus1.forcetaxi.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import club.plus1.forcetaxi.R;
import club.plus1.forcetaxi.databinding.RegistrationRecoveryBinding;
import club.plus1.forcetaxi.service.ActiveLog;
import club.plus1.forcetaxi.viewmodel.RegistrationViewModel;

public class RegistrationRecoveryActivity extends AppCompatActivity {

    private RegistrationViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActiveLog.getInstance().log();
        super.onCreate(savedInstanceState);
        RegistrationRecoveryBinding binding = DataBindingUtil.setContentView(
                this, R.layout.registration_recovery);
        viewModel = RegistrationViewModel.getInstance(this);
        binding.setViewModel(viewModel);

        Intent intent = this.getIntent();
        viewModel.login.set(intent.getStringExtra("login"));
    }
}
