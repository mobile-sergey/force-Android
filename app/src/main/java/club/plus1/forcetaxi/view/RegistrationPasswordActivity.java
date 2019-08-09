package club.plus1.forcetaxi.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import club.plus1.forcetaxi.R;
import club.plus1.forcetaxi.databinding.RegistrationPasswordBinding;
import club.plus1.forcetaxi.service.ActiveLog;
import club.plus1.forcetaxi.viewmodel.RegistrationViewModel;

public class RegistrationPasswordActivity extends AppCompatActivity {

    private RegistrationViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActiveLog.getInstance().log();
        super.onCreate(savedInstanceState);
        RegistrationPasswordBinding binding = DataBindingUtil.setContentView(
                this, R.layout.registration_password);
        viewModel = RegistrationViewModel.getInstance(this);
        binding.setViewModel(viewModel);
    }
}
