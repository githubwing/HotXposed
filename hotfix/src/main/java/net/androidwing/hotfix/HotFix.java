package net.androidwing.hotfix;

import android.app.Activity;
import android.widget.Toast;

/**
 * Created by wing on 4/19/17.
 */

public class HotFix {

  public static void invoke(Activity activity) {
    Toast.makeText(activity, "xposed也要热更新", Toast.LENGTH_SHORT).show();
  }
}
