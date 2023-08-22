package com.example.littlelemon.composables

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.edit
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.littlelemon.Home
import com.example.littlelemon.R
import com.example.littlelemon.ui.theme.LittleLemonTheme

@Composable
fun Onboarding(navController: NavHostController){
    var firstname by remember { mutableStateOf("") }
    var lastname by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    val context = LocalContext.current


    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxHeight().verticalScroll(rememberScrollState())
        ){
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
        }
        Column(
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Let's get to know you",
                color = MaterialTheme.colorScheme.tertiary,
                style = MaterialTheme.typography.bodyMedium,
                fontSize = 30.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.primary)
                    .padding(vertical = 40.dp)
                    .align(Alignment.CenterHorizontally),
                textAlign = TextAlign.Center,
                )
            Column(
                modifier = Modifier.padding(horizontal = 12.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ){
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
                    onValueChange = { firstname = it },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(10.dp),
                )
                Text(
                    text = "Lastname",
                    style = MaterialTheme.typography.bodyMedium
                )
                OutlinedTextField(
                    value = lastname,
                    onValueChange = { lastname = it },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(10.dp)
                )
                Text(
                    text = "Email",
                    style = MaterialTheme.typography.bodyMedium
                )
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(10.dp)
                )
            }

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
                    Toast.makeText(context,"Registration unsuccessful. Please enter all data.",Toast.LENGTH_SHORT).show()
                else{
                    Toast.makeText(context,"Registration successful!",Toast.LENGTH_SHORT).show()
                    saveData(context,firstname,lastname,email)
                    navController.navigate(Home.route)
                }
            }
        ) {
            Text(
                text = "Register",
                style = MaterialTheme.typography.bodyMedium,
                fontSize = 25.sp,
                color = Color.Black
            )
        }
    }
}

private fun saveData(context: Context, firstname:String, lastname:String, email:String){
    val sharedPreferences by lazy {
        context.getSharedPreferences("LittleLemon", Context.MODE_PRIVATE)
    }
    sharedPreferences.edit {
        putString("firstname", firstname)
        putString("lastname", lastname)
        putString("email", email)
        putBoolean("registered", true)
        apply()
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewOnboarding(){
    LittleLemonTheme {
        Onboarding(rememberNavController())
    }
}

