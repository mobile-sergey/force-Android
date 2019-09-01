package club.plus1.forcetaxi.model;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class LocalSettings {

    // Названия хранимых настроек
    private static final String PREF_PIN = "pin";
    private static final String PREF_IN_FNS = "inFns";
    private static final String PREF_FORCE_ACCEPTED = "forceAccepted";

    // Хранимые настройки
    private SharedPreferences mSettings;
    private String pin;
    private Boolean inFns;
    private Boolean forceAccepted;

    public LocalSettings() {
        mSettings = PreferenceManager.getDefaultSharedPreferences(ApplicationModel.getAppContext());
        pin = getStringPref(PREF_PIN);
        inFns = getBoolPref(PREF_IN_FNS);
        forceAccepted = getBoolPref(PREF_FORCE_ACCEPTED);
    }

    // Получение значения хранимой булевской настройки
    private Boolean getBoolPref(String name) {
        if (mSettings == null || !mSettings.contains(name)) {
            return false;
        } else {
            return mSettings.getBoolean(name, false);
        }
    }

    // Сохранение хранимой булевской настройки
    private void setBoolPref(String name, Boolean value) {
        SharedPreferences.Editor editor = mSettings.edit();
        editor.putBoolean(name, value);
        editor.apply();
    }

    // Получение значения хранимой строковой настройки
    private String getStringPref(String name) {
        if (mSettings == null || !mSettings.contains(name)) {
            return "";
        } else {
            return mSettings.getString(name, "");
        }
    }

    // Сохранение хранимой строковой настройки
    private void setStringPref(String name, String value) {
        SharedPreferences.Editor editor = mSettings.edit();
        editor.putString(name, value);
        editor.apply();
    }

    // Получение пинкода
    public String getPin() {
        return pin;
    }

    // Сохранение пинкода в настройки
    public void setPin(String pin) {
        this.pin = pin;
        // TODO: Закомментировано для успешного тестирования. Надо найти обход.
        //setStringPref(PREF_PIN, pin);
    }

    public boolean isPinSet() {
        return !pin.isEmpty();
    }

    public boolean isInFns() {
        return inFns;
    }

    public void setInFns(boolean inFns) {
        this.inFns = inFns;
        // TODO: Закомментировано для успешного тестирования. Надо найти обход.
        //setBoolPref(PREF_IN_FNS, inFns);
    }

    public boolean isForceAccepted() {
        return forceAccepted;
    }

    public void setForceAccepted(boolean forceAccepted) {
        this.forceAccepted = forceAccepted;
        // TODO: Закомментировано для успешного тестирования. Надо найти обход.
        //setBoolPref(PREF_FORCE_ACCEPTED, forceAccepted);
    }
}
