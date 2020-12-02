package com.biz.hello

// VO 클래스
// 내부적으로 생성자, getter, setter, ToString 이 기본적으로 만들어지고
// equals(), hashCode(), copy(), componentN() 이라는 메서드가 생성
data class BookVO(var title: String, var author: String, var comp: String, var price: Int)
data class UserVO(var name: String = "홍길동", var tel: String = "", var age: Int = 0)

fun main() {
    // BookVO 클래스 선언시 기본값을 안넣었기 때문에
    // 객체 생성시 값을 넣어야함.
    var bookVO = BookVO("자바야 놀자", "홍기동", "이지즈", 15000)
    println(bookVO.toString())
    println(bookVO.hashCode())

    // UserVo 클래스는 기본값이 있기 때문에 매개변수 없이 생성 가능.
    var userVO = UserVO()
    println(userVO.hashCode())
    println("userVO 야 너는 UserVO 클래스로 부터 만들어진 객체냐? : " + "${
        if (userVO.equals(UserVO())) {
            "네"
        } else {
            "아니오"
        }
    }")

    println("userVO 야 너는 BookVO 클래스로 부터 만들어진 객체냐? : " + "${
        if (userVO.equals(BookVO("", "", "", 0))) {
            "네"
        } else {
            "아니오"
        }
    }")

    var userVO1 = UserVO(name = "이몽룡", age = 33)
    var bookVO1 = BookVO(author = "성춘향", title = "제이쿼리", comp = "이지즈", price = 12000)
    println(UserVO(name = "장보고", tel = "010-1111-2222").toString())

    var userVOCopy = userVO1.copy(name = "임꺽정")
    println(userVOCopy.toString())

    // 현재 지원되는 코틀린의 다중변수 선언 방법
    var (title, author) = bookVO1
    println("$title, $author")

    var (first, second) = Pair("홍길동", "이몽룡")
    var (f, s, t) = Triple("010", "111", 2222)
    println(StaticClass.ID)
}

class StaticClass {
    // companion object 는 java 에서 static 키워드 역할
    companion object NAVER_KEY {
        var ID = "1234567"
        var SECURITY = "000111000"
    }
}

class Daum_Config {
    companion object {
        var SEC_ID = "0001"
        var SEC_VALUE = 1111
    }
}

fun daum() {
    println(Daum_Config.SEC_ID)
    println(Daum_Config.SEC_VALUE)
}