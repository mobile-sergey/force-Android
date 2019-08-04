package club.plus1.forcetaxi.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import club.plus1.forcetaxi.R;
import club.plus1.forcetaxi.databinding.CheckHistoryBinding;
import club.plus1.forcetaxi.viewmodel.CheckViewModel;

public class CheckHistoryActivity extends AppCompatActivity {

    private CheckViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CheckHistoryBinding binding = DataBindingUtil.setContentView(this, R.layout.check_history);
        viewModel = CheckViewModel.getInstance(this);
        binding.setViewModel(viewModel);
    }
}
