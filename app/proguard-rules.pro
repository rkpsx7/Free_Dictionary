# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

-keepattributes *Annotation*
-keepattributes Signature
-keep class java.lang.reflect.** { *; }
-dontwarn kotlin.reflect.jvm.internal.**

-keep class kotlin.reflect.jvm.internal.** { *; }


-keep class com.dev_akash.freedictionary.core.ApplicationKClass

# the {*;} at the end means that KEEP ALL VARIABLES of the class
#-keep class com.dev_akash.freedictionary.feature_dictionary.data.remote.dto.WordInfoDto {*;}

# Alternatively you can mark complete package to keep intact // the dto.** means, keep all classes in dto package
-keep class com.dev_akash.freedictionary.feature_dictionary.data.remote.dto.** {*;}
-keep class com.dev_akash.freedictionary.feature_dictionary.data.local.** {*;}
-keep class com.dev_akash.freedictionary.feature_dictionary.domain.model.** {*;}
-keep class com.dev_akash.freedictionary.feature_dictionary.data.util.** {*;}
-keep class com.dev_akash.freedictionary.utils.** {*;}

## Retrofit
-keep class com.google.gson.** { *; }
-keep public class com.google.gson.** {public private protected *;}
#-keep class com.google.inject.** { *; }
#-keep class org.apache.http.** { *; }
#-keep class org.apache.james.mime4j.** { *; }
#-keep class javax.inject.** { *; }
#-keep class javax.xml.stream.** { *; }
#-keep class retrofit.** { *; }
#-keep class com.google.appengine.** { *; }
#-keepattributes *Annotation*
#-keepattributes Signature
#-dontwarn com.squareup.okhttp.*
#-dontwarn rx.**
#-dontwarn javax.xml.stream.**
#-dontwarn com.google.appengine.**
-dontwarn java.nio.file.**
#-dontwarn org.codehaus.**




##-Okhttp
#-keepattributes Signature
#-keepattributes Annotation
#-keep class okhttp3.** { *; }
#-keep interface okhttp3.** { *; }
#-dontwarn okhttp3.**
#-dontwarn okio.**













# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

-dontwarn kotlin.reflect.jvm.internal.**

-keep class kotlin.reflect.jvm.internal.** { *; }

-keep interface javax.annotation.Nullable

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

# Glide
#-keep public class * implements com.bumptech.glide.module.GlideModule
#-keep public class * extends com.bumptech.glide.module.AppGlideModule

-keepattributes Signature,RuntimeVisibleAnnotations,AnnotationDefault

-keep class com.google.android.gms.** { *; }
-dontwarn com.google.android.gms.*
-keep class com.google.api.client.** {*;}

# JSR 305 annotations are for embedding nullability information.
-dontwarn javax.annotation.**

#### OkHttp, Retrofit
-dontwarn okhttp3.**
-dontwarn okio.**
-dontwarn javax.annotation.**
-keepclasseswithmembers class * {
    @retrofit2.http.* <methods>;
}

-keepclasseswithmembers class * {
    @com.squareup.moshi.* <methods>;
}

-keep class kotlin.Metadata { *; }
-keepclassmembers class kotlin.Metadata {
    public <methods>;
}

-keepnames @kotlin.Metadata class com.yourpackage.model.**
-keep class com.yourpackage.model.** { *; }
-keepclassmembers class com.yourpackage.model.** { *; }

# Retrofit does reflection on generic parameters. InnerClasses is required to use Signature and
# EnclosingMethod is required to use InnerClasses.
-keepattributes Signature, InnerClasses, EnclosingMethod

# Retrofit does reflection on method and parameter annotations.
-keepattributes RuntimeVisibleAnnotations, RuntimeVisibleParameterAnnotations

# Keep annotation default values (e.g., retrofit2.http.Field.encoded).
-keepattributes AnnotationDefault

# Retain service method parameters when optimizing.
-keepclassmembers,allowshrinking,allowobfuscation interface * {
    @retrofit2.http.* <methods>;
}

# Ignore annotation used for build tooling.

# Ignore JSR 305 annotations for embedding nullability information.
-dontwarn javax.annotation.**

# Guarded by a NoClassDefFoundError try/catch and only used when on the classpath.
-dontwarn kotlin.Unit

# Top-level functions that can only be used by Kotlin.
-dontwarn retrofit2.KotlinExtensions
-dontwarn retrofit2.KotlinExtensions.*

# With R8 full mode, it sees no subtypes of Retrofit interfaces since they are created with a Proxy
# and replaces all potential values with null. Explicitly keeping the interfaces prevents this.
-if interface * { @retrofit2.http.* <methods>; }
-keep,allowobfuscation interface <1>

# Keep generic signature of Call, Response (R8 full mode strips signatures from non-kept items).
-keep,allowobfuscation,allowshrinking interface retrofit2.Call
-keep,allowobfuscation,allowshrinking class retrofit2.Response

# With R8 full mode generic signatures are stripped for classes that are not
# kept. Suspend functions are wrapped in continuations where the type argument
# is used.
-keep,allowobfuscation,allowshrinking class kotlin.coroutines.Continuation
