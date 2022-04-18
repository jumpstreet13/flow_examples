package com.otus.coroutines.exceptions.coroutines

import kotlinx.coroutines.*

fun main() = runBlocking {
    launch {
        val j: Job = coroutineContext[Job]!!
        println(j)

        launch {
            println("captured")
            delay(1000)
            println("child job is ${coroutineContext[Job]}")
        }

        j.children.apply {
            if (this.count() == 0) {
                println("no children")
            } else {
                forEach {
                    println("child of current jobs are $it")
                }
            }
        }
    }
    delay(3000)
}

