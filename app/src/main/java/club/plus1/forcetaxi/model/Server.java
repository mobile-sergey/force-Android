package club.plus1.forcetaxi.model;

import android.annotation.SuppressLint;
import android.content.Context;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

import java.util.Date;
import java.util.Map;

import club.plus1.forcetaxi.R;

public class Server {

    private static Server mInstance;

    private String appToken;
    private String userToken;
    private boolean ok;
    private Error error;
    private Map<String, Object> args;

    public static Server getInstance(Context context) {
        Log.d("Force", "ServerModel::getInstance()");
        if (mInstance == null) {
            mInstance = new Server(context);
        }
        return mInstance;
    }

    @SuppressLint("HardwareIds")
    private Server(Context context) {
        Log.d("Force", "ServerModel::ServerModel()");
        try {
            this.setAppToken(Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID));
            this.setUserToken("aec27f0f-b8a3-43cb-b076-e075a095abfe"); // Должен получаться с сервера, пока случайная строка
            this.setOk(true);
            this.setError(new Error(200, context.getString(R.string.text_server_success)));
        } catch (Exception e) {
            this.setOk(false);
            this.setError(new Error(500, context.getString(R.string.text_server_error, e.toString())));
        }
        Toast.makeText(context, this.getError().getText(), Toast.LENGTH_SHORT).show();
    }

    public void sendMail(Context context, String email) {
        Log.d("Force", "ServerModel::sendMail()");
        try {
            this.setOk(true);
            this.setError(new Error(200, context.getString(R.string.text_email_success, email)));
        } catch (Exception e) {
            this.setOk(false);
            this.setError(new Error(500, context.getString(R.string.text_email_error, email, e.toString())));
        }
        Toast.makeText(context, this.getError().getText(), Toast.LENGTH_SHORT).show();
    }

    public void login(Context context, String login, String password) {
        Log.d("Force", "ServerModel::login()");
        try {
            this.getArgs().put("signUpStep", null);
            this.setOk(true);
            this.setError(new Error(200, context.getString(R.string.text_login_success, login)));
        } catch (Exception e) {
            this.setOk(false);
            this.setError(new Error(500, context.getString(R.string.text_login_error, login, e.toString())));
        }
        Toast.makeText(context, this.getError().getText(), Toast.LENGTH_SHORT).show();
    }

    public void signUp(Context context, String phone, String email, String password) {
        Log.d("Force", "ServerModel::signUp()");
        try {
            this.setOk(true);
            this.setError(new Error(200, context.getString(R.string.text_signup_success)));
        } catch (Exception e) {
            this.setOk(false);
            this.setError(new Error(500, context.getString(R.string.text_signup_error, e.toString())));
        }
        Toast.makeText(context, this.getError().getText(), Toast.LENGTH_SHORT).show();
    }

    public void sendSMS(Context context, String phone) {
        Log.d("Force", "ServerModel::sendSMS()");
        try {
            this.setOk(true);
            this.setError(new Error(200, context.getString(R.string.text_sendsms_success, phone)));
        } catch (Exception e) {
            this.setOk(false);
            this.setError(new Error(500, context.getString(R.string.text_sendsms_error, phone, e.toString())));
        }
        Toast.makeText(context, this.getError().getText(), Toast.LENGTH_SHORT).show();
    }

    public void getUser(Context context) {
        Log.d("Force", "ServerModel::getUser()");
        try {
            this.getArgs().put("name", "ФИО");
            this.getArgs().put("inn", "");
            this.getArgs().put("oktmo", "");
            this.getArgs().put("date", new Date());
            this.getArgs().put("businessType", null);
            this.getArgs().put("isTighten", true);
            this.getArgs().put("isInFns", false);
            this.getArgs().put("isForceAccepted", null);
            this.getArgs().put("isPinSet", false);
            this.getArgs().put("inFnsUrl", "http://locallhost");
            this.getArgs().put("forceAcceptUrl", "http://locallhost");
            this.getArgs().put("shareUrl", "http://locallhost");
            this.setOk(true);
            this.setError(new Error(200, context.getString(R.string.text_getuser_success)));
        } catch (Exception e) {
            this.setOk(false);
            this.setError(new Error(500, context.getString(R.string.text_getuser_error, e.toString())));
        }
        Toast.makeText(context, this.getError().getText(), Toast.LENGTH_SHORT).show();
    }

    public void reserPassword(Context context, String login, LoginType loginType) {
        Log.d("Force", "ServerModel::reserPassword()");
        try {
            this.setOk(true);
            this.setError(new Error(200, context.getString(R.string.text_resetpassword_success)));
        } catch (Exception e) {
            this.setOk(false);
            this.setError(new Error(500, context.getString(R.string.text_resetpassword_error, e.toString())));
        }
        Toast.makeText(context, this.getError().getText(), Toast.LENGTH_SHORT).show();
    }

    public void acceptResetPass(Context context, String code, LoginType newPassword) {
        Log.d("Force", "ServerModel::acceptResetPass()");
        try {
            this.setOk(true);
            this.setError(new Error(200, context.getString(R.string.text_acceptresetpass_success)));
        } catch (Exception e) {
            this.setOk(false);
            this.setError(new Error(500, context.getString(R.string.text_acceptresetpass_error, e.toString())));
        }
        Toast.makeText(context, this.getError().getText(), Toast.LENGTH_SHORT).show();
    }

    public void setPassword(Context context, String oldPassword, LoginType newPassword) {
        Log.d("Force", "ServerModel::setPassword()");
        try {
            this.setOk(true);
            this.setError(new Error(200, context.getString(R.string.text_setpassword_success)));
        } catch (Exception e) {
            this.setOk(false);
            this.setError(new Error(500, context.getString(R.string.text_setpassword_error, e.toString())));
        }
        Toast.makeText(context, this.getError().getText(), Toast.LENGTH_SHORT).show();
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

    private Error getError() {
        return error;
    }

    private void setError(Error error) {
        this.error = error;
    }

    private Map<String, Object> getArgs() {
        return args;
    }

    private void setArgs(Map<String, Object> args) {
        this.args = args;
    }
}
