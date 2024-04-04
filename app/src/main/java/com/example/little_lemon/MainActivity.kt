package com.example.little_lemon

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.little_lemon.ui.theme.LittleLemonColor
import com.example.little_lemon.ui.theme.LittleLemonTheme
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.bumptech.glide.Glide
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

lateinit var menuItems:List<MenuItemRoom>

class MainActivity : ComponentActivity() {
    private val httpClient = HttpClient(Android) {
        install(ContentNegotiation) {
            json(contentType = ContentType("text", "plain"))
        }
    }
    private val database by lazy {
        Room.databaseBuilder(applicationContext,
            AppDatabase::class.java,
            "little_lemon_database2").build()
    }

    private suspend fun fetchMenu(): List<MenuItemNetwork> {
        val response = httpClient
            .get("https://raw.githubusercontent.com/Meta-Mobile-Developer-PC/Working-With-Data-API/main/menu.json")
            .body<MenuNetwork>()

        return response?.menu ?: listOf()
    }

    private fun saveMenuToDatabase(menuItemsNetwork: List<MenuItemNetwork>) {
        val menuItemsRoom = menuItemsNetwork.map { it.toMenuItemRoom() }
        database.menuItemDao().insertAll(*menuItemsRoom.toTypedArray())
    }



    @SuppressLint("CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val glide= Glide.with(this)
        setContent {
            LittleLemonTheme {
                // A surface container using the 'background' color from the theme

                val databaseMenuItems by database.menuItemDao().getAll().observeAsState(emptyList())

                var orderMenuItems by remember { mutableStateOf(false) }


                menuItems = if(orderMenuItems){
                    databaseMenuItems.sortedBy { it.title }
                }else{
                    databaseMenuItems
                }
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    val navController = rememberNavController()
                    Navigation(navController)
                }


            }

        }
        lifecycleScope.launch(Dispatchers.IO) {
            if (database.menuItemDao().isEmpty()) {
                saveMenuToDatabase(fetchMenu())
            }
        }
    }
}



@OptIn(ExperimentalGlideComposeApi::class)
@Composable
private fun MenuItemsList(items: List<MenuItemRoom>) {
    LazyColumn( modifier = Modifier
            .fillMaxHeight()
            .padding(top = 20.dp))
    {
        items(items = items,
            itemContent = { menuItem ->
                Card() {
                    Row(
                        modifier = Modifier.fillMaxWidth()
                            .padding(8.dp)
                    ) {
                        Column {
                            Text(text = menuItem.title,
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold)
                            Text(
                                text = menuItem.description,
                                modifier = Modifier.fillMaxWidth(
                                    0.75F
                                ).padding(top = 5.dp, bottom = 5.dp),
                                    style = MaterialTheme.typography.bodyMedium,
                                color = LittleLemonColor.charcoal)

                            Text(text = "$ "+menuItem.price.toString(),
                                modifier= Modifier.padding(top=5.dp),
                                style = MaterialTheme.typography.bodyLarge,
                                color = LittleLemonColor.charcoal)

                        }
                        GlideImage(model = menuItem.image,
                            contentDescription = "",
                            modifier = Modifier.clip(
                                RoundedCornerShape(10.dp)))

                    }
                }
                Divider(
                    modifier = Modifier.padding(start = 8.dp, end = 8.dp),
                    thickness = 1.dp , color= LittleLemonColor.cloud
                )
                })

    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpperPanel() {
    var search by remember{
        mutableStateOf("")
    }


        Column(
            modifier = Modifier
                .background(color = LittleLemonColor.green)
        ) {
            Text(
                text = stringResource(id = R.string.title),
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold, color = LittleLemonColor.yellow
            )


            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier =Modifier
                    .size(500.dp,180.dp)
                    .padding(start =10.dp,end =10.dp)
            ) {
                Column() {
                    Text(
                        text = "Chicago",
                        fontSize = 24.sp, color = LittleLemonColor.cloud,
                        modifier = Modifier.padding(bottom =10.dp)
                    )

                    Text(
                        text = stringResource(id = R.string.description),
                        style = MaterialTheme.typography.bodyLarge,
                        color = LittleLemonColor.cloud,
                        modifier = Modifier
                            .padding(bottom = 28.dp, end = 20.dp)

                           .fillMaxWidth(0.6f)
                    )
                }
                Image(
                    painter = painterResource(id = R.drawable.heroimage),
                    contentDescription = "Upper Panel Image",
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier.clip(RoundedCornerShape(10.dp))
                    )
            }
            OutlinedTextField(
                value = search,
                leadingIcon = {Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = null
                    )
                },
                placeholder = { Text(text = "Enter Search Phrase") },
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor =   Color.White,
                    cursorColor = Color.Black,
                    focusedIndicatorColor = Color.Black
                ),
                singleLine = true,
                onValueChange = { search = it },

                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .padding(10.dp)
            )

        }
    Text(
        text = "ORDER FOR DELIVERY!",
        fontWeight= FontWeight.Bold,
        style = MaterialTheme.typography.titleLarge,
        color = LittleLemonColor.charcoal,
        modifier = Modifier
            .padding(10.dp)

    )
    menuCategory()
    if (search != "" && search!= "Enter Search Phrase" ){
        MenuItemsList(menuItems.filter { (it.title.lowercase()).contains(search.lowercase()) })
    }
    else if(search.isBlank()){
        MenuItemsList(menuItems)
    }
    else{
        MenuItemsList(menuItems)
    }
}

