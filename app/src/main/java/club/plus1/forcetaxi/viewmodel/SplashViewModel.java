package club.plus1.forcetaxi.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Handler;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import club.plus1.forcetaxi.BR;
import club.plus1.forcetaxi.model.ActiveLog;
import club.plus1.forcetaxi.model.Server;
import club.plus1.forcetaxi.view.EnterActivity;

public class SplashViewModel extends BaseObservable {

    private static SplashViewModel mInstance;
    private final int SPLASH_TIME = 1000;
    private String versionName;
    private int versionCode;

    private SplashViewModel(Context context) {
        ActiveLog.getInstance().log();
        // Получаем информацию о пакете из которого получим версию и номер сборки
        // Если не удалось обратиться к пакету - создаём каркас с необходимыми нам полями
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
        setVersionName(pInfo.versionName);
        setVersionCode(pInfo.versionCode);
        startEnterActivity(context);
    }

    public static SplashViewModel getInstance(Context context) {
        ActiveLog.getInstance().log();
        if (mInstance == null) {
            mInstance = new SplashViewModel(context);
        }
        return mInstance;
    }

    // Запуск экрана "1.Вход" или "33.Ввод ПИН" после завершения загрузки приложения
    private void startEnterActivity(final Context context) {
        ActiveLog.getInstance().log();

        // "Отправляем на сервер запрос GET /
        final Server server = Server.getInstance(context);
        //Toast.makeText(context, server.getError().getText(), Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // По истечении времени, запускаем главный активити или экран ввода пинкода
                //Intent mainIntent = null;
                //if (server.getArgs().get("isPinSet").equals(true)) {
                Intent mainIntent = new Intent(context, EnterActivity.class);
                //} else {
                //    mainIntent = new Intent(context, PinEnterActivity.class);
                //}
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
