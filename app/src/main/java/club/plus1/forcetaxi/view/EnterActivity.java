package club.plus1.forcetaxi.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import club.plus1.forcetaxi.R;
import club.plus1.forcetaxi.databinding.EnterBinding;
import club.plus1.forcetaxi.viewmodel.EnterViewModel;

public class EnterActivity extends AppCompatActivity {

    private EnterViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EnterBinding binding = DataBindingUtil.setContentView(this, R.layout.enter);
        viewModel = new EnterViewModel(this);
        binding.setViewModel(viewModel);
    }
}
