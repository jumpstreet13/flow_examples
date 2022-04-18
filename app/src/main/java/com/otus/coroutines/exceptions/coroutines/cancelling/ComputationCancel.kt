package com.otus.coroutines.exceptions.coroutines.cancelling

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import kotlinx.coroutines.yield

fun main() = runBlocking {
    val startTime = System.currentTimeMillis()
    val job = launch(Dispatchers.Default) {
        var nextPrintTime = startTime
        var i = 0
        while (i < 100) {
//            ensureActive()
//            if (!isActive){
//                return@launch
//            }
            if (System.currentTimeMillis() >= nextPrintTime) {
                println("job: I'm sleeping ${i++} ...")
                nextPrintTime += 500L
                if (i >= 3) {
                    yield()
                }
            }
        }
    }
    delay(1000L)
    println("com.otus.coroutines.exceptions.com.otus.coroutines.exceptions.coroutines.dispatchers.main: I'm tired of waiting!")
    job.cancel()
    println("com.otus.coroutines.exceptions.com.otus.coroutines.exceptions.coroutines.dispatchers.main: Now I can quit.")
}

suspend fun test() {
    withContext(Dispatchers.IO) {
        Thread.sleep(200)
    }
}