package club.plus1.forcetaxi.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import club.plus1.forcetaxi.R;
import club.plus1.forcetaxi.databinding.InnSetResultBinding;
import club.plus1.forcetaxi.viewmodel.InnViewModel;

public class InnSetResultActivity extends AppCompatActivity {

    private InnViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        InnSetResultBinding binding = DataBindingUtil.setContentView(this, R.layout.inn_set_result);
        viewModel = InnViewModel.getInstance(this);
        binding.setViewModel(viewModel);
    }
}
