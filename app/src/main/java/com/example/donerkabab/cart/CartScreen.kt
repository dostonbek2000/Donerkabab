package com.example.donerkabab.cart

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.times
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.donerkabab.R
import com.example.donerkabab.ui.theme.BackgroundColor
import com.example.donerkabab.ui.theme.DonerKababTheme
import com.example.donerkabab.ui.theme.RedColor
import kotlin.time.times

@Composable
fun CartScreen(navController: NavController) {

    val cartItems = remember { mutableStateMapOf<Int, Int>() } // id to quantity mapping
    val priceItems= remember { mutableStateMapOf<Int,Int>() }
    val totalPrice=priceItems.values.sum()

    val totalItems = cartItems.values.sum()
    Scaffold(containerColor = BackgroundColor ,modifier = Modifier.fillMaxSize(), topBar = {   Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 8.dp) // Add padding here
            .clip(RoundedCornerShape(20.dp))
            .background(RedColor),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "$totalItems ta taom savatda",
            fontSize = 18.sp,
            color = Color.White, lineHeight = 46.sp,
            fontWeight = FontWeight.Bold
        )
    }

    }, bottomBar = {  Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        contentAlignment = Alignment.Center
    ) {
        Calculation(totalProducts = totalItems.toString(), totalPrice = totalPrice.toString())
    }
    }) { innerPadding->


    Column(
        modifier = Modifier
            .fillMaxSize().padding(innerPadding)
            .background(BackgroundColor),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        LazyColumn(contentPadding = PaddingValues( horizontal = 10.dp)) {
            items(2) { itemId ->
                FoodCartItem(
                    title = "Paper Burger",
                    type = "BURGER",
                    desc = "Delivery to all regions is available",
                    price = "15000",
                    image = painterResource(R.drawable.hot_dog),
                    quantity = cartItems[itemId] ?: 0,
                    onQuantityChange = { newQuantity -> cartItems[itemId] = newQuantity },
                    totalPrice = { newPrice -> priceItems[itemId] = newPrice }
                )

            }


        }


    }
    }
}

@Composable
fun Calculation(totalProducts:String,totalPrice:String){
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
        Column (verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.Start){
            Text(text = "TOTAL $totalPrice so'm", fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
            Text(text = "$totalProducts taom", fontWeight = FontWeight.SemiBold, fontSize = 14.sp)

        }
        Button( onClick = {}, colors = ButtonDefaults.buttonColors(backgroundColor = RedColor, contentColor = Color.White), modifier = Modifier.padding(horizontal = 6.dp, vertical = 4.dp)) {
            Text(text="Xarid qilish", fontSize = 18.sp, color = Color.White, fontWeight = FontWeight.Bold)
        }
    }

}

@Composable
fun FoodCartItem(
    title: String,
    type: String,
    desc: String,
    price: String,
    image: Painter,
    quantity: Int,
    onQuantityChange: (Int) -> Unit,
    totalPrice:(Int) -> Unit

    ) {
    totalPrice(quantity* price.toInt())


    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp, vertical = 6.dp),
        shape = RoundedCornerShape(12.dp)
    ) {

        Column(
            modifier = Modifier
                .background(Color.White)
                .padding(horizontal = 10.dp, vertical = 6.dp)
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

            Spacer(modifier = Modifier.height(6.dp))

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
                        modifier = Modifier
                            .padding(horizontal = 5.dp)
                            .clickable { if (quantity > 0) onQuantityChange(quantity - 1)
                             if (price.toInt()>1) totalPrice(price.toInt()-price.toInt())}
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
                    Text(quantity.toString(),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = RedColor
                    )
                    Box(
                        modifier = Modifier
                            .padding(horizontal = 5.dp)
                            .clickable {onQuantityChange(quantity + 1)
                                totalPrice(quantity* price.toInt())
                            }
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
        CartScreen(navController = rememberNavController())
    }
}