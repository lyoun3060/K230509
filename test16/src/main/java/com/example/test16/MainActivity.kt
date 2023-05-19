package com.example.test16

import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import com.example.test16.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    //후처리를 위해 쓰는 코드
    //외부 앱에서 연동된 데이터를 원래의 앱으로 가지고 옴
    lateinit var requestPermissionLauncher: ActivityResultLauncher<Array<String>> //(추상메서드로 구성된 클래스임 = 추상클래스), 실행할 몸통이 없는것 ->재정의를 해서 쓰겠다는말
    lateinit var requestContactsLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //사용, 할당함으로써 lateinit 동작함
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //참고코드 : MainActivty(test16)

        requestPermissionLauncher = registerForActivityResult( //<-등록된 액티비티 결과를 Launcher로 담는것
            //여기에는 1)후처리, 2)특정의 퍼미션의 결과를 받을 수 있음
            ActivityResultContracts.RequestMultiplePermissions() //다양한 요청의 용청의 결과값을 처리하겠구나라고 유추 가능
        ) {
            // 넘어온 정보는 해당 앱에 접근을 하기 위한 권한에 대한 승인정보
            // it <- 하나의 매개변수를 가리킴 (여기서는 map의 형탵로 받음)

            for (entry in it.entries) { //entries = key/value를 같이 가져온다는 뜻

                //android.permission.READ_CONTACTS : 액션 문자열(상수로 정해져 있음)
                //외부저장소, 특정 권한 관련된 문자상수는 여러개임(일일이 외우려 하지말자)
                if(entry.key == "android.permission.READ_CONTACTS" && entry.value) { //READ_CONTACTS = alert뜻
                    Log.d("KSJ", "granted ok...")

                    //Intent.ACTION_PICK은 매개변수가 (데이터/연락처/위도경도/웹주소)에 따라서 호출하는 앱이 다름(연락처앱, 지도앱, 브라우저 등..)
                    val intent = Intent(Intent.ACTION_PICK, ContactsContract.CommonDataKinds.Phone.CONTENT_URI)

                    //후처리의 시작,[ 현재앱에서 호출 /여기까지임]-> [넘어간 곳/ 연락처 앱(특정 데이터 선택) ->현재앱으로 돌아가게함 ]
                    requestContactsLauncher.launch(intent)
                }else {
                    Toast.makeText(this, "required permission..", Toast.LENGTH_SHORT).show()
                }
            }

        }
        //위에서, 연락처에 접근하기 위한 권한 설정을 하였다면
        //여기서는 연락처 앱에 접근해서 데이터 선택 후 다시 돌아오는 과정
        //사진첩 카메라 모든 외부앱의 데이터 연동 부분(패던은 동일 함)
        requestContactsLauncher = registerForActivityResult(
            //이 부분이, 후처리 데이터 결과를 처리
            ActivityResultContracts.StartActivityForResult())
        {
            //연락처 접근 승인이 받은 상태이면, 넘어온 연락처 정보를 불러오는 과정의 코드
            if(it.resultCode == RESULT_OK){
                Log.d("KSJ", "${it.data?.data}") //${it.data?.data}부분은 선택된 연락처 정보하나가 있음

                //커서(다음장에 한번 더 설명할 예정)
                val cursor = contentResolver.query(
                    it!!.data!!.data!!,
                    arrayOf<String>(
                        //연락처, 사람이름
                        ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                        //연락처, 전화번호(해당 코드는 상수로 등록된 문자열임)
                        ContactsContract.CommonDataKinds.Phone.NUMBER
                    ),
                    null,
                    null,
                    null
                )
                Log.d("KSJ", "cursor size....${cursor?.count}")
                if (cursor!!.moveToFirst()) {
                    val name = cursor?.getString(0)
                    val phone = cursor?.getString(1)
                    binding.resultContact.text = "name: $name, phone: $phone"
                }
            }
        }
        //연락처 버튼
        //
        binding.contactButton.setOnClickListener {
            if (ContextCompat.checkSelfPermission(
                    this,
                    "android.permission.READ_CONTACTS"
                ) !== PackageManager.PERMISSION_GRANTED
            ) {
                requestPermissionLauncher.launch(arrayOf("android.permission.READ_CONTACTS"))
            } else {
                val intent = Intent(Intent.ACTION_PICK, ContactsContract.CommonDataKinds.Phone.CONTENT_URI)
                requestContactsLauncher.launch(intent)
            }
        }
    }
}