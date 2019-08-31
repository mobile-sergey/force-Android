package club.plus1.forcetaxi.model;

import androidx.annotation.NonNull;

import club.plus1.forcetaxi.stub.ConstantStub;

public class ServerUser {
    public String userToken;    // Токен авторизации пользователя
    public ServerFts fts;       // Информация из ФНС
    private String firstName;
    private String secondName;
    private String patronymic;

    public ServerUser(String firstName, String secondName, String patronymic) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.patronymic = patronymic;
        this.fts = new ServerFts("");
        if (firstName.isEmpty() && secondName.isEmpty() && patronymic.isEmpty()) {
            this.userToken = "";
        } else {
            userToken = ConstantStub.USER_TOKEN;
        }
    }

    @NonNull
    @Override
    public String toString() {
        return firstName + " " + secondName + " " + patronymic;
    }
}
