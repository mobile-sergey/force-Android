package club.plus1.forcetaxi.viewmodel;

import android.content.Context;
import android.content.Intent;

import club.plus1.forcetaxi.R;
import club.plus1.forcetaxi.view.PasswordActivity;
import club.plus1.forcetaxi.view.RecoveryEmailActivity;
import club.plus1.forcetaxi.view.RecoveryPhoneActivity;

public class RecoveryViewModel {
    private String login;
    private String code;
    public String result;

    public RecoveryViewModel(Context context) {
        this.login = "";
        this.code = "";
        this.result = context.getString(R.string.text_recovery_success);
        //this.result = context.getString(R.string.text_recovery_error, "");
    }

    public void onRecovery(Context context) {
        Intent intent = new Intent(context, RecoveryEmailActivity.class);
        context.startActivity(intent);
    }

    public void onEmailRecovery(Context context) {
        Intent intent = new Intent(context, RecoveryPhoneActivity.class);
        context.startActivity(intent);
    }

    public void onPhoneRecovery(Context context) {
        Intent intent = new Intent(context, PasswordActivity.class);
        context.startActivity(intent);
    }

}
