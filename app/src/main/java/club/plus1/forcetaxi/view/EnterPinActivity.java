package club.plus1.forcetaxi.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import club.plus1.forcetaxi.R;
import club.plus1.forcetaxi.databinding.EnterPinBinding;
import club.plus1.forcetaxi.service.ActiveLog;
import club.plus1.forcetaxi.viewmodel.EnterViewModel;

public class EnterPinActivity extends AppCompatActivity {

    private EnterViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActiveLog.getInstance().log();
        super.onCreate(savedInstanceState);
        EnterPinBinding binding = DataBindingUtil.setContentView(this, R.layout.enter_pin);
        viewModel = EnterViewModel.getInstance(this);
        binding.setViewModel(viewModel);
    }
}
