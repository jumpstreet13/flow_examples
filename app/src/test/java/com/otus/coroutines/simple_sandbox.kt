package com.otus.coroutines

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Test

class Sandbox {

    @Test
    fun showcase() {
        runBlocking {

            val producer = flow {
                for (i in 1..10) {
                    delay(100)
                    emit(i)
                }
            }


            /*producer.collect {
                println(it)
            }*/
        }
    }
}