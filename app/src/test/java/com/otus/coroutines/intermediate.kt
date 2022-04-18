package com.otus.coroutines

import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.reduce
import kotlinx.coroutines.flow.startWith
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.runBlocking
import org.junit.Test

class Intermediate {

    /**
     * map
     * filter
     */
    @Test
    fun `intermediate showcase`() {
        runBlocking {
            flowOf(1, 2, 3)
                .take(2)
                .filter { it % 2 == 0 }
                .map { request -> "response $request" }
                .collect {  }
        }
    }

    @Test
    fun `transform showcase`() {
        runBlocking {
            flowOf(1, 2, 3)
                .transform { request ->
                    emit("response $request")
                    emit("response $request")
                }
                .collect { response -> println(response) }
        }
    }

    @Test
    fun `flow builders showcase`() {
        runBlocking {
            val flow = flow<Int> {
                emit(4)
            }

            val secondFlow = flowOf(4)
            (1..10).asFlow()
        }
    }
}