package icarie.com.jetpacknav.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import icarie.com.jetpacknav.R;

public class MainActivity extends AppCompatActivity
{
  private NavController mNavController;

  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.activity_main);

    mNavController = Navigation.findNavController(this, R.id.my_nav_host_fragment);

    NavigationUI.setupActionBarWithNavController(this, mNavController);
  }

  @Override
  public boolean onSupportNavigateUp()
  {
    return mNavController.navigateUp();
  }
}
