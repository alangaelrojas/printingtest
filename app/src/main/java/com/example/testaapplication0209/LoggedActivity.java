package com.example.testaapplication0209;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoggedActivity extends AppCompatActivity {

    private EditText etMatricola;
    private Button btnIntentScan;
    public String inputMatricola;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged);

        etMatricola = findViewById(R.id.etMatricola);
        btnIntentScan = findViewById(R.id.btnIntentScan);

        btnIntentScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                inputMatricola = etMatricola.getText().toString().trim();
                Intent intent = new Intent(LoggedActivity.this, QrScanDistributore.class);
                intent.putExtra("inputMatricola",inputMatricola);
                startActivity(intent);

            }

        });

    }

    public static Intent getLoggedActivityInstance(Context context) {
        Intent intent = new Intent(context, LoggedActivity.class);
        return intent;
    }


}
