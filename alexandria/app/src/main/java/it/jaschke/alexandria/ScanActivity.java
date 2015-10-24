package it.jaschke.alexandria;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class ScanActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {
    private ZXingScannerView mScanView;

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        mScanView = new ZXingScannerView(this);
        setContentView(mScanView);
    }

    @Override
    public void onResume() {
        super.onResume();
        mScanView.setResultHandler(this);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mScanView.startCamera();
            }
        });

    }

    @Override
    public void onPause() {
        super.onPause();
        mScanView.stopCamera();
    }

    @Override
    public void handleResult(Result rawResult) {
        Intent scannerResultIntent = new Intent();
        scannerResultIntent.putExtra("ean_code", rawResult.getText());
        setResult(AddBook.SCANNER, scannerResultIntent);
        finish();
    }
}