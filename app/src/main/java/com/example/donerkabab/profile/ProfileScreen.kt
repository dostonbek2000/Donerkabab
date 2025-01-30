package com.example.donerkabab.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.donerkabab.R
import com.example.donerkabab.ui.theme.BackgroundColor
import com.example.donerkabab.ui.theme.BlackColor
import com.example.donerkabab.ui.theme.RedColor
import com.example.donerkabab.ui.theme.RedDark

@Composable
fun ProfileScreen(){

    Column(verticalArrangement = Arrangement.Top, horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize().background(
        BackgroundColor)) {
Spacer(modifier = Modifier.height(40.dp))
        Box(contentAlignment = Alignment.Center, modifier = Modifier.background(shape = CircleShape, color = RedColor).size(160.dp).border(width = 6.dp, color = Color.Black, shape = CircleShape)) {


            Image(
                painter = painterResource(R.drawable.pizza),
                contentDescription = null,
                modifier = Modifier.padding(2.dp)
            )

        }
        Spacer(modifier = Modifier.height(35.dp))
        Box(modifier = Modifier.fillMaxWidth().clip(
            RoundedCornerShape(24.dp)).padding(horizontal = 30.dp, vertical = 10.dp).background(color = BackgroundColor, shape = RectangleShape).border(shape = RoundedCornerShape(24.dp), width = 2.dp, color = BlackColor)
        ){
            Text(
                text = "ism",
                color = Color.Black,
                fontSize = 16.sp,
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .offset(y = (-9).dp, x = 10.dp) // Adjust to position text over the border
                    .background(BackgroundColor) // Optional: Adds background to separate from border
                    .padding(horizontal = 4.dp) // Padding for better appearance
            )
            Text(textAlign = TextAlign.Start, text = "DOSTONBEK", color = RedDark, modifier = Modifier.padding(horizontal = 10.dp, vertical = 10.dp), fontSize = 22.sp)
        }

        Box(modifier = Modifier.fillMaxWidth().clip(
            RoundedCornerShape(24.dp)).padding(horizontal = 30.dp, vertical = 10.dp).background(color = BackgroundColor, shape = RectangleShape).border(shape = RoundedCornerShape(24.dp), width = 2.dp, color = BlackColor)
        ){
            Text(
                text = "familya",
                fontSize = 16.sp,
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .offset(y = (-9).dp, x = 10.dp) // Adjust to position text over the border
                    .background(BackgroundColor) // Optional: Adds background to separate from border
                    .padding(horizontal = 4.dp) // Padding for better appearance
            )
            Text(textAlign = TextAlign.Start, text = "HUSANOV", color = RedDark, modifier = Modifier.padding(horizontal = 10.dp, vertical = 10.dp), fontSize = 22.sp)
        }
        Box(modifier = Modifier.fillMaxWidth().clip(
            RoundedCornerShape(24.dp)).padding(horizontal = 30.dp, vertical = 10.dp).background(color = BackgroundColor, shape = RectangleShape).border(shape = RoundedCornerShape(24.dp), width = 2.dp, color = BlackColor)
        ){
            Text(
                text = "telefo'n raqam",
                color = Color.Black,
                fontSize = 16.sp,
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .offset(y = (-9).dp, x = 10.dp) // Adjust to position text over the border
                    .background(BackgroundColor) // Optional: Adds background to separate from border
                    .padding(horizontal = 4.dp) // Padding for better appearance
            )
            Text(textAlign = TextAlign.Start, text = "+998 91 044 67 10", color = RedDark, modifier = Modifier.padding(horizontal = 10.dp, vertical = 10.dp), fontSize = 22.sp)
        }

        Box(modifier = Modifier.fillMaxWidth().clip(
            RoundedCornerShape(24.dp)).padding(horizontal = 30.dp, vertical = 10.dp).background(color = BackgroundColor, shape = RectangleShape).border(shape = RoundedCornerShape(24.dp), width = 2.dp, color = BlackColor)
        ){
            Text(
                text = "tug'ilgan sana",
                color = Color.Black,
                fontSize = 16.sp,
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .offset(y = (-9).dp, x = 10.dp) // Adjust to position text over the border
                    .background(BackgroundColor) // Optional: Adds background to separate from border
                    .padding(horizontal = 4.dp) // Padding for better appearance
            )
            Text(textAlign = TextAlign.Start, text = "10/10/2007",color = RedDark, modifier = Modifier.padding(horizontal = 10.dp, vertical = 10.dp), fontSize = 22.sp)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfilePreview(){
    ProfileScreen()
}