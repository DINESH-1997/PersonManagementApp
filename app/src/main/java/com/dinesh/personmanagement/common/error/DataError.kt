package com.dinesh.personmanagement.common.error

sealed interface DataError: Error {
    enum class Network: DataError {
        NO_INTERNET
    }
    data class ApiError(val message: String): DataError
}