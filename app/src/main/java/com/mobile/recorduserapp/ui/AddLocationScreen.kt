package com.mobile.recorduserapp.ui

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
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
import com.mobile.recorduserapp.utils.addimage
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil3.compose.rememberAsyncImagePainter
import com.mobile.recorduserapp.data.request.create_user.CreateUser
import com.mobile.recorduserapp.ui.theme.yellowColor
import com.mobile.recorduserapp.ui.viewmodel.HomeViewModel
import com.mobile.recorduserapp.utils.error.errorbox
import com.mobile.recorduserapp.utils.sh10
import com.mobile.recorduserapp.utils.showToast


@Composable
fun AddLocationScreen(
    modifier: Modifier, viewModel: HomeViewModel = viewModel(), navController: NavController
) {
   var country: String = ""
   var name: String = ""
   var image: String = ""
   var latitude: String =""
   var longitude: String = ""

    val isloading by viewModel.isloading.observeAsState()


    var context = LocalContext.current
    var updatedCountry by remember { mutableStateOf(country) }
    var updatedName by remember { mutableStateOf(name) }
    var updatedImage by remember { mutableStateOf(image) }
    var updatedLatitude by remember { mutableStateOf(latitude.toString()) }
    var updatedLongitude by remember { mutableStateOf(longitude.toString()) }

    val liveuser by viewModel.livecreateuser.observeAsState()
    val error by viewModel.error.observeAsState()

    liveuser?.let {
        LaunchedEffect(Unit) {


            showToast(context, "Created Successfully\nID: ${it.pupilId}",)
            println("FOUNDATA:: " + it)
            navController.popBackStack()
            viewModel.clearlog()
        }
    }


    error?.let {
        println("FOUNDATA:: "+it)
    }




    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(Color.White)
            .verticalScroll(rememberScrollState())
        ,
    ) {
        addimage(R.drawable.back, modifier = Modifier.clickable { navController.popBackStack() })

        error?.let {
            var createUser = CreateUser(updatedCountry,"https://www.freepik.com/premium-photo/indian-young-happy-man-image_23019783.htm",updatedLatitude,updatedLatitude,updatedName,)
            println("CREATINGLL:: "+createUser)

            errorbox("Error","${it}",{ true}, { viewModel.createUsers(createUser)})

        }

        sh10()

        Text(
            text = "Add Location",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        //
            if(isloading == true){
                Row (modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center){
                    CircularProgressIndicator(color = Color.Black, modifier = Modifier.size(30.dp))

                }
            }else{
                if(isloading == null){

                }
            }

        sh10()
        // Image Upload Placeholder
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
                .background(Color.LightGray.copy(alpha = 0.3f), RoundedCornerShape(12.dp))
                .border(1.dp, Color.Gray, RoundedCornerShape(12.dp))
                .clickable {
                    // Trigger image upload action
                },
            contentAlignment = Alignment.Center
        ) {
            if (updatedImage.isEmpty()) {
                Text("Upload Image", color = Color.Gray)
            } else {
                // Load and display the image
                Image(
                    painter = rememberAsyncImagePainter(updatedImage),
                    contentDescription = "Location Image",
                    modifier = Modifier.fillMaxSize()
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Name Field
        OutlinedTextField(
            value = updatedName,
            onValueChange = { updatedName = it },
            label = { Text("Name") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Country Field
        OutlinedTextField(
            value = updatedCountry,
            onValueChange = { updatedCountry = it },
            label = { Text("Country") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Latitude Field
        OutlinedTextField(
            value = updatedLatitude,
            onValueChange = { updatedLatitude = it },
            label = { Text("Latitude") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            isError = updatedLatitude.toDoubleOrNull() == null
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Longitude Field
        OutlinedTextField(
            value = updatedLongitude,
            onValueChange = { updatedLongitude = it },
            label = { Text("Longitude") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            isError = updatedLongitude.toDoubleOrNull() == null
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Save & Cancel Buttons
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = {

                    navController.popBackStack()
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Gray)
            ) {
                Text("Cancel")
            }

            Button(
                onClick = {

                    if(validator(context = context,updatedName,updatedCountry,updatedLatitude,updatedLongitude)){
                        var createUser = CreateUser(updatedCountry,"https://www.freepik.com/premium-photo/indian-young-happy-man-image_23019783.htm",updatedLatitude,updatedLatitude,updatedName,)
                        println("CREATINGLL:: "+createUser)
                        viewModel.createUsers(createUser)

                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = yellowColor)
            ) {

                Text("Save", color = Color.Black)
            }
        }
    }

}

fun validator(context:Context,name:String, ct:String, la:String, lo:String, ):Boolean{

    if (name.isEmpty()){
        showToast(context,"Name Must not be empty")
        return false
    }


        if (ct.isEmpty()){
        showToast(context,"Country Must not be empty")
        return false
    }


        if (la.isEmpty()){
        showToast(context,"Latitude Must not be empty")
        return false
    }


        if (lo.isEmpty()){
        showToast(context,"Longtitude Must not be empty")
        return false
    }

    return true


}
