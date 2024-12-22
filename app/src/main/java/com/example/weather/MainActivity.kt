package com.example.weather

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.ContentView
import androidx.compose.foundation.Image
import androidx.lifecycle.lifecycleScope
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.weather.data.FirebaseCredentialManager
import com.example.weather.ui.screen.SignInPage
import com.example.weather.ui.theme.WeatherTheme
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch
import okhttp3.internal.platform.Platform

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val googleAuthClient = FirebaseCredentialManager(this)

        setContent {
            WeatherTheme {

                var isSignIn by rememberSaveable {
                    mutableStateOf(googleAuthClient.isSignin())
                }

                if (isSignIn) {
                    WeatherApp()
                } else {
                    Card {
                        Column(
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .fillMaxSize()
                        ) {
                            OutlinedButton(onClick = {
                                lifecycleScope.launch {
                                    googleAuthClient.signIn()
                                }
                            }) {
                                Row {
                                    Image(
                                        painter = painterResource(R.drawable._123025_logo_google_g_icon),
                                        contentDescription = "Google",
                                        contentScale = ContentScale.Fit,
                                        modifier = Modifier
                                            .size(20.dp)
                                    )

                                    Spacer(modifier = Modifier.width(5.dp))

                                    Text(
                                        text = "Sign in with Google"
                                    )
                                }
                            }
                        }
                    }

                }
            }
        }
    }
}
