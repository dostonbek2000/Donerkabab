package com.example.donerkabab.onBoarding
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.graphics.Color

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.donerkabab.ui.theme.BackgroundColor
import com.example.donerkabab.ui.theme.RedColor
import com.example.donerkabab.ui.theme.TextColor
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager

@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnBoardingScreen(onFinish: () -> Unit,navController: NavController) {
    val pagerState = com.google.accompanist.pager.rememberPagerState(initialPage = 0)
    val (selectedPage, setSelectedPage) = remember { mutableIntStateOf(0) }

    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.currentPage }.collect { page ->
            setSelectedPage(page)
        }
    }

    val scope = rememberCoroutineScope()

    Column(verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(vertical = 16.dp)
    ) {
        // Horizontal Pager (Dots Indicator)

Spacer(modifier = Modifier.height(70.dp))
        // Horizontal Pager
        HorizontalPager(
            count = listData.size,
            state = pagerState,
            modifier = Modifier.weight(1f)
        ) { page ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Top,
                modifier = Modifier
                    .fillMaxSize().padding(horizontal = 16.dp)
            ) {
                // Display the image
                Image(
                    painter = painterResource(id = listData[page].image),
                    contentDescription = listData[page].title, contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .size(350.dp)
                        .aspectRatio(1f).padding(horizontal = 10.dp)
                )

                // Display the title
                Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                    Text(
                        text = listData[page].title,
                        color = Color.Black,
                        modifier = Modifier.padding(top = 20.dp),
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold
                    )

                    // Display the description
                    Text(
                        text = listData[page].desc,
                        color = TextColor, textAlign = TextAlign.Center,
                        modifier = Modifier.padding(top = 10.dp), fontSize = 22.sp
                    )
                }

            }

        }
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            for (i in listData.indices) {
                Box(
                    modifier = Modifier
                        .padding(4.dp)
                        .size(12.dp)
                        .clip(RoundedCornerShape(2.dp))
                        .background(shape = CircleShape, color =
                        if (i == selectedPage) RedColor
                        else TextColor
                        )
                )
            }
        }

        // Navigation Button
        Button(
            onClick = {
                if (selectedPage == listData.size - 1) {
                    onFinish() // Corrected from `onFinish` to `onFinish()`
                } else {
                    scope.launch {
                        pagerState.animateScrollToPage(selectedPage + 1)
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .height(56.dp),
            shape = RoundedCornerShape(26.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = RedColor,
                contentColor = Color.Black
            )
        ) {
            Text(
                text ="Next",
                color = Color.White, fontSize = 20.sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewOnBoardingScreen() {
    OnBoardingScreen(onFinish = {}, navController = rememberNavController())
}