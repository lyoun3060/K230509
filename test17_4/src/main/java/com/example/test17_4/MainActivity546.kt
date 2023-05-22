package com.example.test17_4

import android.content.ContentUris
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import com.example.test17_4.databinding.ActivityMain546Binding
import java.io.BufferedReader
import java.io.File
import java.io.OutputStreamWriter

class MainActivity546 : AppCompatActivity() {
    lateinit var binding: ActivityMain546Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain546Binding.inflate(layoutInflater)
        setContentView(binding.root)

        //예제 문서 폴더 위치로, 정해진 이름의 상수값 경로 확인해보기

//        val file2:File? =getExternalFilesDir(null)
        val file2:File? =getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS)
        if(file2 !=null) {
            Log.d("KSJ", "getExternalFilesDir의 위치 : ${file2.absolutePath}")
        }

        binding.button1.setOnClickListener {
            //파일 쓰기
            //파일저장소 위치(외부저장소)
            val file: File = File(getExternalFilesDir(null), "test.txt")
            val writeStream: OutputStreamWriter = file.writer()
            writeStream.write("hello world")
            writeStream.flush()
            // 파일 읽기
            val readStream: BufferedReader = file.reader().buffered()
            readStream.forEachLine {
                Log.d("KSJ", "$it")
            }
        }
        binding.button2.setOnClickListener {
            //공용저장소...........
            //projection-> select_ID, ISPLAY_NAME : 특정의 컬럼을 선택하는 항목
            val projection = arrayOf(
                MediaStore.Images.Media._ID,
                MediaStore.Images.Media.DISPLAY_NAME
            )
            //contentResolver->첫번째 매개변수, 공용저장소 위치
            //두번째 매개변수 -> 조건, 내가 조회해서 보기위한 컬럼들
            val cursor = contentResolver.query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                projection,
                null,
                null,
                null
            )
            //cursor -> 공용저장소에 있는 사진의 이미지의 아이디와, 파일명을 불러옴
            //테이블 형식으로 데이터를 저장해 놓음
            cursor?.let {
                //cursor 은 0행부터 대기 -> movetoNext-> 1행으로감
                //1행마다, 컬럼이 id, display로 구성
                while (cursor.moveToNext()) {
                    Log.d("KSJ", "_id : ${cursor.getLong(0)}, name : ${cursor.getString(1)}")
                    //공용저장소 위치의 URI, 파일의 취리
                    val contentUri: Uri = ContentUris.withAppendedId(
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                        cursor.getLong(0)
                    )

                    //파일의 위치에 있는 샂ㄴ의 파열을 읽는 작업, 바이트 단위
                    val resolver = applicationContext.contentResolver
                    resolver.openInputStream(contentUri).use { stream ->
                        
                        // stream 객체에서 작업 수행
                        //stream(바이트단위) -.bitmap타입으로 변경
                        //해당 뷰에 설정
                        //프로젝트때, 아이어리 일기 작성CRUD 예제
                        //프로젝트 때 조금 더 구체적으로 설명
                        //안드로이드 + 스프링 부트 연동 프로젝트
                        val option = BitmapFactory.Options()
                        option.inSampleSize = 10
                        val bitmap = BitmapFactory.decodeStream(stream, null, option)
                        binding.resultImageView.setImageBitmap(bitmap)
                    }
                }
            }
        }

    }
}