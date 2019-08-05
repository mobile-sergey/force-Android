package club.plus1.forcetaxi.model;

import android.annotation.SuppressLint;
import android.content.Context;
import android.provider.Settings;
import android.util.Log;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import club.plus1.forcetaxi.R;

public class Server {

    private static Server mInstance;

    private String appToken;
    private String userToken;
    private boolean ok;
    private Error error;
    private Map<String, Object> args;

    @SuppressLint("HardwareIds")
    private Server(Context context) {
        Log.d("Force", "Server::ServerModel()");
        try {
            this.setAppToken(Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID));
            this.setUserToken("aec27f0f-b8a3-43cb-b076-e075a095abfe"); // Должен получаться с сервера, пока случайная строка
            this.setOk(true);
            this.setError(new Error(200, context.getString(R.string.text_server_success)));
            this.args = new HashMap<>();
        } catch (Exception e) {
            this.setOk(false);
            this.setError(new Error(500, context.getString(R.string.text_server_error, e.toString())));
            this.args = new HashMap<>();
        }
    }

    public static Server getInstance(Context context) {
        Log.d("Force", "Server::getInstance()");
        if (mInstance == null) {
            mInstance = new Server(context);
        }
        return mInstance;
    }

    public void sendMail(Context context, String email) {
        Log.d("Force", "Server::sendMail()");
        try {
            this.setOk(true);
            this.setError(new Error(200, context.getString(R.string.text_email_success, email)));
        } catch (Exception e) {
            this.setOk(false);
            this.setError(new Error(500, context.getString(R.string.text_email_error, email, e.toString())));
        }
    }

    public void login(Context context, String login, String password) {
        Log.d("Force", "Server::login()");
        try {
            this.putArg("signUpStep", null);
            this.setOk(true);
            this.setError(new Error(200, context.getString(R.string.text_login_success, login)));
        } catch (Exception e) {
            this.setOk(false);
            this.setError(new Error(500, context.getString(R.string.text_login_error, login, e.toString())));
        }
    }

    public void signUp(Context context, String phone, String email, String password) {
        Log.d("Force", "Server::signUp()");
        try {
            this.setOk(true);
            this.setError(new Error(200, context.getString(R.string.text_signup_success)));
        } catch (Exception e) {
            this.setOk(false);
            this.setError(new Error(500, context.getString(R.string.text_signup_error, e.toString())));
        }
    }

    public void sendSMS(Context context, String phone) {
        Log.d("Force", "Server::sendSMS()");
        try {
            this.setOk(true);
            this.setError(new Error(200, context.getString(R.string.text_sendsms_success, phone)));
        } catch (Exception e) {
            this.setOk(false);
            this.setError(new Error(500, context.getString(R.string.text_sendsms_error, phone, e.toString())));
        }
    }

    public void getUser(Context context) {
        Log.d("Force", "Server::getUser()");
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
            this.setError(new Error(200, context.getString(R.string.text_getuser_success)));
        } catch (Exception e) {
            this.setOk(false);
            this.setError(new Error(500, context.getString(R.string.text_getuser_error, e.toString())));
        }
    }

    public void reserPassword(Context context, String login, LoginType loginType) {
        Log.d("Force", "Server::reserPassword()");
        try {
            this.setOk(true);
            this.setError(new Error(200, context.getString(R.string.text_resetpassword_success)));
        } catch (Exception e) {
            this.setOk(false);
            this.setError(new Error(500, context.getString(R.string.text_resetpassword_error, e.toString())));
        }
    }

    public void acceptResetPass(Context context, String code, String newPassword) {
        Log.d("Force", "Server::acceptResetPass()");
        try {
            this.setOk(true);
            this.setError(new Error(200, context.getString(R.string.text_acceptresetpass_success)));
        } catch (Exception e) {
            this.setOk(false);
            this.setError(new Error(500, context.getString(R.string.text_acceptresetpass_error, e.toString())));
        }
    }

    public void setPassword(Context context, String oldPassword, String newPassword) {
        Log.d("Force", "Server::setPassword()");
        try {
            this.setOk(true);
            this.setError(new Error(200, context.getString(R.string.text_setpassword_success)));
        } catch (Exception e) {
            this.setOk(false);
            this.setError(new Error(500, context.getString(R.string.text_setpassword_error, e.toString())));
        }
    }

    public void setINN(Context context, String inn) {
        Log.d("Force", "Server::setINN()");
        try {
            this.putArg("inn", "1234567890");
            this.setOk(true);
            this.setError(new Error(200, context.getString(R.string.text_setinn_success, inn)));
        } catch (Exception e) {
            this.setOk(false);
            this.setError(new Error(500, context.getString(R.string.text_setinn_error, inn, e.toString())));
        }
    }

    public void searchINN(Context context, String phone,
                          String surname, String name, String patronymic,
                          String docSeries, String docNumber) {
        Log.d("Force", "Server::searchINN()");
        try {
            this.setOk(true);
            this.setError(new Error(200, context.getString(R.string.text_searchinn_success)));
        } catch (Exception e) {
            this.setOk(false);
            this.setError(new Error(500, context.getString(R.string.text_searchinn_error, e.toString())));
        }
    }

    public void tightenIncome(Context context, String inn) {
        Log.d("Force", "Server::tightenIncome()");
        try {
            this.setOk(true);
            this.setError(new Error(200, context.getString(R.string.text_tightenincome_success, inn)));
        } catch (Exception e) {
            this.setOk(false);
            this.setError(new Error(500, context.getString(R.string.text_tightenincome_error, inn, e.toString())));
        }
    }

    public void balance(Context context) {
        Log.d("Force", "Server::balance()");
        try {
            this.putArg("balance", "1000");
            this.setOk(true);
            this.setError(new Error(200, context.getString(R.string.text_balance_success)));
        } catch (Exception e) {
            this.setOk(false);
            this.setError(new Error(500, context.getString(R.string.text_balance_error, e.toString())));
        }
    }

    public void getCheckHistory(Context context, int startPosition, int endPosition) {
        Log.d("Force", "Server::getCheckHistory()");
        try {
            this.setOk(true);
            this.setError(new Error(200, context.getString(R.string.text_check_history_success)));
        } catch (Exception e) {
            this.setOk(false);
            this.setError(new Error(500, context.getString(R.string.text_check_history_error, e.toString())));
        }
    }

    public void refillBalance(Context context, String amount, String from) {
        Log.d("Force", "Server::refillBalance()");
        try {
            this.putArg("status", "В обработке");
            this.setOk(true);
            this.setError(new Error(200, context.getString(R.string.text_refill_balance_success, amount)));
        } catch (Exception e) {
            this.setOk(false);
            this.setError(new Error(500, context.getString(R.string.text_refill_balance_error, from, e.toString())));
        }
    }


    public String getAppToken() {
        return appToken;
    }

    private void setAppToken(String appToken) {
        this.appToken = appToken;
    }

    public String getUserToken() {
        return userToken;
    }

    private void setUserToken(String userToken) {
        this.userToken = userToken;
    }

    public boolean isOk() {
        return ok;
    }

    private void setOk(boolean ok) {
        this.ok = ok;
    }

    public Error getError() {
        return error;
    }

    private void setError(Error error) {
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
