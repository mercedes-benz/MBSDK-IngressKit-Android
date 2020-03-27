package com.daimler.mbingresskit.implementation.network

import com.daimler.mbingresskit.implementation.network.model.KeycloakTokenResponse
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.POST

interface KeycloakApi {
    companion object {
        const val GRANT_TYPE_PASSWORD = "password"
        const val GRANT_TYPE_REFRESH_TOKEN = "refresh_token"

        // due to https://www.keycloak.org/docs/6.0/server_admin/#_offline-access
        const val SCOPE_OPENID_OFFLINE_ACCESS = "offline_access"

        const val HEADER_STAGE = "Stage"
        const val HEADER_DEVICE_ID = "device-uuid"

        const val FIELD_CLIENT_ID = "client_id"
        const val FIELD_GRANT_TYPE = "grant_type"
        const val FIELD_USERNAME = "username"
        const val FIELD_PASSWORD = "password"
        const val FIELD_REFRESH_TOKEN = "refresh_token"
        const val FIELD_SCOPE = "scope"
    }

    @FormUrlEncoded
    @POST("/auth/realms/Daimler/protocol/openid-connect/token")
    fun requestToken(
        @Header(HEADER_STAGE) stage: String,
        @Header(HEADER_DEVICE_ID) deviceId: String,
        @Field(FIELD_CLIENT_ID) clientId: String,
        @Field(FIELD_GRANT_TYPE) grantType: String = GRANT_TYPE_PASSWORD,
        @Field(FIELD_USERNAME) userName: String,
        @Field(FIELD_PASSWORD) password: String,
        @Field(FIELD_SCOPE) scope: String = SCOPE_OPENID_OFFLINE_ACCESS
    ): Call<KeycloakTokenResponse>

    @FormUrlEncoded
    @POST("/auth/realms/Daimler/protocol/openid-connect/token")
    fun refreshToken(
        @Header(HEADER_STAGE) stage: String,
        @Field(FIELD_CLIENT_ID) clientId: String,
        @Field(FIELD_GRANT_TYPE) grantType: String = GRANT_TYPE_REFRESH_TOKEN,
        @Field(FIELD_REFRESH_TOKEN) refreshToken: String
    ): Call<KeycloakTokenResponse>

    @FormUrlEncoded
    @POST("/auth/realms/Daimler/protocol/openid-connect/logout")
    fun logout(
        @Header(HEADER_STAGE) stage: String,
        @Field(FIELD_CLIENT_ID) clientId: String,
        @Field(FIELD_REFRESH_TOKEN) refreshToken: String
    ): Call<ResponseBody>
}