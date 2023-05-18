package com.example.test13

import android.app.Activity
import android.content.Intent
import android.content.Intent.ACTION_VIEW
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.example.test13.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    //생명주기 onCreate 최초 한번 실행시, binding늦게 할당되어서 전역처럼 쓰겠다는것
    //따라서 onCreate 함수 내부 뿐만아니라 외부에서도 사용가능
    lateinit var binding: ActivityMainBinding


    //생명주기 onCreate 최초 한번 실행
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //버튼 클릭시, detail activity로 이동
        binding.btn1.setOnClickListener {
            //::class.java = 같은 앱의 액티비티로 전달시 타입은 명시적타입
            val intent = Intent(this@MainActivity, DetailActivity::class.java)

            //문자열, 정수를 설정해서 데이터 전송
            intent.putExtra("data1", "김수장")
            intent.putExtra("data2", 610)

//            startActivity(intent) //<-(1교시)단순 화면이동만 하는 함수임
            startActivityForResult(intent, 10) //(2교시)방법1, 화면 이동 및 요청코드로 값 넘겨받기 가능하게함
        }



            //(3교시)
            //(방법2) 설정, 순서1
            val requestLauncher : ActivityResultLauncher<Intent> = registerForActivityResult(
                //Contract의 종류가 많음 예)갤러리앱 선택, 카메라 선택, 연락처 앱 선택
                //그 종류 중에 후처리를 선택
                ActivityResultContracts.StartActivityForResult()
            ){
                //A에서 B로 호출후, B에서 특정 데이터 선택한 값을 불러옴
                //불러온 데이터를 it으로 선택해서 작업
                val resultData = it.data?.getStringExtra("result2")
                binding.result2MainViewText.text = resultData
            }


            //(방법2), ActivityResultLauncher라는 타입으로 진행함(권장)
            binding.btn3.setOnClickListener {
                //::class.java = 같은 앱의 액티비티로 전달시 타입은 명시적타입
                val intent = Intent(this@MainActivity, DetailActivity::class.java)

                //문자열, 정수를 설정해서 데이터 전송
                intent.putExtra("data1", "후처리 방법2")
                intent.putExtra("data2",  777)


                //(방법2)호출하는 함수 형식, 순서 2
                requestLauncher.launch(intent)
            }
        
        
            //인텐트 필터 정보의 속성 예제(3교시)
            //버튼을 사용해, 해당 인텐트 정보 호출
            binding.btn4.setOnClickListener {
                val intent = Intent()
//                intent.action = "ACTION_EDIT" //아무거나 입력해도 상관없지만 해당 형식으로 적으면 ACTION_EDIT이 정의된게 없어서 오류가 발생함
                intent.action = "ACTION_EDIT" //(4교시)
//                intent.action = Intent.ACTION_VIEW //(3교시)
//                intent.data = Uri.parse("http://google.com")
                //intent.setPackage("com.") / 인텐트 setPackage 잠시보류
                startActivity(intent) //<-후처리 하는거 아니니깐

                //지도보는 속성 예제
                //만약 지도맵이 여러개이면, 지도 앱 목록 리스트가 나옴(그 중에서 선택하면됨)
                /*val intent = Intent(Intent.ACTION_VIEW, Uri.parse("geo:37.7749.127.4194"))
                intent.setPackage("com.google.android.apps.maps")
                startActivity(intent)*/

            }
        
        }

    /*2교시*/
    //onCreate밖에서 작업,
    //(처리방법1, 12버전부터 지원 중단, 권장X)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if(requestCode == 10 && resultCode == Activity.RESULT_OK){ //requestCode=위에서 10번으로 보냈기에 받는것도 10으로 받음
            val result = data?.getStringExtra("result")
            Log.d("KSJ", "후처리 result 값 조회 : $result")
            binding.resultMainViewText.text = result
        }
    }
}