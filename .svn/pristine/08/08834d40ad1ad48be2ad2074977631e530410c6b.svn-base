package com.cqg.xposed.demo;

import android.util.Log;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

/**
 * Created by chen on 2016/9/19.
 */
public class SimpleModule implements IXposedHookLoadPackage {
    private static final String TAG = "SimpleModule";

    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam loadPackageParam) throws Throwable {
        XposedBridge.log("应用名:" + loadPackageParam.packageName);

        if (loadPackageParam.packageName.equals("cqg.com.xpoesddemo")) {
            Class clazz = loadPackageParam.classLoader.loadClass("com.cqg.xposed.activity.MainActivity");

            XposedHelpers.findAndHookMethod(clazz, "getStr", new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    super.afterHookedMethod(param);
                    // Log.i(TAG, "afterHookedMethod: 劫持前");
                }

                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {

                    param.setResult("内容已被篡改！！！");
                    super.beforeHookedMethod(param);
                    // Log.i(TAG, "afterHookedMethod: 劫持后");
                }

            });


            Class clazz1 = loadPackageParam.classLoader.loadClass("android.telephony.TelephonyManager");
            XposedHelpers.findAndHookMethod(clazz1, "getDeviceId", new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                }

                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    param.setResult("12345678910");
                    //super.beforeHookedMethod(param);
                    Log.i(TAG, "劫持之后的imei: " + param.getResult());

                }
            });


//
        }

    }
}
