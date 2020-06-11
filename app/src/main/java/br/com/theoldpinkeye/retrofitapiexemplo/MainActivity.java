package br.com.theoldpinkeye.retrofitapiexemplo;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import br.com.theoldpinkeye.retrofitapiexemplo.databinding.ActivityMainBinding;
import br.com.theoldpinkeye.retrofitapiexemplo.model.GithubModel;
import br.com.theoldpinkeye.retrofitapiexemplo.view.MainViewModel;

public class MainActivity extends AppCompatActivity {

  private ActivityMainBinding binding;
  private MainViewModel viewModel;
  private GithubModel model;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
    binding.setModel(viewModel = new MainViewModel());
    model = new GithubModel(viewModel);

    binding.goButton.setOnClickListener(v -> {
      viewModel.setPb(true);
      model.getUser(binding.usernameEditText.getText().toString());
    });
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings) {
      return true;
    }

    return super.onOptionsItemSelected(item);
  }
}