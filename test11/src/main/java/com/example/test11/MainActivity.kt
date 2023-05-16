package com.example.test11

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.test11.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    //재정의 샘플
/*    override fun onSupportNavigateUp(): Boolean {
        Toast.makeText(this@MainActivity, "메인 업버튼 동작확인", Toast.LENGTH_SHORT).show()
        onBackPressed()
        return super.onSupportNavigateUp()
    }*/

    //메뉴가 선택 되었을 때,이벤트 처리하는 함수 확인
    override fun onOptionsItemSelected(item: MenuItem): Boolean = when(item.itemId){
        0->{
            Toast.makeText(this@MainActivity, "메뉴 1번선택", Toast.LENGTH_SHORT).show()
            true //<-Boolean의 결과값 반환
        }
        1->{
            val intent = Intent(this@MainActivity, ThreeActivity::class.java)
            startActivity(intent) //단순 화면 전환으로만 사용하기 때문에 -> 기본 startActivity를 사용
            true
        }
        else -> super.onOptionsItemSelected(item)
    }


    //액션바의 메뉴구성,코드부분 참고
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuItem1 : MenuItem? = menu?.add(0,0,0,"menu1")
        val menuItem2 : MenuItem? = menu?.add(0,1,0,"menu2")
        return super.onCreateOptionsMenu(menu)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding = ActivityMainBinding.inflate((layoutInflater))
        setContentView(binding.root)

        //코드로 업버튼 생성하기
        supportActionBar?.setDisplayHomeAsUpEnabled(true)



        binding.btn1.setOnClickListener {
            //버튼을 클릭시 화면 전환 방법 -> (인텐트(intent)를 이용), intent에 관하여 구체적인 데이터 전달은 13장에서 설명
            val intent = Intent(this@MainActivity, TwoActivity::class.java)
            startActivity(intent) //단순 화면 전환으로만 사용하기 때문에 -> 기본 startActivity를 사용
        }
    }
}