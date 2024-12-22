package com.example.weather.data

import android.content.Context
import android.util.Log
import androidx.credentials.ClearCredentialStateRequest
import androidx.credentials.Credential
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import androidx.credentials.GetCredentialResponse
import androidx.credentials.exceptions.GetCredentialException
import androidx.lifecycle.ViewModel
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenParsingException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.tasks.await


class FirebaseCredentialManager(
    private val context: Context,
): ViewModel()  {
    private val credentialManager = CredentialManager.create(context)
    private val firebaseAuth = FirebaseAuth.getInstance()

    fun isSignin(): Boolean {
        if (firebaseAuth.currentUser !== null) {
            return true
        }

        return false
    }

    suspend fun signIn(): Boolean {
        if (isSignin()) {
            return true
        }

        try {
            val result = getCredentialRequest()
            return handleSignIn(result)

        } catch (e: Exception) {
            e.printStackTrace()
            if (e is CancellationException) throw e

            return false
        }
    }

    private suspend fun handleSignIn(result: GetCredentialResponse) : Boolean {
        val credential = result.credential

        if (
            credential is CustomCredential &&
            credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL
        ) {
            try {
                val tokenCredential = GoogleIdTokenCredential.createFrom(credential.data)

                val authCredential = GoogleAuthProvider.getCredential(
                    tokenCredential.idToken, null
                )
                val authResult =firebaseAuth.signInWithCredential(authCredential).await()

                return authResult.user != null

            } catch (e: GoogleIdTokenParsingException) {
                return false
            }
        } else {
            return false
        }
    }

    private suspend fun getCredentialRequest(): GetCredentialResponse {
        val request = GetCredentialRequest.Builder()
            .addCredentialOption(
                GetGoogleIdOption.Builder()
                    .setFilterByAuthorizedAccounts(false)
                    .setServerClientId(
                        "398752483578-4u0teisbv7lrembkpq998u3c6tcj4gp5.apps.googleusercontent.com"
                    )
                    .build()
            )
            .build()

        return credentialManager.getCredential(
            request = request, context = context
        )
    }

    suspend fun signOut() {
        credentialManager.clearCredentialState(
            ClearCredentialStateRequest()
        )
        firebaseAuth.signOut()
    }
}
