package icarie.com.jetpacknav.viewmodels;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

/**
 * @author romain on 25/06/18.
 */
public class HomeViewModel extends BaseObservable
{
  private String mName;
  private String mNext;

  public HomeViewModel(String name, String next)
  {
    mName = name;
    mNext = next;
  }

  public String getNext()
  {
    return mNext;
  }

  @Bindable
  public String getName()
  {
    return mName;
  }
}
