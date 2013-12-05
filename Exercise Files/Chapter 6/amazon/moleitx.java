/****************************************************************************
Copyright (c) 2010-2012 cocos2d-x.org

http://www.cocos2d-x.org

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
****************************************************************************/
package com.toddperkins.moleitx;

import java.util.HashMap;

import java.util.Map;

import org.cocos2dx.lib.Cocos2dxActivity;
import org.cocos2dx.lib.Cocos2dxEditText;
import org.cocos2dx.lib.Cocos2dxGLSurfaceView;
import org.cocos2dx.lib.Cocos2dxRenderer;

import android.app.ActivityManager;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.pm.ConfigurationInfo;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.view.ViewGroup;

import com.amazon.inapp.purchasing.BasePurchasingObserver;
import com.amazon.inapp.purchasing.ItemDataResponse;
import com.amazon.inapp.purchasing.PurchaseResponse;
import com.amazon.inapp.purchasing.PurchasingManager;
import com.google.ads.*;

class MyObserver extends BasePurchasingObserver {
	 private moleitx mainActivity;
public MyObserver(moleitx activity) {
 
    super(activity);
    mainActivity = activity;
}
 
@Override
public void onItemDataResponse(ItemDataResponse itemDataResponse) {
 
}
 
@Override
public void onPurchaseResponse(PurchaseResponse purchaseResponse) {

	String response = purchaseResponse.getPurchaseRequestStatus().toString();
	if (response.equals("SUCCESSFUL") || response.equals("ALREADY_ENTITLED"))
	{
		mainActivity.unlockSkin();
	}
	else
	{
		Log.v("IAP",purchaseResponse.getPurchaseRequestStatus().toString());
	}
}
} 

public class moleitx extends Cocos2dxActivity{
	private Cocos2dxGLSurfaceView mGLView;
	private AdView adView;
	 // currently logged in user
	 
    private String currentUser;    
 
     
 
    // Mapping of our requestIds to unlockable content
 
    public Map<String, String> requestIds;
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		
		
		
		if (detectOpenGLES20()) {
			// get the packageName,it's used to set the resource path
			String packageName = getApplication().getPackageName();
			super.setPackageName(packageName);
			
            // FrameLayout
            /*ViewGroup.LayoutParams framelayout_params =
                new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,
                                           ViewGroup.LayoutParams.FILL_PARENT);
            FrameLayout framelayout = new FrameLayout(this);
            framelayout.setLayoutParams(framelayout_params);

            // Cocos2dxEditText layout
            ViewGroup.LayoutParams edittext_layout_params =
                new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,
                                           ViewGroup.LayoutParams.WRAP_CONTENT);
            Cocos2dxEditText edittext = new Cocos2dxEditText(this);
            edittext.setLayoutParams(edittext_layout_params);

            // ...add to FrameLayout
            framelayout.addView(edittext);

            // Cocos2dxGLSurfaceView
	        mGLView = new Cocos2dxGLSurfaceView(this);
	        //AdView adView = new AdView(this, AdSize.BANNER, "9ce84e8cd60442ec");
            
    		// Add the adView to it
    

    		// Initiate a generic request to load it with an ad
    //adView.loadAd(new AdRequest());
            // ...add to FrameLayout
            framelayout.addView(mGLView);
            //framelayout.addView(adView);
            adView.setVisibility(AdView.VISIBLE);

	        mGLView.setEGLContextClientVersion(2);
	        mGLView.setCocos2dxRenderer(new Cocos2dxRenderer());
            mGLView.setTextField(edittext);
            
			setContentView(framelayout);
			*/
			requestIds = new HashMap<String, String>();
			setContentView(R.layout.main);
            mGLView = (Cocos2dxGLSurfaceView) findViewById(R.id.game_gl_surfaceview);

            mGLView.setEGLContextClientVersion(2);
            mGLView.setCocos2dxRenderer(new Cocos2dxRenderer());

            //adView = (AdView)this.findViewById(R.id.adView);
            //adView.setVisibility(AdView.VISIBLE);
            
            //adView.loadAd(new AdRequest());
		}
		else {
			Log.d("activity", "don't support gles2.0");
			finish();
		}	
	}
	private native void nativeUnlockSkin();
	public void unlockSkin(){
		nativeUnlockSkin();
	}
	
	 @Override
	 protected void onPause() {
	     super.onPause();
	     mGLView.onPause();
	 }
	 @Override
	 protected void onStart() {
	     super.onStart();
	     MyObserver observer = new MyObserver(this);
	     PurchasingManager.registerObserver(observer);
	     
	 }

	 @Override
	 protected void onResume() {
	     super.onResume();
	     mGLView.onResume();
	     PurchasingManager.initiateGetUserIdRequest();
	 }
	 
	 private boolean detectOpenGLES20() 
	 {
	     ActivityManager am =
	            (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
	     ConfigurationInfo info = am.getDeviceConfigurationInfo();
	     return (info.reqGlEsVersion >= 0x20000 || Build.FINGERPRINT.startsWith("generic"));
	 }
	 
	 public static void buyItem(String item) {
		 PurchasingManager.initiatePurchaseRequest(item);
	 }
	 
	
     static {
         System.loadLibrary("game");
     }
     
}
