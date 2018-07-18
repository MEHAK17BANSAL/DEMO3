package com.mehak.demo.website;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;


import com.mehak.demo.R;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    ImageView im;
    //initializing WebView
    private WebView mwebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
       im =findViewById(R.id.fab);
       im.setVisibility(View.INVISIBLE);
      /*  FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //WebView
        mwebView = (WebView) findViewById(R.id.myWebView);
        mwebView.getSettings().setJavaScriptEnabled(true);
        WebSettings webSettings=mwebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
         // WebView webView = new WebView(this);
        // setContentView(webView);
       // WebSettings webSettings = mwebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        mwebView.getSettings().setJavaScriptEnabled(true);
        //improve webView performance

     /*  mwebView.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        mwebView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        mwebView.getSettings().setAppCacheEnabled(true);
        mwebView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webSettings.setDomStorageEnabled(true);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        webSettings.setUseWideViewPort(true);
        webSettings.setSavePassword(true);
        webSettings.setSaveFormData(true);
        webSettings.setEnableSmoothTransition(true);*/


        mwebView.loadUrl("http://newspexart.onlinedemos.xyz/index.php/home/");

        //force links open in webview only
        mwebView.setWebViewClient(new MyWebviewClient());

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }




    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_us) {
            // Handle the camera action
            mwebView.loadUrl("http://newspexart.onlinedemos.xyz/index.php/home/category/21/1");

        } else if (id == R.id.nav_world) {
            mwebView.loadUrl("http://newspexart.onlinedemos.xyz/index.php/home/coupons");
        } else if (id == R.id.nav_tech) {
            mwebView.loadUrl("http://newspexart.onlinedemos.xyz/index.php/home/legal/gift_offers");
        } else if (id == R.id.nav_sports) {
            mwebView.loadUrl("http://newspexart.onlinedemos.xyz/index.php/home/legal/terms_conditions");
        } else if (id == R.id.nav_share) {
            mwebView.loadUrl("http://newspexart.onlinedemos.xyz/index.php/home/about/");

        } else if (id == R.id.nav_send) {
            mwebView.loadUrl("http://newspexart.onlinedemos.xyz/index.php/home/contact");
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private class MyWebviewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (Uri.parse(url).getHost().equals("http://newspexart.onlinedemos.xyz/index.php/home/\"")) {
                //open url contents in webview
                return false;
            } else {
                //here open external links in external browser or app
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);

                return true;
            }

        }
        //ProgressDialogue
        ProgressDialog pd = null;

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            pd=new ProgressDialog(MainActivity.this);
            pd.setTitle("Please Wait..");
            pd.setMessage("App is Loading..");
            pd.show();
            super.onPageStarted(view, url, favicon);


        }

        @Override
        public void onPageFinished(WebView view, String url) {
            pd.dismiss();
            super.onPageFinished(view, url);
            ImageView im=findViewById(R.id.fab);
            im.setVisibility(View.VISIBLE);
        }
    }
    //goto previous page when pressing back button

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            switch (keyCode) {
                case KeyEvent.KEYCODE_BACK:
                    if (mwebView.canGoBack()) {
                        mwebView.goBack();
                    } else {
                        finish();
                    }
                    return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
