package club.plus1.forcetaxi.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import club.plus1.forcetaxi.R;
import club.plus1.forcetaxi.databinding.RegistrationRecoveryPhoneBinding;
import club.plus1.forcetaxi.service.ActiveLog;
import club.plus1.forcetaxi.viewmodel.RegistrationViewModel;

public class RegistrationRecoveryPhoneActivity extends AppCompatActivity {

    private RegistrationViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActiveLog.getInstance().log();
        super.onCreate(savedInstanceState);
        RegistrationRecoveryPhoneBinding binding = DataBindingUtil.setContentView(
                this, R.layout.registration_recovery_phone);
        viewModel = RegistrationViewModel.getInstance(this);
        binding.setViewModel(viewModel);
    }
}
