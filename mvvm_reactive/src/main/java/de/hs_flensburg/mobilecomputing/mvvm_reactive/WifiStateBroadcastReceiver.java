package de.hs_flensburg.mobilecomputing.mvvm_reactive;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.util.Log;

public class WifiStateBroadcastReceiver extends BroadcastReceiver {
    protected ViewModel viewModel;

    public WifiStateBroadcastReceiver(ViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equals(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION)) {
            viewModel.notifyScanResultsAvailable();
        } else if(intent.getAction().equals(WifiManager.NETWORK_STATE_CHANGED_ACTION)) {
            viewModel.notifyNetworkStateChanged();
        }
    }
}
