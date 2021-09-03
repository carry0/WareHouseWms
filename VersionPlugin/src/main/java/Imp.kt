/**
 *@Author
 *@cerate 2021/8/11 16:43
 **/
object Imp {

    const val ali_version = "1.3.1"
    const val ali_compiler_version = "1.1.4"
    const val room_version = "2.1.0-alpha03"
    const val retrofit2_version = "2.9.0"
    const val OkHttp_version = "4.9.0"
    const val rxjava2_version = "2.1.4"
    const val rx_android_version = "2.0.2"
    const val material_version = "1.2.0"

    const val navigation_version = "2.3.5"
    const val constraintlayout_version = "2.0.4"
    const val legacy_version = "1.0.0"
    const val annotations_version = "15.0"
    const val appcompat_version = "1.2.0"
    const val junit_version = "4.13.2"
    const val ext_junit_version = "1.1.3"
    const val espresso_version = "3.4.0"

    const val glide_version = "4.11.0"

    //====================================build.gradle中原有的依赖================================================//
    const val appcompat = "androidx.appcompat:appcompat:${appcompat_version}"

    const val constraintlayout = "androidx.constraintlayout:constraintlayout:${constraintlayout_version}"

    const val legacy_support_v4 = "androidx.legacy:legacy-support-v4:${legacy_version}"

    const val annotations = "org.jetbrains:annotations:${annotations_version}"

    const val junit = "junit:junit:${junit_version}"

    const val ext_junit = "androidx.test.ext:junit:${ext_junit_version}"

    const val espresso: String = "androidx.test.espresso:espresso-core:${espresso_version}"
    //====================================================================================//


    //-------------------------------ali-arouter-----------------------------------//
    const val arouter = "com.alibaba:arouter-api:${ali_version}"
    const val arouter_compiler = ("com.alibaba:arouter-compiler:${ali_compiler_version}")

    //---------------------------------------room-------------------------------------//
    const val room = "androidx.room:room-runtime:${room_version}"
    const val room_compiler = "androidx.room:room-compiler:${room_version}" // use kapt for Kotlin

    // optional - RxJava support for Room
    const val room_rxjava2 = "androidx.room:room-rxjava2:${room_version}"

    // optional - Guava support for Room, including Optional and ListenableFuture
    const val room_guava = "androidx.room:room-guava:${room_version}"

    // optional - Coroutines support for Room
    const val room_coroutines = "androidx.room:room-coroutines:${room_version}"

    // Test helpers
    const val room_testing = "androidx.room:room-testing:${room_version}"

    //---------------------------------------OkHttp3-------------------------------------//
    const val okhttp3 = ("com.squareup.okhttp3:okhttp:${OkHttp_version}")
    const val okhttp3_logging = ("com.squareup.okhttp3:logging-interceptor:${OkHttp_version}")

    //---------------------------------------retrofit2-------------------------------------//
    const val retrofit2 = ("com.squareup.retrofit2:retrofit:${retrofit2_version}")
    const val retrofit2_gson = ("com.squareup.retrofit2:converter-gson:${retrofit2_version}")
    const val retrofit2_adapter = ("com.squareup.retrofit2:adapter-rxjava2:${retrofit2_version}")

    //---------------------------------------rxjava2-------------------------------------//
    const val rxjava2 = ("io.reactivex.rxjava2:rxjava:${rxjava2_version}")
    const val rx_android = ("io.reactivex.rxjava2:rxandroid:${rx_android_version}")

    //---------------------------------------materialUI工具-------------------------------------//
    const val material = ("com.google.android.material:material:${material_version}")

    const val glide = "com.github.bumptech.glide:glide:${glide_version}"

    const val glide_compiler = "com.github.bumptech.glide:compiler:${glide_version}"

    const val network_model = ":network"

    const val navigation_fragment = "androidx.navigation:navigation-fragment:${navigation_version}"
    const val navigation_ui = "androidx.navigation:navigation-ui:${navigation_version}"
}