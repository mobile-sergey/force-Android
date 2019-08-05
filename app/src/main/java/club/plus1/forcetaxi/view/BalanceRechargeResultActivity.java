package club.plus1.forcetaxi.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import club.plus1.forcetaxi.R;
import club.plus1.forcetaxi.databinding.BalanceRechargeResultBinding;
import club.plus1.forcetaxi.viewmodel.BalanceViewModel;

public class BalanceRechargeResultActivity extends AppCompatActivity {

    private BalanceViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BalanceRechargeResultBinding binding = DataBindingUtil.setContentView(this, R.layout.balance_recharge_result);
        viewModel = BalanceViewModel.getInstance(this);
        binding.setViewModel(viewModel);
    }
}
