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

import net.robotmedia.billing.BillingController;
import net.robotmedia.billing.BillingRequest;
import net.robotmedia.billing.BillingRequest.ResponseCode;
import net.robotmedia.billing.helper.AbstractBillingObserver;

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
import android.os.Handler;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.view.View;
import android.view.ViewGroup;
import net.robotmedia.billing.model.Transaction;
import net.robotmedia.billing.model.Transaction.PurchaseState;

import com.google.ads.*;

public class moleitx extends Cocos2dxActivity{
	private Cocos2dxGLSurfaceView mGLView;
	private AdView adView;
	 // currently logged in user
	 
    private String currentUser;    
     private AbstractBillingObserver mBillingObserver;
    private static moleitx me;
    // Mapping of our requestIds to unlockable content
 
    public Map<String, String> requestIds;
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		me = this;
		BillingController.setDebug(true);
		BillingController.setConfiguration(new BillingController.IConfiguration() {
			
			public byte[] getObfuscationSalt() {
				return new byte[] {41, -90, -116, -41, 66, -53, 122, -110, -127, -96, -88, 77, 127, 115, 1, 73, 57, 110, 48, -116};
			}

			public String getPublicKey() {
				return "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAmDi/m6syNYwsLL43vmFNvnhDOcJ4WerICs4XPpDVZrS8/KuRtFxU1Tey1W375cWdEXjrAT4FbfORlNSfvhq9UclxD4q+l3PFAKQwoTIvwFOA47uHUV5XgkVy8pgV+YdhVbQ/O426o+qck4az/stUjiHA9Og9zukvc/grOekwxMXLYNIf6bXRMamNaFrRtjGRROwO0ZDfsH+hEi1PeroLMXPkb25og/LLjbyz5x249GA1r6jB2BoYZAcsPb3aHDxhRl+JwN9ytpXOTtMOyatiBE5sI+vkhxWW1AAKbFQQ6Eb9t/3MYflrf+rpBAzsbQOSP2K5snp3UUrySBzzDMh1jwIDAQAB";
			}
		});
		mBillingObserver = new AbstractBillingObserver(this) {

			public void onBillingChecked(boolean supported) {
				me.onBillingChecked(supported);
			}

			public void onPurchaseStateChanged(String itemId, Transaction.PurchaseState state) {
				moleitx.this.onPurchaseStateChanged(itemId, state);
			}

			public void onRequestPurchaseResponse(String itemId, ResponseCode response) {
				moleitx.this.onRequestPurchaseResponse(itemId, response);
			}

			public void onSubscriptionChecked(boolean supported) {
				moleitx.this.onSubscriptionChecked(supported);
			}
		
		};
		BillingController.registerObserver(mBillingObserver);
		BillingController.checkBillingSupported(this);
		BillingController.checkSubscriptionSupported(this);
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
	     
	     
	 }
	 @Override
		protected void onDestroy() {
			BillingController.unregisterObserver(mBillingObserver);
			super.onDestroy();
		}

	 @Override
	 protected void onResume() {
	     super.onResume();
	     mGLView.onResume();
	 }
	 
	 private boolean detectOpenGLES20() 
	 {
	     ActivityManager am =
	            (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
	     ConfigurationInfo info = am.getDeviceConfigurationInfo();
	     return (info.reqGlEsVersion >= 0x20000 || Build.FINGERPRINT.startsWith("generic"));
	 }
	 
	 public static void buyItem(String item) {
		Log.v("IAP", "Trying to start puchase");
		BillingController.requestPurchase(me,"android.test.purchased");
	 }
	 
	 public void onPurchaseStateChanged(String itemId, PurchaseState state)
	    {
	    		Log.v("IAP","onPurchaseStateChanged");
	    }

		public void onRequestPurchaseResponse(String itemId, ResponseCode response)
		{
			Log.v("IAP","onRequestPurchaseResponse");
			if(response == ResponseCode.RESULT_OK)
			{
				me.unlockSkin();
			}
		}
		
		public void onBillingChecked(boolean supported)
		{
			Log.v("IAP","onBillingChecked");
		}
		
		public void onSubscriptionChecked(boolean supported)
		{
			Log.v("IAP","onSubscriptionChecked");
		}
	 
	 
	 
	
     static {
         System.loadLibrary("game");
     }
     
}
