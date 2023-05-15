package com.example.test10

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.DialogInterface
import android.content.pm.PackageManager
import android.media.MediaPlayer
import android.media.Ringtone
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.DatePicker
import android.widget.MediaController
import android.widget.TimePicker
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import com.example.test10.databinding.ActivityMainBinding
import kotlin.math.min

class MainActivity : AppCompatActivity() {
    
    val eventHandler = object : DialogInterface.OnClickListener{
        override fun onClick(dialog : DialogInterface?, which : Int){
            if(which == DialogInterface.BUTTON_POSITIVE){
                Toast.makeText(this@MainActivity, "확인시 토스트 띄우기", Toast.LENGTH_SHORT).show()
            }else if(which == DialogInterface.BUTTON_NEGATIVE){
                Toast.makeText(this@MainActivity, "취소시 토스트 띄우기", Toast.LENGTH_SHORT).show()
            }
        }
    }
    
    @RequiresApi(Build.VERSION_CODES.R)// <-2교시 수업부분에서 다시 들을수 있음 15~20분 부분
    fun showTest() {
        val toast = Toast.makeText(this, "메세지내용", Toast.LENGTH_SHORT)
        toast.addCallback(
            object : Toast.Callback() {
                override fun onToastHidden() {
                    super.onToastHidden()
                    Log.d("KSJ", "Toast가 hidden된 후 추가 기능 동작")
                }

                override fun onToastShown() {
                    super.onToastShown()
                    Log.d("KSJ", "Toast가 show된 후 추가 기능 동작")
                }
            }
        )
        toast.show()
    }

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        val toast = Toast.makeText(this, "메세지내용", Toast.LENGTH_SHORT) //context에 지정하고 싶은게 있으면 @지정할이름 으로 쓰면됨

        //샘플영상 확인
        binding.btnVideo.setOnClickListener {
            val videofile : Uri = Uri.parse("android.resouce://"+packageName+"/raw/testvideo")

//            val player : MediaPlayer = MediaPlayer.create(this, R.raw.testvideo)
//            player.start()
            val mc = MediaController(this)

            binding.videoView.setMediaController(mc)
            binding.videoView.setVideoPath(videofile.toString())
            binding.videoView.start()
        }

        //소리버튼 확인
        binding.btnSound.setOnClickListener {
            val notification : Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
            val ringtone = RingtoneManager.getRingtone(applicationContext, notification) //context는 설정을 관리하는거라고 생각하면됨(applicaitonContext = 최상위 버전)
            ringtone.play()
        }


        //라디오 버튼 포함하는 부분
        binding.btnRadio.setOnClickListener {
            var items = arrayOf<String>("1", "2", "3")
            AlertDialog.Builder(this@MainActivity).run{
                setTitle("라디오 alert 다이얼로그")
                setIcon(android.R.drawable.ic_dialog_info)
                setSingleChoiceItems(items, 1, object : DialogInterface.OnClickListener{
                    override fun onClick(dialog: DialogInterface?, which: Int) {
                        Log.d("KSJ", "${items[which]}이 선택되었습니다.")
                    }
                })
                setPositiveButton("확인",null)
                show()
            }
        }



        // 다이얼로그에 체크박스 선택 부분 해보기.
        binding.btnCheck.setOnClickListener {
            val items = arrayOf<String>("두루치기","된장찌개","밀면","칼국수")
            AlertDialog.Builder(this@MainActivity).run {
                setTitle("checkbox alert 다이얼로그")
                setIcon(android.R.drawable.ic_dialog_info)
                setMultiChoiceItems(
                    items,
                    booleanArrayOf(true,false,false,false),
                    object : DialogInterface.OnMultiChoiceClickListener{
                        override fun onClick(
                            dialog: DialogInterface?,
                            which: Int,
                            isChecked: Boolean
                        ) {
                            Log.d("KSJ",
                                "선택한 점심 메뉴 : ${items[which]} 이 ${if(isChecked)"선택됨." else "선택 해제됨."}"
                            )
                        }
                    }
                )
                setPositiveButton("확인",null)
                setCancelable(true)
                show()
            }.setCanceledOnTouchOutside(false)
        }



