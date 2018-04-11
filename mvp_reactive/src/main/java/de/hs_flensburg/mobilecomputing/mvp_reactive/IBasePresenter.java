package de.hs_flensburg.mobilecomputing.mvp_reactive;

public interface IBasePresenter<T> {
    void subscribe(T view);
    void unsubscribe();
}
