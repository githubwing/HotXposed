package net.androidwing.hotxposeddemo;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;
import net.androidwing.hotxposed.IHookerDispatcher;

/**
 * Created  on 2018/3/30.
 */
public class HookerDispatcher implements IHookerDispatcher {
  @Override public void dispatch(XC_LoadPackage.LoadPackageParam loadPackageParam) {

    if(!Constants.TARGET_PACKAGE_NAME.equals(loadPackageParam.packageName)){
      return;
    }
    XposedHelpers.findAndHookMethod("com.wingsofts.zoomimageheader.HomeActivity",
        loadPackageParam.classLoader, "onCreate", Bundle.class, new XC_MethodHook() {
          @Override protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
            super.beforeHookedMethod(param);
          }

          @Override protected void afterHookedMethod(XC_MethodHook.MethodHookParam param)
              throws Throwable {
            super.afterHookedMethod(param);
            Toast.makeText((Context) param.thisObject, "你在看什么", Toast.LENGTH_SHORT).show();
          }
        });
  }
}
