package de.hs_flensburg.mobilecomputing.mvp_reactive;

import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import io.reactivex.disposables.CompositeDisposable;

import javax.inject.Inject;
import java.util.List;

public class Presenter implements IPresenter {
    private static final String TAG = Presenter.class.getName();

    protected Model model;
    protected IView view;

    protected CompositeDisposable compositeDisposable;

    public Presenter(Model model) {
        this.model = model;
    }

    @Override
    public void subscribe(IView view) {
        this.view = view;

        compositeDisposable = new CompositeDisposable();

        compositeDisposable.add(
            model.getWifiState()
                .map(this::mapWifiStateToString)
                .subscribe((v) -> view.setWifiStatus(v))
        );

        compositeDisposable.add(
            model.getScanResults()
                .map(this::mapWifiScanResultsToString)
                .subscribe((v) -> view.setWifiScanResult(v))
        );
    }

    @Override
    public void unsubscribe() {
        if(compositeDisposable != null && !compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
        }
    }

    @Override
    public boolean startScan() {
        view.setWifiScanResult("Scanning ...");

        return model.startScan();
    }

    public String mapWifiStateToString(int state) {
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

    public String mapWifiScanResultsToString(List<ScanResult> results) {

        String result = "";

        for(ScanResult res : results) {
            String ssid = res.SSID.isEmpty() ? "<none>" : res.SSID;
            result += String.format("%s: %ddBm\n", ssid, res.level);
        }

        return result;
    }
}
