package club.plus1.forcetaxi.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import club.plus1.forcetaxi.R;
import club.plus1.forcetaxi.databinding.CheckStornoResultBinding;
import club.plus1.forcetaxi.viewmodel.CheckViewModel;

public class CheckStornoResultActivity extends AppCompatActivity {

    private CheckViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CheckStornoResultBinding binding = DataBindingUtil.setContentView(this, R.layout.check_storno_result);
        viewModel = CheckViewModel.getInstance(this);
        binding.setViewModel(viewModel);
    }
}
