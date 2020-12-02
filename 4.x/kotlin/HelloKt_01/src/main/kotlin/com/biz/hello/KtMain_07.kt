package com.biz.hello

fun main() {
    var intRange: IntRange = 6..100
    println("Range : ${intRange}")
    for (i in intRange) {
        println("$i \t")
    }
}