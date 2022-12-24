package com.movetoplay.domain.repository

import com.movetoplay.domain.model.TokenResponse
import com.movetoplay.domain.utils.ResultStatus


interface AuthRepository {
    suspend fun signIn(email: String, password: String): ResultStatus<TokenResponse>
    suspend fun signUp(email: String, password: String, age: Int): ResultStatus<TokenResponse>
    suspend fun authorizeProfile(childId: String, deviceId: String): ResultStatus<TokenResponse>
    suspend fun confirm(code: Int): ResultStatus<Boolean>
    suspend fun signViaGoogle(token: String): ResultStatus<TokenResponse>
    suspend fun resendConfirmCode(): ResultStatus<Boolean>
}