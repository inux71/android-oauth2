package com.grabieckacper.oauth2.di

import android.content.Context
import androidx.credentials.CredentialManager
import com.grabieckacper.oauth2.service.GoogleSignInService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideCredentialManager(@ApplicationContext context: Context): CredentialManager {
        return CredentialManager.create(context)
    }

    @Provides
    @Singleton
    fun provideGoogleSignInService(credentialManager: CredentialManager): GoogleSignInService {
        return GoogleSignInService(credentialManager)
    }
}
