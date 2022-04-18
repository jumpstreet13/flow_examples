package com.otus.coroutines.exceptions.coroutines.builders

import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext
import kotlin.system.measureTimeMillis

fun main() {
    val time = measureTimeMillis {
        runBlocking {
            val result1 = async(start = CoroutineStart.LAZY) {
                println("demo")
                delay(1000)
                10
            }
            val result2 = async {
                delay(1000)
                10
            }
            val result3 = async {
                delay(1000)
                10
            }
            print("result = ${result1.await()}")
        }
    }
    print("time = $time ms")
}

suspend fun heavyWork(): Int {


    return withContext(CoroutineName("HELLO") + Dispatchers.Main) {
        10
    }
}

fun loadImages() {
    ViewModelScope().launch {
        delay(1000)//network
        val images = arrayListOf<String>("image/1")
        withContext(Dispatchers.Main) {
        }
    }
}

class ViewModelScope : CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO
}

/**
 * 1. добавим диспатчер и поменяем тайминги
 * 2. join 2 к 1
 * 3. Лейзи старт
 */

//fun main() {
//    val time = measureTimeMillis {
//        runBlocking {
//            val jobs = mutableListOf<Job>()
//            for (i in 1..2) {
//                jobs += GlobalScope.launch {
//                    work(i)
//                }
//            }
//            jobs.forEach { it.join() }
//        }
//    }
//    println("Done in $time ms")
//}