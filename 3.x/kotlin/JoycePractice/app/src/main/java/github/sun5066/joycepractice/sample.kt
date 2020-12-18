package github.sun5066.joycepractice

val square = { number: Int -> number * number }

fun extendString(name: String, age: Int): String {
    val introduceMySelf: String.(Int) -> String = {
        "I am $this and $it years old"
    }
    return name.introduceMySelf(age)
}

val calculateGrade: (Int) -> String = {
    when (it) {
        in 0..40 -> "fail"
        in 41..70 -> "pass"
        in 71..100 -> "pefect"
        else -> "Error"
    }
}