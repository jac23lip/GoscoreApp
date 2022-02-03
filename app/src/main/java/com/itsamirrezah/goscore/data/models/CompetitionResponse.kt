package com.itsamirrezah.goscore.data.models

import com.google.gson.annotations.SerializedName

data class CompetitionResponse(
    @SerializedName("count")
    val count: Int,
    @SerializedName("competitions")
    val competitions: List<Competition>

)
