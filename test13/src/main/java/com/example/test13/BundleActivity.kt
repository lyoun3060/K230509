package com.example.test13

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.test13.databinding.ActivityBundleBinding

class BundleActivity : AppCompatActivity() {
    //변겨오디는 변수 및, 바인딩 객체 따로 빼기

    var count = 0
    lateinit var binding : ActivityBundleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBundleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //참고소스 코드 : MA424
        //xml 뷰 작업에서 버튼, 텍스트 뷰
        //버튼 클릭시, 해당 값이 변경되는 부분 확인.
        //번들이라는 객체를 이용해서, 저장/불러오기 확인할것


        binding.plusCountButton.setOnClickListener {
            count++
            binding.countResultView.text="$count"
        }

    }

    //(4교시). 재정의하기, 매개변수가 1개인 경우
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState) //<- 뒤로가기나, 홈버튼 눌렀을떄 임시적으로 저장하는것을 로그로 나타낸것
        Log.d("KSJ","onSaveInstanceState..........")
        
        //값 저장하기
        outState.putString("data1", "hello")
        outState.putInt("data2", 10)
    }

    //(4교시). 재정의하기, 매개변수가 1개인 경우
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        
        //값 불러오기
        val data1 = savedInstanceState.getString("data1")
        val data2 = savedInstanceState.getInt("data2")

        binding.countResultView.text="$data1 - $data2"
    }


}