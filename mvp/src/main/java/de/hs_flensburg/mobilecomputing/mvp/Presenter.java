package de.hs_flensburg.mobilecomputing.mvp;

public class Presenter implements IPresenter {
    protected IView view;
    protected Model model;

    public Presenter(IView view) {
        this.view = view;

        model = new Model();
    }

    @Override
    public void addToBalance() {
        model.modifyBalance(10.0f);
        view.showBalance(model.getBalance());
    }

    @Override
    public void withdrawFromBalance() {
        model.modifyBalance(-10.0f);
        view.showBalance(model.getBalance());
    }
}
