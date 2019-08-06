package club.plus1.forcetaxi.view;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import club.plus1.forcetaxi.R;
import club.plus1.forcetaxi.databinding.CheckHistoryBinding;
import club.plus1.forcetaxi.databinding.CheckItemBinding;
import club.plus1.forcetaxi.viewmodel.CheckViewModel;
import club.plus1.forcetaxi.viewmodel.MenuViewModel;

public class CheckHistoryActivity extends AppCompatActivity {

    private CheckViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CheckHistoryBinding binding = DataBindingUtil.setContentView(this, R.layout.check_history);
        viewModel = CheckViewModel.getInstance(this);
        binding.setViewModel(viewModel);

        CheckItemBinding bindingItem = DataBindingUtil.setContentView(this, R.layout.check_item);
        viewModel = CheckViewModel.getInstance(this);
        bindingItem.setViewModel(viewModel);

        // определяем строковый массив
        final String[] history = new String[]{
                getString(R.string.check_text, "1000"),
                getString(R.string.check_text, "100"),
                getString(R.string.check_text, "200"),
                getString(R.string.check_text, "555.55"),
                getString(R.string.check_text, "33.00"),
        };

        // используем адаптер данных
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                R.layout.check_item, R.id.textCheck, history);

        binding.listHistory.setAdapter(adapter);
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
