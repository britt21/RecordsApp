package com.mobile.recorduserapp.utils

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mobile.recorduserapp.utils.fonts.adinkbold
import com.mobile.recorduserapp.utils.fonts.adinklit


@Composable
fun addimage(image:Int,modifier: Modifier = Modifier){
    Image(painter = painterResource(id = image ), contentDescription = "", modifier = modifier,
        contentScale = ContentScale.Crop)
}


fun showToast(context: Context,message:String){
    Toast.makeText(context,message,Toast.LENGTH_SHORT).show()
}


@Composable
fun textboldcutom(text:String, size:Int, color: Color, click:(()->Unit)? = null, padding : (() -> Int)? = null,modifier: Modifier = Modifier) {
    Text(
        text = "${text}",
        color = color,
        fontFamily = adinkbold,
        fontSize = size.sp,
        modifier = modifier.clickable { if (click != null) { click() }}
            .padding(if (padding != null) padding().dp else 0.dp)
    )
}
@Composable
fun textlit(text:String, size:Int, color: Color, click:(()->Unit)? = null, padding : (() -> Int)? = null,modifier: Modifier = Modifier) {
    Text(
        text = "${text}",
        color = color,
        fontFamily = adinklit,
        fontSize = size.sp,
        modifier = modifier.clickable { if (click != null) { click() }}
            .padding(if (padding != null) padding().dp else 0.dp)
    )
}


@Composable fun sh10(){ Spacer(modifier = Modifier.height(10.dp)) }
@Composable fun sh20(){ Spacer(modifier = Modifier.height(20.dp)) }
@Composable fun sh30(){ Spacer(modifier = Modifier.height(30.dp)) }
@Composable fun sh40(){ Spacer(modifier = Modifier.height(40.dp)) }
@Composable fun sh50(){ Spacer(modifier = Modifier.height(50.dp)) }

