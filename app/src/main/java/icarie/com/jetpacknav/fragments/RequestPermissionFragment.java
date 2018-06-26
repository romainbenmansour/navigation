package icarie.com.jetpacknav.fragments;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import icarie.com.jetpacknav.R;

public class LocationPermissionFragment extends Fragment
{
  private static final int PERMISSIONS_REQUEST_LOCATION = 0x0040;

  private NavController mNavController;

  public LocationPermissionFragment()
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
    View v = inflater.inflate(R.layout.fragment_location_permission, container, false);

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

    requestLocationPermissions();
  }

  private void requestLocationPermissions()
  {
    if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
      || ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
    {
      requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                         PERMISSIONS_REQUEST_LOCATION);
    }
    else
    {
      mNavController.navigate(R.id.action_locationPermissionFragment_to_overlayPermissionFragment);
    }
  }

  @Override
  public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults)
  {
    switch (requestCode)
    {
      case PERMISSIONS_REQUEST_LOCATION:
      {
        if (grantResults.length > 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED &&
          grantResults[1] == PackageManager.PERMISSION_GRANTED)
        {
          mNavController.navigate(R.id.action_locationPermissionFragment_to_overlayPermissionFragment);
        }
        else
        {
          mNavController.navigate(R.id.action_locationPermissionFragment_to_locationPermissionDeniedFragment);
        }
      }
    }
  }
}
