package club.plus1.forcetaxi.viewmodel;

public class RecoveryViewModel {
    private String login;
    private String code;
    private String status;

    public RecoveryViewModel(String login, String code){
        this.login = login;
        this.code = code;
        this.status = "";
    }
}
