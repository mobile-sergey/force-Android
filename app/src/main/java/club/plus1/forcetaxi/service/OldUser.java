package club.plus1.forcetaxi.service;

import android.content.Context;
import android.content.SharedPreferences;

public class OldUser {

    // Названия хранимых настроек
    private static final String PREF = "settings";
    private static final String PREF_APP_TOKEN = "appToken";
    private static final String PREF_USER_TOKEN = "userToken";
    private static final String PREF_DEVICE_TOKEN = "deviceToken";
    private static final String PREF_PIN = "pin";

    // Статусы работы
    public Boolean isTighten;
    private String appToken;
    private String userToken;
    private String deviceToken;
    private String pin;

    // Заглушка с данными пользователя
    public String inn = "012345678943";
    public Boolean isInFns;
    public Boolean isForceAccepted;
    public Boolean isPinSet;
    // Хранимые настройки
    private SharedPreferences mSettings;
    public String oktmo = "46748000890";
    public String dateFNS = "01.07.2019";
    private String name = "Вазген";
    private String surname = "Иванов";
    private String patronymic = "Куйран Оглы";

    // Конструктор класса
    OldUser(Context context) {
        mSettings = context.getSharedPreferences(PREF, Context.MODE_PRIVATE);
        appToken = getStringPref(PREF_APP_TOKEN);
        userToken = getStringPref(PREF_USER_TOKEN);
        deviceToken = getStringPref(PREF_DEVICE_TOKEN);
        pin = getStringPref(PREF_PIN);
        isPinSet = (!pin.isEmpty());
    }

    // Получение значения хранимой настройки
    private String getStringPref(String name) {
        if (mSettings.contains(name)) {
            return mSettings.getString(name, "");
        } else {
            return "";
        }
    }

    // Сохранение хранимой настройки
    private void setStringPref(String name, String value) {
        SharedPreferences.Editor editor = mSettings.edit();
        editor.putString(name, value);
        editor.apply();
    }

    // Получение токена приложения
    public String getAppToken() {
        return appToken;
    }

    // Установка токена приложения в настройки
    void setAppToken(String appToken) {
        this.appToken = appToken;
        setStringPref(PREF_APP_TOKEN, appToken);
    }

    // Получение токена пользователя
    public String getUserToken() {
        return userToken;
    }

    // Сохранение токена пользователя в настройки
    void setUserToken(String userToken) {
        this.userToken = userToken;
        setStringPref(PREF_USER_TOKEN, userToken);
    }

    // Получение токена устройства
    public String getDeviceToken() {
        return deviceToken;
    }

    // Сохранение токна устройства в настройки
    void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
        setStringPref(PREF_DEVICE_TOKEN, deviceToken);
    }

    // Получение пинкода
    public String getPin() {
        return pin;
    }

    // Сохранение пинкода в настройки
    public void setPin(String pin) {
        this.pin = pin;
        setStringPref(PREF_PIN, pin);
    }

    // Получение Фамилии Имени Отчества
    public String getFio() {
        return surname + " " + name + " " + patronymic;
    }
}
