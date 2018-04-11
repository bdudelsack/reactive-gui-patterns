package de.hs_flensburg.mobilecomputing.mvp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import de.hs_flensburg.mobilecomputing.mvp.R;

public class ViewActivity extends Activity implements View.OnClickListener, IView {

    protected Button addButton;
    protected Button withdrawButton;
    protected TextView amountText;

    protected IPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addButton = (Button) findViewById(R.id.buttonAdd);
        withdrawButton = (Button) findViewById(R.id.buttonWithdraw);
        amountText = (TextView) findViewById(R.id.textAmount);

        addButton.setOnClickListener(this);
        withdrawButton.setOnClickListener(this);

        presenter = new Presenter(this);
    }

    @Override
    public void showBalance(float balance) {
        amountText.setText(String.format("%.2f €",balance));
    }

    @Override
    public void onClick(View view) {
        if(view == addButton) {
            presenter.addToBalance();
        } else if(view == withdrawButton) {
            presenter.withdrawFromBalance();
        }
    }
}
