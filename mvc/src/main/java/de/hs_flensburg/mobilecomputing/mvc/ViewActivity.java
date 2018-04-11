package de.hs_flensburg.mobilecomputing.mvc;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ViewActivity extends Activity {

    protected Model model;
    protected Controller controller;

    protected TextView amountText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        model = new Model();
        controller = new Controller(this, model);

        amountText = (TextView) findViewById(R.id.textAmount);
    }

    protected void update() {
        amountText.setText(String.format("%.2f €", model.getBalance()));
    }
}
