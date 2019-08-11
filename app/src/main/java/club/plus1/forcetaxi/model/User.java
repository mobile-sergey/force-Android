package club.plus1.forcetaxi.model;

import android.content.Context;
import android.content.SharedPreferences;

public class User {
    private static final String PREF = "settings";
    private static final String PREF_APP_TOKEN = "appToken";
    private static final String PREF_USER_TOKEN = "userToken";
    private static final String PREF_DEVICE_TOKEN = "deviceToken";
    private static final String PREF_PIN = "pin";

    private SharedPreferences mSettings;
    private String appToken;
    private String userToken;
    private String deviceToken;
    private String pin;

    public Boolean isTighten;
    public Boolean isInFns;
    public Boolean isForceAccepted;
    public Boolean isPinSet;
    public String inn = "012345678943";
    public String oktmo = "46748000890";
    public String dateFNS = "01.07.2019";
    private String name = "Вазген";
    private String surname = "Иванов";
    private String patronymic = "Куйран Оглы";


    User(Context context) {
        mSettings = context.getSharedPreferences(PREF, Context.MODE_PRIVATE);
        appToken = getStringPref(PREF_APP_TOKEN);
        userToken = getStringPref(PREF_USER_TOKEN);
        deviceToken = getStringPref(PREF_DEVICE_TOKEN);
        pin = getStringPref(PREF_PIN);
        isPinSet = (!pin.isEmpty());
    }

    private String getStringPref(String name) {
        if (mSettings.contains(name)) {
            return mSettings.getString(name, "");
        } else {
            return "";
        }
    }

    private void setStringPref(String name, String value) {
        SharedPreferences.Editor editor = mSettings.edit();
        editor.putString(name, value);
        editor.apply();
    }

    public String getAppToken() {
        return appToken;
    }

    void setAppToken(String appToken) {
        this.appToken = appToken;
        setStringPref(PREF_APP_TOKEN, appToken);
    }

    public String getUserToken() {
        return userToken;
    }

    void setUserToken(String userToken) {
        this.userToken = userToken;
        setStringPref(PREF_USER_TOKEN, userToken);
    }

    public String getDeviceToken() {
        return deviceToken;
    }

    void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
        setStringPref(PREF_DEVICE_TOKEN, deviceToken);
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
        setStringPref(PREF_PIN, pin);
    }

    public String getFio() {
        return surname + " " + name + " " + patronymic;
    }
}
