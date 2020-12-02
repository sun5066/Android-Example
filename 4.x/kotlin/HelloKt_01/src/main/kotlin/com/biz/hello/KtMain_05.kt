package com.biz.hello

fun main() {
    var intNum: Int = 123 // 10
    val binNum: Int = 0b1001001 // 2
    val hexNum: Int = 0xF0 // 16

    val oneMillion = 1_000_000
    var creitCardNum = 1234_5678_9000_8888L
    val hexByte = 0xFF_FC_F1_F0
    val binByte = 0b0100_1001_1111_000

    var num1: Int = 10
    var num2: Int = 20
    var num3: Long = (num1 + num2).toLong()
    num3 = num1.toLong() + num2.toLong()

    var num4: Long = num3 + num2
    var asciiA: Int = 'A'.toInt()
    println("문자 A의 ASCII 코드 $asciiA")

    var charA: Char = 'A'.toChar()
    println("문자 A의 Char 문자 $charA")

}