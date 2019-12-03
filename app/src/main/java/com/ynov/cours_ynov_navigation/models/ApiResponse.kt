package com.ynov.cours_ynov.models

data class ApiResponse<T>(val success: Boolean, val data: T, val errors: Any)