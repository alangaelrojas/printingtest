package com.example.testaapplication0209;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class QrScanIdBus extends AppCompatActivity {

    private Button btnScanBus, btnVaiAvantiBus;
    private TextView tvResultQRBus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_scan_id_bus);

        btnScanBus = findViewById(R.id.btnScanBus);
        btnVaiAvantiBus = findViewById(R.id.btnVaiAvantiBus);
        tvResultQRBus = findViewById(R.id.tvTitoloResultQRBus);

        btnScanBus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentIntegrator integrator = new IntentIntegrator(QrScanIdBus.this);
                integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE);
                integrator.setPrompt("Scan");
                integrator.setCameraId(0);
                integrator.setBeepEnabled(false);
                integrator.setBarcodeImageEnabled(false);
                integrator.initiateScan();
            }
        });

        btnVaiAvantiBus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(QrScanIdBus.this, InputFormActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        if(result != null){
            if(result.getContents() == null){
                Toast.makeText(this, "Hai fermato lo Scan", Toast.LENGTH_SHORT).show();
            } else {
                String IDBus = result.getContents();
                tvResultQRBus.setText(IDBus);
            }
        }
    }
}


