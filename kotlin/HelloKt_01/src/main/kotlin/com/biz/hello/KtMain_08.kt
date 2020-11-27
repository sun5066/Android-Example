package com.biz.hello

fun main() {
    var str1: String = "Korea"
    var str2: String = "Korea"

    if (str1 == str2) {
        println("같은 문자열 입니다")
    }

    // 레퍼런스로 비교
    if (str1 === str2) {
        println("주소가 다를 수 있습니다")
    }

    var num1 = 10
    var num2 = 20
    var max1 = num1
    if (num1 < num2) {
        max1 = num2
    }

    max1 = if (num1 < num2) {
        println("num2 가 더큼")
        num2
    } else {
        println("num1 이 더큼")
        num1
    }

    var num3 = 3
    when (num3) {
        1 -> println("num3 = 1")
        2 -> println("num3 = 2")
        3 -> println("num3 = 3")
        4 -> println("num3 = 4")
        else -> println("없음")
    }

    var rNum: Int = (Math.random() * 100 + 1).toInt()
    when (rNum) {
        1 -> println("rNum는 1이다")
        in 10..20 -> println("10~20 인 값임 : $rNum")
        in 21..31 -> println("21~31 인 값임 : $rNum")
        else -> println("어디에도 속하지 않는다 : $rNum")
    }

    var arrInt = intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    rNum = (Math.random() * 20 + 1).toInt()
    when (rNum) {
        in arrInt -> println("arrInt 배열에 포함된 값")
    }

    // Any 모든 자료형
    var xVar: Any = "Republic of Korea"
    var x = when (xVar) {
        is String -> xVar.indexOf("Korea") > 0 // 문자열 검색
        is String -> xVar.startsWith("of") // 시작하는 문자열이 of 이냐?
        else -> false
    }

    println("$xVar 에는 Korea 문자열이 ${if (x) "포함 되었음" else "포함되지 않음"}")
}