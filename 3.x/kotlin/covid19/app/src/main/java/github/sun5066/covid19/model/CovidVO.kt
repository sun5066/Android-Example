package github.sun5066.covid19.model

import com.google.gson.annotations.SerializedName

data class CovidVO(
    @SerializedName("countryName")
    var countryName: String?,

    @SerializedName("newCase")
    var newCase: String?,

    @SerializedName("totalCase")
    var totalCase: String?,

    @SerializedName("recovered")
    var recovered: String?,

    @SerializedName("death")
    var death: String?,

    @SerializedName("percent")
    var percent: String?,

    @SerializedName("newFCase")
    var newFCase: String?,

    @SerializedName("newCCase")
    var newCCase: String?
)