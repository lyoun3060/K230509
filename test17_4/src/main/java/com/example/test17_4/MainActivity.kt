package com.example.test17_4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.test17_4.databinding.ActivityMainBinding
import java.io.BufferedReader
import java.io.File
import java.io.OutputStreamWriter

class MainActivity : AppCompatActivity() {
    //참고코드 : test17->MainActivity
    lateinit var binding : ActivityMainBinding
    lateinit var file : File

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //file 선언, 뷰, xml에서 복사해서 가져오기
        binding.button1.setOnClickListener {
            //실제 물리경로에, 물리파일을 만드는 작업
            file = File(filesDir, "test230522-1.txt")

            //해당 파일에 쓰기 작업(OutputStreamWriter)
            val writeStream: OutputStreamWriter = file.writer()

            //writeStream라는 객체에 write함수로 쓰기 작업을 진행
            writeStream.write("hello world")

            //적용한다.
            writeStream.flush()

//            openFileOutput("test.txt", Context.MODE_PRIVATE).use {
//                it.write("hello world!!".toByteArray())
//            }

        }
        binding.button2.setOnClickListener {
            //IO(Input Output) stream 단어가 없으면, 문자기반(문자열)
            //stream이 있으면, 바이트 단위로 작업을 한다.
            //여기에는 stream을 사용하지 않았기 때문에 문자형식으로 작업
            val readStream: BufferedReader = file.reader().buffered()
            readStream.forEachLine {
                Log.d("KSJ", "$it")
            }

//            openFileInput("test.txt").bufferedReader().forEachLine {
//                Log.d("kkang", "$it")
//            }
        }
    }
}