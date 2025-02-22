package com.mobile.recorduserapp.ui

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
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.mobile.recorduserapp.data.cons.EDITSCREEN
import com.mobile.recorduserapp.ui.theme.litg
import com.mobile.recorduserapp.ui.theme.whitecolor
import com.mobile.recorduserapp.ui.viewmodel.HomeViewModel
import com.mobile.recorduserapp.utils.buttons.appbutton
import com.mobile.recorduserapp.utils.sh10
import com.google.accompanist.placeholder.material.placeholder
import com.google.accompanist.placeholder.material.shimmer
import com.mobile.recorduserapp.utils.error.errorbox


@Composable
fun HomeScreen(modifier: Modifier, viewModel: HomeViewModel = viewModel(), navController: NavController){
    val allusers by viewModel.liveUsers.observeAsState()
    val error by viewModel.error.observeAsState()
    val isloading by viewModel.isloading.observeAsState()

    var context = LocalContext.current


    allusers?.let {
        println("DDDAATAA:: +" + it)
    }

    error?.let {
        Toast.makeText(context,it,Toast.LENGTH_SHORT).show()
    }
    println("USERSSFONUND:: "+allusers)
    println("FATALL:: "+error)

    LaunchedEffect(key1 = Unit) {
        viewModel.getUsers()
    }

    Column (
        modifier = modifier
            .background(Color.White)
            .fillMaxSize()) {

        Column (
            modifier = Modifier
                .padding(horizontal = 11.dp)
                .background(Color.White)
                .fillMaxWidth()

        ) {

            error?.let {
                errorbox("Error","${it}",{ true}, { viewModel.getUsers()})

            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 0.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {


                addimage(image = R.drawable.avatar)
                addimage(image = R.drawable.bell,Modifier.size(47.dp))

            }


            textboldcutom(text = "Hey there, Lucy! ", size = 15, color = Color.Black)
            textlit(text = "Are you excited to create a task today? ", size = 14, color = greyTextColor)


            var text by remember { mutableStateOf("") }

            OutlinedTextField(
                value = text,
                onValueChange = { text = it },
                placeholder = { Text("Search by id...", color = Color.Gray) },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search",
                        tint = Color.Gray
                    )
                },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.LightGray.copy(alpha = 0.2f),
                    unfocusedContainerColor = Color.LightGray.copy(alpha = 0.2f),
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 0.dp,)
            )
            sh10()

            textboldcutom(text = "All ", size = 14, color = Color.Black)


            println("ISLOADING:: "+isloading)
//
//            if(isloading == true){
//                Row (modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center){
//                    CircularProgressIndicator(color = Color.Black, modifier = Modifier.size(30.dp))
//
//                }
//            }else{
//                if(isloading == null){
//
//                }
//            }




            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(450.dp)
            ) {
                if (isloading == true) {
                    items(5) {
                        LocationCard(
                            name = "",
                            country = "",
                            latitude = "",
                            longitude = "",
                            isLoading = true
                        )
                    }
                } else {
                    allusers?.items?.let { info ->
                        items(info) { item ->
                            item?.let {
                                LocationCard(
                                    name = it.name ?: "",
                                    country = it.country ?: "",
                                    latitude = it.latitude.toString(),
                                    longitude = it.longitude.toString()
                                )
                            }
                        }
                    }
                }
            }

        }




        Box(modifier = Modifier.fillMaxWidth().height(80.dp).padding(vertical = 4.dp)){
            Column (modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Bottom){
                appbutton("Add", modifier = Modifier.padding(horizontal = 10.dp, vertical = 0.dp), click = {
                    println("TO ADD")
                    navController.navigate(
                    EDITSCREEN)})
            }

        }



    }
    }

@Composable
fun LocationCard(
    name: String,
    country: String,
    latitude: String,
    longitude: String,
    isLoading: Boolean = false
) {
    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = litg),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .placeholder(
                visible = isLoading,
                highlight = PlaceholderHighlight.shimmer()
            )
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            InfoText(title = "Name:", value = name, isBold = true, isLoading = isLoading)
            InfoText(title = "Country:", value = country, isLoading = isLoading)
            InfoText(title = "Latitude:", value = latitude, isLoading = isLoading)
            InfoText(title = "Longitude:", value = longitude, isLoading = isLoading)
        }
    }
}

@Composable
fun InfoText(title: String, value: String, isBold: Boolean = false, isLoading: Boolean = false) {
    Row(
        modifier = Modifier
            .padding(vertical = 4.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = title,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.weight(1f)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = if (isLoading) "" else value,  // Empty text during shimmer
            fontSize = 16.sp,
            fontWeight = if (isBold) FontWeight.Bold else FontWeight.Normal,
            color = Color.Black.copy(alpha = 0.8f),
            modifier = Modifier
                .weight(2f)
                .placeholder(
                    visible = isLoading,
                    highlight = PlaceholderHighlight.shimmer()
                )
        )
    }
}


