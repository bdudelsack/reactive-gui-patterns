package de.hs_flensburg.mobilecomputing.mvvm_reactive.di;

import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import de.hs_flensburg.mobilecomputing.mvvm_reactive.Application;

import javax.inject.Singleton;

@Component(modules = { AndroidSupportInjectionModule.class, AppModule.class })
@Singleton
public interface AppComponent extends AndroidInjector<Application> {
    @Component.Builder
    abstract class Builder extends AndroidInjector.Builder<Application> {}
}