        //다이얼로그에 메뉴 선택부분 확인 해보기.
        binding.btnMenu.setOnClickListener {
            val items = arrayOf<String>("돼지불백", "된장찌개", "밀면", "칼국수")
            AlertDialog.Builder(this@MainActivity).run {
                setTitle("점심메뉴 / 메뉴 alert 다이얼로그")
                setIcon(android.R.drawable.ic_dialog_info)
                setItems(
                    items,
                    object: DialogInterface.OnClickListener{
                        override fun onClick(dialog: DialogInterface?, which: Int) {
                            Log.d("KSJ", "선택한 점심메뉴 :${items[which]}")
                        }
                    }
                )
                setPositiveButton("확인", null)
                show()
            }
        }




        //다이얼로그에 메뉴 선택부분 확인 해보기.
        binding.btnMenu.setOnClickListener {
            val items = arrayOf<String>("돼지불백", "된장찌개", "밀면", "칼국수")
            AlertDialog.Builder(this@MainActivity).run {
                setTitle("점심메뉴 / 메뉴 alert 다이얼로그")
                setIcon(android.R.drawable.ic_dialog_info)
                setItems(
                    items,
                    object: DialogInterface.OnClickListener{
                        override fun onClick(dialog: DialogInterface?, which: Int) {
                            Log.d("KSJ", "선택한 점심메뉴 :${items[which]}")
                        }
                    }
                )
                setPositiveButton("닫기", null)
                show()
            }
        }




        //경고창, 특정 정보를 띄워서, 확인 시 동작 가능, 취소 시 동작
        binding.btnAlert.setOnClickListener{
            AlertDialog.Builder(this@MainActivity).run {
                setTitle("경고창 타이틀")
                setIcon(android.R.drawable.ic_dialog_alert)
                setMessage("경고창 바디, 내용")
                setPositiveButton("OK", eventHandler)
                setNegativeButton("Cancle", eventHandler)
                show()
            }
        }
        
        
        
        
        //시간을 띄우는 버튼 UI 추가, 해당 시간을 출력,
        //1)Log.d, 2)토스트 메세지에도 출력
        binding.btnTime.setOnClickListener {
            //TimePickerDialog(this, 익명클래스, 시간, 분, 24시로 표시여부).show()
            TimePickerDialog(this@MainActivity, object : TimePickerDialog.OnTimeSetListener{
                override fun onTimeSet(view:TimePicker?, hour:Int, minute:Int){
                    Log.d("KSJ", "시간:$hour, 분:$minute")
                    Toast.makeText(this@MainActivity, "시간:$hour, 분:$minute", Toast.LENGTH_SHORT).show()
                }
            }, 15, 30, true).show()
        }



        //날짜 다이얼로그 띄우기, 출력은, 콜솔 또는 토스트 메시지
        binding.btnDate.setOnClickListener{
            //DatePickerDialog(this, 리스너, 년도, 월, 일).show()
            DatePickerDialog(this@MainActivity, object : DatePickerDialog.OnDateSetListener{
                override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
                    Log.d("KSJ", "year(년도): $year, month: ${month+1}, dayOfMonth: $dayOfMonth") //<-(month에 +1하는이유= 여기서는 0~11로 잡기때문애)
                    //토스트에 해당날짜 띄어보기
                    Toast.makeText(this@MainActivity, "year(년도): $year, month: ${month+1}, dayOfMonth: $dayOfMonth", Toast.LENGTH_SHORT).show()
                }
            }, 2023, 5, 15).show()

        }


        binding.btn1.setOnClickListener {
//            toast.show() //토스트 출력 방법1
//            Toast.makeText(this, "토스트 출력 방법2", Toast.LENGTH_SHORT).show()

            showTest()
        }


//        val requsetPermissionLauncher = registerForActivityResult(
//            ActivityResultContracts.RequestPermission()
//        ){ isGranted->
//            if(isGranted){
//                Log.d("KSJ", "callback, granted, 승인됨")
//            }else{
//                Log.d("KSJ", "callback, denied, 승인 안됨")
//            }
//
//        }
//        val status = ContextCompat.checkSelfPermission(this, "android.permission.ACCESS_FINE_LOCATION")
//        if(status == PackageManager.PERMISSION_GRANTED){
//            Log.d("KSJ", "callback, granted, 승인됨_2")
//        }else{
//            requsetPermissionLauncher.launch("android.permission.ACCESS_FINE_LOCATION")
//        }
    }
}