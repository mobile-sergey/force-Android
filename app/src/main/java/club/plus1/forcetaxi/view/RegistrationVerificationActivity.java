package club.plus1.forcetaxi.view;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import club.plus1.forcetaxi.R;
import club.plus1.forcetaxi.databinding.RegistrationVerificationBinding;
import club.plus1.forcetaxi.viewmodel.RegistrationViewModel;

public class RegistrationVerificationActivity extends AppCompatActivity {

    private RegistrationViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("Force", "RegistrationVerificationActivity::onCreate()");
        super.onCreate(savedInstanceState);
        viewModel = RegistrationViewModel.getInstance(this);
        RegistrationVerificationBinding binding = DataBindingUtil.setContentView(this, R.layout.registration_verification);
        binding.setViewModel(viewModel);
    }
}
