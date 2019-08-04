package club.plus1.forcetaxi.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import club.plus1.forcetaxi.R;
import club.plus1.forcetaxi.model.Server;
import club.plus1.forcetaxi.view.InnBindActivity;
import club.plus1.forcetaxi.view.InnBindResultActivity;
import club.plus1.forcetaxi.view.InnInfoActivity;
import club.plus1.forcetaxi.view.InnSearchActivity;
import club.plus1.forcetaxi.view.InnSearchResultActivity;
import club.plus1.forcetaxi.view.InnSetResultActivity;
import club.plus1.forcetaxi.view.SplashActivity;

public class InnViewModel {

    private static InnViewModel mInstance;
    public String result;
    public String inn;
    public String phone;
    public String surname;
    public String name;
    public String patronymic;
    public String docSeries;
    public String docNumber;
    public String urlInfo;

    private InnViewModel(Context context) {
        Log.d("Force", "InnViewModel::InnViewModel()");
        urlInfo = context.getString(R.string.url_inn_info);
        inn = "";
    }

    public static InnViewModel getInstance(Context context) {
        Log.d("Force", "InnViewModel::getInstance()");
        if (mInstance == null) {
            mInstance = new InnViewModel(context);
        }
        return mInstance;
    }

    // Запуск экрана "13.Поиск ИНН" при нажатии ссылки "Напомнить ИНН" в экране "11.Указание ИНН"
    public void onInnRemind(Context context) {
        Log.d("Force", "InnViewModel::onInnRemind()");
        Intent intent = new Intent(context, InnSearchActivity.class);
        context.startActivity(intent);
    }

    // Запуск экрана "12.Указание ИНН. Результат" при нажатии кнопки "Продолжить" в экране "11.Указание ИНН"
    public void onInnSet(Context context) {
        Log.d("Force", "InnViewModel::onInnSet()");

        Server server = Server.getInstance(context);
        server.setINN(context, inn);
        if (server.isOk()) {
            result = context.getString(R.string.text_set_inn_success);
        } else {
            result = context.getString(R.string.text_set_inn_error, server.getError().getText());
        }

        Intent intent = new Intent(context, InnSetResultActivity.class);
        context.startActivity(intent);
    }

    // Запуск экрана "14.Поиск ИНН. Результат" при нажатии кнопки "Поиск в ФНС" в экране "13.Поиск ИНН"
    public void onSearch(Context context) {
        Log.d("Force", "InnViewModel::onSearch()");

        Server server = Server.getInstance(context);
        server.searchINN(context, phone, surname, name, patronymic, docSeries, docNumber);
        inn = server.getArgs().get("inn").toString();
        if (server.isOk()) {
            result = context.getString(R.string.text_search_inn_success, inn);
        } else {
            result = context.getString(R.string.text_search_inn_error, server.getError().getText());
        }

        Intent intent = new Intent(context, InnSearchResultActivity.class);
        context.startActivity(intent);
    }

    // Запуск экрана "15.Мой налог. Просмотр инструкции" при нажатии ссылки
    // "Как зарегистрироваться самозанятым" в экране "13.Поиск ИНН"
    public void onInnInfo(Context context) {
        Log.d("Force", "InnViewModel::onInnInfo()");
        Intent intent = new Intent(context, InnInfoActivity.class);
        context.startActivity(intent);
    }

    // Запуск экрана "17.Привязка ИНН. Результат" при нажатии кнопки "Привязать" в экране "16.Привязка ИНН"
    public void onBind(Context context) {
        Log.d("Force", "InnViewModel::onBind()");

        Server server = Server.getInstance(context);
        server.tightenIncome(context, inn);
        if (server.isOk()) {
            result = context.getString(R.string.text_bind_inn_success, inn);
        } else {
            result = context.getString(R.string.text_bind_inn_error, inn, server.getError().getText());
        }

        Intent intent = new Intent(context, InnBindResultActivity.class);
        context.startActivity(intent);
    }

    // Запуск экрана "15.Мой налог. Просмотр инструкции" при нажатии ссылки
    // "Как дать разрешения на работу площадки" в экране "16.Привязка ИНН"
    public void onBindInfo(Context context) {
        Log.d("Force", "InnViewModel::onBindInfo()");
        Intent intent = new Intent(context, InnInfoActivity.class);
        context.startActivity(intent);
    }

    // При нажатии на экране "12.Указание ИНН. Результат"
    // запуск экрана "16.Привязка ИНН" если ИНН найден
    // или запуск экрана "13.Поиск ИНН" если ИНН не найден
    public void onSetResult(Context context) {
        Log.d("Force", "InnViewModel::onSetResult()");
        if (inn.isEmpty()) {
            Intent intent = new Intent(context, InnSearchActivity.class);
            context.startActivity(intent);
        } else {
            Intent intent = new Intent(context, InnBindActivity.class);
            context.startActivity(intent);
        }
    }

    // При нажатии на экране "14.Поиск ИНН. Результат"
    // запуск экрана "16.Привязка ИНН" если ИНН найден
    // или запуск экрана "15.Мой налог. Просмотр инструкции" если ИНН не найден
    public void onSearchResult(Context context) {
        Log.d("Force", "InnViewModel::onSearchResult()");
        if (inn.isEmpty()) {
            Intent intent = new Intent(context, InnInfoActivity.class);
            context.startActivity(intent);
        } else {
            Intent intent = new Intent(context, InnBindActivity.class);
            context.startActivity(intent);
        }
    }

    // Запуск экрана "0.Заставка" при нажатии на экране "17.Привязка ИНН. Результат"
    // TODO: Когда появится экран "34.Закрытое меню" - нужно будет перенаправлять туда
    public void onBindResult(Context context) {
        Log.d("Force", "InnViewModel::onBindResult()");
        Intent intent = new Intent(context, SplashActivity.class);
        context.startActivity(intent);
    }

}
