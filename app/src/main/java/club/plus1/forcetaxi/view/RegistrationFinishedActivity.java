package club.plus1.forcetaxi.view;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import club.plus1.forcetaxi.R;
import club.plus1.forcetaxi.databinding.RegistrationFinishedBinding;
import club.plus1.forcetaxi.viewmodel.RegistrationViewModel;

public class RegistrationFinishedActivity extends AppCompatActivity {

    private RegistrationViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("Force", "RegistrationFinishedActivity::onCreate()");
        super.onCreate(savedInstanceState);
        viewModel = RegistrationViewModel.getInstance(this);
        viewModel.setIsTighten(true);
        viewModel.setIsInFns(false);
        viewModel.setIsForceAccepted(null);
        viewModel.setIsPinSet(false);
        RegistrationFinishedBinding binding = DataBindingUtil.setContentView(this, R.layout.registration_finished);
        binding.setViewModel(viewModel);
    }
}
