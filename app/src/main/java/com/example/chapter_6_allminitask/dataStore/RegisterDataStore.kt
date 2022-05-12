//package com.example.chapter_6_allminitask.dataStore
//
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//
//import android.content.Context
//import android.content.Intent
//import androidx.activity.compose.setContent
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.padding
//import androidx.compose.material.*
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.platform.LocalLifecycleOwner
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.text.input.PasswordVisualTransformation
//import androidx.compose.ui.text.input.VisualTransformation
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import androidx.lifecycle.lifecycleScope
//import kotlinx.coroutines.launch
//
//class RegisterDataStore : AppCompatActivity() {
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//            LoginSessionTheme {
//                Surface(color = MaterialTheme.colors.background) {
//                    MainLayoutRegisterActivity(this)
//                }
//            }
//        }
//    }
//}
//
//@Composable
//fun MainLayoutRegisterActivity(context: Context) {
//    (context as RegisterDataStore)
//    val appDatastore = AppDatastore.getInstance(context)!!
//    val lifecycleOwner = LocalLifecycleOwner.current
//    val username = remember { mutableStateOf("") }
//    val password = remember { mutableStateOf("") }
//    val visiblePassword = remember { mutableStateOf(false) }
//
//    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
//
//        Text(text = "Register New Account", modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 48.dp))
//
//        // EditText Username
//        OutlinedTextField(value = username.value, onValueChange = { username.value = it }, label = { Text(text = "Username") }, modifier = Modifier.padding(4.dp),
//            // Icon Username
//            leadingIcon = { IconButton(onClick = {}) {
//                Icon(painter = painterResource(id = R.drawable.ic_account_circle), contentDescription = null)
//            } }
//        )
//
//        // EditText Password
//        OutlinedTextField(value = password.value, onValueChange = { password.value = it }, label = { Text(text = "Password") }, modifier = Modifier.padding(4.dp),
//            visualTransformation = if (visiblePassword.value) VisualTransformation.None else PasswordVisualTransformation(),
//            trailingIcon = { IconButton(onClick = { visiblePassword.value = !visiblePassword.value }) {
//                // Jika visibility password = true => ganti icon ic_visibility_off
//                if (visiblePassword.value) Icon(painter = painterResource(id = R.drawable.ic_visibility_off), contentDescription = "visible")
//                // Jika visibility password = false => ganti icon ic_visibility
//                else Icon(painter = painterResource(id = R.drawable.ic_visibility), contentDescription = "invisible")
//            } }
//        )
//
//        // Button Create New Account
//        Button(onClick = {
//            if (username.value.isBlank() or password.value.isBlank()) AppUtils.showToast(context, "Username or Password cant blank")
//            else {
//                // Buat Akun Baru (Update Data di Datastore)
//                lifecycleOwner.lifecycleScope.launch {
//                    appDatastore.setUserName(username.value)
//                    appDatastore.setUserPassword(password.value)
//                }.invokeOnCompletion {
//                    AppUtils.showToast(context, "Account Edited")
//                    context.startActivity(Intent(context, MainActivity::class.java))
//                    context.finish()
//                }
//            }
//        }, modifier = Modifier.padding(0.dp, 32.dp, 0.dp, 0.dp)) { Text(text = "Create New Account") }
//    }
//}
//
//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun MainPreviewRegisterActivity() {
//    LoginSessionTheme {
//        MainLayoutRegisterActivity(LocalContext.current)
//    }
//}