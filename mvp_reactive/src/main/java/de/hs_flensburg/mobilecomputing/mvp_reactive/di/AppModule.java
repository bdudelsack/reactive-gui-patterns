package de.hs_flensburg.mobilecomputing.mvp_reactive.di;

import android.net.wifi.WifiManager;
import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;
import de.hs_flensburg.mobilecomputing.mvp_reactive.*;

import javax.inject.Singleton;


@Module
public abstract class AppModule {
    @Provides
    public static WifiManager providesWifiManager(Application app) {
        return app.getSystemService(WifiManager.class);
    }

    @Provides
    public static IPresenter providesPresenter(Model model) {
        return new Presenter(model);
    }

    @Provides
    @Singleton
    public static Model providesModel(WifiManager wifiManager) {
        return new Model(wifiManager);
    }

    @ContributesAndroidInjector
    abstract ViewActivity contributeActivityInjector();
}
