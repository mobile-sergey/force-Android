package club.plus1.forcetaxi.viewmodel;

public class RegistrationViewModel {
    private String login;
    private String email;
    private String phone;
    private String password;
    private String confirm;
    private String status;

    public RegistrationViewModel(String login, String email, String phone, String password, String confirm){
        this.login = login;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.confirm = confirm;
        this.status = "";
    }
}
