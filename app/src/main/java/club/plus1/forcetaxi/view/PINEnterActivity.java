package club.plus1.forcetaxi.view;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import club.plus1.forcetaxi.R;
import club.plus1.forcetaxi.databinding.PinEnterBinding;
import club.plus1.forcetaxi.viewmodel.PinViewModel;

public class PinEnterActivity extends AppCompatActivity {

    private PinViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("Force", "PinEnterActivity::onCreate()");
        super.onCreate(savedInstanceState);
        PinEnterBinding binding = DataBindingUtil.setContentView(this, R.layout.pin_enter);
        viewModel = PinViewModel.getInstance(this);
        binding.setViewModel(viewModel);
    }
}
