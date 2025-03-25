package com.example.donerkabab.registration

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.airbnb.lottie.model.content.Mask
import com.example.donerkabab.ui.theme.BackgroundColor
import com.example.donerkabab.ui.theme.RedColor
import com.example.donerkabab.ui.theme.TextColor

@Composable
fun RegisterScreen(
    viewModel: UserViewModel,
    navController: NavController
) {
    val firstName = remember { mutableStateOf("") }
    val lastName = remember { mutableStateOf("") }
    val password= remember { mutableStateOf("") }
    val phoneNumber = remember { mutableStateOf("") }
    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor)
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Roʻyxatdan oʻtish",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Biz sizga bir martalik parol yuborishimiz uchun telefon raqamingizni kiriting",
            color = Color.Black,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(30.dp))

        // First Name Field
        OutlinedTextField(
            value = firstName.value,
            onValueChange = { firstName.value = it },
            label = { Text(text = "Ismingiz", color = Color.Black) },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = BackgroundColor,
                unfocusedContainerColor = BackgroundColor,
                focusedIndicatorColor = Color.Black,
                cursorColor = Color.Black
            ),
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            textStyle = TextStyle(color = Color.Black, fontSize = 18.sp),
            isError = firstName.value.isNotEmpty() && !isValidFullName(firstName.value)
        )

        Spacer(modifier = Modifier.height(10.dp))

        // Last Name Field
        OutlinedTextField(
            value = lastName.value,
            onValueChange = { lastName.value = it },
            label = { Text(text = "Familyangiz", color = Color.Black) },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = BackgroundColor,
                unfocusedContainerColor = BackgroundColor,
                focusedIndicatorColor = Color.Black,
                cursorColor = Color.Black
            ),
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            textStyle = TextStyle(color = Color.Black, fontSize = 18.sp),
            isError = lastName.value.isNotEmpty() && !isValidFullName(lastName.value)
        )

        Spacer(modifier = Modifier.height(10.dp))

        // Phone Number Field
        OutlinedTextField(
            value = phoneNumber.value,
            onValueChange = { if (it.length <= 9) phoneNumber.value = it },
            label = { Text(text = "Telefon raqamingiz", color = Color.Black) },
            placeholder = {Text( text = "(--) --- -- --",
                color = TextColor,
                fontSize = 18.sp)},
            colors = TextFieldDefaults.colors(
                focusedContainerColor = BackgroundColor,
                unfocusedContainerColor = BackgroundColor,
                focusedIndicatorColor = Color.Black,
                cursorColor = Color.Black
            ),
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Phone,
                imeAction = ImeAction.Done
            ),
            textStyle = TextStyle(color = Color.Black, fontSize = 18.sp),
            visualTransformation = MaskVisualTransformation("(##) ### ## ##"),
            prefix = { Text(text = "+998 ", fontSize = 18.sp, color = Color.Black) },
            isError = phoneNumber.value.isNotEmpty() && phoneNumber.value.length != 9
        )
        Spacer(modifier = Modifier.height(10.dp))

        // Last Name Field
        OutlinedTextField(
            value = password.value,
            onValueChange = { password.value = it },
            label = { Text(text = "Parol kiriting", color = Color.Black) },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = BackgroundColor,
                unfocusedContainerColor = BackgroundColor,
                focusedIndicatorColor = Color.Black,
                cursorColor = Color.Black
            ),
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            textStyle = TextStyle(color = Color.Black, fontSize = 18.sp),

        )

        Spacer(modifier = Modifier.height(30.dp))

        // Register Button
        Button(
            onClick = {
                keyboardController?.hide()
                viewModel.registerUser(
                    context,
                    name = firstName.value,
                    surname = lastName.value,
                    tel = phoneNumber.value,
                   password =  password.value
                ) { verificationId->
                    if (verificationId != null) {
                        Log.d("RegisterScreen", "Navigating to verify screen")
                        navController.navigate("verify/${phoneNumber.value}/$verificationId")
                    } else {
                        Log.e("RegisterScreen", "Failed to get verification ID")
                    }
                }
            },
            colors = ButtonDefaults.buttonColors(
                contentColor = BackgroundColor,
                containerColor = RedColor
            ),
            enabled = firstName.value.isNotEmpty()
                    && lastName.value.isNotEmpty()
                    && phoneNumber.value.length == 9&&password.value.isNotEmpty()
        ) {
            Text(text = "Ro'yxatdan o'tish")
        }
        Spacer(modifier = Modifier.size(20.dp))
        LoginPrompt(navController = navController)
        Spacer(modifier = Modifier.weight(2f))
    }
}

// Function to Validate Full Name
fun isValidFullName(text: String): Boolean {
    return text.matches(Regex("[a-zA-ZʻʼА-Яа-я]+"))
}
@Composable
fun LoginPrompt(navController: NavController) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = "Hisobingiz bormi? ", color = TextColor)

        Text(
            text = "Tizimga kiring.",
            color = Color.Blue,
            modifier = Modifier
                .clickable {
                    navController.navigate("login")
                }
        )
    }
}
