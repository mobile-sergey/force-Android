package club.plus1.forcetaxi.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.databinding.DataBindingUtil;

import club.plus1.forcetaxi.R;
import club.plus1.forcetaxi.databinding.SplashBinding;
import club.plus1.forcetaxi.viewmodel.SplashViewModel;

public class SplashActivity extends Activity {

    // Время в милесекундах, в течение которого будет отображаться Splash Screen
    private final int SPLASH_DISPLAY_LENGTH = 1000;

    private SplashViewModel viewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Создаём ViewModel и показываем экран исползуя биндинги
        viewModel = new SplashViewModel(this);
        SplashBinding binding = DataBindingUtil.setContentView(this, R.layout.splash);
        binding.setSplash(viewModel);

        // Запуск экрана "1.Вход" параллельно через некоторое время после завершения загрузки приложения
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // По истечении времени, запускаем главный активити, а Splash Screen закрываем
                Intent mainIntent = new Intent(SplashActivity.this, EnterActivity.class);
                SplashActivity.this.startActivity(mainIntent);
                SplashActivity.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }

    @Override
    protected void onResume() {
        super.onResume();
        viewModel.onResume(this);
    }
}