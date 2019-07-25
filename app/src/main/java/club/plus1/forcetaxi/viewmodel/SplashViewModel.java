package club.plus1.forcetaxi.viewmodel;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

public class SplashViewModel {
    public String versionName;
    public int versionCode;

    public SplashViewModel(Context context) {
        setVersion(context);
    }

    public void onResume(Context context) {
        setVersion(context);
    }

    public void setVersion(Context context) {
        // Получаем информацию о пакете из которого получим версию и номер сборки
        PackageInfo pInfo = null;
        try {
            pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        // Если не удалось обратиться к пакету - создаём каркас с необходимыми нам полями
        if (pInfo == null) {
            pInfo = new PackageInfo();
            pInfo.versionName = "0.0";
            pInfo.versionCode = 0;
        }

        this.versionName = pInfo.versionName;
        this.versionCode = pInfo.versionCode;
    }
}
