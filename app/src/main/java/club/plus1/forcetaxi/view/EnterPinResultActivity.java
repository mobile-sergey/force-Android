package club.plus1.forcetaxi.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import club.plus1.forcetaxi.R;
import club.plus1.forcetaxi.databinding.EnterPinResultBinding;
import club.plus1.forcetaxi.service.ActiveLog;
import club.plus1.forcetaxi.viewmodel.EnterViewModel;

public class EnterPinResultActivity extends AppCompatActivity {

    private EnterViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActiveLog.getInstance().log();
        super.onCreate(savedInstanceState);
        EnterPinResultBinding binding = DataBindingUtil.setContentView(this, R.layout.enter_pin_result);
        viewModel = EnterViewModel.getInstance(this);
        binding.setViewModel(viewModel);
    }
}
