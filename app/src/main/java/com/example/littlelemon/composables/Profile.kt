package com.example.littlelemon.composables

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.littlelemon.R
import com.example.littlelemon.*
import com.example.littlelemon.ui.theme.LittleLemonTheme

@Composable
fun Profile(navController: NavHostController){
    val context = LocalContext.current
    val sharedPreferences by lazy {
        context.getSharedPreferences("LittleLemon", Context.MODE_PRIVATE)
    }
    val firstname = sharedPreferences.getString("firstname","na") ?: "na"
    val lastname = sharedPreferences.getString("lastname","na") ?: "na"
    val email = sharedPreferences.getString("email","na") ?: "na"

    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxHeight()
            .padding(vertical = 8.dp)
    ){
        Box(
            modifier = Modifier.fillMaxWidth()
        ){
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "logo",
                alignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth(0.35f)
                    .align(Alignment.Center),
            )
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier.padding(horizontal = 10.dp),
            
        ) {
            Text(
                text = "Personal information",
                style = MaterialTheme.typography.bodyMedium,
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(vertical = 40.dp)
            )
            Text(
                text = "Firstname",
                style = MaterialTheme.typography.bodyMedium
            )
            OutlinedTextField(
                value = firstname,
                onValueChange = {},
                enabled = false,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(10.dp)
            )
            Text(
                text = "Lastname",
                style = MaterialTheme.typography.bodyMedium
            )
            OutlinedTextField(
                value = lastname,
                onValueChange = {},
                enabled = false,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(10.dp)
            )
            Text(
                text = "Email",
                style = MaterialTheme.typography.bodyMedium
            )
            OutlinedTextField(
                value = email,
                onValueChange = {},
                enabled = false,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(10.dp)
            )

        }
        Button(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 20.dp),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary),
            shape = RoundedCornerShape(10),
            onClick = {
                if (firstname.isBlank() || lastname.isBlank() || email.isBlank())
                    Toast.makeText(context,"Registration unsuccessful. Please enter all data.",
                        Toast.LENGTH_SHORT).show()
                else{
                    sharedPreferences.edit().clear().apply()
                    navController.navigate(Onboarding.route)
                }
            }
        ) {
            Text(
                text = "Log out",
                style = MaterialTheme.typography.bodyMedium,
                fontSize = 25.sp,
                color = Color.Black
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewProfile(){
    LittleLemonTheme {
        Profile(navController = rememberNavController())
    }
}

