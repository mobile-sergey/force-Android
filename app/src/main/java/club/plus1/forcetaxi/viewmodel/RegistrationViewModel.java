package club.plus1.forcetaxi.viewmodel;

import android.content.Context;
import android.content.Intent;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import club.plus1.forcetaxi.BR;
import club.plus1.forcetaxi.view.EnterActivity;
import club.plus1.forcetaxi.view.PINSetActivity;
import club.plus1.forcetaxi.view.VerificationActivity;

public class RegistrationViewModel extends BaseObservable {

    private String login;
    private String email;
    private String phone;
    private String password;
    private String confirm;
    private String status;

    private String srcTighten;
    private String srcInFns;
    private String srcForceAccepted;
    private String srcPinSet;

    public RegistrationViewModel() {
        this.login = "";
        this.email = "";
        this.phone = "";
        this.password = "";
        this.confirm = "";
        this.status = "";
        setSrcTighten("wait");
        setSrcInFns("wait");
        setSrcForceAccepted("wait");
        setSrcPinSet("wait");
    }

    public void onRegistration(Context context) {
        Intent intent = new Intent(context, VerificationActivity.class);
        context.startActivity(intent);
    }

    public void onLater(Context context) {
        Intent intent = new Intent(context, EnterActivity.class);
        context.startActivity(intent);
    }

    public void onPIN(Context context) {
        Intent intent = new Intent(context, PINSetActivity.class);
        context.startActivity(intent);
    }

    @Bindable
    public String getSrcTighten() {
        return srcTighten;
    }

    public void setSrcTighten(String src) {
        this.srcTighten = src;
        notifyPropertyChanged(BR.srcTighten);
    }

    @Bindable
    public String getSrcInFns() {
        return srcInFns;
    }

    public void setSrcInFns(String src) {
        this.srcInFns = src;
        notifyPropertyChanged(BR.srcInFns);
    }

    public String getSrcForceAccepted() {
        return srcForceAccepted;
    }

    @Bindable
    public void setSrcForceAccepted(String src) {
        this.srcForceAccepted = src;
        notifyPropertyChanged(BR.srcForceAccepted);
    }

    @Bindable
    public String getSrcPinSet() {
        return srcPinSet;
    }

    public void setSrcPinSet(String src) {
        this.srcPinSet = src;
        notifyPropertyChanged(BR.srcPinSet);
    }

}
