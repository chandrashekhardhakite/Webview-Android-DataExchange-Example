package com.example.hybridapppoc;

import android.content.Context;

import android.os.Bundle;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements OnClickListener {


    private Button scanBtn;
    private TextView formatTxt, contentTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        scanBtn = (Button) findViewById(R.id.scan_button);
        formatTxt = (TextView) findViewById(R.id.scan_format);
        contentTxt = (TextView) findViewById(R.id.scan_content);
        scanBtn.setOnClickListener(this);

        final WebView myWebView = (WebView) findViewById(R.id.webview);
        myWebView.setWebViewClient(new WebViewClient());

        myWebView.addJavascriptInterface(new JavaScriptInterface(this), "example");
        WebSettings webSettings = myWebView.getSettings();

        webSettings.setJavaScriptEnabled(true);
        myWebView.loadUrl("file:///android_asset/index.html");

        myWebView.setWebViewClient(new WebViewClient(){
            public void onPageFinished(WebView view, String url){
                myWebView.loadUrl("javascript:init('" + "CHnadra" + "')");
            }
        });
    }

    public void onClick(View v) {
        if (v.getId() == R.id.scan_button) {
//            scanCode();
            //  IntentIntegrator scanIntegrator = new IntentIntegrator(this);
            //  scanIntegrator.initiateScan();
        }
    }
//    public void scanCode(){
//        IntentIntegrator scanIntegrator = new IntentIntegrator(this);
//        scanIntegrator.initiateScan();
//    }

    public String scanContent;
    public String scanFormat;
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {

//        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
//        if (scanningResult != null) {
//            scanContent = scanningResult.getContents();
//            scanFormat = scanningResult.getFormatName();
//            formatTxt.setText("FORMAT: " + scanFormat);
//            contentTxt.setText("CONTENT: " + scanContent);
//        } else {
//            Toast toast = Toast.makeText(getApplicationContext(),
//                    "No scan data received!", Toast.LENGTH_SHORT);
//            toast.show();
//        }

    }
    public String getContent(){ return scanContent; }

    public class JavaScriptInterface {
        Context mContext;

        /* Instantiate the interface and set the context */
        JavaScriptInterface(Context c) {
            mContext = c;
        }

        /* Show a toast from the web page */
        @JavascriptInterface
        public void showToast(String toast) {
            Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show();
        }

        @JavascriptInterface
        public void scanJS() {
            Log.d("called","clicked");


//            scanCode();
        }

        @JavascriptInterface
        public String scanResult() {
            return getContent();
        }
    }
}