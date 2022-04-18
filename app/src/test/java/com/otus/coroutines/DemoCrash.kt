package com.otus.coroutines

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.supervisorScope
import kotlinx.coroutines.withContext
import org.junit.Test
import kotlin.coroutines.CoroutineContext

class DemoCrash {

    class DemoScope : CoroutineScope {
        override val coroutineContext: CoroutineContext
            get() = SupervisorJob() +Dispatchers.Default
    }

    val scope = DemoScope()

    @Test
    fun emulateCrash() {
        runBlocking {
            val job = launch {
                println("Thread 1 is ${Thread.currentThread().name}")
                supervisorScope {
                    val result = async(Dispatchers.Default) {
                        test()
                    }
                    withContext(Dispatchers.Default) {
                        result.await()
                    }
                }
            }
            job.join()
        }
    }

    suspend fun test(): Int {
        delay(100)
        println("Thread 2 is ${Thread.currentThread().name}")
        return 10
    }
}