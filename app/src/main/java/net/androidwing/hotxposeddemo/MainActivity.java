package net.androidwing.hotxposeddemo;

import android.app.Activity;
import android.app.ActivityManager;
import android.os.Bundle;
import net.androidwing.hotxposed.HotXposed;

public class MainActivity extends Activity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ShellUtil.execCommand("am force-stop " +Constants.TARGET_PACKAGE_NAME,true);
  }
}
