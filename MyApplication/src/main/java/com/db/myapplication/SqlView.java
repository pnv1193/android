package com.db.myapplication;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.TextView;

import java.sql.SQLException;

/**
 * Created by newUser on 10/22/13.
 */
public class SqlView extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sqlview);
        TextView tvInfo = (TextView) findViewById(R.id.tvInfo);

        SqlDataHandler info = new SqlDataHandler(SqlView.this);
        try {
            info.open();
            String data = info.getData();
            tvInfo.setText(data);
            info.close();
        } catch (SQLException e) {
            Dialog d = new Dialog(this);
            d.setTitle("Error" + e.toString());
        }
    }
}
