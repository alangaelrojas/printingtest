package com.example.testaapplication0209;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.mazenrashed.printooth.Printooth;
import com.mazenrashed.printooth.data.printable.Printable;
import com.mazenrashed.printooth.data.printable.RawPrintable;
import com.mazenrashed.printooth.data.printable.TextPrintable;
import com.mazenrashed.printooth.data.printer.DefaultPrinter;
import com.mazenrashed.printooth.ui.ScanningActivity;
import com.mazenrashed.printooth.utilities.Printing;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;

public class PrintActivity extends AppCompatActivity {
    private Printing printing;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_print);
        Printooth.INSTANCE.init(this);
        printing = Printooth.INSTANCE.printer();

        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!Printooth.INSTANCE.hasPairedPrinter())
                    startActivityForResult(new Intent(PrintActivity.this, ScanningActivity.class), ScanningActivity.SCANNING_FOR_PRINTER);
                else
                    printText();
            }
        }

        );
        }

    private void printText() {
        ArrayList<Printable> printables = new ArrayList<>();
        printables.add(new RawPrintable.Builder(new byte[]{27, 100, 4}).build());

        //Add Text
        printables.add(new TextPrintable.Builder()
                .setText("Hello World: Jagadeesh")
                .setCharacterCode(DefaultPrinter.Companion.getCHARCODE_PC1252())
                .setNewLinesAfter(1)
                .build());

        printables.add((new TextPrintable.Builder())
                .setText(" Hello World : été è à '€' içi Bò Xào Coi Xanh")
                .setCharacterCode(DefaultPrinter.Companion.getCHARCODE_PC1252())
                .setNewLinesAfter(1)
                .build());

        //Custom Text
        printables.add((new TextPrintable.Builder())
                .setText("Hello World")
                .setLineSpacing(DefaultPrinter.Companion.getLINE_SPACING_60())
                .setAlignment(DefaultPrinter.Companion.getALIGNMENT_CENTER())
                .setEmphasizedMode(DefaultPrinter.Companion.getEMPHASIZED_MODE_BOLD())
                .setUnderlined(DefaultPrinter.Companion.getUNDERLINED_MODE_ON())
                .setNewLinesAfter(1)
                .build());

        printing.print(printables);
    }
    }

