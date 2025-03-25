package com.example.donerkabab.registration

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.URL
import java.net.URLEncoder

class UserViewModel : ViewModel() {
    private val _loginResult = MutableLiveData<String>()
    val loginResult: LiveData<String> = _loginResult
    private val _isRegistered = mutableStateOf(false)
    val isRegistered: Boolean get() = _isRegistered.value

    fun registerUser(
        context: Context,
        name: String,
        surname: String,
        tel: String,
        password: String,
        onSuccess: (Any?) -> Unit
    ) {
        val baseUrl =
            "https://script.google.com/macros/s/AKfycbygqSJaPgWSzAGuQh4BzIvHSq3c78sGpRFxErQxF_ZJan0smTg5tIRGz0FiPwyzTTR6YQ/exec"

        val encodedName = URLEncoder.encode(name, "UTF-8")
        val encodedSurname = URLEncoder.encode(surname, "UTF-8")
        val encodedTel = URLEncoder.encode(tel, "UTF-8")
        val encodePassword = URLEncoder.encode(password, "UTF-8")

        val url =
            "$baseUrl?action=create&name=$encodedName&surname=$encodedSurname&tel=$encodedTel&password=$encodePassword"

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            { response ->
                Log.d("ServerResponse", "Response: $response")
                Toast.makeText(context, response, Toast.LENGTH_SHORT).show()

                // ✅ Indicate successful registration
                _isRegistered.value = true
                onSuccess("success")  // ✅ Trigger navigation
            },
            { error ->
                Log.d("ServerResponse", "Error: ${error.message}")
                Toast.makeText(context, "Error: ${error.message}", Toast.LENGTH_LONG).show()
            }
        )

        val queue: RequestQueue = Volley.newRequestQueue(context)
        queue.add(stringRequest)
    }

    fun verifyUser(tel: String, codeA: String, callback: (Boolean, String) -> Unit) {
        val url =
            "https://script.google.com/macros/s/AKfycbz0fWhrjZL8MzmtRKklSfAss0m3NFTePl3gKoINNZ3o98KcrW49d_YKCs_stF7Yeui9/exec?action=checkUser&tel=$tel&codeA=$codeA"

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = URL(url).readText()
                withContext(Dispatchers.Main) {
                    when (response) {
                        "Success" -> callback(true, "Tabriklaymiz! ")
                        "Invalid code" -> callback(false, "Yaroqsiz kod")
                        "User not found" -> callback(false, "Verifikatsiya kodi xato")
                        else -> callback(false, "Error: $response")
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    callback(false, "Internet aloqasini tekshiring")
                }
            }
        }
    }


    fun loginUser(phone: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val url =
                    "https://script.google.com/macros/s/AKfycbz0fWhrjZL8MzmtRKklSfAss0m3NFTePl3gKoINNZ3o98KcrW49d_YKCs_stF7Yeui9/exec?action=loginUser&tel=$phone&password=$password"
                val response = URL(url).readText()

                withContext(Dispatchers.Main) {
                    _loginResult.value = response.trim() // Ensure no extra spaces
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    _loginResult.value = "Error: ${e.message}"
                }
            }
        }
    }

}
