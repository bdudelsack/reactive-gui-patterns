package de.hs_flensburg.mobilecomputing.mvp_reactive.di;

import dagger.Component;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import de.hs_flensburg.mobilecomputing.mvp_reactive.Application;
import de.hs_flensburg.mobilecomputing.mvp_reactive.IPresenter;
import de.hs_flensburg.mobilecomputing.mvp_reactive.IView;

import javax.inject.Singleton;

@Component(modules = { AndroidSupportInjectionModule.class, AppModule.class })
@Singleton
public interface AppComponent extends AndroidInjector<Application> {
    @Component.Builder
    abstract class Builder extends AndroidInjector.Builder<Application> {}
}
