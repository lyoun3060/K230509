package com.example.test11

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

class MainActivity3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        //참고하는 파일 1)MainActivity338, 2)OneFragment, 3)fragment_one.xml, 4)activiy_main338.xml
        //Fragment생성ㅅ하는 방법, 액티비티 파일 생성하는것과 비슷합니다. ->Activity 밑에 Fagment를 선택해서 만들면 됨
        //
        val fragmentManager: FragmentManager = supportFragmentManager
        val transaction: FragmentTransaction = fragmentManager.beginTransaction()
        val fragment = OneFragment()

        //activity_main338.xml 여기에 , 각 프로그래맨트들이 보여주는 하나의 틀
        //프래그먼트들은 부품처럼 교체 되어서 보여지게 된다.
        transaction.add(R.id.fragment_content, fragment)


        transaction.commit()
    }
}