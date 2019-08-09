package club.plus1.forcetaxi.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Handler;

import androidx.databinding.BaseObservable;
import androidx.databinding.ObservableField;

import club.plus1.forcetaxi.model.Server;
import club.plus1.forcetaxi.service.ActiveLog;
import club.plus1.forcetaxi.view.EnterActivity;
import club.plus1.forcetaxi.view.EnterPinActivity;

public class SplashViewModel extends BaseObservable {

    private static SplashViewModel mInstance;   // Единственный экземпляр класса
    // Текущая версия приложения собранная из VersionName и VersionCode
    public ObservableField<String> version = new ObservableField<>();
    private Server server;                      // Связь с моделью

    // Конструктор класса
    private SplashViewModel(Context context) {
        ActiveLog.getInstance().log();
        server = Server.getInstance(context);
        version.set(getVersion(context));
        startEnterActivity(context);
    }

    // Получение единственного экземпляра класса
    public static SplashViewModel getInstance(Context context) {
        ActiveLog.getInstance().log();
        if (mInstance == null) {
            mInstance = new SplashViewModel(context);
        }
        return mInstance;
    }

    // Получение номера версии из настроек проекта
    private String getVersion(Context context) {
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
        return pInfo.versionName + "." + pInfo.versionCode;
    }

    // "0.Заставка" -> "1.Вход" или "0.Заставка" -> "33.Ввод ПИН" - в зависимости от наличия пинкода
    // Выполняется после завершения загрузки приложения
    private void startEnterActivity(final Context context) {
        ActiveLog.getInstance().log();
        final int SPLASH_TIME = 1000;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // По истечении времени, запускаем главный активити или экран ввода пинкода
                Intent mainIntent;
                if (server.user.getPin().isEmpty()) {
                    mainIntent = new Intent(context, EnterActivity.class);
                } else {
                    mainIntent = new Intent(context, EnterPinActivity.class);
                }
                context.startActivity(mainIntent);
            }
        }, SPLASH_TIME);
    }
}
