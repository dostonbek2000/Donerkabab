package com.example.donerkabab.registration

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import java.io.UnsupportedEncodingException
import java.net.URLEncoder

class UserViewModel {
    fun createRegister(context: Context, name: String, surname: String, tel: String) {
        val baseUrl = "https://script.google.com/macros/s/AKfycbyjUmX4VmUQNNfD17o25nxHZG3_VYzY2I2wKRq-wzc9crF-CkM_yftAgS4lYt_sQBXQRA/exec"
            val encodedName = try {
            URLEncoder.encode(name, "UTF-8")
        } catch (e: UnsupportedEncodingException) {
            e.printStackTrace()
            name // Default to the original if encoding fails
        }

        val encodedSurname = try {
            URLEncoder.encode(surname, "UTF-8")
        } catch (e: UnsupportedEncodingException) {
            e.printStackTrace()
            surname
        }

        val encodedTel = try {
            URLEncoder.encode(tel, "UTF-8")
        } catch (e: UnsupportedEncodingException) {
            e.printStackTrace()
            tel
        }

        // Full URL with parameters
        val url = "$baseUrl?action=create&name=$encodedName&surname=$encodedSurname&tel=$encodedTel"

        // Create the GET request
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            { response ->
                Log.d("ServerResponse", "Response: $response")
                Toast.makeText(context, response, Toast.LENGTH_SHORT).show()
            },
            { error ->
                Log.d("ServerResponse", "Error: ${error.message}")
                Toast.makeText(context, "Error: ${error.message}", Toast.LENGTH_LONG).show()
            }
        )

        // Request Queue to send the request
        val queue: RequestQueue = Volley.newRequestQueue(context)
        queue.add(stringRequest)
    }
}
