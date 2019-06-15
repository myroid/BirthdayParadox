package com.birthdayparadox

import java.util.*

class BirthdayParadox {
    companion object {
        private const val classNum = 10000000
        private const val studentNum = 23

        @JvmStatic
        fun main(args: Array<String>) {
            var equalTimes = 0
            for (i in 0 until classNum) {
                val randomArray =
                    createRandomArray(studentNum)
                if (isSameElementInArray(randomArray)) {
                    equalTimes++
                }
            }
            val percent = equalTimes * 1.0f / classNum * 100
            println("学生人数为 $studentNum 的 $classNum 个班级中生日相同的概率为：$percent%")
        }

        private fun createRandomArray(size: Int): IntArray {
            val ia = IntArray(size)
            for (i in 0 until size) {
                ia[i] = Random().nextInt(365)
            }
            return ia
        }

        private fun isSameElementInArray(array: IntArray): Boolean {
            if (array.isEmpty()) {
                return false
            }
            for (i in array) {
                if (array.indexOf(i) != array.lastIndexOf(i)) {
                    return true
                }
            }
            return false
        }
    }
}