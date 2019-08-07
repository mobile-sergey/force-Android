package club.plus1.forcetaxi.view;

import android.app.Activity;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;

import club.plus1.forcetaxi.R;
import club.plus1.forcetaxi.databinding.SplashBinding;
import club.plus1.forcetaxi.model.ActiveLog;
import club.plus1.forcetaxi.viewmodel.SplashViewModel;

public class SplashActivity extends Activity {

    private SplashViewModel viewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        ActiveLog.getInstance().log();
        super.onCreate(savedInstanceState);
        SplashBinding binding = DataBindingUtil.setContentView(this, R.layout.splash);
        viewModel = SplashViewModel.getInstance(this);
        binding.setViewModel(viewModel);
    }
}