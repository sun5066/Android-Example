package com.biz.hello

fun main() {
    val math = MathService()
    var sum = math.add(100, 200)
    println(sum)

    sum = math.add(200)
    println(sum)
}

class MathService {
    fun add(num1: Int, num2: Int = 60): Int {
        return num1 + num2
    }
}