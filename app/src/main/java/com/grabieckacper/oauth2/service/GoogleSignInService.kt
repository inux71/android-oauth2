package com.grabieckacper.oauth2.service

import android.content.Context
import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
import androidx.credentials.GetCredentialResponse
import com.google.android.libraries.identity.googleid.GetSignInWithGoogleOption
import com.grabieckacper.oauth2.common.Constants
import javax.inject.Inject

class GoogleSignInService @Inject constructor(
    private val credentialManager: CredentialManager
) {
    private val signInWithGoogleOption: GetSignInWithGoogleOption = GetSignInWithGoogleOption
        .Builder(serverClientId = Constants.WEB_CLIENT_ID)
        .build()

    private val request: GetCredentialRequest = GetCredentialRequest.Builder()
            .addCredentialOption(signInWithGoogleOption)
            .build()

    suspend fun signInWithGoogle(context: Context): GetCredentialResponse {
        return credentialManager.getCredential(
            request = request,
            context = context
        )
    }
}
