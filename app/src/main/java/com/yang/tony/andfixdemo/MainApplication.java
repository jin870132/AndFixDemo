package com.yang.tony.andfixdemo;

import android.app.Application;
import android.os.Environment;
import android.util.Log;

import com.alipay.euler.andfix.patch.PatchManager;

import java.io.File;
import java.io.IOException;

public class MainApplication extends Application {
    private static final String TAG = "euler";

    private static final String APATCH_PATH = "/out.apatch";

    private static final String DIR = "apatch";//补丁文件夹
    private PatchManager mPatchManager;

    @Override
    public void onCreate() {
        super.onCreate();
        try {
            // initialize
            mPatchManager = new PatchManager(this);
            //        注意每次appversion变更都会导致所有补丁被删除,如果appversion没有改变，则会加载已经保存的所有补丁。
            String appversion = getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
            mPatchManager.init(appversion);
            mPatchManager.loadPatch();

            //addPatch
            //在需要的地方调用PatchManager的addPatch方法加载新补丁，比如可以在下载补丁文件之后调用
            String patchFileString = Environment.getExternalStorageDirectory()
                    .getAbsolutePath() + APATCH_PATH;
            mPatchManager.addPatch(patchFileString);
            //复制且加载补丁成功后，删除下载的补丁
            File f = new File(this.getFilesDir(), DIR + APATCH_PATH);
            if (f.exists()) {
                boolean result = new File(patchFileString).delete();
                if (!result)
                    Log.e(TAG, patchFileString + " delete fail");
            }
        } catch (Exception e) {
            Log.e(TAG, "", e);
        }

    }

}
