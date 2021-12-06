package com.example.photogallery;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Switch flashControl;
    CameraManager cameraManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        flashControl = findViewById(R.id.switchFlashLight);
        cameraManager = (CameraManager) getSystemService( CAMERA_SERVICE);

        if (getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY)){
            if (getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)){
                flashControl.setEnabled(true);

            }else{
                Log.e(String.valueOf(MainActivity.this), "onCreate:flash yok ");
                Toast.makeText(MainActivity.this,"flash yok",Toast.LENGTH_SHORT);
            }

        }else{
            Log.e(String.valueOf(MainActivity.this), "onCreate:cam yok ");
            Toast.makeText(MainActivity.this,"cam yok",Toast.LENGTH_SHORT);
        }

        flashControl.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    try {
                        cameraManager.setTorchMode("0",false);
                    } catch (CameraAccessException e) {
                        e.printStackTrace();
                    }
                    flashControl.setText("flash on");
                }else{
                    try {
                        cameraManager.setTorchMode("0",true);
                    } catch (CameraAccessException e) {
                        e.printStackTrace();
                    }
                    flashControl.setText("flash off");
                }
            }
        });



    }
}