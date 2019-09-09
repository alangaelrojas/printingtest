package com.example.testaapplication0209;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import androidx.appcompat.app.AppCompatActivity;

public class QrScanDistributore extends AppCompatActivity {

    private Button btnScan, btnVaiAvanti;
    private TextView tvResultQR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_scan_distributore);

       final Activity activity = this;

        btnScan = findViewById(R.id.btnScan);
        tvResultQR = findViewById(R.id.tvResultQR);
        btnVaiAvanti = findViewById(R.id.btnVaiAvanti);

        Intent previousIntent = getIntent();
        String inputMatricola = previousIntent.getStringExtra("inputMatricola");




        btnVaiAvanti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity,QrScanIdBus.class);
                startActivity(intent);
            }
        });


        btnScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentIntegrator integrator = new IntentIntegrator(activity);
                integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE);
                integrator.setPrompt("Scannerizza");
                integrator.setCameraId(0);
                integrator.setBeepEnabled(false);
                integrator.setOrientationLocked(false);
                integrator.setBarcodeImageEnabled(false);
                integrator.initiateScan();
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        if(result != null){
            if(result.getContents() == null){
                Toast.makeText(this, "Hai fermato lo scan", Toast.LENGTH_SHORT).show();
            } else {
                //Settare  una TextView contenente le info dello scan
                String dataScan = result.getContents();
                tvResultQR.setText(dataScan);
            }
        } /* else  {
            super.onActivityResult(requestCode, resultCode, data);
        } */
    }


}
