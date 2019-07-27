package club.plus1.forcetaxi.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import club.plus1.forcetaxi.R;
import club.plus1.forcetaxi.databinding.RegistrationVerificationBinding;
import club.plus1.forcetaxi.viewmodel.RegistrationViewModel;

public class RegistrationVerificationActivity extends AppCompatActivity {

    private RegistrationViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new RegistrationViewModel();
        RegistrationVerificationBinding binding = DataBindingUtil.setContentView(this, R.layout.registration_verification);
        binding.setViewModel(viewModel);
    }
}
