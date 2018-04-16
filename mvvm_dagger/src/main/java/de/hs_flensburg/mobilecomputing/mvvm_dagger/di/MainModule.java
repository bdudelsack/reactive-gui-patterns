package de.hs_flensburg.mobilecomputing.mvvm_dagger.di;

import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;
import de.hs_flensburg.mobilecomputing.mvvm_dagger.Model;
import de.hs_flensburg.mobilecomputing.mvvm_dagger.ViewActivity;
import de.hs_flensburg.mobilecomputing.mvvm_dagger.ViewModel;

@Module
public abstract class MainModule {
    @Provides
    static Model provideModel() {
        return new Model();
    }

    @Provides
    static ViewModel provideMainViewModel(Model model) {
        return new ViewModel(model);
    }

    @ContributesAndroidInjector
    abstract ViewActivity contributeMainActivityInjector();
}
