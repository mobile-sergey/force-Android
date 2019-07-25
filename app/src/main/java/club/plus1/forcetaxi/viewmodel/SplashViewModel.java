package club.plus1.forcetaxi.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Handler;

import club.plus1.forcetaxi.view.EnterActivity;

public class SplashViewModel {
    public String versionName;
    public int versionCode;
    private final int SPLASH_TIME = 1000;

    public SplashViewModel(Context context) {
        setVersion(context);
    }

    public void onResume(Context context) {
        setVersion(context);
    }

    // Получаем информацию о пакете из которого получим версию и номер сборки
    // Если не удалось обратиться к пакету - создаём каркас с необходимыми нам полями
    public void setVersion(Context context) {
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
        this.versionName = pInfo.versionName;
        this.versionCode = pInfo.versionCode;
    }

    // Запуск экрана "1.Вход" параллельно через некоторое время после завершения загрузки приложения
    public void startEnterActivity(final Context context) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // По истечении времени, запускаем главный активити, а Splash Screen закрываем
                Intent mainIntent = new Intent(context, EnterActivity.class);
                context.startActivity(mainIntent);
            }
        }, SPLASH_TIME);
    }
}
