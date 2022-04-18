package com.otus.coroutines

import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlin.coroutines.coroutineContext


suspend fun helloWorld(arg: String): Int {
    val a = coroutineContext[Job]
  delay(2000)
    return 100
}