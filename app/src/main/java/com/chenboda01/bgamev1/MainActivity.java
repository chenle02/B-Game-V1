package com.chenboda01.bgamev1;
import android.app.*;import android.os.*;import android.webkit.*;import android.content.*;import android.widget.*;
public class MainActivity extends Activity{WebView w;public class Bridge{
 @JavascriptInterface public void openApp(String p,String c,String l){runOnUiThread(()->{try{Intent x=getPackageManager().getLaunchIntentForPackage(p);if(x==null&&c!=null&&c.length()>0){x=new Intent(Intent.ACTION_MAIN);x.addCategory(Intent.CATEGORY_LAUNCHER);x.setClassName(p,c);}if(x!=null)startActivity(x);else Toast.makeText(MainActivity.this,l+" is not installed yet.",Toast.LENGTH_LONG).show();}catch(Exception e){Toast.makeText(MainActivity.this,"Could not open "+l+".",Toast.LENGTH_LONG).show();}});}}
 protected void onCreate(Bundle b){super.onCreate(b);w=new WebView(this);setContentView(w);WebSettings s=w.getSettings();s.setJavaScriptEnabled(true);s.setDomStorageEnabled(true);s.setAllowFileAccess(true);s.setAllowContentAccess(true);w.setWebChromeClient(new WebChromeClient());w.addJavascriptInterface(new Bridge(),"AndroidBridge");w.loadUrl("file:///android_asset/index.html");}
 public void onBackPressed(){w.evaluateJavascript("window.bgameBack&&window.bgameBack()",null);}
}
