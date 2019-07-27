package club.plus1.forcetaxi.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import club.plus1.forcetaxi.R;
import club.plus1.forcetaxi.databinding.RegistrationBinding;
import club.plus1.forcetaxi.viewmodel.RegistrationViewModel;

public class RegistrationActivity extends AppCompatActivity {

    private RegistrationViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RegistrationBinding binding = DataBindingUtil.setContentView(this, R.layout.registration);
        viewModel = new RegistrationViewModel();
        binding.setViewModel(viewModel);
    }
}
