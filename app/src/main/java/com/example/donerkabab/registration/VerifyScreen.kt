package com.example.donerkabab.registration

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.donerkabab.ui.theme.BackgroundColor
import com.example.donerkabab.ui.theme.RedColor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject
import java.io.IOException

@Composable
fun VerifyScreen(phoneNumber: String,
    password: String,
    verificationId: String,
    onLoginSuccess: () -> Unit,
    navController:NavController
) {
    val code = remember { mutableStateOf("") }
    val generatedCode = remember { mutableStateOf("") }
    LaunchedEffect(Unit) {
        val scriptUrl = "https://script.google.com/macros/s/YOUR_SCRIPT_ID/exec?action=getCode&phone_number=$phoneNumber"

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = getUser(scriptUrl)
                val jsonResponse = JSONObject(response)
                generatedCode.value = jsonResponse.getString("code")

                withContext(Dispatchers.Main) {

                }
            } catch (e: Exception) {
                Log.e("VerifyScreen", "Error: ${e.message}")
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = "Tasdiqlash",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Spacer(modifier = Modifier.size(10.dp))
        Text(
            text = "Biz sizga SMS orqali tasdiqlash kodini jo'natdik.",
            color = Color.Black,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.size(50.dp))
        BasicTextField(
            value = code.value,
            onValueChange = {
                if (it.length <= 6) {
                    code.value = it
                }
            },
            decorationBox = {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    repeat(6) { index ->
                        val char = when {
                            index >= code.value.length -> ""
                            else -> code.value[index].toString()
                        }
                        val isFocused = code.value.length == index
                        Box(
                            modifier = Modifier
                                .width(40.dp)
                                .height(50.dp)
                                .border(
                                    width = if (isFocused) {
                                        2.dp
                                    } else {
                                        1.dp
                                    },
                                    color = if (isFocused) {
                                        Color.Black
                                    } else {
                                        Color.Gray
                                    },
                                    shape = RoundedCornerShape(8.dp)
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = char,
                                style = TextStyle(
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = if (isFocused) {
                                        Color.Black
                                    } else {
                                        Color.Black
                                    }
                                ),
                                color = Color.Black,
                                textAlign = TextAlign.Center
                            )
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                    }
                }
            },
            maxLines = 1,
            keyboardOptions = KeyboardOptions.Default
                .copy(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                ),
            singleLine = true
        )
        Spacer(modifier = Modifier.size(30.dp))
        Button(
            onClick = {  if (code.value == generatedCode.value) {
                navController.navigate("homeScreen")
            } else {

                Log.e("VerifyScreen", "Code did not match")
            }},
            colors = ButtonDefaults.buttonColors(
                RedColor
            ),
            enabled = code.value.length == 6
        ) {
            Text(text = "Login")
        }
        Spacer(modifier = Modifier.weight(2f))
    }
}

suspend fun getUser(url: String): String {
    return withContext(Dispatchers.IO) {
        val client = OkHttpClient()
        val request = Request.Builder().url(url).get().build()

        client.newCall(request).execute().use { response ->
            if (!response.isSuccessful) throw IOException("Unexpected response: $response")
            response.body?.string() ?: ""
        }
    }
}