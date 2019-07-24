package club.plus1.forcetaxi.viewmodel;

public class PINViewModel {
    private int pin;
    private int confirm;
    private String status;

    public PINViewModel(int pin, int confirm){
        this.pin = pin;
        this.confirm = confirm;
        this.status = "";
    }
}
