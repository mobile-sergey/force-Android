package club.plus1.forcetaxi.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import club.plus1.forcetaxi.R;
import club.plus1.forcetaxi.databinding.InnSearchBinding;
import club.plus1.forcetaxi.viewmodel.InnViewModel;

public class InnSearchActivity extends AppCompatActivity {

    private InnViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        InnSearchBinding binding = DataBindingUtil.setContentView(this, R.layout.inn_search);
        viewModel = InnViewModel.getInstance(this);
        binding.setViewModel(viewModel);
    }
}
