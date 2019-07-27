package club.plus1.forcetaxi.view;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import club.plus1.forcetaxi.R;
import club.plus1.forcetaxi.databinding.EnterBinding;
import club.plus1.forcetaxi.viewmodel.EnterViewModel;

public class EnterActivity extends AppCompatActivity {

    private EnterViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("Force", "EnterActivity::onCreate()");
        super.onCreate(savedInstanceState);
        EnterBinding binding = DataBindingUtil.setContentView(this, R.layout.enter);
        viewModel = EnterViewModel.getInstance(this);
        binding.setViewModel(viewModel);
    }
}
