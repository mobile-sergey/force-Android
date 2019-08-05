package club.plus1.forcetaxi.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import club.plus1.forcetaxi.R;
import club.plus1.forcetaxi.databinding.BalanceSberbankBinding;
import club.plus1.forcetaxi.viewmodel.BalanceViewModel;

public class BalanceSberbankActivity extends AppCompatActivity {

    private BalanceViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BalanceSberbankBinding binding = DataBindingUtil.setContentView(this, R.layout.balance_sberbank);
        viewModel = BalanceViewModel.getInstance(this);
        binding.setViewModel(viewModel);
    }
}
