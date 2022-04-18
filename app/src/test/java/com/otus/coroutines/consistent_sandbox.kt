package com.otus.coroutines

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.runBlocking
import org.junit.Test

class ConsistentSandbox {

    @Test
    fun showcase() {
        runBlocking {

            val producer = flow {
                for (i in 1..10) {
                    println("sent $i")
                    delay(200)
                    emit(i)
                }
            }

            producer.collect {
                delay(100)
                println("received $it")
            }
        }
    }
}