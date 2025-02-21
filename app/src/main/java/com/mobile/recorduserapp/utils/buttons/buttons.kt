package com.mobile.recorduserapp.utils.buttons

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.mobile.recorduserapp.ui.theme.statusbgb
import com.mobile.recorduserapp.ui.theme.yellowColor
import com.mobile.recorduserapp.utils.textboldcutom


@Composable
fun appbutton(
    text: String,
    click: (() -> Unit)?=null,
    isloading: (() -> Boolean)? = null,
    modifier: Modifier = Modifier
) {
    Row (modifier = modifier){
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .clip(shape = RoundedCornerShape(7.dp))
                .fillMaxWidth()
                .height(55.dp)
                .background(yellowColor)
                .clickable(enabled = isloading?.invoke() != true) {
                    if (click != null) {
                        click()
                    }
                }
        ) {
            Row {
                textboldcutom(text = text, size = 15, color = statusbgb, click = {if (click != null) {
                    click()
                }
                })
                Box(modifier = Modifier.width(10.dp))
                if (isloading?.invoke() == true) {
                    CircularProgressIndicator(color = Color.Black, modifier = Modifier.size(20.dp))
                }
            }

        }
    }
}