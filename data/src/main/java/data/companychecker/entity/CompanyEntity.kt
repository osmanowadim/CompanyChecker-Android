package data.companychecker.entity

import com.google.gson.annotations.SerializedName

data class CompanyEntity(
    @SerializedName("name")
    val name: String?,
    @SerializedName("img")
    val image: String?,
    @SerializedName("url")
    val url: String?
)
