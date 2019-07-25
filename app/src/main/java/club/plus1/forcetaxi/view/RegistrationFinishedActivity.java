package club.plus1.forcetaxi.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import club.plus1.forcetaxi.R;
import club.plus1.forcetaxi.databinding.RegistrationFinishedBinding;
import club.plus1.forcetaxi.viewmodel.RegistrationViewModel;

public class RegistrationFinishedActivity extends AppCompatActivity {

    private RegistrationViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new RegistrationViewModel();
        viewModel.setSrcTighten("yes");
        viewModel.setSrcInFns("no");
        viewModel.setSrcForceAccepted("wait");
        viewModel.setSrcPinSet("no");
        RegistrationFinishedBinding binding = DataBindingUtil.setContentView(this, R.layout.registration_finished);
        binding.setRegistration(viewModel);
    }
}
