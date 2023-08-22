package com.example.littlelemon

import kotlinx.serialization.SerialInfo
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
class MenuNetworkData(
    @SerialName("menu")
    val menu: List<MenuItemNetwork>
)

@Serializable
class MenuItemNetwork(
    @SerialName("id")
    val id: Int,
    @SerialName("title")
    val title: String,
    @SerialName("description")
    val description: String,
    @SerialName("price")
    val price: String,
    @SerialName("category")
    val category: String,
    @SerialName("image")
    val image: String
){
    fun toMenuItemRoom() = MenuItemRoom(
        id,
        title,
        description,
        price,
        category,
        image
    )
}