package io.study.moduletemp.web.base.response

import com.fasterxml.jackson.annotation.JsonPropertyOrder

@JsonPropertyOrder("message", "data")
class SuccessResponse<T>(
    val data: T,
) : BaseResponse() {

    companion object {
        fun <T> of(data: T): SuccessResponse<T> {
            return SuccessResponse(data)
        }
    }

}