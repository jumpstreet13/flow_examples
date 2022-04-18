package com.otus.coroutines.exceptions.coroutines.cancelling

import kotlinx.coroutines.*

fun main() = runBlocking {
    val job = launch {
        repeat(1000) { i ->
            println("job: I'm sleeping $i ...")
        }
    }

    println("com.otus.coroutines.exceptions.com.otus.coroutines.exceptions.coroutines.dispatchers.main: I'm tired of waiting!")
    delay(1500)

    job.cancel()
    println("com.otus.coroutines.exceptions.com.otus.coroutines.exceptions.coroutines.dispatchers.main: Now I can quit.")
}