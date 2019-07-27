package club.plus1.forcetaxi.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.util.Log;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import club.plus1.forcetaxi.BR;
import club.plus1.forcetaxi.view.EnterActivity;

public class SplashViewModel extends BaseObservable {

    private static SplashViewModel mInstance;
    private final int SPLASH_TIME = 1000;
    private String versionName;
    private int versionCode;

    private SplashViewModel(Context context) {
        Log.d("Force", "SplashViewModel::SplashViewModel()");
        setVersion(context);
    }

    public static SplashViewModel getInstance(Context context) {
        Log.d("Force", "SplashViewModel::getInstance()");
        if (mInstance == null) {
            mInstance = new SplashViewModel(context);
        }
        return mInstance;
    }

    public void onResume(Context context) {
        Log.d("Force", "SplashViewModel::onResume()");
        setVersion(context);
    }

    // Получаем информацию о пакете из которого получим версию и номер сборки
    // Если не удалось обратиться к пакету - создаём каркас с необходимыми нам полями
    public void setVersion(Context context) {
        Log.d("Force", "SplashViewModel::setVersion()");
        PackageInfo pInfo = null;
        try {
            pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        if (pInfo == null) {
            pInfo = new PackageInfo();
            pInfo.versionName = "0.0";
            pInfo.versionCode = 0;
        }
        this.setVersionName(pInfo.versionName);
        this.setVersionCode(pInfo.versionCode);
    }

    // Запуск экрана "1.Вход" параллельно через некоторое время после завершения загрузки приложения
    public void startEnterActivity(final Context context) {
        Log.d("Force", "SplashViewModel::startEnterActivity()");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // По истечении времени, запускаем главный активити, а Splash Screen закрываем
                Intent mainIntent = new Intent(context, EnterActivity.class);
                context.startActivity(mainIntent);
            }
        }, SPLASH_TIME);
    }

    @Bindable
    public String getVersionName() {
        return versionName;
    }

    private void setVersionName(String versionName) {
        this.versionName = versionName;
        notifyPropertyChanged(BR.versionName);
    }

    @Bindable
    public int getVersionCode() {
        return versionCode;
    }

    private void setVersionCode(int versionCode) {
        this.versionCode = versionCode;
        notifyPropertyChanged(BR.versionCode);
    }
}
