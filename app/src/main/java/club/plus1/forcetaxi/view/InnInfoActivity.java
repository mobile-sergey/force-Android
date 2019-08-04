package club.plus1.forcetaxi.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import club.plus1.forcetaxi.R;
import club.plus1.forcetaxi.databinding.InnInfoBinding;
import club.plus1.forcetaxi.viewmodel.InnViewModel;

public class InnInfoActivity extends AppCompatActivity {

    private InnViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        InnInfoBinding binding = DataBindingUtil.setContentView(this, R.layout.inn_info);
        viewModel = InnViewModel.getInstance(this);
        binding.webInnInfo.loadUrl(viewModel.urlInfo);
        binding.setViewModel(viewModel);
    }
}
