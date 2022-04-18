package com.otus.coroutines

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.junit.Test

class ErrorHandlingSanbox {

    fun produce(): Flow<Int> = flow {
        withContext(Dispatchers.IO) {
            (1..6).forEach {
                println("sent $it on ${Thread.currentThread().name}")
                delay(200)
                emit(it)
            }
        }
    }.flowOn(Dispatchers.IO)

    fun produceOnIo(): Flow<Int> =
        flow {
            (1..6).forEach {
                println("sent $it on ${Thread.currentThread().name}")
                delay(200)
                emit(it)
            }
        }.flowOn(Dispatchers.IO)

    fun produceWithError(): Flow<Int> =
        flow {
            (1..6).forEach {
                if (it == 3) {
                    throw RuntimeException("You Shall Not Pass")
                }
                println("sent $it on ${Thread.currentThread().name}")
                delay(200)
                emit(it)
            }
        }.catch {}
            .flowOn(Dispatchers.IO)

    @Test
    fun showcaseError() {
        runBlocking {
            try {
                produceWithError()
                    // .onCompletion {  }
                    .collect {
                        println("received $it on ${Thread.currentThread().name}")
                    }
            } catch (e: Exception) {
                println("caught $e")
            }
        }
    }

    @Test
    fun showcase() {
        runBlocking {
            withContext(Dispatchers.IO) {
                produce().collect {
                    println("received $it on ${Thread.currentThread().name}")
                }
            }
        }
    }

    @Test
    fun showcaseReceiveOnMain() {
        runBlocking {
            produceOnIo().collect {
                println("received $it on ${Thread.currentThread().name}")
            }
        }
    }

}