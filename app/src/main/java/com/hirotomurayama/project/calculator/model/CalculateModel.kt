package com.hirotomurayama.project.calculator.model

import java.util.Stack
import kotlin.math.floor

// スタックを使って計算していく。
// ただし、その計算はアクティビティの責務以上のことをしてしまっているため、そのロジックをここに整理する。
// 長くなるぞ。
class CalculateModel {
    fun calculate(expression: String): Long {
        // 関数を完成させてください
        val nums = Stack<String>()
        val ops = Stack<String>()

        var i = 0
        while (i < expression.length) {
            // opeの時
            if (!Character.isDigit(expression[i])) {
                val c = expression[i]
                val currentOp = c.toString() + ""

                // (の時
                if (c == '(') ops.push(currentOp)
                else if (c == ')') {
                    while (ops.peek() != "(") {
                        calculate(nums, ops.pop())
                    }
                    ops.pop()
                } else if (c == '+' || c == '-' || c == '*' || c == '/') {
                    while (!ops.isEmpty() && ops.peek() != "(" && getPriority(currentOp) <= getPriority(
                            ops.peek()
                        )
                    ) {
                        calculate(nums, ops.pop())
                    }
                    ops.push(currentOp)
                }


                // numの時
            } else {
                var numberStr = ""
                while (i < expression.length && Character.isDigit(expression[i])) {
                    numberStr += expression[i]
                    i++
                }
                i--
                nums.push(numberStr)
            }

            i++
        }

        while (!ops.isEmpty()) {
            calculate(nums, ops.pop())
        }

        return nums.pop().toLong()
    }

    private fun printStack(stack: Stack<String?>) {
        // System.out.println("開始");
        for (element in stack) {
            println(element)
        }
        // System.out.println("終了");
    }


    private fun calculate(stack: Stack<String>, op: String?) {
        val right = stack.pop().toLong()
        val left = stack.pop().toLong()
        var result: Long = 0

        when (op) {
            "+" -> result = left + right
            "-" -> result = left - right
            "*" -> result = left * right
            "/" -> result = floor((left / right).toDouble()).toInt().toLong()
        }
        stack.push(result.toString() + "")
    }

    private fun getPriority(op: String): Int {
        val map: HashMap<String, Int> = object : HashMap<String, Int>() {
            init {
                put("*", 2)
                put("/", 2)
                put("+", 1)
                put("-", 1)
            }
        }

        return map.getOrDefault(op, 0)
    }
}