//package com.example.chapter_6_allminitask.dataStore
//
//import androidx.appcompat.app.AppCompatActivity
//import com.example.chapter_6_allminitask.R
//
//
//import android.content.Context
//import android.content.Intent
//import android.os.Bundle
//import android.widget.Toast
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.compose.foundation.layout.*
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
//import androidx.lifecycle.asLiveData
//
//class DataStoreMain : ComponentActivity() {
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//            LoginSessionTheme {
//                Surface(color = MaterialTheme.colors.background) {
//                    MainLayoutMainActivity(this)
//                }
//            }
//        }
//    }
//}
//
//@Composable
//fun MainLayoutMainActivity(context: Context) {
//    (context as DataStoreMain)
//    val lifecycleOwner = LocalLifecycleOwner.current
//    val appDatastore = AppDatastore.getInstance(context)!!
//    val usernameState = remember { mutableStateOf("") }
//    val passwordState = remember { mutableStateOf("") }
//    val visiblePassword = remember { mutableStateOf(false) }
//
//    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
//
//        Text(text = "Login ", modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 48.dp))
//
//        // EditText Username
//        OutlinedTextField(value = usernameState.value, onValueChange = { usernameState.value = it }, label = { Text(text = "Username") }, modifier = Modifier.padding(4.dp),
//            // Icon Username
//            leadingIcon = { IconButton(onClick = {}) {
//                Icon(painter = painterResource(id = R.drawable.ic_account_circle), contentDescription = null)
//            } }
//        )
//
//        // EditText Password
//        OutlinedTextField(value = passwordState.value, onValueChange = { passwordState.value = it }, label = { Text(text = "Password") }, modifier = Modifier.padding(4.dp),
//            visualTransformation = if (visiblePassword.value) VisualTransformation.None else PasswordVisualTransformation(),
//            trailingIcon = { IconButton(onClick = { visiblePassword.value = !visiblePassword.value }) {
//                // Jika visibility password = true => ganti icon ic_visibility_off
//                if (visiblePassword.value) Icon(painter = painterResource(id = R.drawable.ic_visibility_off), contentDescription = "visible")
//                // Jika visibility password = false => ganti icon ic_visibility
//                else Icon(painter = painterResource(id = R.drawable.ic_visibility), contentDescription = "invisible")
//            } }
//        )
//
//        // Button Login
//        Button(onClick = {
//            if (usernameState.value.isBlank() or passwordState.value.isBlank()) AppUtils.showToast(context, "Username or Password cant blank")
//            else {
//                appDatastore.getUserName.asLiveData().observe(lifecycleOwner, { name ->
//                    appDatastore.getUserPassword.asLiveData().observe(lifecycleOwner, { password ->
//                        if ((usernameState.value == name) and (passwordState.value == password)) {
//                            // Login Berhasil
//                            Toast.makeText(context, "Berhasil", Toast.LENGTH_SHORT).show()
//                        } else Toast.makeText(context, "Username or Password Wrong", Toast.LENGTH_SHORT).show()  // Login Gagal
//                    })
//                })
//            }
//        }, modifier = Modifier.width(200.dp).padding(0.dp, 32.dp, 0.dp, 0.dp)) { Text(text = "Login") }
//
//        // Button To RegisterActivity
//        Button(onClick = {
//            val i = Intent(context, RegisterDataStore::class.java)
//            context.startActivity(i)
//            context.finish()
//        }, modifier = Modifier.width(200.dp).padding(0.dp, 16.dp, 0.dp, 0.dp)) { Text(text = "Register") }
//    }
//}
//
//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun MainPreviewMainActivity() {
//    LoginSessionTheme {
//        MainLayoutMainActivity(LocalContext.current)
//    }
//}