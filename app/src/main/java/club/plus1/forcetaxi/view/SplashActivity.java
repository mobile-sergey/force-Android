package club.plus1.forcetaxi.view;

import android.app.Activity;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;

import club.plus1.forcetaxi.R;
import club.plus1.forcetaxi.databinding.SplashBinding;
import club.plus1.forcetaxi.viewmodel.SplashViewModel;

public class SplashActivity extends Activity {

    private SplashViewModel viewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SplashBinding binding = DataBindingUtil.setContentView(this, R.layout.splash);
        viewModel = new SplashViewModel(this);
        viewModel.startEnterActivity(this);
        binding.setSplash(viewModel);
    }

    @Override
    protected void onResume() {
        super.onResume();
        viewModel.onResume(this);
    }
}