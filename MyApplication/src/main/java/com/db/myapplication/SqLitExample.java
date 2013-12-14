package com.db.myapplication;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by newUser on 10/22/13.
 */
public class SqLitExample extends Activity implements View.OnClickListener {
    Button bAdd, bView;
    EditText etSSN, etName, etAddress, etItem, etCost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sqlitexample);
        bAdd = (Button) findViewById(R.id.bAdd);
        bView = (Button) findViewById(R.id.bView);
        etSSN = (EditText) findViewById(R.id.etSSN);
        etName = (EditText) findViewById(R.id.etName);
        etAddress = (EditText) findViewById(R.id.etAddress);
        etItem = (EditText) findViewById(R.id.etItem);
        etCost = (EditText) findViewById(R.id.etCost);
        bAdd.setOnClickListener(this);
        bView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bAdd:
                boolean flag_work = true;
                try {
                    String SSN = etSSN.getText().toString();
                    String Name = etName.getText().toString();
                    String Address = etAddress.getText().toString();
                    String Item = etItem.getText().toString();
                    String Cost = etCost.getText().toString();

                    SqlDataHandler entry = new SqlDataHandler(SqLitExample.this);
                    entry.open();
                    entry.createEntry(SSN, Name, Address, Item, Cost);
                    entry.close();
                } catch (Exception e) {
                    flag_work = false;
                    String error = e.toString();
                    Dialog d;
                    d = new Dialog(this);
                    d.setTitle("It didn't Work!");
                    TextView tv = new TextView(this);
                    tv.setText(error);
                    d.setContentView(tv);
                    d.show();
                } finally {
                    if (flag_work) {
                        Dialog d;
                        d = new Dialog(this);
                        d.setTitle("It Worked!");
                        TextView tv = new TextView(this);
                        tv.setText("Success!");
                        d.setContentView(tv);
                        d.show();
                    }
                }
                break;
            case R.id.bView:
                Intent i = new Intent("com.db.myapplication.SQLVIEW");
                startActivity(i);
                break;
        }
    }
}
