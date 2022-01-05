package test_java_kotlin

import kotlin.math.abs

fun longestCommonPrefix(strs: Array<String>): String {
    var result = ""
    var shortestString = strs[0]
    var numOfShortest = strs[0].length
    for (i in 0 until strs.size) {
        if (strs[i].length < numOfShortest) {
            numOfShortest = strs[i].length
            shortestString = strs[i]
        }
    }
    for (i in 0 until numOfShortest) {
        for (j in i + 1..numOfShortest) {
            var index = 0
            var isBreak = false
            while (index < strs.size) {
                if (strs[index].lastIndexOf(shortestString.substring(i, j)) != -1) {
                    index++
                } else {
                    isBreak = true
                    break
                }
            }
            if (isBreak == true) {
                break
            }
            if (shortestString.substring(i, j).length > result.length) {
                result = shortestString.substring(i, j)
            }

        }
    }
    return result
}

fun main() {
    val strs = arrayOf("reflower", "flow", "flight")
    println(longestCommonPrefix(strs))
}