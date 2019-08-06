package club.plus1.forcetaxi.view;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import club.plus1.forcetaxi.R;
import club.plus1.forcetaxi.databinding.InnInfoBinding;
import club.plus1.forcetaxi.viewmodel.InnViewModel;
import club.plus1.forcetaxi.viewmodel.MenuViewModel;

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (MenuViewModel.onOptionsItemSelected(this, item.getItemId())) {
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }
}
