package com.lupa.trolli

import android.app.Application
import android.os.Build
import com.facebook.flipper.BuildConfig
import com.facebook.flipper.android.AndroidFlipperClient
import com.facebook.flipper.android.utils.FlipperUtils
import com.facebook.flipper.plugins.inspector.DescriptorMapping
import com.facebook.flipper.plugins.inspector.InspectorFlipperPlugin
import com.facebook.flipper.plugins.network.NetworkFlipperPlugin
import com.facebook.flipper.plugins.sharedpreferences.SharedPreferencesFlipperPlugin
import com.facebook.soloader.SoLoader
import com.lupa.core.di.CoreModuleContainer
import com.lupa.core.local.DataPreferences
import com.lupa.profile.data.di.ProfileModuleContainer
import com.lupa.shared.profile.di.ProfileInteractorModule
import com.lupa.trolli.di.HomePageModuleContainer
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        flipperIntegration()
        val coreModuleContainer = CoreModuleContainer()
        val homepageModuleContainer = HomePageModuleContainer()
        val profileModuleContainer = ProfileModuleContainer()

        val profileInteractorModuleContainer = ProfileInteractorModule()

        startKoin {
            androidContext(this@MainApplication)
            modules(
                coreModuleContainer.modules() +
                        profileModuleContainer.modules() +
                        homepageModuleContainer.modules() +
                        profileInteractorModuleContainer.modules()
            )
        }
    }

    private fun flipperIntegration() {
        val isVersionAboveNougat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.N
        if (isVersionAboveNougat) {
            SoLoader.init(this, false)
            if (BuildConfig.DEBUG && FlipperUtils.shouldEnableFlipper(this)) {
                val flipperClient = AndroidFlipperClient.getInstance(this)
                    .apply {
                        val plugin = InspectorFlipperPlugin(
                            this@MainApplication,
                            DescriptorMapping.withDefaults()
                        )
                        // ENTAR AJA
                        // NetworkFlipperPlugin networkFlipperPlugin = new NetworkFlipperPlugin();
                        //new NetworkingModule.CustomClientBuilder() {
                        //    @Override
                        //    public void apply(OkHttpClient.Builder builder) {
                        //        builder.addNetworkInterceptor(new FlipperOkhttpInterceptor(networkFlipperPlugin));
                        //    }
                        //});
                        //client.addPlugin(networkFlipperPlugin);

                        val networkPlugin = NetworkFlipperPlugin()

                        // client.addPlugin(
                        //    new SharedPreferencesFlipperPlugin(context, "my_shared_preference_file"));

                        val preferencesPlugin = SharedPreferencesFlipperPlugin(
                            this@MainApplication,
                            DataPreferences.NAME
                        )
                        addPlugin(plugin)
                        addPlugin(networkPlugin)
                        addPlugin(preferencesPlugin)
                    }
                flipperClient.start()
            }
        }
    }
}