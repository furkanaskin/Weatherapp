object Versions {
    // gradle
    const val googleGradleVersion = "4.3.8"
    const val buildGradleVersion = "4.2.2"
    const val kotlinVersion = "1.5.21"
    const val navigationGradleVersion = "2.4.0-alpha04"
    const val hiltVersion = "2.38"
    const val klintVersion = "0.41.0"

    // dependency
    const val supportVersion = "1.0.0"
    const val appCompatVersion = "1.3.0"
    const val materialDesignVersion = "1.4.0"
    const val coreKtxVersion = "1.6.0"

    const val lifecycleViewModelKtxVersion = "2.4.0-alpha02"
    const val lifecycleVersion = "2.2.0"
    const val roomVersion = "2.3.0"
    const val navigationVersion = "2.4.0-alpha04"

    const val rxJavaVersion = "2.2.21"
    const val rxKotlinVersion = "2.4.0"
    const val rxAndroidVersion = "2.1.1"
    const val rxAnimationsVersion = "1.0.0"
    const val retrofitVersion = "2.9.0"
    const val okHttpVersion = "5.0.0-alpha.2"
    const val moshiConverterVersion = "2.9.0"
    const val moshiVersion = "1.12.0"
    const val picassoVersion = "2.71828"
    const val threetenabpVersion = "1.3.1"
    const val shadowViewVersion = "0.0.3"
    const val algoliaVersion = "3.+"
    const val stethoVersion = "1.6.0"
    const val timberVersion = "4.7.1"

    const val coreTestingVersion = "2.1.0"
    const val mockKVersion = "1.12.0"
    const val truthExtVersion = "1.4.0"
    const val truthVersion = "1.1.3"
    const val androidxTestRunnerVersion = "1.4.0"
    const val espressoCoreVersion = "3.4.0"
    const val junitVersion = "4.12"
    const val junitExtVersion = "1.1.1"
    const val robolectricVersion = "4.6.1"
}

object BuildPlugins {
    const val googleGradle = "com.google.gms:google-services:${Versions.googleGradleVersion}"
    const val buildGradle = "com.android.tools.build:gradle:${Versions.buildGradleVersion}"
    const val kotlinGradle = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlinVersion}"
    const val navigationGradle = "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navigationGradleVersion}"
    const val hiltGradle = "com.google.dagger:hilt-android-gradle-plugin:${Versions.hiltVersion}"
}

object Dependencies {
    // kotlin
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlinVersion}"
    const val kotlinReflect = "org.jetbrains.kotlin:kotlin-reflect:${Versions.kotlinVersion}"

    // android support
    const val supportv4 = "androidx.legacy:legacy-support-v4:${Versions.supportVersion}"
    const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompatVersion}"
    const val materialDesign = "com.google.android.material:material:${Versions.materialDesignVersion}"
    const val recyclerView = "androidx.recyclerview:recyclerview:${Versions.supportVersion}"
    const val cardView = "androidx.cardview:cardview:${Versions.supportVersion}"
    const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtxVersion}"

    // lifecycle
    const val lifecycleExt = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycleVersion}"
    const val lifecycleAnnotation = "androidx.lifecycle:lifecycle-compiler:${Versions.lifecycleVersion}"
    const val liveDataKtx = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycleVersion}"
    const val lifecycleViewModelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycleViewModelKtxVersion}"

    // navigation
    const val navigationFragment = "androidx.navigation:navigation-fragment-ktx:${Versions.navigationVersion}"
    const val navigationUI = "androidx.navigation:navigation-ui-ktx:${Versions.navigationVersion}"

    // room
    const val roomRuntime = "androidx.room:room-runtime:${Versions.roomVersion}"
    const val roomCompiler = "androidx.room:room-compiler:${Versions.roomVersion}"

    // hilt
    const val hilt = "com.google.dagger:hilt-android:${Versions.hiltVersion}"
    const val hiltKapt = "com.google.dagger:hilt-compiler:${Versions.hiltVersion}"

    // rx
    const val rxJava = "io.reactivex.rxjava2:rxjava:${Versions.rxJavaVersion}"
    const val rxAndroid = "io.reactivex.rxjava2:rxandroid:${Versions.rxAndroidVersion}"
    const val rxKotlin = "io.reactivex.rxjava2:rxkotlin:${Versions.rxKotlinVersion}"
    const val rxAnimations = "com.mikhaellopez:rxanimation:${Versions.rxAnimationsVersion}"

    // network
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofitVersion}"
    const val okHttp = "com.squareup.okhttp3:okhttp:${Versions.okHttpVersion}"
    const val rxJavaAdapter = "com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofitVersion}"

    // moshi
    const val moshiConverter = "com.squareup.retrofit2:converter-moshi:${Versions.moshiConverterVersion}"
    const val moshi = "com.squareup.moshi:moshi:${Versions.moshiVersion}"
    const val moshiKotlin = "com.squareup.moshi:moshi-kotlin:${Versions.moshiVersion}"
    const val moshiKapt = "com.squareup.moshi:moshi-kotlin-codegen:${Versions.moshiVersion}"

    // picasso
    const val picasso = "com.squareup.picasso:picasso:${Versions.picassoVersion}"

    // shadowView
    const val shadowView = "com.loopeer.lib:shadow:${Versions.shadowViewVersion}"

    // threetenabp
    const val threetenabp = "com.jakewharton.threetenabp:threetenabp:${Versions.threetenabpVersion}"

    // algolia
    const val algolia = "com.algolia:algoliasearch-android:${Versions.algoliaVersion}"

    // stetho
    const val stethoCore = "com.facebook.stetho:stetho:${Versions.stethoVersion}"
    const val stethoOkHttp = "com.facebook.stetho:stetho-okhttp3:${Versions.stethoVersion}"
    const val stethoUrlConnection = "com.facebook.stetho:stetho-urlconnection:${Versions.stethoVersion}"

    // timber
    const val timber = "com.jakewharton.timber:timber:${Versions.timberVersion}"

    // test
    const val junit = "junit:junit:${Versions.junitVersion}"
    const val androidxTestRunner = "androidx.test:runner:${Versions.androidxTestRunnerVersion}"
    const val androidxTestRules = "androidx.test:rules:${Versions.androidxTestRunnerVersion}"
    const val junitTestExt = "androidx.test.ext:junit-ktx:${Versions.junitExtVersion}"
    const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espressoCoreVersion}"
    const val truth = "com.google.truth:truth:${Versions.truthVersion}"
    const val truthExt = "androidx.test.ext:truth:${Versions.truthExtVersion}"
    const val mockK = "io.mockk:mockk:${Versions.mockKVersion}"
    const val coreTesting = "androidx.arch.core:core-testing:${Versions.coreTestingVersion}"
    const val robolectric = "org.robolectric:robolectric:${Versions.robolectricVersion}"
}