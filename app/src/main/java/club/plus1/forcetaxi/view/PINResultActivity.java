package club.plus1.forcetaxi.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import club.plus1.forcetaxi.R;
import club.plus1.forcetaxi.databinding.PinResultBinding;
import club.plus1.forcetaxi.viewmodel.PinViewModel;

public class PinResultActivity extends AppCompatActivity {

    private PinViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PinResultBinding binding = DataBindingUtil.setContentView(this, R.layout.pin_result);
        viewModel = new PinViewModel(this);
        binding.setViewModel(viewModel);
    }
}
