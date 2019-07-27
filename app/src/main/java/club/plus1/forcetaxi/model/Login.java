package club.plus1.forcetaxi.model;

public class Login {

    private String login;
    private String email;
    private String phone;
    private String code;
    private String password;
    private LoginType loginType;

    public Login(String login, String email, String phone, String password) {
        this.loginType = LoginType.login;
        this.code = "";
        this.login = login;
        this.email = email;
        this.phone = phone;
        this.password = password;
    }
}
