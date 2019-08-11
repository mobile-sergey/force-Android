package club.plus1.forcetaxi.model;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.provider.Settings;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import club.plus1.forcetaxi.R;
import club.plus1.forcetaxi.service.ActiveLog;

public class Server {

    // Основные поля класса
    private static Server mInstance;    // Единственный объект этого класса
    public User user;                   // Связанный с сервером пользователь

    // Различные параметры, которые должны храниться на сервере
    public String serviceType;
    public String client;
    public String urlCheck;
    public String checkNumber;
    public String[] history;
    public String[] transactions;
    public Drawable imgQR;

    // Параметры, возращаемые методами сервера
    private boolean ok;                 // Результат работы метода
    private ServerError error;          // Описание результата работы с кодом и текстом
    private Map<String, Object> args;   // Набор дополнительных параметров

    // Заглушка с данными сервера
    private static final String APP_TOKEN = "5aa27b1100fa7d9e369f5bc726b05b69";
    private static final String USER_TOKEN = "aec27f0f-b8a3-43cb-b076-e075a095abfe";
    public final String URL_APP = "link.to/app/";
    public final String URL_INFO = "https://npd.nalog.ru/#start";
    public final String URL_INSTRUCTIONS = "https://www.nalog.ru/rn77/fl/interest/inn/calculation/";

    // Заглушки для хранения локальных данных
    private Map<String, String> userpass = new HashMap<>();

