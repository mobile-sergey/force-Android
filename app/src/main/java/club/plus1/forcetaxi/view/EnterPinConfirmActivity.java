package club.plus1.forcetaxi.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import club.plus1.forcetaxi.R;
import club.plus1.forcetaxi.databinding.EnterPinConfirmBinding;
import club.plus1.forcetaxi.service.ActiveLog;
import club.plus1.forcetaxi.viewmodel.EnterViewModel;

public class EnterPinConfirmActivity extends AppCompatActivity {

    private EnterViewModel viewModel;
    private EnterPinConfirmBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActiveLog.getInstance().log();
        super.onCreate(savedInstanceState);
        viewModel = EnterViewModel.getInstance(this);
        binding = DataBindingUtil.setContentView(this, R.layout.enter_pin_confirm);
        binding.setViewModel(viewModel);
    }
}
