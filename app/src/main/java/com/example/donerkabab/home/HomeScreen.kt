package com.example.donerkabab.home

import android.provider.FontsContract.Columns
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.donerkabab.R
import com.example.donerkabab.ui.theme.BackgroundColor
import com.example.donerkabab.ui.theme.BlackColor
import com.example.donerkabab.ui.theme.DonerKababTheme
import com.example.donerkabab.ui.theme.RedColor
import com.example.donerkabab.ui.theme.RedDark

@Composable
fun HomeScreen(){
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize().background(
        BackgroundColor)) {
        val foodGroups= listOf( Pair("Popular food",listOf( FoodData(R.drawable.pizza, "Pepper Pizza", "PIZZA", "15000"),
            FoodData(R.drawable.hot_dog, "Classic Burger", "BURGER", "12000"),
            FoodData(R.drawable.lavash, "Chicken Lavash", "LAVASH", "10000"))),Pair("Burger Delights",listOf( FoodData(R.drawable.pizza, "Pepper Pizza", "PIZZA", "15000"),
            FoodData(R.drawable.hot_dog, "Classic Burger", "BURGER", "12000"),
            FoodData(R.drawable.lavash, "Chicken Lavash", "LAVASH", "10000"))),Pair("Lavash Specials",listOf( FoodData(R.drawable.pizza, "Pepper Pizza", "PIZZA", "15000"),
            FoodData(R.drawable.hot_dog, "Classic Burger", "BURGER", "12000"),
            FoodData(R.drawable.lavash, "Chicken Lavash", "LAVASH", "10000"))))


      SearchScreen()
        CategoriesRow()

      LazyColumn(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.spacedBy(8.dp)) {
          items(foodGroups){ (title,foodItems)->
              FoodSection(foodData =foodItems, foodType = title)
          }
      }


       }





}


@Composable
fun FoodSection(foodData: List<FoodData>,foodType:String) {
    Column {
        Text(
            text = foodType,
            color = BlackColor,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )
    LazyRow () {
        items(foodData) { food ->
            Column(modifier = Modifier.padding(10.dp)) {


                FoodItem(
                    title = food.title,
                    image = painterResource(id = food.image),
                    desc = food.desc,
                    price = food.price,
                )
            }
        }
        }
    }
}

@Composable
fun FoodItem(
    title: String,
    image: Painter,
    desc: String,
    price: String
) {
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
            .background(Color.White)
            .width(160.dp).clickable {  }
    ) {
        // Favorite icon at the end
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding( top = 8.dp, end = 8.dp)
        ) {

                    Icon(
                        imageVector = Icons.Default.FavoriteBorder,
                        contentDescription = null,
                        modifier = Modifier.clip(CircleShape)
                            .size(20.dp)
                            .align(Alignment.TopEnd).clickable { }, tint = RedColor
                    )

            Image(
                painter = image,
                contentDescription = null,
                modifier = Modifier
                    .size(140.dp)
                    .align(Alignment.Center)
            )
        }

        // Food image

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = title,
                fontSize = 16.sp,
                color = RedColor,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
            Text(
                text = desc,
                fontSize = 12.sp,
                color = Color.Black,
                textAlign = TextAlign.Start,
                modifier = Modifier.padding(horizontal = 8.dp))
        }



        // Price and add button row with space between
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = price,
                    fontSize = 16.sp,
                    color = RedColor,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "so'm",
                    fontSize = 16.sp,
                    color = RedColor
                )
            }

            // Plus icon
            Box(
                modifier = Modifier.clickable {  }
                    .size(20.dp) // Size of the background box
                    .clip(RoundedCornerShape(4.dp)) // Rounded corners
                    .background(RedColor), // Background color
                contentAlignment = Alignment.Center // Center the icon inside the box
            ) {
            Icon(
               imageVector = Icons.Default.Add,
                contentDescription = null,
                modifier = Modifier.size(20.dp).background(RedColor), tint = Color.White

            )
        }}
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
fun CategoriesRow(onCategorySelected:(String)-> Unit ={}) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        val categories = listOf("All", "Pizza", "Burger", "Lavash")
        var selectedCategory by remember { mutableStateOf("All") }
        categories.forEach { category ->
            Button(
                onClick = { selectedCategory=category
                    onCategorySelected(category)},
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(containerColor = if (category == selectedCategory) Color.Red else Color.White)
            ) {
                Text(text = category, color = if (category == selectedCategory) Color.White else Color.Black)
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
