package com.example.test6

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

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
    }
}