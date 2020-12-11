package com.fju.myphone;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TelephonyManager tm;
    TextView imeitxt;
    Button imeibtn;
    String imei;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imeitxt = (TextView)findViewById(R.id.imei);
        imeibtn = (Button)findViewById(R.id.getimeibtn);

        int permisI = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE);

        if(permisI == PackageManager.PERMISSION_GRANTED){
            tm = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
            imei = tm.getImei().toString();
        }
        else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE}, 123);
        }

        imeibtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imeitxt.setText(imei);
            }
        });
    }
}
