package club.plus1.forcetaxi.viewmodel;

import android.content.Context;
import android.content.Intent;

import club.plus1.forcetaxi.R;
import club.plus1.forcetaxi.view.EnterActivity;
import club.plus1.forcetaxi.view.PasswordResultActivity;

public class PasswordViewModel {
    private String password;
    private String confirm;
    public String result;

    public PasswordViewModel(Context context) {
        this.password = "";
        this.confirm = "";
        this.result = context.getString(R.string.text_password_success);
        //this.result = context.getString(R.string.text_password_error, "");
    }

    public void onPasswordChange(Context context) {
        Intent intent = new Intent(context, PasswordResultActivity.class);
        context.startActivity(intent);
    }

    public void onResult(Context context) {
        Intent intent = new Intent(context, EnterActivity.class);
        context.startActivity(intent);
    }

}
