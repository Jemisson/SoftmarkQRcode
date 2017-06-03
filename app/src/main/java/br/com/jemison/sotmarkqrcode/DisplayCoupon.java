package br.com.jemison.sotmarkqrcode;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by james on 03/06/17.
 */

public class DisplayCoupon extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_coupon);

        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.ZXingScannerResultHandler.EXTRA_MESSAGE);

        String[] coupon = message.split("&");
        String cliente = coupon[0];
        String validade = coupon[1];
        String desconto = coupon[2];
        String[] data_hora = coupon[3].split("T");
        String[] data = data_hora[0].split("-");

        TextView cli = (TextView) findViewById(R.id.cli);
        TextView val = (TextView) findViewById(R.id.val);
        TextView des = (TextView) findViewById(R.id.des);
        TextView dat = (TextView) findViewById(R.id.dat);

        cli.setText(cliente);
        val.setText(validade);
        des.setText(desconto);
        dat.setText(data[2] + "/" + data[1] + "/" + data[0]);


    }

}
