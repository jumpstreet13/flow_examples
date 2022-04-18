package coroutines

import kotlinx.coroutines.*
import kotlinx.coroutines.sync.Mutex
import java.util.concurrent.atomic.AtomicInteger
import kotlin.system.measureTimeMillis

suspend fun massiveRun(action: suspend () -> Unit) {
    val n = 100
    val k = 1000
    val time = measureTimeMillis {
        coroutineScope {
            repeat(n) {
                launch {
                    repeat(k) { action() }
                }
            }
        }
    }
    println("Completed ${n * k} actions in $time ms")
}

var counter = 0

//atomics
//single thread fine
//coroutines.getMutex


//val mutex = Mutex()

@ObsoleteCoroutinesApi
fun main() {
    runBlocking(newSingleThreadContext("showcase")) {
        massiveRun {
            counter++
        }
        println(counter)
    }
}