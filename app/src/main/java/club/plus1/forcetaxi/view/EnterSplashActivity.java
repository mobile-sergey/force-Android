package club.plus1.forcetaxi.view;

import android.app.Activity;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;

import club.plus1.forcetaxi.R;
import club.plus1.forcetaxi.databinding.EnterSplashBinding;
import club.plus1.forcetaxi.service.ActiveLog;
import club.plus1.forcetaxi.viewmodel.EnterViewModel;

public class EnterSplashActivity extends Activity {

    private EnterViewModel viewModel;
    private EnterSplashBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        ActiveLog.getInstance().log();
        super.onCreate(savedInstanceState);
        viewModel = EnterViewModel.getInstance(this);
        binding = DataBindingUtil.setContentView(this, R.layout.enter_splash);
        binding.setViewModel(viewModel);
    }
}