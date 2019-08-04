package club.plus1.forcetaxi.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import club.plus1.forcetaxi.R;
import club.plus1.forcetaxi.databinding.InnBindBinding;
import club.plus1.forcetaxi.viewmodel.InnViewModel;

public class InnBindActivity extends AppCompatActivity {

    private InnViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        InnBindBinding binding = DataBindingUtil.setContentView(this, R.layout.inn_bind);
        viewModel = InnViewModel.getInstance(this);
        binding.setViewModel(viewModel);
    }
}
