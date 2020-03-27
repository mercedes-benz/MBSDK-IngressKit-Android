package com.daimler.mbingresskit.common

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    val ciamId: String,
    val userId: String,
    val firstName: String,
    val lastName: String,
    val birthday: String,
    val email: String,
    val mobilePhone: String,
    val landlinePhone: String,
    val countryCode: String,
    val languageCode: String,
    val createdAt: String,
    val updatedAt: String,
    val pinStatus: UserPinStatus = UserPinStatus.UNKNOWN,
    val address: Address? = null,
    val communicationPreference: CommunicationPreference,
    val unitPreferences: UnitPreferences,
    val accountIdentifier: AccountIdentifier = AccountIdentifier.UNKNOWN,
    val title: String,
    val salutationCode: String,
    val taxNumber: String,
    val bodyHeight: UserBodyHeight?,
    val accountVerified: Boolean
) : Parcelable {

    companion object {
        fun pinStatusFromInt(ordinal: Int?) =
            UserPinStatus.values().getOrElse(ordinal ?: -1) { UserPinStatus.UNKNOWN }

        fun accountIdentifierFromInt(ordinal: Int?) =
            AccountIdentifier.values().getOrElse(ordinal ?: -1) { AccountIdentifier.UNKNOWN }
    }
}