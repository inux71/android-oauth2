package com.grabieckacper.oauth2.viewmodel

import android.content.Context
import android.util.Log
import androidx.credentials.Credential
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialResponse
import androidx.credentials.exceptions.GetCredentialException
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenParsingException
import com.grabieckacper.oauth2.service.GoogleSignInService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val googleSignInService: GoogleSignInService,
): ViewModel() {
    private fun handleSignInWithGoogle(result: GetCredentialResponse) {
        when (val credential: Credential = result.credential) {
            is CustomCredential -> {
                if (credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {
                    try {
                        val googleIdTokenCredential: GoogleIdTokenCredential =
                            GoogleIdTokenCredential.createFrom(credential.data)
                    } catch (e: GoogleIdTokenParsingException) {
                        Log.e("[Google Sign In]", e.localizedMessage ?: "")
                    }
                } else {
                    Log.e("[Google Sign In]", "Unexpected type of credential")
                }
            } else -> {
                Log.e("[Google Sign In]", "Unexpected type of credential")
            }
        }
    }

    fun signInWithGoogle(context: Context) {
        viewModelScope.launch {
            try {
                val result = googleSignInService.signInWithGoogle(context)

                handleSignInWithGoogle(result)
            } catch (e: GetCredentialException) {
                Log.e("[Google Sign In]", e.localizedMessage ?: "")
            }
        }
    }
}
