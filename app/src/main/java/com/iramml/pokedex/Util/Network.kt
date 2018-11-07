package com.iramml.pokedex.Util

import android.content.Context
import android.net.ConnectivityManager
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.iramml.pokedex.Interfaces.HttpRequest
import com.iramml.pokedex.Messages.Errors
import com.iramml.pokedex.Messages.Message

class Network(var activity: AppCompatActivity){
    fun availableNetwok():Boolean {
        val connectivityManager = activity.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }
    fun httpRequest(url:String, httpResponse:HttpRequest){
        if(availableNetwok()){
            val queue = Volley.newRequestQueue(activity.applicationContext)

            val request = StringRequest(Request.Method.GET, url, Response.Listener<String> { response ->
                httpResponse.httpResponseSuccess(response)
            }, Response.ErrorListener { error ->
                Log.d("HTTP_REQUEST", error.message.toString())
                Message.messageError(activity.applicationContext, Errors.HTTP_REQUEST_ERROR)
            })
            queue.add(request)
        }else{
            Message.messageError(activity.applicationContext, Errors.NO_NETWORK_AVAILABLE)
        }
    }

}