package github.sun5066.covid_19.model

data class CovidVO(
    var countryName: String = "",
    var newCase: String = "",
    var totalCase: String = "",
    var death: String = "",
    var percent: String = "",
    var newFCase: String = "",
    var newCCase: String = ""
)