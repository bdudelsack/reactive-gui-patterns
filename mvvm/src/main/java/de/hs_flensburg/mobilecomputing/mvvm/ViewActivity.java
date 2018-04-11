package de.hs_flensburg.mobilecomputing.mvvm;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import de.hs_flensburg.mobilecomputing.mvvm.databinding.ActivityMainBinding;

public class ViewActivity extends Activity {

    protected ViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel = new ViewModel();

        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setViewModel(viewModel);
    }
}
