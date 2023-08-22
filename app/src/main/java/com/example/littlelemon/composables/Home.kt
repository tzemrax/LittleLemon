package com.example.littlelemon.composables

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.littlelemon.MenuItemRoom
import com.example.littlelemon.Profile
import com.example.littlelemon.R
import com.example.littlelemon.ui.theme.LittleLemonTheme

@Composable
fun Home(navController: NavHostController, databaseMenuItems: List<MenuItemRoom>){
    var searchPhrase  by remember { mutableStateOf("") }
    var data = databaseMenuItems
    Column(
        modifier = Modifier.fillMaxHeight()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ){
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "logo",
                alignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth(0.35f)
                    .align(Alignment.Center),
            )
            IconButton(
                onClick = { navController.navigate(Profile.route) },
                modifier = Modifier.align(Alignment.CenterEnd)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.profile),
                    contentDescription = "profile",
                    alignment = Alignment.Center
                )
            }
        }

        Column(
            modifier = Modifier
                .background(Color(0xFF495E57))
                .padding(start = 12.dp, end = 12.dp, top = 16.dp, bottom = 16.dp)
        ) {
            Text(
                text = stringResource(id = R.string.restaurant_name),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.secondary,
            )
            Row(
                modifier = Modifier.padding(bottom = 18.dp)
            ) {
                Column (modifier = Modifier.fillMaxWidth(0.6f)){
                    Text(
                        text = stringResource(id = R.string.city),
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.colorScheme.tertiary
                    )
                    Text(
                        text = stringResource(id = R.string.description),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.tertiary
                    )
                }
                Image(
                    painter = painterResource(id = R.drawable.hero_image),
                    contentDescription = "Upper Panel Image",
                    modifier = Modifier
                        .padding(start = 12.dp)
                        .aspectRatio(1f)
                        .clip(RoundedCornerShape(20.dp)),
                    contentScale = ContentScale.Crop
                )
            }
            TextField(
                value = searchPhrase ,
                onValueChange = { searchPhrase  = it },
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.tertiary, RoundedCornerShape(10.dp))
                    .clip(RoundedCornerShape(10.dp))
                    .fillMaxWidth(),
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = MaterialTheme.colorScheme.tertiary
                ),
                leadingIcon = {
                    Image(
                        imageVector = Icons.Default.Search,
                        contentDescription = "search icon")
                },
                placeholder = {
                    Text(
                        text = "Enter Search Phrase",
                        style = MaterialTheme.typography.bodyMedium
                    )
                },
                maxLines = 1
            )
            if (searchPhrase.isNotEmpty()){
                data = databaseMenuItems.filter {
                    it.title.lowercase().contains(searchPhrase)
                }
            }
        }
        MenuItems(data)
    }
}

val Categories = listOf(
    "Starters",
    "Mains",
    "Desserts",
    "Drinks"
)
@Composable
fun MenuItems(databaseMenuItems: List<MenuItemRoom>){
    var filterCategory  by remember { mutableStateOf("") }
    var data = databaseMenuItems
    Column(modifier = Modifier.padding(12.dp)){
        Text(
            text = "ORDER FOR DELIVERY!",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )
        LazyRow (
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ){
            items(Categories) { category ->
                Button(
                    onClick = {
                        filterCategory = if (filterCategory == category) "" else category
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.tertiary),
                    shape = RoundedCornerShape(40),
                    modifier = Modifier
                        .padding(5.dp)
                        .height(30.dp),
                    contentPadding = PaddingValues(horizontal = 5.dp)

                ) {
                    Text(
                        text = category,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }
        if (filterCategory.isNotEmpty()) data = databaseMenuItems.filter { it.category.contains(filterCategory.lowercase())}
        LazyColumn(
            modifier = Modifier
                .fillMaxHeight()
                .padding(top = 20.dp)
        ){
            items(
                items = data,
                itemContent = { menuItem ->
                    MenuItem(
                        title = menuItem.title,
                        description = menuItem.description,
                        price = menuItem.price,
                        image = menuItem.image
                    )
                }
            )
        }
    }
}

@Composable
fun MenuItem(title: String, description: String, price: String, image: String){
    Divider(
        modifier = Modifier.padding(8.dp),
        color = Color.LightGray,
        thickness = 1.dp
    )
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ){
        Column(
            modifier = Modifier.fillMaxWidth(0.75f),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.labelLarge,
            )
            Text(
                text = description,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.primary,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = "$$price",
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.primary
            )
        }

        AsyncImage(
            model = image,
            contentDescription = title,
            modifier = Modifier.padding(start = 12.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHome(){
    LittleLemonTheme {
        Home(rememberNavController(), emptyList())
    }
}