package club.plus1.forcetaxi.viewmodel;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.databinding.BaseObservable;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;

import club.plus1.forcetaxi.R;
import club.plus1.forcetaxi.model.LocalSettings;
import club.plus1.forcetaxi.model.ResponseBalance;
import club.plus1.forcetaxi.service.ActiveLog;
import club.plus1.forcetaxi.stub.ConstantStub;
import club.plus1.forcetaxi.stub.ResponseBalanceStub;
import club.plus1.forcetaxi.stub.ServerStub;
import club.plus1.forcetaxi.view.BalanceActivity;
import club.plus1.forcetaxi.view.CheckActivity;
import club.plus1.forcetaxi.view.CheckHistoryActivity;
import club.plus1.forcetaxi.view.EnterActivity;
import club.plus1.forcetaxi.view.InnInfoActivity;
import club.plus1.forcetaxi.view.InnSetActivity;
import club.plus1.forcetaxi.view.MenuBalanceActivity;
import club.plus1.forcetaxi.view.MenuInstructionActivity;
import club.plus1.forcetaxi.view.MenuInviteActivity;
import club.plus1.forcetaxi.view.RegistrationProfileActivity;

public class MenuViewModel extends BaseObservable {

    // Поле экрана "2.Результат входа"
    public ObservableField<String> result = new ObservableField<>();

    // Поле экрана "28.Инструкция"
    public ObservableField<String> url = new ObservableField<>();

    // Поле экрана "29.Пригласить друга"
    public ObservableField<String> urlApp = new ObservableField<>();

    // Поля экрана "5.Регистрация завершена"
    public ObservableBoolean isInFns = new ObservableBoolean();
    public ObservableBoolean isForceAccepted = new ObservableBoolean();

    // Ссылки MVVM
    private static MenuViewModel mInstance; // Ссылка для биндинга с View
    private ServerStub server;              // Ссылка на Model сервера
    private LocalSettings settings;         // Ссылка на Model локальных настроек
    private ResponseBalance responseBalance;// Ссылка на Model баланса

    // Конструктор класса
    private MenuViewModel(Context context) {
        ActiveLog.getInstance().log();
        server = ServerStub.getInstance(ConstantStub.APP_TOKEN);
        url.set(ConstantStub.URL_INSTRUCTIONS);
        urlApp.set(ConstantStub.URL_APP);
        settings = new LocalSettings();
        responseBalance = new ResponseBalanceStub(ConstantStub.APP_TOKEN);
    }

    // Получение единственного экземпляра класса
    public static MenuViewModel getInstance(Context context) {
        ActiveLog.getInstance().log();
        if (mInstance == null) {
            mInstance = new MenuViewModel(context);
        }
        return mInstance;
    }

    // Меню "Выбить чек " -> "18.Выбивание чека"
    // Меню "История"-> "19.История чеков"
    // Меню "Баланс" -> "35.Закрытый баланс"
    // Меню "Профиль" -> "27.Профиль"
    // Меню "Инструкции" -> "28.Инструкция"
    // Меню "Пригласить друга" -> "29.Пригласить друга"
    // Меню "Выход" -> "1.Вход"
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
                intent = new Intent(context, RegistrationProfileActivity.class);
                context.startActivity(intent);
                return true;
            case R.id.actionInstructions:
                intent = new Intent(context, MenuInstructionActivity.class);
                context.startActivity(intent);
                return true;
            case R.id.actionInvite:
                intent = new Intent(context, MenuInviteActivity.class);
                context.startActivity(intent);
                return true;
            case R.id.actionExit:
                intent = new Intent(context, EnterActivity.class);
                context.startActivity(intent);
                return true;
            default:
                return false;
        }
    }

    // "34.Закрытое меню" -> "18.Выбивание чека"
    // Выполняется при при загрузке экрана
    public void onMenuCheck(Context context) {
        ActiveLog.getInstance().log();
        if (server.user.tinConnected) {
            Intent intent = new Intent(context, CheckActivity.class);
            context.startActivity(intent);
        }
    }

    // "34.Закрытое меню" -> "11.Указание ИНН"
    // Выполняется при нажатии ссылки "Привязать учет доходов и выбивание чеков"
    public void onTighten(Context context) {
        ActiveLog.getInstance().log();
        Intent intent = new Intent(context, InnSetActivity.class);
        context.startActivity(intent);
    }

    // "35.Закрытый баланс" -> "22.Баланс"
    // Выполняется при при загрузке экрана
    public void onMenuBalance(Context context) {
        ActiveLog.getInstance().log();
        isInFns.set(settings.isInFns());
        isForceAccepted.set(settings.isForceAccepted());
        if (isInFns.get() && isForceAccepted.get()) {
            server.user.balance = responseBalance.balance();
            result.set(responseBalance.getErrorText());
            Intent intent = new Intent(context, BalanceActivity.class);
            context.startActivity(intent);
        }
    }

    // "35.Закрытый баланс" -> "15.Мой налог. Просмотре инструкции"
    // Выполняется при нажатии ссылки "Зарегистрироваться в ФНС"
    public void onInFns(Context context) {
        ActiveLog.getInstance().log();
        Intent intent = new Intent(context, InnInfoActivity.class);
        context.startActivity(intent);
    }

    // "35.Закрытый баланс" -> "28. Инструкция"
    // Выполняется при нажатии ссылки "Предоставить права площадке ..."
    public void onForceAccepted(Context context) {
        ActiveLog.getInstance().log();
        Intent intent = new Intent(context, MenuInstructionActivity.class);
        context.startActivity(intent);
    }

    // "29.Пригласить друга"
    // Выполняется при нажатии ссылки "Скопировать ссылку"
    // Копирует ссылку на приложение в буфер обмена
    public void onCopyLink(Context context) {
        ActiveLog.getInstance().log();
        ClipData clipData = ClipData.newPlainText("text", urlApp.get());
        ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        if (clipboardManager != null) {
            clipboardManager.setPrimaryClip(clipData);
        }
        Toast.makeText(context, context.getString(R.string.text_buffer_copy), Toast.LENGTH_SHORT).show();
    }

    // "29.Пригласить друга"
    // Выполняется при нажатии ссылки "Поделться"
    // Делится ссылкой в любых приложениях, которые принимают текст
    public void onShare(Context context) {
        ActiveLog.getInstance().log();
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, urlApp.get());
        context.startActivity(intent);
    }
}
