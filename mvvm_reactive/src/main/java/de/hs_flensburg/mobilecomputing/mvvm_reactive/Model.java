package de.hs_flensburg.mobilecomputing.mvvm_reactive;

import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;

import java.util.List;

public class Model {
    protected WifiManager wifiManager;

    public Model(WifiManager wifiManager) {
        this.wifiManager = wifiManager;
    }

    public int getWifiState() {
        return wifiManager.getWifiState();
    }

    public boolean startScan() {
        return wifiManager.startScan();
    }

    public List<ScanResult> getScanResults() {
        return wifiManager.getScanResults();
    }
}
