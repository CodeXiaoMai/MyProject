# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in E:\AndroidSdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

#######################################自定义混淆的配置#########################################
# VerticalSeekBar start
-keepclassmembers class com.xiaomai.myproject.view.VerticalSeekBar {
    void onProgressRefresh(float, boolean);
}
# VerticalSeekBar end
#nohttp start
-dontwarn com.yolanda.nohttp.**
-keep class com.yolanda.nohttp.**{*;}
#nohttp end

########################################公共混淆的配置##########################################
#指定代码的压缩级别
-optimizationpasses 5
#包名不混合大小写
-dontusemixedcaseclassnames
#不忽略非公共的库类
-dontskipnonpubliclibraryclasses
#优化/不优化输入的类文件
-dontoptimize
#预校验
-dontpreverify
#混淆时是否记录日志
-verbose
#混淆时所采用的算法
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*
#忽略警告
-ignorewarning
#保护注解
-keepattributes *Annotation*
#保护反射的正常调用
-keepattributes Signature
-keepattributes EnclosingMethod

#不混淆哪些类
-keep public class * extends android.app.Fragment
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference
-keep public class com.android.vending.licensing.ILicensingService

#不混淆所有View的子类及其子类的get、set方法
-keep public class * extends android.view.View {
    public <init>(android.content.Context);
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
    public void set*(...);
    public *** get*();
}

#指定不混淆所有的JNI方法
-keepclasseswithmembernames class * {
    native <methods>;
}

#不混淆Activity中参数类型为View的所有方法
-keepclassmembers class * extends android.app.Activity {
   public void *(android.view.View);
}

#不混淆Parcelable和它的子类，还有Creator成员变量
-keep class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator *;
}

#不混淆Serializable接口
-keepnames class * implements java.io.Serializable

#不混淆Serializable接口的子类中指定的某些成员变量和方法
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    !static !transient <fields>;
    !private <fields>;
    !private <methods>;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}


#不混淆Enum类型的指定方法，如果混淆报错，
#建议直接使用上面的 -keepclassmembers class * implements java.io.Serializable即可
#-keepclassmembers enum * {
#    public static **[] values();
#    public static ** valueOf(java.lang.String);
#}


#不混淆R类里及其所有内部static类中的所有static变量字段
-keepclassmembers class **.R$* {
    public static <fields>;
}

#如果有用到Gson解析包的，直接添加下面这几行就能成功混淆，不然会报错。
-keep class sun.misc.Unsafe { *; }
-keep class com.google.gson.examples.android.model.** { *; }

#如果有用到WebView的JS调用接口，需加入如下规则。
-keepclassmembers class fqcn.of.javascript.interface.for.webview {
   public *;
}