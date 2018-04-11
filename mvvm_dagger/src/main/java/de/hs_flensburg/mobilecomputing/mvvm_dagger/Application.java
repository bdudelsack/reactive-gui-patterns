package de.hs_flensburg.mobilecomputing.mvvm_dagger;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import de.hs_flensburg.mobilecomputing.mvvm_dagger.di.DaggerMainComponent;

public class Application extends DaggerApplication {
    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerMainComponent.create();
    }
}
