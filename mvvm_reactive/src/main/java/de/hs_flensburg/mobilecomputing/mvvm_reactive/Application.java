package de.hs_flensburg.mobilecomputing.mvvm_reactive;


import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;
import de.hs_flensburg.mobilecomputing.mvvm_reactive.di.DaggerAppComponent;

import javax.inject.Inject;

public class Application extends DaggerApplication {

    @Inject
    WifiStateBroadcastReceiver wifiStateBroadcastReceiver;

    @Override
    public void onCreate() {
        super.onCreate();

        IntentFilter filter = new IntentFilter(WifiManager.NETWORK_STATE_CHANGED_ACTION);
        filter.addAction(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION);

        this.registerReceiver(wifiStateBroadcastReceiver, filter);
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder().create(this);
    }
}
