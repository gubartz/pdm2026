package br.edu.ifsp.hto.htoipdm.applazycolumn.data.model

import com.google.gson.annotations.SerializedName

data class ApiResponse<T>(
    @SerializedName("data_response")
    val dataResponse: T?,
    val message: String,
    @SerializedName("response_code")
    val responseCode: Long
)
