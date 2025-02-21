package com.mobile.recorduserapp.ui

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import com.mobile.recorduserapp.ui.theme.litg
import com.mobile.recorduserapp.ui.theme.whitecolor
import com.mobile.recorduserapp.ui.viewmodel.HomeViewModel
import com.mobile.recorduserapp.utils.buttons.appbutton
import com.mobile.recorduserapp.utils.sh10


@Composable
fun HomeScreen(modifier: Modifier,viewModel: HomeViewModel = HomeViewModel()){


    val allusers by viewModel.liveUsers.observeAsState()
    val error by viewModel.error.observeAsState()


    allusers.let { println("DDDAATAA:: +" + it) }
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
            textlit(text = "Are you excited to create a tasty dish today? ", size = 14, color = greyTextColor)


            var text by remember { mutableStateOf("") }

            OutlinedTextField(
                value = text,
                onValueChange = { text = it },
                placeholder = { Text("Search...", color = Color.Gray) },
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
                    .padding(top = 20.dp,)
            )
            sh10()

            Row {
                Box(modifier = Modifier.height(30.dp)){
                    textlit(text = "All", size = 13, color = whitecolor)
                }
            }
            textboldcutom(text = "All ", size = 14, color = Color.Black)

            Box(
                modifier = Modifier
                    .height(250.dp)
                    .fillMaxWidth().border(color = greyTextColor, width = 1.dp, shape = RoundedCornerShape(10.dp))
            ) {



                Column(modifier = Modifier.fillMaxSize().padding(0.dp)) {

                    addimage(image = R.drawable.food, modifier = Modifier.fillMaxWidth())

                        Column(modifier = Modifier.fillMaxWidth().padding(10.dp)) {

                        Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        textboldcutom(
                            text = "Garlic Butter Shrimp Pasta",
                            size = 14,
                            color = Color.Black,
                            modifier = Modifier.weight(1f) // Ensures text gets space
                        )
                        addimage(image = R.drawable.like)
                    }

                    Row(modifier = Modifier.fillMaxWidth()) {
                        addimage(image = R.drawable.fire)
                        textlit(
                            text = "320 Calories",
                            size = 14,
                            color = Color.Black,
                            modifier = Modifier.padding(start = 8.dp) // Adds spacing from image
                        )
                    }
                    Row(modifier = Modifier.fillMaxWidth()) {
                        textlit(
                            text = "Creamy hummus spread on whole grain toast topped with sliced cucumbers and radishes.",
                            size = 14,
                            color = Color.Black,
                            modifier = Modifier.padding(start = 0.dp)
                        )
                    }
                    }

                }
            }


            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(500.dp)
            )
            {
                allusers?.let {
                    items(allusers!!.data!!) {



                    }
                }

            }
        }


        Column (modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Bottom){
            appbutton("Add", modifier = Modifier.padding(horizontal = 10.dp, vertical = 10.dp))
        }



    }
    }

@Composable
fun LocationCard(name: String, country: String, latitude: String, longitude: String) {
    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = litg),

        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            InfoText(title = "Name:", value = name, isBold = true)
            InfoText(title = "Country:", value = country)
            InfoText(title = "Latitude:", value = latitude)
            InfoText(title = "Longitude:", value = longitude)
        }
    }
}

@Composable
fun InfoText(title: String, value: String, isBold: Boolean = false) {
    Row(modifier = Modifier.padding(vertical = 4.dp)) {
        Text(
            text = title,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = value,
            fontSize = 16.sp,
            fontWeight = if (isBold) FontWeight.Bold else FontWeight.Normal,
            color = Color.Black.copy(alpha = 0.8f)
        )
    }
}


