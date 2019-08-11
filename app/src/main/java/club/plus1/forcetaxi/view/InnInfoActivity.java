package club.plus1.forcetaxi.view;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import org.jetbrains.annotations.NotNull;

import club.plus1.forcetaxi.R;
import club.plus1.forcetaxi.databinding.InnInfoBinding;
import club.plus1.forcetaxi.service.ActiveLog;
import club.plus1.forcetaxi.viewmodel.InnViewModel;
import club.plus1.forcetaxi.viewmodel.MenuViewModel;

public class InnInfoActivity extends AppCompatActivity {

    private InnViewModel viewModel;
    private InnInfoBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActiveLog.getInstance().log();
        super.onCreate(savedInstanceState);
        viewModel = InnViewModel.getInstance(this);
        binding = DataBindingUtil.setContentView(this, R.layout.inn_info);
        binding.webInnInfo.loadUrl(viewModel.url.get());
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
