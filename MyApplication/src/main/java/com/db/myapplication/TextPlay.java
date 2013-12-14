package com.db.myapplication;

import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.Random;

/**
 * Created by newUser on 10/21/13.
 */
public class TextPlay extends Activity implements View.OnClickListener {
    EditText etCommands;
    Button bResults;
    ToggleButton tbPassword;
    TextView tvResults;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.text);
        connectToLayout();

        tbPassword.setOnClickListener(this);
        bResults.setOnClickListener(this);
    }

    private void connectToLayout() {
        etCommands = (EditText) findViewById(R.id.etCommands);
        bResults = (Button) findViewById(R.id.bResults);
        tbPassword = (ToggleButton) findViewById(R.id.tbPassword);
        tvResults = (TextView) findViewById(R.id.tvResults);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bResults:
                String check = etCommands.getText().toString();
                tvResults.setText(check);
                if (check.contentEquals("center")) {
                    tvResults.setGravity(Gravity.CENTER);
                } else if (check.contentEquals("left")) {
                    tvResults.setGravity(Gravity.LEFT);
                } else if (check.contentEquals("right")) {
                    tvResults.setGravity(Gravity.RIGHT);
                } else if (check.contentEquals("blue")) {
                    tvResults.setTextColor(Color.BLUE);
                } else if (check.contentEquals("WTF")) {
                    Random crazy = new Random();
                    tvResults.setTextSize(crazy.nextInt(75));
                    tvResults.setTextColor(Color.rgb(crazy.nextInt(256), crazy.nextInt(256), crazy.nextInt(256)));
                    switch (crazy.nextInt(3)) {
                        case 0:
                            tvResults.setGravity(Gravity.CENTER);
                            break;
                        case 1:
                            tvResults.setGravity(Gravity.LEFT);
                            break;
                        case 2:
                            tvResults.setGravity(Gravity.RIGHT);
                            break;
                    }
                } else {
                    tvResults.setText("Invalid");
                    tvResults.setGravity(Gravity.CENTER);
                }
                break;
            case R.id.tbPassword:
                if (tbPassword.isChecked()) {
                    etCommands.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                } else {
                    etCommands.setInputType(InputType.TYPE_CLASS_TEXT);
                }
                break;
        }

    }
}
