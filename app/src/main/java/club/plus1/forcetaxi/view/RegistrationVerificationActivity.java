package club.plus1.forcetaxi.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import club.plus1.forcetaxi.R;
import club.plus1.forcetaxi.databinding.RegistrationVerificationBinding;
import club.plus1.forcetaxi.model.ActiveLog;
import club.plus1.forcetaxi.viewmodel.RegistrationViewModel;

public class RegistrationVerificationActivity extends AppCompatActivity {

    private RegistrationViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActiveLog.getInstance().log();
        super.onCreate(savedInstanceState);
        viewModel = RegistrationViewModel.getInstance(this);
        RegistrationVerificationBinding binding = DataBindingUtil.setContentView(this, R.layout.registration_verification);
        binding.setViewModel(viewModel);
    }
}
