package club.plus1.forcetaxi.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import club.plus1.forcetaxi.BR;
import club.plus1.forcetaxi.R;
import club.plus1.forcetaxi.view.EnterActivity;
import club.plus1.forcetaxi.view.PinSetActivity;
import club.plus1.forcetaxi.view.RegistrationFinishedActivity;
import club.plus1.forcetaxi.view.RegistrationVerificationActivity;

public class RegistrationViewModel extends BaseObservable {

    private static RegistrationViewModel mInstance;
    public String textCode;
    private String srcTighten;
    private String srcInFns;
    private String srcForceAccepted;
    private String srcPinSet;

    private RegistrationViewModel(Context context) {
        Log.d("Force", "RegistrationViewModel::RegistrationViewModel()");
        this.textCode = context.getString(R.string.text_code);
        setSrcTighten("wait");
        setSrcInFns("wait");
        setSrcForceAccepted("wait");
        setSrcPinSet("wait");
    }

    public static RegistrationViewModel getInstance(Context context) {
        Log.d("Force", "RegistrationViewModel::getInstance()");
        if (mInstance == null) {
            mInstance = new RegistrationViewModel(context);
        }
        return mInstance;
    }

    public void onRegistration(Context context) {
        Log.d("Force", "RegistrationViewModel::onRegistration()");
        Intent intent = new Intent(context, RegistrationVerificationActivity.class);
        context.startActivity(intent);
    }

    public void onVerification(Context context) {
        Log.d("Force", "RegistrationViewModel::onVerification()");
        Intent intent = new Intent(context, RegistrationFinishedActivity.class);
        context.startActivity(intent);
    }

    public void onLater(Context context) {
        Log.d("Force", "RegistrationViewModel::onLater()");
        Intent intent = new Intent(context, EnterActivity.class);
        context.startActivity(intent);
    }

    public void onPIN(Context context) {
        Log.d("Force", "RegistrationViewModel::onPIN()");
        Intent intent = new Intent(context, PinSetActivity.class);
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

    @Bindable
    public String getSrcForceAccepted() {
        return srcForceAccepted;
    }

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
