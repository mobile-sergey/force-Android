package club.plus1.forcetaxi.viewmodel;

import android.content.Context;
import android.content.Intent;

import club.plus1.forcetaxi.view.EnterActivity;
import club.plus1.forcetaxi.view.EnterResultActivity;
import club.plus1.forcetaxi.view.RecoveryActivity;
import club.plus1.forcetaxi.view.RegistrationActivity;

public class EnterViewModel {
    private String login;
    private String password;
    private String status;

    public EnterViewModel() {
        this.login = "";
        this.password = "";
        this.status = "";
    }

    public void onEnter(Context context) {
        Intent intent = new Intent(context, EnterResultActivity.class);
        context.startActivity(intent);
    }

    public void onRegister(Context context) {
        Intent intent = new Intent(context, RegistrationActivity.class);
        context.startActivity(intent);
    }

    public void onRecovery(Context context) {
        Intent intent = new Intent(context, RecoveryActivity.class);
        context.startActivity(intent);
    }

    public void onResult(Context context) {
        Intent intent = new Intent(context, EnterActivity.class);
        context.startActivity(intent);
    }
}