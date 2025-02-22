package com.mobile.recorduserapp.utils.error


import androidx.compose.material3.Text

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SegmentedButtonDefaults.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.mobile.recorduserapp.R
import com.mobile.recorduserapp.ui.theme.greyTextColor
import com.mobile.recorduserapp.utils.addimage
import com.mobile.recorduserapp.utils.textboldcutom
import com.mobile.recorduserapp.utils.textlit
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.*
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.*
import androidx.compose.ui.modifier.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.mobile.recorduserapp.data.cons.EDITSCREEN
import com.mobile.recorduserapp.ui.theme.litg
import com.mobile.recorduserapp.ui.theme.whitecolor
import com.mobile.recorduserapp.ui.viewmodel.HomeViewModel
import com.mobile.recorduserapp.utils.buttons.appbutton
import com.mobile.recorduserapp.utils.sh10
import com.google.accompanist.placeholder.material.placeholder
import com.google.accompanist.placeholder.material.shimmer


@Composable
fun errorbox(
    title: String = "Error",
    message: String,
    onDismiss: () -> Unit,
    onRetry: (() -> Unit)? = null
) {
    var showDialog by remember { mutableStateOf(true) }

    if (showDialog) {
        val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.error))
        val progress by animateLottieCompositionAsState(composition)

        AlertDialog(
            onDismissRequest = {
                showDialog = false
                onDismiss()  // Trigger external dismiss
            },
            confirmButton = {
                Button(
                    onClick = {
                        showDialog = false
                        onDismiss()  // Close button dismiss
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                    modifier = Modifier.padding(8.dp)
                ) {
                    Text("Close", color = Color.White)
                }
            },
            dismissButton = {
                onRetry?.let {
                    Button(
                        onClick = {
                            it()  // Retry action
                            showDialog = false
                            onDismiss()  // Dismiss after retry
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Text("Retry", color = Color.White)
                    }
                }
            },
            title = {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    LottieAnimation(
                        composition = composition,
                        progress = { progress },
                        modifier = Modifier.size(120.dp)
                    )
                    Text(title, fontWeight = FontWeight.Bold, fontSize = 20.sp)
                }
            },
            text = {
                Text(
                    text = message,
                    fontSize = 16.sp,
                    color = Color.Gray
                )
            },
            shape = RoundedCornerShape(16.dp),
            containerColor = Color.White
        )
    }
}