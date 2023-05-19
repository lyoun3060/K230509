package com.example.test16

import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import com.example.test16.databinding.ActivityMain2Binding
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date

class MainActivity2 : AppCompatActivity() {
    lateinit var binding : ActivityMain2Binding
    lateinit var filePath: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        //갤러리 앱에서 사진 선택 후, 후처리하기
        // 참고코드 : ch16_provider에 MainActivity,
        //뷰는 재활용, 복사,

        //(순서1)
        //gallery request launcher..................
        //갤러리 앱에서 선택된 사진 데이터를 여기서 처리
        val requestGalleryLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult())
        { //<-it 은 선택된 사진 데이터를 말함
            try {
                //calRatio의 결과값은 정수(Int), (만약 calRatio의 결과값이 4가 나온경우)
                //(option.inSampleSize = calRatio)의 뜻은 결과값을 1/4로 줄이겠단 말임
                //calculateInSampleSize= 그 값에 맞추는 함수를 만들었다.
                val calRatio = calculateInSampleSize(

                    //실물의 사진이 높이를 반으로 줄이면서, inSampleSize를 맞춰가는 과정
                    it.data!!.data!!,
                    //dimes.xml에 특정크기 부여했음
                    //그냥 하드코딩으로 150dp으로 주어도 상관 없음
                    resources.getDimensionPixelSize(R.dimen.imgSize),
                    resources.getDimensionPixelSize(R.dimen.imgSize)
                )
                //BitmapFactory 사진을 읽는 업무, Options()를 통해서 크기를 지정
                val option = BitmapFactory.Options()

                option.inSampleSize = calRatio


                //inputStream = 사진을 바이트로 읽은 데이터가 있음
                var inputStream = contentResolver.openInputStream(it.data!!.data!!)
                //바이트 타입 ->bitmap 타입 형식으로 사진타입을 변경
                //사진의 결과는 원본 사이즈가 줄어서, bitmap타입으로 변경됨
                val bitmap = BitmapFactory.decodeStream(inputStream, null, option)
                inputStream!!.close()
                inputStream = null

                bitmap?.let {
                    //원하는 사진을 내가원하는 크기에 맞게끔 할당
                    //
                    binding.userImageView.setImageBitmap(bitmap)
                } ?: let{
                    Log.d("kkang", "bitmap null")
                }
            }catch (e: Exception){
                e.printStackTrace()
            }
        }

        //(순서 시작)
        //갤러리 버튼을 클릭 = 이벤트 발생
        binding.galleryButton.setOnClickListener {
            //gallery app........................
            //(Intent.ACTION_PICK는 여기서 하는 역할 -> 갤러리 앱 호출) 원래 뒤에 매개변수 타입보고 판단가능
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            //속성의 값은 모든 이미지를 의미, MIME type
            intent.type = "image/*"

            //후처리 작업 시작함을 알림(현재 액티비티 -> 갤러리 앱(사진선택) -> 현재 액티비티)순으로 진행될 예정
            //위에 정의된 requestGalleryLauncherdmfh dlehd
            requestGalleryLauncher.launch(intent)
        }

        //camera request launcher.................
        val requestCameraFileLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()){
            val calRatio = calculateInSampleSize(
                Uri.fromFile(File(filePath)),
                resources.getDimensionPixelSize(R.dimen.imgSize),
                resources.getDimensionPixelSize(R.dimen.imgSize)
            )
            val option = BitmapFactory.Options()
            option.inSampleSize = calRatio
            val bitmap = BitmapFactory.decodeFile(filePath, option)
            bitmap?.let {
                binding.userImageView.setImageBitmap(bitmap)
            }
        }


        binding.cameraButton.setOnClickListener {
            //camera app......................
            //파일 준비...............
            val timeStamp: String =
                SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
            val storageDir: File? = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
            val file = File.createTempFile(
                "JPEG_${timeStamp}_",
                ".jpg",
                storageDir
            )
            filePath = file.absolutePath
            val photoURI: Uri = FileProvider.getUriForFile(
                this,
                "com.example.ch16_provider.fileprovider",
                file
            )
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
            requestCameraFileLauncher.launch(intent)

        }
    }

    //이미지의 크기를 원본으로 읽으면 OOM현상이 발생하여 문제가됨
    //아래의 함수는 이미지의 크기를 적당히 조절하는 함수임
    //예를들어 현재 뷰의 가로세로는(150,150dp)이고, 실제 사진의 해상도는(3000,2000dp)라 가정
    //반으로 줄여나감 (1500, 1000) -> (750, 500) -> (375, 250) -> (187.5,125)
    private fun calculateInSampleSize(fileUri: Uri, reqWidth: Int, reqHeight: Int): Int { //(첫 매개변수 = 사진의 파일URI), (두번째 매개변수 = 원하는 가로사이즈), (세번쨰 매개변수 = 원하는 세로사이즈)

        //option, BitmapFactory 사진을 처리하는 업무
        //그런데 Options가 있으면 (사진처리업무 -> 관리옵션 정하는것)로 생각하면됨
        val options = BitmapFactory.Options()

        //inJustDecodeBounds가 ㅅtrue이면 본업무X->옵션만 처리
        options.inJustDecodeBounds = true
        try {

            //contentResolver.openInputStream = 선택된 사진을 바이트로 읽고, 바이트로 변환
            //inputStream은 따라서 사진을 바이트 형태로 읽은 상태
            var inputStream = contentResolver.openInputStream(fileUri)

            //inJustDecodeBounds 값을 true 로 설정한 상태에서 decodeXXX() 를 호출.
            //로딩 하고자 하는 이미지의 각종 정보가 options 에 설정 된다.
            BitmapFactory.decodeStream(inputStream, null, options)
            inputStream!!.close()
            inputStream = null
        } catch (e: Exception) {
            e.printStackTrace()
        }
        //비율 계산........................
        //(읽은 사진의 가로X세로 정보를 사이즈 조절하여 재할당)하는 코드
        val (height: Int, width: Int) = options.run { outHeight to outWidth }
        var inSampleSize = 1 //<-원본사이즈
        //inSampleSize 비율 계산
        if (height > reqHeight || width > reqWidth) { //(height = 원본사진 높이 <-> reqHeight = 조절한 사이즈의 높이)

            //원본 사이즈 반으로 줄이기
            val halfHeight: Int = height / 2
            val halfWidth: Int = width / 2

            while (halfHeight / inSampleSize >= reqHeight && halfWidth / inSampleSize >= reqWidth) {
                inSampleSize *= 2
            }
        }
        return inSampleSize
    }


}