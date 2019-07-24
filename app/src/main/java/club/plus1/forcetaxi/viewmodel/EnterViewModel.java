package club.plus1.forcetaxi.viewmodel;

public class EnterViewModel {
    private String login;
    private String password;
    private String status;

    public EnterViewModel(String login, String password){
        this.login = login;
        this.password = password;
        this.status = "";
    }
}