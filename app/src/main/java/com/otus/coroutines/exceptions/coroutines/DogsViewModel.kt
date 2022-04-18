package com.otus.coroutines.exceptions

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.coroutines.CoroutineContext

class Presenter {

    lateinit var action: (String) -> Unit

    fun attach(action: (String) -> Unit) {
        this.action = action
    }

    fun getDogs() {
        ViewModelScope().launch(CoroutineExceptionHandler { coroutineContext, throwable ->
            println("caught $throwable in handler")
        }) {
            val puppies = async {
                throw IllegalArgumentException()
                "PUPPY"
            }
            try {
                action(puppies.await())
            } catch (ex: Exception) {
                println("caught $ex")
            }
        }
    }


}


class ViewModelScope : CoroutineScope {
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO + SupervisorJob()

}

fun main() {
    runBlocking {
        val job = ViewModelScope().launch(CoroutineExceptionHandler { coroutineContext, throwable ->
            println("caught $throwable in handler")
        }) {
            try {
                val puppies = async {
                    throw RuntimeException()
                }
            } catch (ex: RuntimeException) {
                println("caught $ex")
            }
        }
        job.join()
    }
}