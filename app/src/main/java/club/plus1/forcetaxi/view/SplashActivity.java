package club.plus1.forcetaxi.view;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import androidx.databinding.DataBindingUtil;

import club.plus1.forcetaxi.R;
import club.plus1.forcetaxi.databinding.SplashBinding;
import club.plus1.forcetaxi.viewmodel.SplashViewModel;

public class SplashActivity extends Activity {

    private SplashViewModel viewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d("Force", "SplashActivity::onCreate()");
        super.onCreate(savedInstanceState);
        SplashBinding binding = DataBindingUtil.setContentView(this, R.layout.splash);
        viewModel = SplashViewModel.getInstance(this);
        viewModel.startEnterActivity(this);
        binding.setViewModel(viewModel);
    }

    @Override
    protected void onResume() {
        Log.d("Force", "SplashActivity::onResume()");
        super.onResume();
        viewModel.onResume(this);
    }
}