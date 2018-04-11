package de.hs_flensburg.mobilecomputing.mvvm_dagger;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import dagger.android.DaggerActivity;
import javax.inject.Inject;
import de.hs_flensburg.mobilecomputing.mvvm_dagger.databinding.ActivityMainBinding;

public class ViewActivity extends DaggerActivity {

    @Inject
    protected ViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setViewModel(viewModel);
    }
}
