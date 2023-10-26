package com.example.demo.exception

enum class ErrorCode(val status: Int, val code: String, val message: String) {
    // Member
    ID_DUPLICATION(400, "M001", "ID is Duplication"),
    LOGIN_INPUT_INVALID(400, "M002", "Login input is invalid"),
}