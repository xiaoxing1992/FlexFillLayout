package com.example.flexfilllayout

import android.content.res.Resources
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.children
import com.example.flexfilllayout.databinding.ActivityMainBinding
import com.example.flexfilllayout.databinding.ItemFlexTagBinding
import com.google.android.flexbox.FlexboxLayout

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val strList = listOf("add", "adddddd", "asdaewdqffq", "222", "asda", "xfgewf", "AFGNI")


        binding.fabView.setOnClickListener {
            val numStr = strList.random()

            val itemBinding = ItemFlexTagBinding.inflate(LayoutInflater.from(binding.flexLayout.context))
            itemBinding.root.text = numStr
            val params = FlexboxLayout.LayoutParams(
                FlexboxLayout.LayoutParams.WRAP_CONTENT, 70.dp()
            )
            params.flexGrow = 1f
            itemBinding.root.layoutParams = params
            binding.flexLayout.addView(itemBinding.root)

            itemBinding.root.setOnClickListener {
                binding.flexLayout.children.forEach {
                    it.isSelected = false
                }
                itemBinding.root.isSelected = true
                Toast.makeText(this@MainActivity, itemBinding.root.text.toString(),Toast.LENGTH_SHORT).show()

            }
        }
    }
}

/**
 * dp 转换成 px
 * */
fun Int.dp(): Int {
    val value = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP, toFloat(), Resources.getSystem().displayMetrics
    )
    return when {
        value > 0 -> (value + 0.5f).toInt()
        value < 0 -> (value - 0.5f).toInt()
        else -> value.toInt()
    }
}