    // Конструктор класса с заполнением заглушечных данных
    @SuppressLint("HardwareIds")
    private Server(Context context) {
        ActiveLog.getInstance().log();
        user = new User(context);
        user.setAppToken(APP_TOKEN);
        user.setUserToken(USER_TOKEN);
        user.setDeviceToken(Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID));
        user.isTighten = false;
        user.isInFns = false;
        user.isForceAccepted = false;
        try {
            this.setOk(true);
            this.setError(new ServerError(200, context.getString(R.string.text_server_success)));
            this.args = new HashMap<>();
        } catch (Exception e) {
            this.setOk(false);
            this.setError(new ServerError(500, context.getString(R.string.text_server_error, e.toString())));
            this.args = new HashMap<>();
        }
        String executor = user.getFio() + ", " + user.inn;
        urlCheck = "https://link.to/order/1234567";
        checkNumber = "1234567";
        client = "89212345678";
        serviceType = "Оказание услуг такси";
        history = new String[]{
                context.getString(R.string.check_text, serviceType, "1000", executor, "01.08.2019"),
                context.getString(R.string.check_text, serviceType, "100", executor, "02.08.2019"),
                context.getString(R.string.check_text, serviceType, "200", executor, "02.08.2019"),
                context.getString(R.string.check_text, serviceType, "555.55", executor, "03.08.2019"),
                context.getString(R.string.check_text, serviceType, "133.00", executor, "03.08.2019"),
        };
        transactions = new String[]{
                context.getString(R.string.text_transaction_status, "В обработке"),
                context.getString(R.string.text_transaction_status, "Отклонено"),
                context.getString(R.string.text_transaction_status, "Успешно"),
                context.getString(R.string.text_transaction_status, "Успешно"),
                context.getString(R.string.text_transaction_status, "Успешно"),
        };
        imgQR = context.getDrawable(R.drawable.qr);
    }

    // Получение единственного экземпляра класса
    public static Server getInstance(Context context) {
        ActiveLog.getInstance().log();
        if (mInstance == null) {
            mInstance = new Server(context);
        }
        return mInstance;
    }

    // Валидация почты – отправляется письмо с инструкциями.
    // Вызывается в экране «7. Восстановление пароля по e-mail» и результат отображается там же.
    public void sendMail(Context context, String email) {
        ActiveLog.getInstance().log();
        try {
            this.setOk(true);
            this.setError(new ServerError(200, context.getString(R.string.text_email_success, email)));
        } catch (Exception e) {
            this.setOk(false);
            this.setError(new ServerError(500, context.getString(R.string.text_email_error, email, e.toString())));
        }
        ActiveLog.getInstance().logError(ok, error);
    }

    // В методе производится проверка логина и зашифрованного пароля на сервере
    // Вызывается после нажатия кнопки «Войти» на экране «1. Вход»
    // Результат метода показывается на экране «2. Результат входа»
    public void login(Context context, String login, String password) {
        ActiveLog.getInstance().log();
        if (userpass.containsKey(login)) {
            if (userpass.get(login).equals(password)) {
                this.setOk(true);
                this.setError(new ServerError(200, context.getString(R.string.text_login_success, login)));
            } else {
                this.setOk(false);
                this.setError(new ServerError(500, context.getString(R.string.text_login_password_error, login)));
            }
        } else {
            this.setOk(false);
            this.setError(new ServerError(500, context.getString(R.string.text_login_missing_error, login)));
        }
        ActiveLog.getInstance().logError(ok, error);
    }

    // Метод вызывается в экране «3. Регистрация» при нажатии кнопки «Регистрация»
    // В методе производится попытка зарегистрировать пользователя приложения на сервере.
    // В случае успешного выполнения метода – вызывается отправляется СМС пользователю через POST sendSMS
    public void signUp(Context context, String phone, String email, String password) {
        ActiveLog.getInstance().log();
        if (userpass.containsKey(phone)) {
            this.setOk(false);
            this.setError(new ServerError(500, context.getString(R.string.text_signup_phone_error, phone)));
        } else if (userpass.containsKey(email)) {
            this.setOk(false);
            this.setError(new ServerError(500, context.getString(R.string.text_signup_email_error, email)));
        } else {
            userpass.put(phone, password);
            userpass.put(email, password);
            this.setOk(true);
            this.setError(new ServerError(200, context.getString(R.string.text_signup_success)));
        }
        ActiveLog.getInstance().logError(ok, error);
    }

    // В методе отправляется СМС по указанному номеру телефона.
    // Используется в экранах «4. Регистрация. Подтверждение телефона»
    // и «8. Восстановление пароля по телефону» для отправки кода подтверждения и в экране
    // «25. Пополнение баланса. Ссылка на Сбербанк» для отправки ссылки для оплаты на Сбербанк
    public void sendSMS(Context context, String phone) {
        ActiveLog.getInstance().log();
        try {
            this.setOk(true);
            this.setError(new ServerError(200, context.getString(R.string.text_sendsms_success, phone)));
        } catch (Exception e) {
            this.setOk(false);
            this.setError(new ServerError(500, context.getString(R.string.text_sendsms_error, phone, e.toString())));
        }
        ActiveLog.getInstance().logError(ok, error);
    }

    // В методе проверяется статус выполнения шагов и получается информация по пользователю.
    // Используется в экране «5. Регистрация завершена» и в экране «27. Профиль»
    public void getUser(Context context) {
        ActiveLog.getInstance().log();
        try {
            this.putArg("name", user.getFio());
            this.putArg("inn", user.inn);
            this.putArg("oktmo", user.oktmo);
            this.putArg("date", user.dateFNS);
            this.putArg("businessType", null);
            this.putArg("isTighten", user.isTighten);
            this.putArg("isInFns", user.isInFns);
            this.putArg("isForceAccepted", user.isForceAccepted);
            this.putArg("isPinSet", user.isPinSet);
            this.putArg("inFnsUrl", URL_INFO);
            this.putArg("forceAcceptUrl", URL_INSTRUCTIONS);
            this.putArg("shareUrl", URL_APP);
            this.setOk(true);
            this.setError(new ServerError(200, context.getString(R.string.text_getuser_success)));
        } catch (Exception e) {
            this.setOk(false);
            this.setError(new ServerError(500, context.getString(R.string.text_getuser_error, e.toString())));
        }
        ActiveLog.getInstance().logError(ok, error);
    }

    // Метод сохраняет ИНН введенный пользователем на сервере
    // Вызывается в экране «11. Указание ИНН» при нажатии на кнопку «Продолжить»
    // и результаты выполнения метода (найден ли ИНН, привязан ли и т. д.) отображаются
    // на экране «12. Указание ИНН. Результат»
    public void setINN(Context context, String inn) {
        ActiveLog.getInstance().log();
        try {
            this.putArg("inn", "1234567890");
            this.setOk(true);
            this.setError(new ServerError(200, context.getString(R.string.text_setinn_success, inn)));
        } catch (Exception e) {
            this.setOk(false);
            this.setError(new ServerError(500, context.getString(R.string.text_setinn_error, inn, e.toString())));
        }
        ActiveLog.getInstance().logError(ok, error);
    }

    // Метод привязывает учет доходов и выписку чеков к указанному ИНН.
    // Метод вызывается в экране «16. Привязка ИНН» при нажатии на кнопку «Привязать».
    // Далее результат привязки отображается на экране «17. Привязка ИНН. Результат»
    public void tightenIncome(Context context, String inn) {
        ActiveLog.getInstance().log();
        user.isTighten = true;
        try {
            this.setOk(true);
            this.setError(new ServerError(200, context.getString(R.string.text_tightenincome_success, inn)));
        } catch (Exception e) {
            this.setOk(false);
            this.setError(new ServerError(500, context.getString(R.string.text_tightenincome_error, inn, e.toString())));
        }
        ActiveLog.getInstance().logError(ok, error);
    }

    // Метод ищет ИНН в ФНС по переданным параметрам.
    // Метод вызывается при нажатии кнопки «Поиск в ФНС» в экране «13. Поиск ИНН»
    // и результат поиска отображается на экране «14. Поиск ИНН. Результат»
    public void searchINN(Context context, String phone,
                          String surname, String name, String patronymic,
                          String docSeries, String docNumber) {
        ActiveLog.getInstance().log();
        try {
            this.setOk(true);
            this.setError(new ServerError(200, context.getString(R.string.text_searchinn_success)));
        } catch (Exception e) {
            this.setOk(false);
            this.setError(new ServerError(500, context.getString(R.string.text_searchinn_error, e.toString())));
        }
        ActiveLog.getInstance().logError(ok, error);
    }

    // В методе запускается процесс восстановления пароля в зависимости от того, что введено:
    // телефон или электронная почта.
    // Если электронная почта – то на неё отправляется письмо.
    // Если телефон – то на него отправляется код.
    // Метод вызывается при нажатии кнопки «Восстановить» на экране «6. Восстановление пароля»,
    // а результат выполнения показывается на экране «7. Восстановление пароля по e-mail»
    public void reserPassword(Context context, String login, LoginType loginType) {
        ActiveLog.getInstance().log();
        try {
            this.setOk(true);
            this.setError(new ServerError(200, context.getString(R.string.text_resetpassword_success)));
        } catch (Exception e) {
            this.setOk(false);
            this.setError(new ServerError(500, context.getString(R.string.text_resetpassword_error, e.toString())));
        }
        ActiveLog.getInstance().logError(ok, error);
    }

    // Метод изменяет пароль на сервере
    // Метод называется в экране «9. Смена пароля» при нажатии на кнопку «Сменить пароль».
    // Результат метода показывается на экране «10. Смена пароля. Результат»
    public void setPassword(Context context, String login, String newPassword) {
        ActiveLog.getInstance().log();
        userpass.put(login, newPassword);
        try {
            this.setOk(true);
            this.setError(new ServerError(200, context.getString(R.string.text_setpassword_success)));
        } catch (Exception e) {
            this.setOk(false);
            this.setError(new ServerError(500, context.getString(R.string.text_setpassword_error, e.toString())));
        }
        ActiveLog.getInstance().logError(ok, error);
    }

    // В методе проверяется введенный код с кодом, отправленным по СМС.
    // Метод вызывается на экранах «4. Регистрация. Подтверждение телефона»
    // и «8. Восстановление пароля по телефону» при нажатии на кнопку «Подтвердить»
    public void acceptResetPass(Context context, String code, String newPassword) {
        ActiveLog.getInstance().log();
        try {
            this.setOk(true);
            this.setError(new ServerError(200, context.getString(R.string.text_acceptresetpass_success)));
        } catch (Exception e) {
            this.setOk(false);
            this.setError(new ServerError(500, context.getString(R.string.text_acceptresetpass_error, e.toString())));
        }
        ActiveLog.getInstance().logError(ok, error);
    }

    // Метод получает всю необходимую информацию для печати виртуального чека
    // Метод вызывается в экране «18. Выбивание чека» при нажатии на кнопку печати чека,
    // далее полученные данные показываются прямо на виртуальном чеке.
    public void generateCheck(Context context, String amount, String client) {
        ActiveLog.getInstance().log();
        try {
            this.putArg("checkNumber", checkNumber);
            this.putArg("serviceType", serviceType);
            this.putArg("amount", amount);
            this.putArg("executor", user.getFio() + ", " + user.inn);
            this.putArg("date", new SimpleDateFormat("dd.MM.yyyy", Locale.ROOT).format(Calendar.getInstance().getTime()));
            this.putArg("url", urlCheck);
            this.setOk(true);
            this.setError(new ServerError(200, context.getString(R.string.text_check_success, amount)));
        } catch (Exception e) {
            this.setOk(false);
            this.setError(new ServerError(500, context.getString(R.string.text_check_error, e.toString())));
        }
        ActiveLog.getInstance().logError(ok, error);
    }

    // Метод получает список всех чеков или порцию чеков с … по …
    // Метод вызывается при открытии и проматывании экранов «18. История чеков» и «22. Баланс»
    public void getCheckHistory(Context context, int startPosition, int endPosition) {
        ActiveLog.getInstance().log();
        try {
            this.setOk(true);
            this.setError(new ServerError(200, context.getString(R.string.text_check_history_success)));
        } catch (Exception e) {
            this.setOk(false);
            this.setError(new ServerError(500, context.getString(R.string.text_check_history_error, e.toString())));
        }
        ActiveLog.getInstance().logError(ok, error);
    }

    // Отменяется выбранный чек
    // Метод вызывается из экрана «20. Сторнирование чека» при нажатии кнопки «Сторнировать чек».
    // Результат метода показывается на экране «21. Сторнирование чека. Результат»
    public void deleteCheck(Context context, String checkNumber, String reason) {
        ActiveLog.getInstance().log();
        try {
            this.setOk(true);
            this.setError(new ServerError(200, context.getString(R.string.text_storno_success, checkNumber)));
        } catch (Exception e) {
            this.setOk(false);
            this.setError(new ServerError(500, context.getString(R.string.text_storno_error, checkNumber, e.toString())));
        }
        ActiveLog.getInstance().logError(ok, error);
    }

    // Получается баланс пользователя
    public void balance(Context context) {
        ActiveLog.getInstance().log();
        try {
            this.putArg("balance", "1000");
            this.setOk(true);
            this.setError(new ServerError(200, context.getString(R.string.text_balance_success)));
        } catch (Exception e) {
            this.setOk(false);
            this.setError(new ServerError(500, context.getString(R.string.text_balance_error, e.toString())));
        }
        ActiveLog.getInstance().logError(ok, error);
    }

    // Асинхронное пополнение баланса
    // Метод вызывается в экране «23. Пополнение баланса»
    public void refillBalance(Context context, String amount, String from) {
        ActiveLog.getInstance().log();
        try {
            this.putArg("status", "В обработке");
            this.setOk(true);
            this.setError(new ServerError(200, context.getString(R.string.text_refill_balance_success, amount)));
        } catch (Exception e) {
            this.setOk(false);
            this.setError(new ServerError(500, context.getString(R.string.text_refill_balance_error, from, e.toString())));
        }
        ActiveLog.getInstance().logError(ok, error);
    }

    // Получение результата вызова последнего метода
    public boolean isOk() {
        return ok;
    }

    // Установка результата вызова последнего метода
    private void setOk(boolean ok) {
        this.ok = ok;
    }

    // Получение описания результата вызова последнего метода
    public ServerError getError() {
        return error;
    }

    // Установка описания результата вызова последнего метода
    private void setError(ServerError error) {
        this.error = error;
    }

    // Получение дополнительных параметров
    public Map<String, Object> getArgs() {
        return args;
    }

    // Загрузка всех дополнительных параметров
    public void setArgs(Map<String, Object> args) {
        this.args = args;
    }

    // Установка конкретного дополнительного параметра
    private void putArg(String key, Object object) {
        this.args.put(key, object);
    }

    // Получение конкретного дополнительного параметра
    public Object getArg(String key) {
        return args.get(key);
    }
}
