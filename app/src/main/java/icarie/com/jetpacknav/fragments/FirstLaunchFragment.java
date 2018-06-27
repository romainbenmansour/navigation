package icarie.com.jetpacknav.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.navigation.Navigation;
import icarie.com.jetpacknav.R;

public class FirstLaunchFragment extends Fragment
{
  public FirstLaunchFragment()
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
    View v = inflater.inflate(R.layout.fragment_first_launch, container, false);

    v.findViewById(R.id.start).setOnClickListener(new View.OnClickListener()
    {
      @Override
      public void onClick(View v)
      {
        Navigation.findNavController(v).navigateUp();
      }
    });

    return v;
  }
}
