package com.example.moviesdemo.response

data class LoginResponse(
    var status: String,
    var token: String,
    var email: String,
    var service_authhash: String,
    var reason: String?
)