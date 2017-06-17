package br.com.jemison.sotmarkqrcode;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by james on 03/06/17.
 */

public class DisplayCoupon extends AppCompatActivity {

    public EditText name,phone;
    public Button btnSend;
    private String strname ="", strphone ="";
    private JSONObject json;
    private int success=0;
    private HTTPURLConnection service;

    //Initialize webservice URL
    private String path = "http://138.197.65.209/insert.php";

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


        name = (EditText) findViewById(R.id.name);
        btnSend = (Button) findViewById(R.id.btnSubmit);

        //Initialize HTTPURLConnection class object
        service = new HTTPURLConnection();

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!name.getText().toString().equals("")) {
                    strname = name.getText().toString();
                    //Call WebService
                    new PostDataTOServer().execute();
                } else {
                    Toast.makeText(getApplicationContext(), "Preencha todos os Campos!", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

//    @Override
//    public void onBackPressed(){
//
//    }

    private class PostDataTOServer extends AsyncTask<Void, Void, Void> {

        String resp = "";
        //Create hashmap Object to send parameters to web service
        HashMap<String, String> postDataParams;
        @Override
        protected Void doInBackground(Void... arg0) {
            postDataParams = new HashMap<String, String>();
            postDataParams.put("name", strname);
            postDataParams.put("phone", strphone);

            //Call ServerData() method to call webservice and store result in response
            resp = service.ServerData(path,postDataParams);


            try {
                json = new JSONObject(resp);
                //Get Values from JSONobject
                System.out.println("success=" + json.get("success"));
                success = json.getInt("success");

            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

    }

}
