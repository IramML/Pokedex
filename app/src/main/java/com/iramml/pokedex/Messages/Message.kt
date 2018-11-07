package com.iramml.pokedex.Messages

import android.content.Context
import android.widget.Toast

class Message(){
    companion object {
        fun message(context:Context, message: Messages){
            var messageStr=""
            when(message){

            }
            Toast.makeText(context, messageStr, Toast.LENGTH_SHORT).show()
        }
        fun messageError(context:Context, message:Errors){
            var messageStr=""
            when(message){
                Errors.NO_NETWORK_AVAILABLE->messageStr="No network available"
                Errors.HTTP_REQUEST_ERROR-> messageStr="There was an error with the request"
            }
            Toast.makeText(context, messageStr, Toast.LENGTH_SHORT).show()
        }
    }
}