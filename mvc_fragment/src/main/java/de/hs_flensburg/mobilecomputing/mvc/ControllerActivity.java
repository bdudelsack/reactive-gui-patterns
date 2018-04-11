package de.hs_flensburg.mobilecomputing.mvc;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.FragmentManager;
import android.view.View;

public class ControllerActivity extends Activity {

    protected Model model;
    protected ViewFragment view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        model = new Model();        // Model wird erstellt
        view = new ViewFragment();  // Die View wird erstellt
        view.setModel(model);       // Das Model wird an die View übergeben

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.parentLayout, view);
        fragmentTransaction.commit();
    }

    public void addToBalance() {
        model.modifyBalance(10.0f);
        view.update();
    }

    public void withdrawFromBalance() {
        model.modifyBalance(-10.0f);
        view.update();
    }
}
