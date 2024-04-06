package com.nitishsharma.domain.models.TurnsPVRModels

import com.google.gson.annotations.SerializedName

data class NewTokenModel(
    @SerializedName("request_token")
    val requestToken: String?
)