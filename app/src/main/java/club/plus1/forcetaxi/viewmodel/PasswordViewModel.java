package club.plus1.forcetaxi.viewmodel;

public class PasswordViewModel {
    private String password;
    private String confirm;
    private String status;

    public PasswordViewModel(String password, String confirm){
        this.password = password;
        this.confirm = confirm;
        this.status = "";
    }
}
