package com.example.testaapplication0209;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class InputFormActivity extends AppCompatActivity {

    private Button btnConcludi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_form);

        btnConcludi = findViewById(R.id.btnConcludi);

        btnConcludi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intetn = new Intent(InputFormActivity.this, PrintActivity.class);
                startActivity(intetn);
            }
        });
    }
}
