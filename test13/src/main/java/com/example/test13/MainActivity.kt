package com.example.test13

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.test13.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //버튼 클릭시, detail activity로 이동
        binding.btn1.setOnClickListener{
            //::class.java = 같은 앱의 액티비티로 전달시 타입은 명시적타입
            val intent = Intent(this@MainActivity, DetailActivity::class.java)

            //문자열, 정수를 설정해서 데이터 전송
            intent.putExtra("data1", "김수장")
            intent.putExtra("data2",  610)

            startActivity(intent)
        }
    }
}