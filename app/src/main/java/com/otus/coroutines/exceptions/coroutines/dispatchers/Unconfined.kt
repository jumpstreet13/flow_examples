package com.otus.coroutines.exceptions.coroutines.dispatchers

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext


fun main() =
    runBlocking {
        println("#1 " + Thread.currentThread().name) //MAIN
        withContext(Dispatchers.Main) {
            println("#2 " + Thread.currentThread().name)//MAIN
            changeExecutor()
            println("coroutines.foo thread is ${Thread.currentThread().name}")
        }
        println(Thread.currentThread().name)
        println("Done")
    }

suspend fun changeExecutor() {
    withContext(Dispatchers.IO) {
        println(Thread.currentThread().apply { name = "OtusThread" }.name)
    }
}
