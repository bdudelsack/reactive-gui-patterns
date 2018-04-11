package de.hs_flensburg.mobilecomputing.mvc;

import android.view.View;
import android.widget.Button;

public class Controller implements View.OnClickListener {
    protected ViewActivity viewActivity;
    protected Model model;

    protected Button addButton;
    protected Button withdrawButton;


    public Controller(ViewActivity view, Model model) {
        this.viewActivity = view;
        this.model = model;

        addButton = (Button) viewActivity.findViewById(R.id.buttonAdd);
        withdrawButton = (Button) viewActivity.findViewById(R.id.buttonWithdraw);

        addButton.setOnClickListener(this);
        withdrawButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view == addButton) {
            model.modifyBalance(10.0f);
        } else if(view == withdrawButton) {
            model.modifyBalance(-10.0f);
        }

        viewActivity.update();
    }
}
