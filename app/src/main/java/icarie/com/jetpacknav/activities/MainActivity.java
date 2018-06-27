package icarie.com.jetpacknav.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import icarie.com.jetpacknav.R;

public class MainActivity extends AppCompatActivity
{
  private final static String TAG = MainActivity.class.getSimpleName();

  private NavController mNavController;

  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.activity_main);

    mNavController = Navigation.findNavController(this, R.id.my_nav_host_fragment);

    NavigationUI.setupActionBarWithNavController(this, mNavController);

//    mNavController.setGraph(R.navigation.nav_graph);

    mNavController.addOnNavigatedListener(new NavController.OnNavigatedListener()
    {
      @Override
      public void onNavigated(@NonNull NavController controller, @NonNull NavDestination destination)
      {
        Log.e(TAG, destination.getLabel().toString());
      }
    });
  }

  @Override
  protected void onStart()
  {
    super.onStart();

    Log.e(TAG, "onStart");
  }


  @Override
  public boolean onSupportNavigateUp()
  {
    return mNavController.navigateUp();
  }
}
