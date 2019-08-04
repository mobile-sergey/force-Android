package club.plus1.forcetaxi.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import club.plus1.forcetaxi.R;
import club.plus1.forcetaxi.databinding.InnSetBinding;
import club.plus1.forcetaxi.viewmodel.InnViewModel;

public class InnSetActivity extends AppCompatActivity {

    private InnViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        InnSetBinding binding = DataBindingUtil.setContentView(this, R.layout.inn_set);
        viewModel = InnViewModel.getInstance(this);
        binding.setViewModel(viewModel);
    }
}
