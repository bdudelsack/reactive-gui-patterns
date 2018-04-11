package de.hs_flensburg.mobilecomputing.mvvm_reactive.di;

import android.net.wifi.WifiManager;
import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;
import de.hs_flensburg.mobilecomputing.mvvm_reactive.*;

import javax.inject.Singleton;


@Module
public abstract class AppModule {
    @Provides
    @Singleton
    public static ViewModel providesViewModel(Model model) {
        return new ViewModel(model);
    }

    @Provides
    public static WifiManager providesWifiManager(Application app) {
        return app.getSystemService(WifiManager.class);
    }

    @Provides
    @Singleton
    public static Model providesModel(WifiManager wifiManager) {
        return new Model(wifiManager);
    }

    @Provides
    public static WifiStateBroadcastReceiver providesWifiStateBroadcastReceiver(ViewModel viewModel) {
        return new WifiStateBroadcastReceiver(viewModel);
    }

    @ContributesAndroidInjector
    abstract ViewActivity contributeActivityInjector();
}
