package com.otus.coroutines

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.zip
import kotlinx.coroutines.runBlocking
import org.junit.Test

class Combining {

    @Test
    fun `zip showcase`() = runBlocking {
        val nums = (1..3).asFlow()
        val strs = flowOf("one", "two", "three")
        nums.zip(strs) { a, b -> "$a -> $b" }
            .collect { println(it) }
    }

    @Test
    fun `zip vs combine`() = runBlocking {
        val nums = (1..3).asFlow().onEach { delay(300) }
        val strs = flowOf("one", "two", "three").onEach { delay(400) }
        val startTime = System.currentTimeMillis()

        /*nums.zip(strs) { a, b -> "$a -> $b" }
            .collect { value ->
                println("$value at ${System.currentTimeMillis() - startTime} ms from start")
            }*/
        nums.combine(strs) { a, b -> "$a -> $b" }
            .collect { value ->
                println("$value at ${System.currentTimeMillis() - startTime} ms from start")
            }
    }
}