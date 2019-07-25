package club.plus1.forcetaxi.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import club.plus1.forcetaxi.R;
import club.plus1.forcetaxi.databinding.EnterResultBinding;
import club.plus1.forcetaxi.viewmodel.EnterViewModel;

public class EnterResultActivity extends AppCompatActivity {

    private EnterViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EnterResultBinding binding = DataBindingUtil.setContentView(this, R.layout.enter_result);
        viewModel = new EnterViewModel(this);
        binding.setEnter(viewModel);
    }
}
