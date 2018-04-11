package de.hs_flensburg.mobilecomputing.mvvm_dagger.di;

import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;
import de.hs_flensburg.mobilecomputing.mvvm_dagger.Application;

@Component(modules = { AndroidInjectionModule.class, MainModule.class })
public interface MainComponent extends AndroidInjector<Application> {

}
