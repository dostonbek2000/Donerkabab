package com.example.donerkabab.home

import android.graphics.drawable.shapes.OvalShape
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.donerkabab.R
import com.example.donerkabab.ui.theme.DonerKababTheme
import com.example.donerkabab.ui.theme.RedColor

@Composable
fun HomeScreen(){
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        SearchScreen()
        CategoriesRow()
    }

}
@Composable
fun SearchScreen() {
    var text by remember { mutableStateOf("") }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        BasicTextField(
            value = text,
            onValueChange = { text = it },
            textStyle = TextStyle(color = Color.White, fontSize = 16.sp),
            cursorBrush = SolidColor(Color.White),
            modifier = Modifier
                .weight(1f)
                .background(RedColor, RoundedCornerShape(24.dp))
                .padding(horizontal = 16.dp, vertical = 12.dp),
            decorationBox = { innerTextField ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.search),
                        contentDescription = "Search",
                        tint = Color.White
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Box(contentAlignment = Alignment.CenterStart) {
                        if (text.isEmpty()) {
                            Text(
                                text = "Search",
                                style = TextStyle(color = Color.White, fontSize = 16.sp)
                            )
                        }
                        innerTextField()
                    }
                }
            }
        )
        Spacer(modifier = Modifier.width(16.dp))
        IconButton(
            onClick = { /*TODO: Implement Filter*/ },
            modifier = Modifier.background(RedColor, shape = CircleShape).size(40.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.settings),
                contentDescription = "Settings",
                tint = Color.White
            )
        }
    }
}

@Composable
fun CategoriesRow() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        val categories = listOf("All", "Pizza", "Burger", "Lavash")
        categories.forEach { category ->
            Button(
                onClick = { /* Handle Category Click */ },
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(containerColor = if (category == "All") Color.Red else Color.White)
            ) {
                Text(text = category, color = if (category == "All") Color.White else Color.Black)
            }
        }
    }
}
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomePreview(){
    DonerKababTheme {
        HomeScreen()
    }
}
