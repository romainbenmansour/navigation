package icarie.com.jetpacknav.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.navigation.Navigation;
import icarie.com.jetpacknav.R;

public class GreenFragment extends Fragment
{
  private Button mNext;
  private Button mPrevious;

  public GreenFragment()
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
    View v = inflater.inflate(R.layout.fragment_green, container, false);

    mNext = v.findViewById(R.id.next);
    mPrevious = v.findViewById(R.id.previous);

    mPrevious.setOnClickListener(new View.OnClickListener()
    {
      @Override
      public void onClick(View v)
      {
        Navigation.findNavController(v).popBackStack();
      }
    });

    mNext.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.blueFragment, null));

    return v;
  }
}
