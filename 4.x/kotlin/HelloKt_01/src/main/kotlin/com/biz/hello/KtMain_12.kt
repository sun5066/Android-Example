package com.biz.hello

fun main() {
    // 지역함수 선언
    // 어떤변수에 여러가지 연산을 수행한 후 그 결과를 담아야 할 경우
    // 함수로 선언하면 묶인 그룹이 명확해지는 효과
    fun privateSum(): Int {
        fun privateSum(num1: Int, num2: Int) = num1 + num2
        return privateSum(50, 50)
    }
    println(privateSum())

}
