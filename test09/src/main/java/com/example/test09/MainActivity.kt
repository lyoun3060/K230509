package com.example.test09

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.WindowMetrics
import com.example.test09.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)

        var binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //리소스 폴더에서 values폴더 하위에 string.xml의 속성값을 사용하는 형식만 참고
//        binding.myName = getString(R.string.my_name)

        //api 30버전을 기준으로, 해당 기종의 사이즈 크기를 보는 함수 방법이 조금 다르다.
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.R){ //<-최신메서드
            val windowMetrics: WindowMetrics = windowManager.currentWindowMetrics
            Log.d("KSJ", "width : ${windowMetrics.bounds.width()}, " + "height : ${windowMetrics.bounds.height()}")
        }else { //<- 구형메서드
            val display = windowManager.defaultDisplay
            val displayMetrics = DisplayMetrics()
            display?.getRealMetrics(displayMetrics)
            Log.d("KSJ","width : ${displayMetrics.widthPixels}, " + "height : ${displayMetrics.heightPixels}")
        }
    }
}