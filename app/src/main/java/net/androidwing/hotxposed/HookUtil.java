package net.androidwing.hotxposed;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;
import dalvik.system.DexFile;
import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

/**
 * Created by wing on 4/18/17.
 */

public class HookUtil implements IXposedHookLoadPackage {
  @Override public void handleLoadPackage(final XC_LoadPackage.LoadPackageParam loadPackageParam)
      throws Throwable {

    if (!loadPackageParam.packageName.equals("com.wingsofts.zoomimageheader")) {
      return;
    }

    XposedHelpers.findAndHookMethod("com.wingsofts.zoomimageheader.HomeActivity", loadPackageParam.classLoader,
        "onCreate", Bundle.class, new XC_MethodHook() {
          @Override protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
            super.beforeHookedMethod(param);
          }

          @Override protected void afterHookedMethod(MethodHookParam param) throws Throwable {
            super.afterHookedMethod(param);
            //Toast.makeText((Context) param.thisObject, "哈哈", Toast.LENGTH_SHORT).show();

            DexFile dexFile = new DexFile(Environment.getExternalStorageDirectory()+"/classes.dex");
            Class clazz = dexFile.loadClass("net.androidwing.hotfix.HotFix",loadPackageParam.classLoader);
            clazz.getDeclaredMethod("invoke", Activity.class).invoke(null,(Activity)param.thisObject);
          }
        });
  }
}
