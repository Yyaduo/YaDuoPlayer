// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.android.library) apply false
}

allprojects {
    extra.apply {
        set("appName", "YaDuoPlayer")
        set("appPackageName", "com.yaduo.yaduoplayer")
        set("versionName", "1.5.3")
        set("versionCode", 10503)
        set("compileSdk", 34)
        set("buildToolsVersion", "34.0.0")
        set("minSdk", 26)
        set("targetSdk", 26)
        set("kotlin_version", "1.6.0")
    }
}