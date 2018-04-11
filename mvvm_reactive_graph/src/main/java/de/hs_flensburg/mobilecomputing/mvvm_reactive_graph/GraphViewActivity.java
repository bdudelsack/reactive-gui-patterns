package de.hs_flensburg.mobilecomputing.mvvm_reactive_graph;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.databinding.Observable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import dagger.android.support.DaggerAppCompatActivity;

import javax.inject.Inject;

public class GraphViewActivity extends DaggerAppCompatActivity {
    @Inject
    protected ViewModel viewModel;

    protected GraphView graphView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (this.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            this.requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 0);
        } else {
            setContentView(R.layout.activity_graph);
            graphView = (GraphView) findViewById(R.id.graph_view);
            viewModel.wifiScanResults.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
                @Override
                public void onPropertyChanged(Observable sender, int propertyId) {
                    invalidateResults();
                }
            });

            invalidateResults();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        recreate();
    }

    protected void invalidateResults() {
        graphView.setScanResultList(viewModel.wifiScanResults.get());
    }

}
