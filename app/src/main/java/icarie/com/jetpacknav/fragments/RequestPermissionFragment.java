package icarie.com.jetpacknav.fragments;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
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

public class RequestPermissionFragment extends Fragment
{
  private final static String TAG = RequestPermissionFragment.class.getSimpleName();

  private static final int PERMISSIONS_REQUEST = 0x0040;
  private static final int REQUEST_LOCATION = 1;
  private static final int REQUEST_OVERLAY = REQUEST_LOCATION + 1;
  private static final int REQUEST_PHONE_STATE = REQUEST_OVERLAY + 1;

  private int mCurrentRequest;
  private boolean isPermissionRequestOngoing;
  private NavController mNavController;

  public RequestPermissionFragment()
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
    Bundle bundle = getArguments();
    mCurrentRequest = bundle != null ? bundle.getInt("permission", 1) : 0;

    int layout;
    switch (mCurrentRequest)
    {
      case REQUEST_LOCATION:
        layout = R.layout.fragment_location_permission;
        break;
      case REQUEST_PHONE_STATE:
        layout = R.layout.fragment_read_phone_permission;
        break;
      case REQUEST_OVERLAY:
        layout = R.layout.fragment_overlay_permission;
        break;
      default:
        throw new IllegalStateException("Permission not handle");
    }

    Log.e(TAG, "layout inflated: " + mCurrentRequest);

    return inflater.inflate(layout, container, false);
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

    requestPermission();
  }

  private void requestPermission()
  {
    switch (mCurrentRequest)
    {
      case REQUEST_LOCATION:
        requestLocationPermissions();
        break;
      case REQUEST_PHONE_STATE:
        requestReadPhonePermissions();
        break;
      case REQUEST_OVERLAY:
        requestOverlayPermissions();
        break;
      default:
        break;
    }
  }

  @Override
  public void onStart()
  {
    super.onStart();

    if (!isPermissionRequestOngoing)
    {
      return;
    }

    isPermissionRequestOngoing = false;

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && Settings.canDrawOverlays(getContext()))
    {
      mNavController.navigateUp();
    }
  }

  private void requestOverlayPermissions()
  {
    if (isPermissionRequestOngoing)
    {
      return;
    }

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Settings.canDrawOverlays(getContext()))
    {
      Log.e(TAG, "requesting Overlay");

      isPermissionRequestOngoing = true;

      final Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + getActivity().getPackageName()));
      startActivityForResult(intent, PERMISSIONS_REQUEST);
    }
    else
    {
      Log.e(TAG, "already have Overlay");

      mNavController.navigateUp();
    }
  }

  private void requestReadPhonePermissions()
  {
    if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED)
    {
      Log.e(TAG, "requesting Phone");

      requestPermissions(new String[]{Manifest.permission.READ_PHONE_STATE}, PERMISSIONS_REQUEST);
    }
    else
    {
      Log.e(TAG, "already have phone");

      mNavController.navigateUp();
    }
  }

  private void requestLocationPermissions()
  {
    if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
      || ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
    {
      Log.e(TAG, "requesting Location");

      requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                         PERMISSIONS_REQUEST);
    }
    else
    {
      Log.e(TAG, "already have location");

      mNavController.navigateUp();
    }
  }

  @Override
  public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults)
  {
    switch (requestCode)
    {
      case PERMISSIONS_REQUEST:
      {
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED ||
          grantResults.length > 1 && grantResults[1] == PackageManager.PERMISSION_GRANTED)
        {
          Log.e(TAG, "onRequestPermissionsResult");

          mNavController.navigateUp();
        }
        else
        {
          Log.e(TAG, "Handle error");
        }
      }
    }
  }
}
