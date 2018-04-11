package de.hs_flensburg.mobilecomputing.mvc;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class ViewFragment extends Fragment implements View.OnClickListener {
    protected Model model;

    protected TextView amountText;
    protected Button addButton;
    protected Button withdrawButton;

    protected ControllerActivity controller;

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_view, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        amountText = (TextView) view.findViewById(R.id.textAmount);
        addButton = (Button) view.findViewById(R.id.buttonAdd);
        withdrawButton = (Button) view.findViewById(R.id.buttonWithdraw);

        withdrawButton.setOnClickListener(this);
        addButton.setOnClickListener(this);

        update();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            controller = (ControllerActivity) context;
        } catch (ClassCastException e) {

        }
    }

    public void update() {
        amountText.setText(String.format("%.2f €", getModel().getBalance()));
    }

    @Override
    public void onClick(View v) {
        if(controller != null) {
            if(v == addButton) {
                controller.addToBalance();
            } else if (v == withdrawButton) {
                controller.withdrawFromBalance();
            }
        }
    }
}
