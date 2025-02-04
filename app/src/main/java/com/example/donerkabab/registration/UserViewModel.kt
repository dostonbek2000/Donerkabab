package com.example.donerkabab.registration

import android.content.Context
import android.widget.Toast
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class UserViewModel {
    fun createRegister(context: Context, name:String,surname:String,tel:String) {
        val url =
            "https://script.google.com/macros/s/AKfycbwiD4dQlo4Ymenh8u-O_m-oKL74bZHotqVkdBG-Z9XlCkV4YM-AXSTkyy6HuHAqjhfVjA/exec/"
        val stringRequest = object : StringRequest(Method.GET, url, Response.Listener {
            Toast.makeText(
                context, "Code Sent", Toast.LENGTH_SHORT
            ).show()
        }, Response.ErrorListener {
            Toast.makeText(
                context, it.message, Toast.LENGTH_LONG
            ).show()
        }){
            override fun getParams(): MutableMap<String, String> {
                val params =HashMap<String,String>()
                params["action"]="create"
                params["name"]="name"
               params["surname"]="surname"
                params["tel"]=tel
                return params
            }
        }
        val queue:RequestQueue=Volley.newRequestQueue(context)
        queue.add(stringRequest)

    }
}