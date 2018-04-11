package de.hs_flensburg.mobilecomputing.mvvm_reactive;

import android.Manifest;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import dagger.android.support.DaggerAppCompatActivity;
import de.hs_flensburg.mobilecomputing.mvvm_reactive.databinding.ActivityViewBinding;

import javax.inject.Inject;

public class ViewActivity extends DaggerAppCompatActivity {
    @Inject
    protected ViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(this.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            this.requestPermissions(new String[]{ Manifest.permission.ACCESS_FINE_LOCATION }, 0);
        } else {
            ActivityViewBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_view);
            binding.setViewModel(viewModel);
        }
    }
}
