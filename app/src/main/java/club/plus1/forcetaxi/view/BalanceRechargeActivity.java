package club.plus1.forcetaxi.view;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import org.jetbrains.annotations.NotNull;

import club.plus1.forcetaxi.R;
import club.plus1.forcetaxi.databinding.BalanceRechargeBinding;
import club.plus1.forcetaxi.service.ActiveLog;
import club.plus1.forcetaxi.viewmodel.BalanceViewModel;
import club.plus1.forcetaxi.viewmodel.MenuViewModel;

public class BalanceRechargeActivity extends AppCompatActivity {

    private BalanceViewModel viewModel;
    private BalanceRechargeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActiveLog.getInstance().log();
        super.onCreate(savedInstanceState);
        viewModel = BalanceViewModel.getInstance(this);
        binding = DataBindingUtil.setContentView(this, R.layout.balance_recharge);
        binding.setViewModel(viewModel);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        ActiveLog.getInstance().log();
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NotNull MenuItem item) {
        ActiveLog.getInstance().log();
        if (MenuViewModel.onOptionsItemSelected(this, item.getItemId())) {
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }
}
