package icarie.com.jetpacknav.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import icarie.com.jetpacknav.R;

public class PurpleFragment extends Fragment
{
  public PurpleFragment()
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
    View v = inflater.inflate(R.layout.fragment_purple, container, false);

    return v;
  }
}
