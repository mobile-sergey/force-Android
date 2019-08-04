package club.plus1.forcetaxi.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import club.plus1.forcetaxi.R;
import club.plus1.forcetaxi.databinding.CheckStornoBinding;
import club.plus1.forcetaxi.viewmodel.CheckViewModel;

public class CheckStornoActivity extends AppCompatActivity {

    private CheckViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CheckStornoBinding binding = DataBindingUtil.setContentView(this, R.layout.check_storno);
        viewModel = CheckViewModel.getInstance(this);
        binding.setViewModel(viewModel);
    }
}
