package club.plus1.forcetaxi.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import club.plus1.forcetaxi.R;
import club.plus1.forcetaxi.databinding.RegistrationPasswordResultBinding;
import club.plus1.forcetaxi.service.ActiveLog;
import club.plus1.forcetaxi.viewmodel.RegistrationViewModel;

public class RegistrationPasswordResultActivity extends AppCompatActivity {

    private RegistrationViewModel viewModel;
    private RegistrationPasswordResultBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActiveLog.getInstance().log();
        super.onCreate(savedInstanceState);
        viewModel = RegistrationViewModel.getInstance(this);
        binding = DataBindingUtil.setContentView(this, R.layout.registration_password_result);
        binding.setViewModel(viewModel);
    }
}
