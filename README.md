# AndFixDemo
andFix热修复框架demo
博客地址http://blog.csdn.net/jinfulin/article/details/51775640

一.什么是AndFix

AndFix，全称是Android hot-fix。是阿里开源的一个Android热补丁框架，允许APP在不重新发布版本的情况下修复线上的bug。支持Android 2.3 到 6.0。

二.使用方式

1.首先添加依赖

dependencies {
    compile 'com.alipay.euler:andfix:0.4.0@aar'
}

2.初始化数据(最好写在application的oncreate中)

// initialize
            mPatchManager = new PatchManager(this);
            //        注意每次appversion变更都会导致所有补丁被删除,如果appversion没有改变，则会加载已经保存的所有补丁。
            String appversion = getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
            mPatchManager.init(appversion);
            mPatchManager.loadPatch();


3.加载数据(addpath)

//addPatch
            //在需要的地方调用PatchManager的addPatch方法加载新补丁，比如可以在下载补丁文件之后调用
            String patchFileString = Environment.getExternalStorageDirectory()
                    .getAbsolutePath() + APATCH_PATH;
            mPatchManager.addPatch(patchFileString);

4.,复制且加载补丁成功后，删除下载的补丁

 File f = new File(this.getFilesDir(), DIR + APATCH_PATH);
            if (f.exists()) {
                boolean result = new File(patchFileString).delete();
                if (!result)
                    Log.e(TAG, patchFileString + " delete fail");
            }

5.生成打包补丁

之后就是打补丁的过程了，首先生成一个apk文件，然后更改代码，在修复bug后生成另一个apk。
通过官方提供的工具apkpatch
生成一个.apatch格式的补丁文件，需要提供原apk，修复后的apk，以及一个签名文件。
可以直接使用命令apkpatch查看具体的使用方法。
使用示例：
apkpatch -o D:/Patch/ -k debug.keystore -p android-a androiddebugkey -e android f bug-fix.apk t release.apk

三.局限性

1.无法添加类和字段
2.不能修改xml布局文件

四.demo下载
https://github.com/jin870132/AndFixDemo


