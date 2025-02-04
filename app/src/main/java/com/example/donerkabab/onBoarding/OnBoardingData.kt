package com.example.donerkabab.onBoarding

import com.example.donerkabab.R

class OnBoardingData(val image: Int, val title: String, val desc: String)

val listData = listOf(
    OnBoardingData(
        R.drawable.hot_dog,
        "Mazali Taomlar",
        "Sevimli ovqatlaringizni osongina buyurtma qiling va tezkor yetkazib berishdan rohatlaning!"
    ),
    OnBoardingData(
        R.drawable.kfc,
        "Cheksiz Tanlov",
        "Yuzlab taomlar va restoranlar orasidan tanlang, har doim yangi ta'mlarni kashf eting."
    ),
    OnBoardingData(
        R.drawable.pizza,
        "Tez va Oson",
        "Qulay to'lov va tez yetkazib berish bilan taomingiz har doim siz bilan!"
    )
)
