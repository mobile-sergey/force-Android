package club.plus1.forcetaxi.viewmodel;

import android.content.Context;

public class RecoveryViewModel {
    private String login;
    private String code;
    private String result;

    public RecoveryViewModel(Context context) {
        this.login = "";
        this.code = "";
        this.result = "";
    }
}
