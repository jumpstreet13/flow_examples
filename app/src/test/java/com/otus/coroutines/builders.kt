package com.otus.coroutines

import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import org.junit.Test

class Builders {

    @Test
    fun produce() = runBlocking {
        (1..3).asFlow().collect { value -> println(value) }
    }
}