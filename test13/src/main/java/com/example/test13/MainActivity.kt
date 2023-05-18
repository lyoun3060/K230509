package com.example.test13

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.test13.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    //생명주기 onCreate 최초 한번 실행시, binding늦게 할당되어서 전역처럼 쓰겠다는것
    //따라서 onCreate 함수 내부 뿐만아니라 외부에서도 사용가능
    lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //버튼 클릭시, detail activity로 이동
        binding.btn1.setOnClickListener{
            //::class.java = 같은 앱의 액티비티로 전달시 타입은 명시적타입
            val intent = Intent(this@MainActivity, DetailActivity::class.java)

            //문자열, 정수를 설정해서 데이터 전송
            intent.putExtra("data1", "김수장")
            intent.putExtra("data2",  610)

//            startActivity(intent) //<-단순 화면이동만 하는 함수임(2교시 후처리에는 사용해도 값이안넘어감)
            startActivityForResult(intent, 10) //(2교시)

        }
    }
    /*2교시*/
    //onCreate밖에서 작업,
    //(처리방법1, 12버전부터 지원 중단, 권장X)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 10 && resultCode == Activity.RESULT_OK){ //requestCode=위에서 10번으로 보냈기에 받는것도 10으로 받음
            val result = data?.getStringExtra("result")
            Log.d("KSJ", "후처리 result 값 조회 : $result")
            binding.resultMainViewText.text = result
        }
    }
}