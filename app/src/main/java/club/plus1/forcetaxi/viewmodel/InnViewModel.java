package club.plus1.forcetaxi.viewmodel;

import android.content.Context;
import android.content.Intent;

import androidx.databinding.ObservableField;

import club.plus1.forcetaxi.model.ResponseUser;
import club.plus1.forcetaxi.service.ActiveLog;
import club.plus1.forcetaxi.stub.ConstantStub;
import club.plus1.forcetaxi.stub.ResponseUserStub;
import club.plus1.forcetaxi.stub.ServerStub;
import club.plus1.forcetaxi.view.InnBindActivity;
import club.plus1.forcetaxi.view.InnBindResultActivity;
import club.plus1.forcetaxi.view.InnInfoActivity;
import club.plus1.forcetaxi.view.InnSearchActivity;
import club.plus1.forcetaxi.view.InnSearchResultActivity;
import club.plus1.forcetaxi.view.InnSetResultActivity;
import club.plus1.forcetaxi.view.MenuCheckActivity;

import static java.util.Objects.requireNonNull;

public class InnViewModel {

    // Поле экрана "11.Указание ИНН"
    public ObservableField<String> inn = new ObservableField<>();

    // Поле экрана "12.Указание ИНН. Результат"
    public ObservableField<String> result = new ObservableField<>();

    // Поля экрана "13.	Поиск ИНН"
    public ObservableField<String> phone = new ObservableField<>();
    public ObservableField<String> birthday = new ObservableField<>();
    public ObservableField<String> surname = new ObservableField<>();
    public ObservableField<String> name = new ObservableField<>();
    public ObservableField<String> patronymic = new ObservableField<>();
    public ObservableField<String> docSeries = new ObservableField<>();
    public ObservableField<String> docNumber = new ObservableField<>();

    // Поле экрана "15.	Мой налог. Просмотр инструкции"
    public ObservableField<String> url = new ObservableField<>();

    // Поле экрана "16.Привязка ИНН"
    public ObservableField<String> fio = new ObservableField<>();
    public ObservableField<String> oktmo = new ObservableField<>();
    public ObservableField<String> dateFNS = new ObservableField<>();

    // Ссылки MVVM
    private static InnViewModel mInstance;  // Ссылка для биндинга с View
    private ServerStub server;              // Ссылка на Model сервера
    private ResponseUser responseUser;      // Ссылка на Model ответа по пользователю

    // Конструктор класса
    private InnViewModel(Context context) {
        ActiveLog.getInstance().log();
        server = ServerStub.getInstance(ConstantStub.APP_TOKEN);
        responseUser = new ResponseUserStub(ConstantStub.APP_TOKEN);
        url.set(ConstantStub.URL_INFO);
    }

    // Получение единственного экземпляра класса
    public static InnViewModel getInstance(Context context) {
        ActiveLog.getInstance().log();
        if (mInstance == null) {
            mInstance = new InnViewModel(context);
        }
        return mInstance;
    }

    // "11.Указание ИНН" -> "13.Поиск ИНН"
    // Выполняется при нажатии ссылки "Напомнить ИНН"
    public void onInnRemind(Context context) {
        ActiveLog.getInstance().log();
        Intent intent = new Intent(context, InnSearchActivity.class);
        context.startActivity(intent);
    }

    // "11.Указание ИНН" -> "12.Указание ИНН. Результат"
    // Выполняется при нажатии кнопки "Продолжить"
    // Вызывает серверный метод setINN
    public void onInnSet(Context context) {
        ActiveLog.getInstance().log();
        responseUser.checkConnectedTin(inn.get());
        result.set(responseUser.getErrorText());
        Intent intent = new Intent(context, InnSetResultActivity.class);
        context.startActivity(intent);
    }

    // "12.Указание ИНН. Результат" -> "13.Поиск ИНН"
    // "12.Указание ИНН. Результат" -> "16.Привязка ИНН"
    // Если ИНН не введен -> 13, если введен -> 16
    // Выполняется при нажатии на экране
    public void onSetResult(Context context) {
        ActiveLog.getInstance().log();
        if (requireNonNull(inn.get()).isEmpty()) {
            Intent intent = new Intent(context, InnSearchActivity.class);
            context.startActivity(intent);
        } else {
            Intent intent = new Intent(context, InnBindActivity.class);
            context.startActivity(intent);
        }
    }

    // "13.Поиск ИНН" -> "14.Поиск ИНН. Результат"
    // Выполняется при нажатии кнопки "Поиск в ФНС"
    // Вызывает серверный метод searchINN
    public void onSearch(Context context) {
        ActiveLog.getInstance().log();
        inn.set(responseUser.searchTin(surname.get(), name.get(), patronymic.get(),
                birthday.get(), docSeries.get(), docNumber.get()));
        result.set(responseUser.getErrorText());
        Intent intent = new Intent(context, InnSearchResultActivity.class);
        context.startActivity(intent);
    }

    // "13.Поиск ИНН" -> "15.Мой налог. Просмотр инструкции"
    // Выполняется при нажатии ссылки "Как зарегистрироваться самозанятым"
    public void onInnInfo(Context context) {
        ActiveLog.getInstance().log();
        Intent intent = new Intent(context, InnInfoActivity.class);
        context.startActivity(intent);
    }

    // "14.Поиск ИНН. Результат" -> "15.Мой налог. Просмотр инструкции"
    // "14.Поиск ИНН. Результат" -> "16.Привязка ИНН"
    // Если ИНН не найден -> 15, если найден -> 16
    // Выполняется нажатии на экране
    public void onSearchResult(Context context) {
        ActiveLog.getInstance().log();
        if (requireNonNull(inn.get()).isEmpty()) {
            Intent intent = new Intent(context, InnInfoActivity.class);
            context.startActivity(intent);
        } else {
            Intent intent = new Intent(context, InnBindActivity.class);
            context.startActivity(intent);
        }
    }

    // "16.Привязка ИНН -> "17.Привязка ИНН. Результат"
    // Выполняется при нажатии кнопки "Привязать"
    // Вызывает серверный метод tightenIncome
    public void onBind(Context context) {
        ActiveLog.getInstance().log();
        responseUser.connectTin(inn.get());
        result.set(responseUser.getErrorText());
        Intent intent = new Intent(context, InnBindResultActivity.class);
        context.startActivity(intent);
    }

    // "16.Привязка ИНН" -> "15.Мой налог. Просмотр инструкции"
    // Выполняется при нажатии ссылки "Как дать разрешения на работу площадки"
    public void onBindInfo(Context context) {
        ActiveLog.getInstance().log();
        Intent intent = new Intent(context, InnInfoActivity.class);
        context.startActivity(intent);
    }

    // "17.Привязка ИНН. Результат" -> "34.Закрытое меню"
    // Выполняется при нажатии на экране
    public void onBindResult(Context context) {
        ActiveLog.getInstance().log();
        Intent intent = new Intent(context, MenuCheckActivity.class);
        context.startActivity(intent);
    }
}
