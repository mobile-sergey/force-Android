package club.plus1.forcetaxi.viewmodel;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import club.plus1.forcetaxi.BR;
import club.plus1.forcetaxi.R;
import club.plus1.forcetaxi.model.ActiveLog;
import club.plus1.forcetaxi.model.Server;
import club.plus1.forcetaxi.view.CheckActivity;
import club.plus1.forcetaxi.view.CheckHistoryActivity;
import club.plus1.forcetaxi.view.EnterActivity;
import club.plus1.forcetaxi.view.InnInfoActivity;
import club.plus1.forcetaxi.view.InnSetActivity;
import club.plus1.forcetaxi.view.MenuBalanceActivity;
import club.plus1.forcetaxi.view.MenuInstructionActivity;
import club.plus1.forcetaxi.view.MenuInviteActivity;
import club.plus1.forcetaxi.view.MenuProfileActivity;
import club.plus1.forcetaxi.view.PinSetActivity;

public class MenuViewModel extends BaseObservable {

    private static MenuViewModel mInstance;

    @Nullable
    private Boolean isTighten;
    @Nullable
    private Boolean isInFns;
    @Nullable
    private Boolean isForceAccepted;
    @Nullable
    private Boolean isPinSet;


    private MenuViewModel(Context context) {
        ActiveLog.getInstance().log();
    }

    public static MenuViewModel getInstance(Context context) {
        ActiveLog.getInstance().log();
        if (mInstance == null) {
            mInstance = new MenuViewModel(context);
        }
        return mInstance;
    }

    public static boolean onOptionsItemSelected(Context context, int id) {
        ActiveLog.getInstance().log();
        Intent intent;
        switch (id) {
            case R.id.actionCheck:
                intent = new Intent(context, CheckActivity.class);
                context.startActivity(intent);
                return true;
            case R.id.actionHistory:
                intent = new Intent(context, CheckHistoryActivity.class);
                context.startActivity(intent);
                return true;
            case R.id.actionBalance:
                intent = new Intent(context, MenuBalanceActivity.class);
                context.startActivity(intent);
                return true;
            case R.id.actionProfile:
                intent = new Intent(context, MenuProfileActivity.class);
                context.startActivity(intent);
                return true;
            case R.id.actionInstructions:
                intent = new Intent(context, MenuInstructionActivity.class);
                context.startActivity(intent);
                return true;
            case R.id.actionExit:
                intent = new Intent(context, EnterActivity.class);
                context.startActivity(intent);
                return true;
            case R.id.actionInvite:
                intent = new Intent(context, MenuInviteActivity.class);
                context.startActivity(intent);
                return true;
            default:
                return false;
        }
    }

    public void onCopyLink(Context context) {
        ActiveLog.getInstance().log();

        ClipData clipData = ClipData.newPlainText("text", context.getString(R.string.url_link_app));
        ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        if (clipboardManager != null) {
            clipboardManager.setPrimaryClip(clipData);
        }

        Toast.makeText(context, context.getString(R.string.text_buffer_copy), Toast.LENGTH_SHORT).show();
    }

    // Запуск экрана "11.Указание ИНН" при нажатии ссылки "Привязать учет доходов и выбивание чеков"
    // в экране "5.Регистрация завершена"
    public void onTighten(Context context) {
        ActiveLog.getInstance().log();
        Intent intent = new Intent(context, InnSetActivity.class);
        context.startActivity(intent);
    }

    // Запуск экрана "15.Мой налог. Просмотре инструкции" при нажатии ссылки "Зарегистрироваться в ФНС"
    // в экране "5.Регистрация завершена"
    public void onInFns(Context context) {
        ActiveLog.getInstance().log();
        Intent intent = new Intent(context, InnInfoActivity.class);
        context.startActivity(intent);
    }

    // Запуск экрана "22.Баланс" при нажатии ссылки "Предоставить права площадке ..."
    // в экране "5.Регистрация завершена"
    public void onForceAccepted(Context context) {
        ActiveLog.getInstance().log();

        Server server = Server.getInstance(context);
        server.balance(context);
        server.getCheckHistory(context, 0, 0);

        Intent intent = new Intent(context, MenuInstructionActivity.class);
        context.startActivity(intent);
    }

    // Запуск экрана "30.Установка ПИН" при нажатии ссылки "Установить ПИН"
    // в экране "5.Регистрация завершена"
    public void onPIN(Context context) {
        ActiveLog.getInstance().log();
        Intent intent = new Intent(context, PinSetActivity.class);
        context.startActivity(intent);
    }

    public void onInvite(Context context) {
        ActiveLog.getInstance().log();
        Intent intent = new Intent(context, MenuInviteActivity.class);
        context.startActivity(intent);
    }

    public void onExit(Context context) {
        ActiveLog.getInstance().log();
        Intent intent = new Intent(context, EnterActivity.class);
        context.startActivity(intent);
    }

    @Bindable
    @Nullable
    public Boolean getIsTighten() {
        return isTighten;
    }

    public void setIsTighten(@Nullable Boolean is) {
        this.isTighten = is;
        notifyPropertyChanged(BR.isTighten);
    }

    @Bindable
    @Nullable
    public Boolean getIsInFns() {
        return isInFns;
    }

    public void setIsInFns(@Nullable Boolean is) {
        this.isInFns = is;
        notifyPropertyChanged(BR.isInFns);
    }

    @Bindable
    @Nullable
    public Boolean getIsForceAccepted() {
        return isForceAccepted;
    }

    public void setIsForceAccepted(@Nullable Boolean is) {
        this.isForceAccepted = is;
        notifyPropertyChanged(BR.isForceAccepted);
    }

    @Bindable
    @Nullable
    public Boolean getIsPinSet() {
        return isPinSet;
    }

    public void setIsPinSet(@Nullable Boolean is) {
        this.isPinSet = is;
        notifyPropertyChanged(BR.isPinSet);
    }

}
