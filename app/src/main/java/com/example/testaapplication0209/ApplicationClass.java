package com.example.testaapplication0209;

import android.app.Application;
import com.mazenrashed.printooth.Printooth;

public class ApplicationClass extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Printooth.INSTANCE.init(this);
    }
}