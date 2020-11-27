package com.biz.hello

fun main() {
    var rawString: String = """
        하늘을 우러러
        한점 부끄럼 없기를
        잎새에 이는 바람에도
        괴로워 했다.
    """
    println(rawString.trimIndent()) // 여백 제거후 출력
    var rawIntentString: String = """
        |하늘을 우러러 한점
        |부끄럼 없기를
    """
    println(rawIntentString.trimMargin("|"))

    // Kt는 문자열을 Char 배열과 동일하게 취급한다.
    // 문자열 변수에 첨자를 부착하여 사용하면 문자를 추출해 낼수 있다.
    var strArray = "Republic of Korea"
    println(strArray[3])

    for (i in strArray) {
        println(i)
        Thread.sleep(100)
    }
}