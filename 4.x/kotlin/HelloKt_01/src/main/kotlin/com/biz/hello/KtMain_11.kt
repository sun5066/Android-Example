package com.biz.hello

import kotlin.reflect.typeOf

fun main() {

    // 코틀린에서는 자료형이 지정되지 않은 변수에 null 값을 저장하면 안된다.
    // 반드시 자료형을 지정하고 자료형 뒤에 ?를 첨가해야한다.
    var anyVar: Int? = null
    println(anyVar)

    // Any 키워드는 모든 타입의 값을 저장할 수 있는 변수
    // Java Object Type
    // 가능하면 Any 타입은 쓰지말자
    var anyVar1: Any = 123

    when(anyVar1) {
        is String -> println("String")
        is Int -> println("int")
        is Float -> println("float")
        is Double -> println("double")
        else -> println("모르겠습니다")
    }
}
