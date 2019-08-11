package club.plus1.forcetaxi.model;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.provider.Settings;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import club.plus1.forcetaxi.R;
import club.plus1.forcetaxi.service.ActiveLog;

public class Server {

    private static Server mInstance;

    private boolean ok;
    private ServerError error;
    private Map<String, Object> args;

    // Токены должны получаться с сервера, пока случайные строки
    private static final String APP_TOKEN = "5aa27b1100fa7d9e369f5bc726b05b69";
    private static final String USER_TOKEN = "aec27f0f-b8a3-43cb-b076-e075a095abfe";

    public final String URL_APP = "link.to/app/";
    public final String URL_INFO = "https://npd.nalog.ru/#start";
    public final String URL_INSTRUCTIONS = "https://www.nalog.ru/rn77/fl/interest/inn/calculation/";

    public Drawable imgQR;
    public String serviceType;
    public String[] history;
    public String[] transactions;
    public String client;
    public String urlCheck;
    public String checkNumber;

    public User user;
    private ActiveLog log;

    @SuppressLint("HardwareIds")
    private Server(Context context) {
        ActiveLog.getInstance().log();
        user = new User(context);
        user.setAppToken(APP_TOKEN);
        user.setUserToken(USER_TOKEN);
        user.setDeviceToken(Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID));
        user.isTighten = false;
        user.isInFns = true;
        user.isForceAccepted = true;
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

    public static Server getInstance(Context context) {
        ActiveLog.getInstance().log();
        if (mInstance == null) {
            mInstance = new Server(context);
        }
        return mInstance;
    }

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

    public void login(Context context, String login, String password) {
        ActiveLog.getInstance().log();
        try {
            this.putArg("signUpStep", null);
            this.setOk(true);
            this.setError(new ServerError(200, context.getString(R.string.text_login_success, login)));
        } catch (Exception e) {
            this.setOk(false);
            this.setError(new ServerError(500, context.getString(R.string.text_login_error, login, e.toString())));
        }
        ActiveLog.getInstance().logError(ok, error);
    }

    public void signUp(Context context, String phone, String email, String password) {
        ActiveLog.getInstance().log();
        try {
            this.setOk(true);
            this.setError(new ServerError(200, context.getString(R.string.text_signup_success)));
        } catch (Exception e) {
            this.setOk(false);
            this.setError(new ServerError(500, context.getString(R.string.text_signup_error, e.toString())));
        }
        ActiveLog.getInstance().logError(ok, error);
    }

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

    public void getUser(Context context) {
        ActiveLog.getInstance().log();
        try {
            this.putArg("name", "ФИО");
            this.putArg("inn", "");
            this.putArg("oktmo", "");
            this.putArg("date", new Date());
            this.putArg("businessType", null);
            this.putArg("isTighten", true);
            this.putArg("isInFns", false);
            this.putArg("isForceAccepted", null);
            this.putArg("isPinSet", false);
            this.putArg("inFnsUrl", "http://locallhost");
            this.putArg("forceAcceptUrl", "http://locallhost");
            this.putArg("shareUrl", "http://locallhost");
            this.setOk(true);
            this.setError(new ServerError(200, context.getString(R.string.text_getuser_success)));
        } catch (Exception e) {
            this.setOk(false);
            this.setError(new ServerError(500, context.getString(R.string.text_getuser_error, e.toString())));
        }
        ActiveLog.getInstance().logError(ok, error);
    }

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

    public void setPassword(Context context, String oldPassword, String newPassword) {
        ActiveLog.getInstance().log();
        try {
            this.setOk(true);
            this.setError(new ServerError(200, context.getString(R.string.text_setpassword_success)));
        } catch (Exception e) {
            this.setOk(false);
            this.setError(new ServerError(500, context.getString(R.string.text_setpassword_error, e.toString())));
        }
        ActiveLog.getInstance().logError(ok, error);
    }

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

    public void tightenIncome(Context context, String inn) {
        ActiveLog.getInstance().log();
        try {
            this.setOk(true);
            this.setError(new ServerError(200, context.getString(R.string.text_tightenincome_success, inn)));
        } catch (Exception e) {
            this.setOk(false);
            this.setError(new ServerError(500, context.getString(R.string.text_tightenincome_error, inn, e.toString())));
        }
        ActiveLog.getInstance().logError(ok, error);
    }

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


    public boolean isOk() {
        return ok;
    }

    private void setOk(boolean ok) {
        this.ok = ok;
    }

    public ServerError getError() {
        return error;
    }

    private void setError(ServerError error) {
        this.error = error;
    }

    public Map<String, Object> getArgs() {
        return args;
    }

    public void setArgs(Map<String, Object> args) {
        this.args = args;
    }

    private void putArg(String key, Object object) {
        this.args.put(key, object);
    }

    public Object getArg(String key) {
        return args.get(key);
    }
}
