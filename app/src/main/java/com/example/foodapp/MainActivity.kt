package com.example.foodapp

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebSettings
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.Toast
//import android.R
//import android.test.orchestrator.junit.BundleJUnitUtils.getResult
//import jdk.nashorn.internal.runtime.ECMAException.getException
//import org.junit.experimental.results.ResultMatchers.isSuccessful
import com.google.firebase.iid.InstanceIdResult
import com.google.android.gms.tasks.Task
import androidx.annotation.NonNull
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.iid.FirebaseInstanceId
import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.nfc.Tag
import android.util.Log
import android.util.Log.*
import android.webkit.WebView


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
       super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        webView.settings.javaScriptEnabled = true
        webView.settings.loadWithOverviewMode = true
        webView.settings.useWideViewPort = true
        webView.settings.domStorageEnabled = true
        webView.settings.builtInZoomControls = true
        webView.webViewClient = MyWebViewClient(this)

        webView.loadUrl("http://192.168.43.207/PANCHFORON")
        FirebaseInstanceId.getInstance().instanceId
            .addOnCompleteListener(OnCompleteListener { task ->

                val TAG="varsha"
                if (!task.isSuccessful) {
                    Log.v(TAG,"Error")
                    return@OnCompleteListener
                }
                else{

                // Get new Instance ID token
                val token = task.result?.token

                //
                // val msg = getString(R.string.msg_token_fmt, token)
                val msg=token
                Log.v(TAG,msg)
                //Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()// Log and toast

            }})
    }

    }

