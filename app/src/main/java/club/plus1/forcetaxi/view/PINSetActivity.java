package club.plus1.forcetaxi.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import club.plus1.forcetaxi.R;
import club.plus1.forcetaxi.databinding.PinSetBinding;
import club.plus1.forcetaxi.viewmodel.PinViewModel;

public class PinSetActivity extends AppCompatActivity {

    private PinViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PinSetBinding binding = DataBindingUtil.setContentView(this, R.layout.pin_set);
        viewModel = PinViewModel.getInstance(this);
        binding.setViewModel(viewModel);
    }
}
