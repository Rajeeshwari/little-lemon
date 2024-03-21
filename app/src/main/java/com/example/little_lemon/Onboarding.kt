package com.example.little_lemon

import android.content.Context
import android.content.SharedPreferences
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.R
import androidx.compose.material.*

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

import com.example.little_lemon.R


//Shared Preference
class PreferencesManager(context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("LittleLemon.prefs", Context.MODE_PRIVATE)

    fun saveData(key: String, value: String) {
        val editor = sharedPreferences.edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun getData(key: String, defaultValue: String): String {
        return sharedPreferences.getString(key, defaultValue) ?: defaultValue
    }

    fun remove(key: String) {
        val editor = sharedPreferences.edit()
        editor.remove(key)
        editor.clear()
        editor.apply()

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Onboarding(navController: NavHostController){

    val context = LocalContext.current

    val preferencesManager = remember { PreferencesManager(context) }
    val firstNameData = remember { mutableStateOf(preferencesManager.getData("firstName", "")) }
    val lastNameData = remember { mutableStateOf(preferencesManager.getData("lastName", "")) }
    val emailData = remember { mutableStateOf(preferencesManager.getData("email", "")) }


    var firstName by rememberSaveable { mutableStateOf("") }
    var lastName by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable {mutableStateOf("")}

    Column(
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top

    ) {


        Image(
            painter = painterResource(
                id = R.drawable.logo
            ),
            contentDescription = "Logo Image",alignment = Alignment.Center,
            modifier = Modifier.size(400.dp, 200.dp)
                .padding(30.dp)

            )

        //Lets get to know text
        Card(  modifier = Modifier.size(600.dp,50.dp)
            ,colors =CardDefaults.cardColors( Color(0xFF495e57))
            ,shape = RectangleShape) {
            Text(text = "Lets get to know you",
                modifier = Modifier.padding(10.dp).align(Alignment.CenterHorizontally),
                color = Color(0xFFEDEFEE),
                fontWeight = FontWeight.Bold)
        }

        Text(text = "Personal Information",
            modifier = Modifier.padding(10.dp,top =30.dp,end =0.dp,bottom=30.dp),
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp)
        // text field , firstname, lastname, email
        Text(text = "FirstName",modifier = Modifier.padding(10.dp))
        OutlinedTextField(
            value = firstName,
            singleLine =true,
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.White,
                cursorColor = Color.Black,
                focusedIndicatorColor = Color.Black
            ),
            onValueChange = { firstName = it },
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier.fillMaxWidth(1f).padding(10.dp)
        )


        Text(text = "LastName",modifier = Modifier.padding(10.dp))
        OutlinedTextField(
            value = lastName,
            singleLine =true,
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.White,
                cursorColor = Color.Black,
                focusedIndicatorColor = Color.Black
            ),
            onValueChange = { lastName = it },
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier.fillMaxWidth(1f).padding(10.dp)

        )

        Text(text = "Email",modifier = Modifier.padding(10.dp))
        OutlinedTextField(
            value = email,
            singleLine =true,
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.White,
                cursorColor = Color.Black,
                focusedIndicatorColor = Color.Black
            ),

            onValueChange = {email= it },
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier.fillMaxWidth(1f).padding(10.dp)


        )


        //button to register
        Button(modifier = Modifier.fillMaxWidth(1f)
            .padding(10.dp,top =50.dp,end=0.dp,bottom=0.dp),


            onClick = {
                if (firstName.isBlank()|| lastName.isBlank() || email.isBlank()){

                    Toast.makeText(context,"Registration unsuccessful. Please enter all data.",
                        Toast.LENGTH_LONG).show()
                }
                else{

                    Toast.makeText(context,"Registration Successfully",
                        Toast.LENGTH_LONG).show()
                    preferencesManager.saveData("firstName",firstName)
                    preferencesManager.saveData("lastName",lastName)
                    preferencesManager.saveData("email",email)
                    //firstNameData.value = firstName
                    //lastNameData.value = lastName
                    //emailData.value = email
                    // Navigating to Home screen after registration
                    navController.navigate(Home.route)

                }


            },
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(
                Color(0xFFF4CE14)
            )
        ) {
            Text(
                text = "Register",
                color = Color(0xFF333333)
            )
        }

    }

}
