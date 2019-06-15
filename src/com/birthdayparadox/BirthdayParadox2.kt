package com.birthdayparadox

import java.util.*

class BirthdayParadox2 {
    companion object {
        private const val classNum = 10000000
        private const val studentNum = 55

        @JvmStatic
        fun main(args: Array<String>) {
            val map = mutableMapOf<Int, Int?>()

            for (i in 0 until classNum) {
                val randomArray =
                    createRandomArray(studentNum)
                val sameCountSet =
                    getSameElementCountInArrayToSet(randomArray)
                for (j in sameCountSet) {
                    for (m in 2 until studentNum) {
                        if (j == m) {
                            map[m] = if (map[m] == null) 1 else map[m]!!.plus(1)
                        }
                    }
                }
            }

            println("学生人数为 $studentNum 的 $classNum 个班级中")
            for (entry in map) {
                entry.value?.run {
                    val percent = entry.value!!.toFloat() / classNum * 100
                    println("有 ${entry.key} 人相同的生日概率是：$percent%")
                }
            }
        }

        private fun createRandomArray(size: Int): IntArray {
            val intArray = IntArray(size)
            for (i in 0 until size) {
                intArray[i] = Random().nextInt(365)
            }
            return intArray
        }

        private fun getSameElementCountInArrayToSet(array: IntArray): Set<Int> {
            val arrayToSet = array.toSet()
            val frequencyList = mutableListOf<Int>()
            for (i in arrayToSet) {
                val frequency = Collections.frequency(array.toMutableList(), i)
                frequencyList.add(frequency)
            }
            return frequencyList.toSet().filter { i -> i > 1 }.toSortedSet()
        }
    }
}