package de.hs_flensburg.mobilecomputing.mvp_reactive;

import android.Manifest;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.widget.TextView;
import dagger.android.support.DaggerAppCompatActivity;
import de.hs_flensburg.mobilecomputing.mvp_reactive.di.DaggerAppComponent;

import javax.inject.Inject;

public class ViewActivity extends DaggerAppCompatActivity implements IView {

    protected TextView txtWifiStatus;
    protected TextView txtWifiScanResult;

    protected FloatingActionButton fab;

    @Inject
    protected IPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(this.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            this.requestPermissions(new String[]{ Manifest.permission.ACCESS_FINE_LOCATION }, 0);
        } else {
            setContentView(R.layout.activity_view);

            txtWifiStatus = (TextView)findViewById(R.id.textWifiStatus);
            txtWifiScanResult = (TextView)findViewById(R.id.textWifiList);

            fab = findViewById(R.id.fab);
            fab.setOnClickListener((v) -> presenter.startScan());

            presenter.subscribe(this);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        recreate();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.unsubscribe();
    }

    @Override
    public void setWifiStatus(String status) {
        txtWifiStatus.setText(status);
    }

    @Override
    public void setWifiScanResult(String result) {
        txtWifiScanResult.setText(result);
    }
}
