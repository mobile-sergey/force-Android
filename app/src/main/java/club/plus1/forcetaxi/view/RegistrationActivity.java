package club.plus1.forcetaxi.view;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import club.plus1.forcetaxi.R;
import club.plus1.forcetaxi.databinding.RegistrationBinding;
import club.plus1.forcetaxi.viewmodel.RegistrationViewModel;

public class RegistrationActivity extends AppCompatActivity {

    private RegistrationViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("Force", "RegistrationActivity::onCreate()");
        super.onCreate(savedInstanceState);
        RegistrationBinding binding = DataBindingUtil.setContentView(this, R.layout.registration);
        viewModel = RegistrationViewModel.getInstance(this);
        binding.setViewModel(viewModel);
    }
}
