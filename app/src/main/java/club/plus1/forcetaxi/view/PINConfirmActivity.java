package club.plus1.forcetaxi.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import club.plus1.forcetaxi.R;
import club.plus1.forcetaxi.databinding.PinConfirmBinding;
import club.plus1.forcetaxi.viewmodel.PinViewModel;

public class PinConfirmActivity extends AppCompatActivity {

    private PinViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PinConfirmBinding binding = DataBindingUtil.setContentView(this, R.layout.pin_confirm);
        viewModel = new PinViewModel(this);
        binding.setViewModel(viewModel);
    }
}
