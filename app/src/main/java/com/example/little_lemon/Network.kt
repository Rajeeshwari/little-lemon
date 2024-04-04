package com.example.little_lemon

import android.net.Uri
import androidx.annotation.DrawableRes
import androidx.compose.ui.text.UrlAnnotation
import androidx.core.net.toUri
import io.ktor.http.URLBuilder
import io.ktor.http.Url
import io.ktor.http.toURI
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.net.URL


@Serializable
data class MenuNetwork (
    val menu: List<MenuItemNetwork>
)


@Serializable
@SerialName("category")
data class MenuItemNetwork (

    val id: Int,
    val title: String,
    val description: String,
    val price: Double,
     val image: String,
    val category: String

) {
    fun toMenuItemRoom() = MenuItemRoom(
        id = id.toInt(),
        title = title.toString(),
        description = description.toString(),
        price = price.toDouble(),
        image = image.toString(),
        category = category.toString()
    )
}