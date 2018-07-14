package com.mehak.demo;

import android.content.Context;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

class myjava {
    private Context context;

    myjava(Context context){
        this.context=context;
    }
    @JavascriptInterface
   public void show(){

       Toast.makeText(context,"this is a webview",Toast.LENGTH_LONG).show();
    }
}
