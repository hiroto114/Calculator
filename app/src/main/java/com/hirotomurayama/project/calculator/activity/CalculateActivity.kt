package com.hirotomurayama.project.calculator.activity

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity
import com.hirotomurayama.project.calculator.R
import com.hirotomurayama.project.calculator.model.CalculateModel

class CalculateActivity : ComponentActivity()  {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.calculate_result)

        // 「計算」以外のボタンがクリックされた時の処理
        val buttonIdsExceptEquals = intArrayOf(R.id.buttonCancel, R.id.buttonPlus, R.id.buttonMinus, R.id.buttonDivide
            , R.id.button7, R.id.button8, R.id.button9, R.id.buttonMultiply
            , R.id.button4, R.id.button5, R.id.button6
            , R.id.button1, R.id.button2, R.id.button3, R.id.button0)

        for (id in buttonIdsExceptEquals) {
            findViewById<Button>(id).setOnClickListener(ButtonClickListener())
        }

        // 「計算」のボタンがクリックされた時の処理
        findViewById<Button>(R.id.buttonCalculate).setOnClickListener(equalsClickListener())
    }

    // ここでアルゴリズムとデータ構造で学んだ事の真骨頂を見せるとき。
    // スタックを使って計算していく。
    // ただし、その計算はアクティビティの責務以上のことをしてしまっているため、どこか別の場所（CalculateModel）でやる。
    // 下記メソッドは「計算」以外のボタンが押下された時のクリックリスナー
    private inner class ButtonClickListener : View.OnClickListener {
        override fun onClick(view: View){
            val buttonText = view as Button
            var equation: TextView = findViewById<TextView>(R.id.CalculateResult)
            equation.setText(equation.text.toString() + buttonText.text.toString())
        }
    }

    // 下記メソッドは「計算」のボタンが押下された時のクリックリスナー
    // ここでCalculateModelのメソッドを呼び出して結果を返すだけでいいな。
    private inner class equalsClickListener : View.OnClickListener {
        override fun onClick(view: View){
            val model = CalculateModel()
            var equation: TextView = findViewById<TextView>(R.id.CalculateResult)
            equation.setText(model.calculate(equation.text.toString()).toString())
        }
    }

}
