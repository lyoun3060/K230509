package com.example.test8

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.MotionEvent
import android.widget.CompoundButton
import com.example.test8.databinding.ActivityMainBinding
import java.security.Key



//방법2(클래스로 따로 때어와서 구현)
//class MyEventHandler : CompoundButton.OnCheckedChangeListener{
//    override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
//        Log.d("KSJ", "클래스로 구현 방법2 쳌박스 클릭")
//    }
//}


//뷰 이벤트 처리 방법1( 인터페이스 구현시 재정의)
//class MainActivity : AppCompatActivity(), CompoundButton.OnCheckedChangeListener {
//
//    override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
//        Log.d("KSJ", "쳌박스 클릭")
//    }

class MainActivity : AppCompatActivity(){

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        when(keyCode) {
            KeyEvent.KEYCODE_BACK -> Log.d("KSJ", "BackKey 누름")
            KeyEvent.KEYCODE_VOLUME_UP -> Log.d("KSJ", "Volume 누름")
            KeyEvent.KEYCODE_VOLUME_DOWN -> Log.d("KSJ", "Volume down 누름")
        }
//        Log.d("KSJ", "onKeyDown 실행")
        return super.onKeyDown(keyCode, event)
    }

    override fun onKeyUp(keyCode: Int, event: KeyEvent?): Boolean {
        Log.d("KSJ", "onKeyUp 실행")
        return super.onKeyUp(keyCode, event)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when(event?.action){
            MotionEvent.ACTION_DOWN -> {
                Log.d("KSJ", "좌표 X값 : ${event.x}, rawX 좌표 : ${event.rawX}")
            }
            MotionEvent.ACTION_UP -> {
                Log.d("KSJ", "좌표 Y값 : ${event.y}, rawY 좌표 : ${event.rawY}")
            }
        }
        return super.onTouchEvent(event)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(R.layout.activity_main)
        setContentView(binding.root)

        //방법1 : 리스너 인터페이스를 메인 액티비티에서 구현 후, 이벤트 처리 방법
//        binding.check1.setOnCheckedChangeListener(this)

        //방법2: 클래스로 정의된 리스너를 사용
//        binding.check1.setOnCheckedChangeListener(MyEventHandler())

        //방법3: SAM기법(Single Abstract Method), 자바: 함수형 인터페이스, 람다식 구현
        //인터페이스 = 구성을 추상메서드로 구성된 것
        //추상메서드 : 메서드의 선언부는 있지만, 구현부가 없는것을 말함
        //보통 인터페이스를 구현해서 사용 할 때, 보통 재정의해서 사용함.(구현부가 없기에 재정의해서 사용하는것)
        binding.check1.setOnCheckedChangeListener{
            a, b -> Log.d("KSJ", "방법3번, SAM기법으로 쳌박스 클릭")
        }

        binding.btn1.setOnLongClickListener { //<-불리언 형태의 함수
            Log.d("KSJ", "롱 클릭")
            true //<-롱클릭 쓸때 써달라고함
        }
    }
}