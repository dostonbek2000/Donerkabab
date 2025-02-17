package com.example.donerkabab.registration



import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.donerkabab.ui.theme.BackgroundColor
import com.example.donerkabab.ui.theme.DonerKababTheme
import com.example.donerkabab.ui.theme.RedColor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.junit.runner.Request
import java.io.IOException

@Composable
fun RegisterScreen(
    modifier: Modifier = Modifier,
    viewModel: UserViewModel,navController: NavController
) {
    val firstName = remember { mutableStateOf("") }
    val lastName = remember { mutableStateOf("") }
    val phoneNumber = remember { mutableStateOf("") }
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = modifier.background(BackgroundColor),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = "Roʻyxatdan oʻtish",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Spacer(modifier = Modifier.size(10.dp))
        Text(text = "Biz sizga bir martalik parol yuborishimiz", color = Color.Black)
        Text(text = "uchun telefon raqamingizni kiriting", color = Color.Black)
        Spacer(modifier = Modifier.size(50.dp))

        // First Name Field
        OutlinedTextField(
            value = firstName.value,
            onValueChange = { firstName.value = it },
            label = { Text(text = "Ismingizni kiriting", color = Color.Black) },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = BackgroundColor,
                unfocusedContainerColor = BackgroundColor,
                focusedIndicatorColor = Color.Black,
                cursorColor = Color.Black
            ),
            modifier = Modifier.fillMaxWidth().padding(start = 20.dp, end = 20.dp),
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            textStyle = TextStyle(color = Color.Black, fontSize = 18.sp),
            isError = firstName.value.isNotEmpty() && !isValidFullNameText(firstName.value),
            supportingText = {
                if (firstName.value.isNotEmpty() && !isValidFullNameText(firstName.value)) {
                    Text(text = "Ismingiz faqat harflardan iborat bo'lishi kerak", color = Color.Red)
                }
            }
        )

        // Last Name Field
        OutlinedTextField(
            value = lastName.value,
            onValueChange = { lastName.value = it },
            label = { Text(text = "Familyangizni kiriting", color = Color.Black) },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = BackgroundColor,
                unfocusedContainerColor = BackgroundColor,
                focusedIndicatorColor = Color.Black,
                cursorColor = Color.Black
            ),
            modifier = Modifier.fillMaxWidth().padding(start = 20.dp, end = 20.dp),
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            textStyle = TextStyle(color = Color.Black, fontSize = 18.sp),
            isError = lastName.value.isNotEmpty() && !isValidFullNameText(lastName.value),
            supportingText = {
                if (lastName.value.isNotEmpty() && !isValidFullNameText(lastName.value)) {
                    Text(text = "Familya faqat harflardan iborat bo'lishi kerak", color = Color.Red)
                }
            }
        )

        OutlinedTextField(
            value = phoneNumber.value,
            onValueChange = {
                if (it.length <= 9) {
                    phoneNumber.value = it
                }
            },
            label = { Text(text = "Telefon raqamingizni kiriting", color = Color.Black) },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = BackgroundColor,
                unfocusedContainerColor = BackgroundColor,
                focusedIndicatorColor = Color.Black,
                cursorColor = Color.Black
            ),
            modifier = Modifier.fillMaxWidth().padding(start = 20.dp, end = 20.dp),
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Phone,
                imeAction = ImeAction.Done
            ),
            textStyle = TextStyle(color = Color.Black, fontSize = 18.sp),
            visualTransformation = MaskVisualTransformation("(##) ### ## ##"),
            prefix = { Text(text = "+998 ", fontSize = 18.sp, color = Color.Black) },
            isError = phoneNumber.value.isNotEmpty() && phoneNumber.value.length != 9,
            supportingText = {
                if (phoneNumber.value.isNotEmpty() && phoneNumber.value.length != 9) {
                    Text(text = "Telefon raqam 9 ta belgidan iborat boʻlishi kerak", color = Color.Red)
                }
            }
        )

        Spacer(modifier = Modifier.size(30.dp))

        // Register Button
        Button(
            onClick = {
                keyboardController?.hide() // Hide keyboard after submission
                coroutineScope.launch {
                    try {
                        viewModel.createRegister(
                            context = context,
                            name = firstName.value,
                            surname = lastName.value,
                            tel = phoneNumber.value
                        )
                        navController.navigate("verifyScreen/${phoneNumber.value}")
                    } catch (e:Exception){
                        Log.e("RegisterScreen", "Error: ${e.message}")

                    }

                }

            },

            colors = ButtonDefaults.buttonColors(
                contentColor = BackgroundColor,
                containerColor = RedColor
            ),
            enabled = firstName.value.isNotEmpty()
                    && lastName.value.isNotEmpty()
                    && phoneNumber.value.length == 9
        ) {
            Text(text = "Ro'yxatdan o'tish")
        }

        Spacer(modifier = Modifier.size(20.dp))
        Spacer(modifier = Modifier.weight(2f))
    }
}

// Full Name Validation (Updated for Uzbek names)
fun isValidFullNameText(text: String): Boolean {
    return text.matches(Regex("[a-zA-ZʻʼА-Яа-я]+"))
}

