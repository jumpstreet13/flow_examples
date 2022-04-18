package com.otus.coroutines.exceptions

import kotlinx.coroutines.delay


suspend fun helloWorld(x: String) {
    print("hello")
    print(x)
    delay(2000)
    print("world")
    test()

}

suspend fun test(){
    print("test")
    delay(200)
    print("test2")
}