package coroutines

import kotlinx.coroutines.*
import java.lang.RuntimeException

fun main() = runBlocking {

    val supervisor = SupervisorJob()

    with(CoroutineScope(coroutineContext + supervisor)) {

        val firstChild = launch(CoroutineExceptionHandler { _, _ ->  }) {
            println("The first child is failing")
            throw AssertionError("The first child is cancelled")
        }

        val secondChild = launch {
            firstChild.join()
            println("The first child is cancelled: ${firstChild.isCancelled}, but the second one is still active")
            try {
                delay(Long.MAX_VALUE)
            } finally {
                println("The second child is cancelled because the supervisor was cancelled")
            }
        }
        firstChild.join()
        println("Cancelling the supervisor")
        supervisor.cancel()
        secondChild.join()
    }
}