package com.example.little_lemon

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.little_lemon.ui.theme.LittleLemonColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Profile(navController: NavController){
    val context = LocalContext.current

    val preferencesManager = remember { PreferencesManager(context) }
    //preferencesManager.getData()
    val firstNameData =preferencesManager.getData("firstName","")
    val secondNameData=preferencesManager.getData("lastName","lastName")
    val emailData=preferencesManager.getData("email","")

    Column(


    ) {


        Image(
            painter = painterResource(
                id = R.drawable.logo
            ),
            contentDescription = "Logo Image", alignment = Alignment.Center,
            modifier = Modifier.size(400.dp, 120.dp)
                .padding(30.dp)

        )
        Text(text = "Personal Information",
            modifier = Modifier.padding(10.dp,top =100.dp,end =0.dp,bottom=30.dp),
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp)

        Text(text = "FirstName",modifier = Modifier.padding(10.dp))
        OutlinedTextField(
            value = firstNameData,
            readOnly = true,
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor =   Color.White,
                cursorColor = Color.Black,
               focusedIndicatorColor = Color.Gray
            ),
            onValueChange = { firstNameData },
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier.fillMaxWidth(1f).padding(10.dp)
        )

        Text(text = "LastName",modifier = Modifier.padding(10.dp))
        OutlinedTextField(
            value = secondNameData,
            readOnly = true,
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor =   Color.White,
                cursorColor = Color.Black,
                focusedIndicatorColor = Color.Gray
            ),
            onValueChange = { secondNameData },
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier.fillMaxWidth(1f).padding(10.dp)

        )

        Text(text = "Email",modifier = Modifier.padding(10.dp))
        OutlinedTextField(
            value = emailData,
            readOnly = true,
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor =   Color.White,
                cursorColor = Color.Black,
                focusedIndicatorColor = Color.Gray
            ),

            onValueChange = {emailData },
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier.fillMaxWidth(1f).padding(10.dp)


        )
        /* Button to logout */

        Button(modifier = Modifier.fillMaxWidth(1f)
            .padding(10.dp,top =120.dp,end=0.dp,bottom=0.dp)
            .size(50.dp),

            onClick = {
                preferencesManager.remove("firstName")
                preferencesManager.remove("lastName")
                preferencesManager.remove("email")

                navController.navigate(Onboarding.route )
            },
            border= BorderStroke(1.dp, LittleLemonColor.peach),
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(
                LittleLemonColor.yellow
            )
        ) {
            Text(
                text = "Log Out",
                color = Color(0xFF333333),
                style = MaterialTheme.typography.bodyLarge,
                fontWeight=FontWeight.Bold
            )
        }

    }
}