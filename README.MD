周六的早晨，刷到一条有趣的博文。原文如下：
![](https://blog-1251473749.cos.ap-beijing.myqcloud.com/birthday_paradox/birthday_paradox_weibo_origin.png)

有关生日悖论的词条

- [**维基百科**](https://zh.wikipedia.org/wiki/%E7%94%9F%E6%97%A5%E5%95%8F%E9%A1%8C)
- [**百度百科**](https://baike.baidu.com/item/%E7%94%9F%E6%97%A5%E6%82%96%E8%AE%BA)

这是一个很有趣的一个问题，自己就用Kotlin模拟了一遍。

> 假设有<font color="#dd0000">10000000</font>个班级，每个班级人数为<font color="#dd0000">23人</font>:

```kotlin
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
```
输出结果显示生日相同的概率为：<font color="#dd0000">50.724857%</font>，验证了生日悖论的说法。

关于这个问题，我们可以在深入一点。比如班级中3位同学生日相同的概率是多少？4位呢？5位又是多少呢？

> 假设有<font color="#dd0000">10000000</font>个班级，每个班级人数为<font color="#dd0000">55人</font>:

```kotlin
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
```

输出结果如下：

```
学生人数为 55 的 10000000 个班级中
有 2 人相同的生日概率是：98.19454%
有 3 人相同的生日概率是：15.88791%
有 4 人相同的生日概率是：0.60795003%
有 5 人相同的生日概率是：0.016490001%
有 6 人相同的生日概率是：4.3999997E-4%
有 7 人相同的生日概率是：2.0E-5%
```

[GitHub地址](https://github.com/myroid/BirthdayParadox)