package com.example.little_lemon

interface Destinations {
    val route: String
}
object Onboarding : Destinations {
    override val route = "onboarding"
}

object Home : Destinations {
    override val route = "Home"
}



object Profile : Destinations {
   // const val argOrderNo = "OrderNo"
    override val route = "Profile"
}