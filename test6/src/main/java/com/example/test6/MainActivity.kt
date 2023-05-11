package com.example.test6

import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import com.example.test6.databinding.ActivityMain2Binding
import com.example.test6.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() { //AppCompatActivity() 부모클래스로 부터 상속을 받음
    //코드 작업 공간임,
    // 뷰작업도 가능하지만, 뷰는 가급적 xml에서작업하자 <-이유? = 여기에는 데이터 관련 작업 및 이벤트 핸들러 등 작업하기 위해서
    // 만약 한 파일에 뷰까지 더해서 작업하면, 가독성 저하 및 유지보수 어려움
        override fun onCreate(savedInstanceState: Bundle?) { //onCreate = 액티비티의 생명주기에 관한것, 13장에서 더
        super.onCreate(savedInstanceState) //savedInstanceState = 저장소 개념, 객체에 저장 (16장에서 더 설명)

        setContentView(R.layout.activity_main)
        //res -> layout -> xml파일을 불러와서 실제로 화면에 출력
        //리소스 R.java파일이라는 곳에 상수값으로 저장되는데, 여기서 불러옵니다.
        //그래서, 코드에서 리소스 값을 불러올때 항상 R.layout 등으로 시작을해서 불러옴
        // 따라서 이코드는 화면을 출력하겠다는 코드, 레이아웃(뷰 그룹: 뷰(텍스트, 이미지, 인풋, 체크박스, 라디오..)를 모아주는 역할


        //뷰 바인딩 기술을 통해서, 뷰들을, 특정 바인딩이라는 객체에 모두 모아서 접근을 쉽게 해주는 기술
        //사용하기 전에 항상, build.gradle 파일에 설정후, sync now를 적용해서 사용해야함
        val binding = ActivityMainBinding.inflate(layoutInflater)
        //이 문법은 자동으로 생성된 바인딩 파일을 inflate함수와, 인자는:layoutInflater로 고정
        //setContentView = 화면에 그리기
        //binding 변수에 모든 뷰가 다 들어가 있음
        //견론은 여기서 필요한 뷰를 꺼내서 ㅅ용합니다.
        setContentView(binding.root)

        //button1을 뷰바인딩 기법으로 접근
        var status = 0
        binding.btn1.setOnClickListener{

            if(status == 0){
                binding.img1.visibility = View.GONE
                status = 1
            }else {
                binding.img1.visibility = View.VISIBLE
                status = 0
            }

        }
        }

//        val button1 = findViewById<Button>(R.id.btn1)
//        val image1 = findViewById<ImageView>(R.id.img1)
//        var status = 0
//
//        //button1 눌러서 -> image1 show/hide
//        button1.setOnClickListener{
//            if(status == 0){
//                image1.visibility = View.GONE
//                status = 1
//            }else {
//                image1.visibility = View.VISIBLE
//                status = 0
//            }
//
//        }
//
//    }
}