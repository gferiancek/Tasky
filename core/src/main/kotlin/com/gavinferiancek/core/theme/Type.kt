package com.gavinferiancek.core.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.gavinferiancek.core.R

// Set of Material typography styles to start with
private val inter = FontFamily(
    Font(R.font.inter_light, weight = FontWeight.Light),
    Font(R.font.inter_regular, weight = FontWeight.Normal),
    Font(R.font.inter_bold, weight = FontWeight.Bold)
)
val Typography = Typography(
    h1 = TextStyle(
        fontFamily = inter,
        fontWeight = FontWeight.Light,
        fontSize = 36.sp,
    ),
    h2 = TextStyle(
        fontFamily = inter,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp
    ),
    h3 = TextStyle(
        fontFamily = inter,
        fontWeight = FontWeight.Normal,
        fontSize =  24.sp
    ),
    h4 = TextStyle(
        fontFamily = inter,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp
    ),
    h5 = TextStyle(
        fontFamily = inter,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp
    ),
    h6 = TextStyle(
        fontFamily = inter,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp
    ),
    subtitle1 = TextStyle(
        fontFamily = inter,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    subtitle2 = TextStyle(
        fontFamily = inter,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp
    ),
    body1 = TextStyle(
        fontFamily = inter,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    ),
    body2 = TextStyle(
        fontFamily = inter,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    ),
    button = TextStyle(
        fontFamily = inter,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp
    ),
    caption = TextStyle(
        fontFamily = inter,
        fontWeight = FontWeight.Normal,
        fontSize = 11.sp
    ),
    overline = TextStyle(
        fontFamily = inter,
        fontWeight = FontWeight.Normal,
        fontSize = 10.sp
    ),
)