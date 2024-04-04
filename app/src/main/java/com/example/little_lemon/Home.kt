package com.example.little_lemon

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.IconButton
import androidx.compose.material.ScaffoldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.little_lemon.ui.theme.LittleLemonColor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Composable
fun Home(navController: NavController){

    Column{
        TopAppBar(navController)
        UpperPanel()

        }

    }

@Composable
fun menuCategory(){
    Row(horizontalArrangement = Arrangement.SpaceAround,
       // modifier = Modifier.fillMaxWidth().padding(10.dp),
        verticalAlignment = Alignment.CenterVertically
    )  {
        Button(
            onClick = {},
            shape = RoundedCornerShape(20.dp),
            colors = ButtonDefaults.buttonColors(LittleLemonColor.cloud),
            modifier =Modifier.padding(start = 5.dp)

        ) {
            Text(
                text = "Starters",
                color = Color(0xFF333333),
                style = MaterialTheme.typography.titleMedium

            )
        }
        Button(
            onClick = {},
            shape = RoundedCornerShape(20.dp),
            colors = ButtonDefaults.buttonColors(LittleLemonColor.cloud),
            modifier =Modifier.padding(start = 5.dp)
        ) {
            Text(
                text = "Mains",
                color = Color(0xFF333333)
            )
        }
        Button(
            onClick = {},
            shape = RoundedCornerShape(20.dp),
            colors = ButtonDefaults.buttonColors(LittleLemonColor.cloud),
            modifier =Modifier.padding(start = 5.dp)
        ) {
            Text(
                text = "Desserts",
                color = Color(0xFF333333)
            )
        }
        Button(
            onClick = {},
            shape = RoundedCornerShape(20.dp),
            colors = ButtonDefaults.buttonColors(LittleLemonColor.cloud),
            modifier =Modifier.padding(start = 5.dp)
        ) {
            Text(
                text = "Drinks",
                color = Color(0xFF333333)
            )
        }
    }
}

@Composable
fun TopAppBar(navController: NavController) {
    Row(horizontalArrangement = Arrangement.SpaceAround,
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Little Lemon Logo",
            modifier= Modifier.requiredSize(230.dp,80.dp)

        )
        IconButton(onClick = {navController.navigate(Profile.route ) }) {
            Image(
                painter = painterResource(id = R.drawable.profile),
                contentDescription = "Cart",
                modifier = Modifier.size(70.dp)
            )
        }

    }
}