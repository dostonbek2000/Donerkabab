package com.example.donerkabab.cart

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.donerkabab.R
import com.example.donerkabab.ui.theme.BackgroundColor
import com.example.donerkabab.ui.theme.BlackColor
import com.example.donerkabab.ui.theme.DonerKababTheme
import com.example.donerkabab.ui.theme.RedColor
@Composable
fun CartScreen() {
    val title by remember { mutableStateOf("") }
    Column(
        modifier = Modifier.fillMaxSize().background(BackgroundColor),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(10.dp).width(10.dp))
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(20.dp))
                .background(RedColor).width(370.dp)

                ,
            horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(text = "1", fontSize = 18.sp, color = Color.White, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.width(2.dp))
                Text(
                    text = "ta taom savatda",
                    fontSize = 18.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold, lineHeight = 42.sp
                )
            }


        }
        Spacer(modifier = Modifier.width(10.dp))

        FoodCartItem(
            "Paper Burger",
            "BURGER",
            "Delivery to all regions is available",
            "15000",
            image = painterResource(R.drawable.hot_dog)
        )
    }
}
@Composable
fun CartTitle(title: String) {

    Column(
        modifier = Modifier
            .fillMaxWidth().clip(RoundedCornerShape(10.dp))

            .background(RedColor)
             .padding(10.dp)
            .height(35.dp),
        horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(text = title, fontSize = 18.sp, color = Color.White, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.width(2.dp))
            Text(
                text = "ta taom savatda",
                fontSize = 18.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }


    }

}
@Composable
fun FoodCartItem(
    title: String,
    type: String,
    desc: String,
    price: String,
    image: Painter
) {
    val itemCount = remember { mutableStateOf(1) }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier
                .background(Color.White)
                .padding(10.dp)
        ) {
            // Row for Image and Food Details
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Image Section
                Column(
                    verticalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = title,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Text(
                        text = type,
                        fontSize = 14.sp,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = desc,
                        fontSize = 12.sp,
                        color = Color.Black,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }
                Spacer(modifier = Modifier.width(16.dp))
                Image(
                    painter = image,
                    contentDescription = "Food Image",
                    modifier = Modifier
                        .size(100.dp)
                        .clip(RoundedCornerShape(12.dp)),
                    contentScale = ContentScale.Crop
                )



                // Food Details Section

            }

            Spacer(modifier = Modifier.height(10.dp))

            // Row for Price and Quantity
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Price Section
                Text(
                    text = "$price so'm",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = RedColor // Red color
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End, modifier = Modifier.fillMaxWidth()
                ) {
                    Box(
                        modifier = Modifier.padding(horizontal = 5.dp)
                            .clickable {if (itemCount.value > 1) itemCount.value--  }
                            .size(18.dp) // Size of the background box
                            .clip(RoundedCornerShape(4.dp)) // Rounded corners
                            .background(RedColor), // Background color
                        contentAlignment = Alignment.Center // Center the icon inside the box
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.minus),
                            contentDescription = null,
                            modifier = Modifier
                                .background(RedColor), tint = Color.White

                        )
                    }
                    Text(itemCount.value.toString(), fontSize = 18.sp, fontWeight = FontWeight.Bold, color = RedColor)
                    Box(
                        modifier = Modifier.padding(horizontal = 5.dp)
                            .clickable { itemCount.value++  }
                            .size(18.dp) // Size of the background box
                            .clip(RoundedCornerShape(4.dp)) // Rounded corners
                            .background(RedColor), // Background color
                        contentAlignment = Alignment.Center // Center the icon inside the box
                    ) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = null,
                            modifier = Modifier

                                .background(RedColor), tint = Color.White

                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FoodCartPreview() {
    DonerKababTheme {
        CartScreen()
    }
}