package com.example.foodapp
import android.app.Activity
import android.app.PendingIntent.getActivity
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.annotation.RequiresApi
import java.net.URI
import java.net.URISyntaxException
class MyWebViewClient internal constructor(private val activity: Activity) : WebViewClient() {

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
        val url: String = request?.url.toString()
        if (url.startsWith("http")){
            view?.loadUrl(url)
            return true
        }
        val parsedUri = Uri.parse(url)
        val packageManager = activity.getPackageManager()
        val browseIntent=Intent(Intent.ACTION_VIEW).setData(parsedUri)
        if(browseIntent.resolveActivity(packageManager)!=null){
            activity.startActivity(browseIntent)
            return true
        }
        if(url.startsWith("intent:")){
            try{
                val intent = Intent.parseUri(url, Intent.URI_INTENT_SCHEME)
                if(intent.resolveActivity(activity.packageManager)!=null){
                    activity.startActivity(intent)
                    return true
                }
                val fallbackUrl = intent.getStringExtra("browser_fallback_url")
                if (fallbackUrl != null) {
                    view?.loadUrl(fallbackUrl)
                    return true
                }
            }catch (e:URISyntaxException){

            }
        }
        return true
    }

    override fun shouldOverrideUrlLoading(webView: WebView, url: String): Boolean {
        webView.loadUrl(url)
        return true
    }

    override fun onReceivedError(view: WebView, request: WebResourceRequest, error: WebResourceError) {
        Toast.makeText(activity, "Got Error! $error", Toast.LENGTH_SHORT).show()
    }
}


//<?xml version="1.0" encoding="utf-8"?>
//<!--
//  ~ Copyright (C) 2016 The Android Open Source Project
//  ~
//  ~ Licensed under the Apache License, Version 2.0 (the "License");
//  ~ you may not use this file except in compliance with the License.
//  ~ You may obtain a copy of the License at
//  ~
//  ~      http://www.apache.org/licenses/LICENSE-2.0
//  ~
//  ~ Unless required by applicable law or agreed to in writing, software
//  ~ distributed under the License is distributed on an "AS IS" BASIS,
//  ~ WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//  ~ See the License for the specific language governing permissions and
//  ~ limitations under the License.
//-->
//
//<animated-selector xmlns:android="http://schemas.android.com/apk/res/android">
//
//    <item
//        android:id="@+id/visible"
//        android:drawable="@drawable/design_ic_visibility"
//        android:state_checked="true"/>
//
//    <item
//        android:id="@+id/masked"
//        android:drawable="@drawable/design_ic_visibility_off"/
//class MyWebViewClient(mainActivity: MainActivity) {
//
//}
