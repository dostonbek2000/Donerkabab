package com.example.donerkabab.home

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.donerkabab.cart.CartScreen
import com.example.donerkabab.favourites.FavouriteScreen
import com.example.donerkabab.onBoarding.OnBoardingScreen
import com.example.donerkabab.orders.OrdersScreen
import com.example.donerkabab.registration.RegisterScreen
import com.example.donerkabab.registration.VerifyScreen
import com.example.donerkabab.ui.theme.DonerKababTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DonerKababTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { padding->
                    Column(modifier = Modifier.fillMaxSize().padding(padding), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                        //HomeScreen()
                        //FavouriteScreen()
                    //CartScreen()
                    //OrdersScreen()
                     //   OnBoardingScreen(onFinish = {})
                       // VerifyScreen(password = "", verificationId = ""){}
                        RegisterScreen()




                    }
                }

            }
        }
    }
}





