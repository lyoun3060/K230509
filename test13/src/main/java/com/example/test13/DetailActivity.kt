package com.example.test13

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.test13.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //(1교시)인텐트에 등록된 데이터 가져오기
        val data1 = intent.getStringExtra("data1")
        val data2 = intent.getIntExtra("data2", 0) //<-2번째 매개변수의 값은 불러온 값이 없을경우 나타내는 디폴트값

        binding.resultViewText.text = "data1의 키값 -> $data1, data2의 키값 -> $data2"


        //(2교시)후처리 작업 테스트

    }
}