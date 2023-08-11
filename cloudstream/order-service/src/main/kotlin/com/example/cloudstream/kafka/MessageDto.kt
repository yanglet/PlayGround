package com.example.cloudstream.kafka

data class Message<T>(
    val payLoad: T
)