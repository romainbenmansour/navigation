package icarie.com.jetpacknav.fragments;

import android.Manifest;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import icarie.com.jetpacknav.R;

import static android.content.Context.MODE_PRIVATE;

public class HomeFragment extends Fragment
{
  public final String TAG = HomeFragment.class.getSimpleName();
  public static final String FIRST_LAUNCH = "first_launch";
  private NavController mNavController;

  private boolean mIsFirstLaunch;

  private SharedPreferences mPreferences;

  public HomeFragment()
  {
    // Required empty public constructor
  }

  @Override
  public void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState)
  {
    // Inflate the layout for this fragment
    View v = inflater.inflate(R.layout.fragment_home, container, false);

    mPreferences = getActivity().getSharedPreferences("TOTO", MODE_PRIVATE);

    return v;
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
  {
    super.onViewCreated(view, savedInstanceState);

    mNavController = Navigation.findNavController(view);
  }

  @Override
  public void onResume()
  {
    super.onResume();

    mIsFirstLaunch = mPreferences.getBoolean("first_launch", true);

    if (mIsFirstLaunch)
    {
      mPreferences.edit().putBoolean(FIRST_LAUNCH, false).apply();

      mNavController.navigate(R.id.action_homeFragment_to_greenFragment);
    }
    else if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
    {
      mNavController.navigate(R.id.action_homeFragment_to_yellowFragment);
    }
    else
    {
      mNavController.navigate(R.id.action_homeFragment_to_purpleFragment);
    }
  }
}
