import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Test
import kotlin.system.measureTimeMillis

class Buffering {

    private fun produce(): Flow<Int> = flow {
        for (i in 1..3) {
            delay(100)
            emit(i)
        }
    }

    @Test
    fun `buffering showcase`() = runBlocking {
        val time = measureTimeMillis {
            produce()
                .buffer()
                .collect { value ->
                    delay(300)
                    println(value)
                }
        }
        println("Collected in $time ms")
    }

    @Test
    fun `conflation showcase`() = runBlocking {
        val time = measureTimeMillis {
            produce()
                .conflate()
                .collect { value ->
                    delay(300)
                    println(value)
                }
        }
        println("Collected in $time ms")
    }

    @Test
    fun `collectLatest showcase`() = runBlocking {
        val time = measureTimeMillis {
            produce()
                .collectLatest { value ->
                    println("Collecting $value")
                    delay(300)
                    println("Done $value")
                }
        }
        println("Collected in $time ms")
    }
}