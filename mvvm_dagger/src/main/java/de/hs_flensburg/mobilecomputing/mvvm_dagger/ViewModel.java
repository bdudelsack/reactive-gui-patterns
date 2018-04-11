package de.hs_flensburg.mobilecomputing.mvvm_dagger;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

public class ViewModel extends BaseObservable {
    @Bindable
    protected String balance;

    protected Model model;

    public ViewModel(Model model) {
        this.model = model;
    }

    public String getBalance() {
        return String.format("%.2f €", model.getBalance());
    }

    public void addToBalance() {
        model.modifyBalance(10.0f);
        notifyPropertyChanged(BR.balance);
    }

    public void withdrawFromBalance() {
        model.modifyBalance(-10.0f);
        notifyPropertyChanged(BR.balance);
    }
}
