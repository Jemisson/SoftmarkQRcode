package br.com.jemison.sotmarkqrcode;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class MainActivity extends AppCompatActivity {
    private ZXingScannerView scannerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scannerView = new ZXingScannerView(this);
        scannerView.setResultHandler(new ZXingScannerResultHandler());

        setContentView(scannerView);
        scannerView.startCamera();
    }

    @Override
    public void onPause() {
        super.onPause();
        scannerView.stopCamera();
    }

    class ZXingScannerResultHandler implements ZXingScannerView.ResultHandler{

        public final static String EXTRA_MESSAGE = "com.example.james.sotmarkqrcode";

        @Override
        public void handleResult(Result result){
            String resultCode = result.getText();
            setContentView(R.layout.activity_main);
            scannerView.stopCamera();


            Intent intent = new Intent(MainActivity.this, DisplayCoupon.class);
            intent.putExtra(EXTRA_MESSAGE, resultCode);
            startActivity(intent);
        }
    }



}
