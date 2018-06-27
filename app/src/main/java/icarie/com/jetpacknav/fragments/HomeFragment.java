package icarie.com.jetpacknav.fragments;

import android.Manifest;
import android.arch.lifecycle.ViewModelProviders;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
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
import icarie.com.jetpacknav.databinding.FragmentHomeBinding;
import icarie.com.jetpacknav.viewmodels.HomeViewModel;

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
    FragmentHomeBinding binding = FragmentHomeBinding.inflate(getLayoutInflater(), container, false);

    HomeViewModel homeViewModel = new HomeViewModel("TOTO", "TEST");

    binding.setHomeViewModel(homeViewModel);

    mPreferences = getActivity().getSharedPreferences("TOTO", MODE_PRIVATE);

    return binding.getRoot();
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

      mNavController.navigate(R.id.action_homeFragment_to_firstLaunchFragment);
    }
    else if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
    {
      Bundle bundle = new Bundle();
      bundle.putInt("permission", 1);
      mNavController.navigate(R.id.action_homeFragment_to_requestPermissionFragment, bundle);
    }
    else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Settings.canDrawOverlays(getContext()))
    {
      Bundle bundle = new Bundle();
      bundle.putInt("permission", 2);
      mNavController.navigate(R.id.action_homeFragment_to_requestPermissionFragment, bundle);
    }
    else if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED)
    {
      Bundle bundle = new Bundle();
      bundle.putInt("permission", 3);
      mNavController.navigate(R.id.action_homeFragment_to_requestPermissionFragment, bundle);
    }
    else
    {
      mNavController.navigate(R.id.action_homeFragment_to_purpleFragment);
    }
  }
}
