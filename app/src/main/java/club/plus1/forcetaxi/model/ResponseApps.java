package club.plus1.forcetaxi.model;

import org.jetbrains.annotations.NotNull;

public interface ResponseApps {

    // Параметры, возращаемые методами сервера
    boolean ok = false;          // Результат работы метода
    ServerError error = null;    // Описание результата работы с кодом и текстом

    /**
     * Возвращает текст ошибки, если она есть
     *
     * @return String - текст ошибки, если она есть
     */
    @NotNull
    String getErrorText();

    /**
     * Метод регистрации установки приложения
     *
     * @param appId             - айди разработчика
     * @param installationToken - токен разработчика приложения
     * @param description       - коментарий к устанвке, виден только разработчику
     * @return app_token - токен приложения, необходимо передавать в каждом методе сервера
     */
    String registrationInstallation(String appId, String installationToken, String description);
}
