package de.hs_flensburg.mobilecomputing.mvvm_reactive;

import android.databinding.ObservableField;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;

import javax.inject.Inject;
import java.util.List;

public class ViewModel {

    public final ObservableField<String> wifiState = new ObservableField<>();
    public final ObservableField<String> wifiScanResults = new ObservableField<>();

    protected Model model;

    @Inject
    public ViewModel(Model model) {
        this.model = model;

        wifiState.set(convertWifiStateToString(model.getWifiState()));
        wifiScanResults.set(convertWifiScanResultsToString(model.getScanResults()));
    }

    public String convertWifiStateToString(int state) {
        switch(state) {
            case WifiManager.WIFI_STATE_DISABLED:
                return "ausgeschaltet";

            case WifiManager.WIFI_STATE_DISABLING:
                return "wird ausgeschaltet";

            case WifiManager.WIFI_STATE_ENABLED:
                return "eingeschaltet";

            case WifiManager.WIFI_STATE_ENABLING:
                return "wird eingeschaltet";

            case WifiManager.WIFI_STATE_UNKNOWN:
            default:
                return "unbekannt";
        }
    }

    public String convertWifiScanResultsToString(List<ScanResult> results) {

        String result = "";

        for(ScanResult res : results) {
            String ssid = res.SSID.isEmpty() ? "<none>" : res.SSID;
            result += String.format("%s: %ddBm\n", ssid, res.level);
        }

        return result;
    }

    public void startScan() {
        wifiScanResults.set("Wird gescannt ...");
        model.startScan();
    }

    public void notifyScanResultsAvailable() {
        String txt = convertWifiScanResultsToString(model.getScanResults());
        wifiScanResults.set(txt);
    }

    public void notifyNetworkStateChanged() {
        int state = model.getWifiState();
        wifiState.set(convertWifiStateToString(state));
    }
}
