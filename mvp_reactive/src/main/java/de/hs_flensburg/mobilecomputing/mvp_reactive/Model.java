package de.hs_flensburg.mobilecomputing.mvp_reactive;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

import java.util.List;

public class Model extends BroadcastReceiver {
    protected WifiManager wifiManager;

    protected PublishSubject<Intent> broadcastIntentStream = PublishSubject.create();

    public Model(WifiManager wifiManager) {
        this.wifiManager = wifiManager;
    }

    public Observable<Integer> getWifiState() {
        return broadcastIntentStream
            .filter((i) -> i.getAction().equals(WifiManager.NETWORK_STATE_CHANGED_ACTION))
            .flatMap((i) -> Observable.fromCallable(wifiManager::getWifiState))
            .startWith(wifiManager.getWifiState());
    }

    public Observable<List<ScanResult>> getScanResults() {
        return broadcastIntentStream
            .filter((i) -> i.getAction().equals(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION))
            .flatMap((i) -> Observable.fromCallable(wifiManager::getScanResults))
            .startWith(wifiManager.getScanResults());
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        broadcastIntentStream.onNext(intent);
    }

    public boolean startScan() {
        return wifiManager.startScan();
    }
}
