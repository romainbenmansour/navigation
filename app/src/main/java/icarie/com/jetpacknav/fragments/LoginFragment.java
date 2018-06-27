package icarie.com.jetpacknav.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.navigation.Navigation;
import icarie.com.jetpacknav.R;

public class LoginFragment extends Fragment
{
  public LoginFragment()
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
    View v = inflater.inflate(R.layout.fragment_login, container, false);

    v.findViewById(R.id.start).setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_loginFragment_to_purpleFragment));

    return v;
  }
}
