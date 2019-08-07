package club.plus1.forcetaxi.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import club.plus1.forcetaxi.R;
import club.plus1.forcetaxi.databinding.PasswordResultBinding;
import club.plus1.forcetaxi.model.ActiveLog;
import club.plus1.forcetaxi.viewmodel.PasswordViewModel;

public class PasswordResultActivity extends AppCompatActivity {

    private PasswordViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActiveLog.getInstance().log();
        super.onCreate(savedInstanceState);
        PasswordResultBinding binding = DataBindingUtil.setContentView(this, R.layout.password_result);
        viewModel = PasswordViewModel.getInstance(this);
        binding.setViewModel(viewModel);
    }
}
