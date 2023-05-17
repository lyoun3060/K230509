package com.example.test12

import android.content.Context
import android.graphics.Color
import android.graphics.Rect
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.test12.databinding.ActivityMainBinding
import com.example.test12.databinding.ItemRecyclerviewBinding

class MainActivity : AppCompatActivity() {
    lateinit var toggle: ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)




        //툴바 적용하기.
        setSupportActionBar(binding.toolbar)

        //ActionBarDrawerToggle 버튼 적용, 왼쪽 서랍처럼 열리는 메뉴
        // 꾸미는 작업은 네비게이션 뷰에서 작업.
        // res -> values -> strings.xml 해당 내용 복사.
        toggle = ActionBarDrawerToggle(this, binding.drawer, R.string.drawer_opened, R.string.drawer_closed)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toggle.syncState()

        //임시 데이터 만들기.
        val datas = mutableListOf<String>()
        for(i in 1..20) {
            datas.add("Item $i")
        }

        //리사이클러뷰 만드는 재료
        // 뷰홀더, 어댑터 , 레이아웃 매니저 , 적용.
        // 클래스 2개 만들고,

        // 리사이클러뷰 적용하기.
        val layoutManager = LinearLayoutManager(this)
        binding.recyclerview.layoutManager = layoutManager
        val adapter = MyAdapter(datas)
        binding.recyclerview.adapter = adapter
        binding.recyclerview.addItemDecoration(MyDecoration(this))

        //앱바 라는 뷰에, 툴바, 이미지 뷰, 리사이클러뷰도
        //참고코드 : MainActivity378

    }



    // 툴바 구성시 옵션 추가부분 필요.
    // 개발자가 직접 정의를 해야함.
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // 참고 menu : menu_378.xml
        menuInflater.inflate(R.menu.menu_378, menu)

//        return super.onCreateOptionsMenu(menu)
        return true
    }

    //test 11 : MainActivtity357 복사 했음.
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //이벤트가 toggle 버튼에서 제공된거라면..
        if(toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }

//    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
//
//        0 -> {
//            Log.d("kkang", "menu1 click")
//            true
//        }
//        1 -> {
//            Log.d("kkang", "menu2 click")
//            true
//        }
//        else -> super.onOptionsItemSelected(item)
//    }



    //리사이클러뷰 클래스 추가 부분.
    // 참고 코드 -> layout -> item_recyclerview.xml 복사.
    class MyViewHolder(val binding: ItemRecyclerviewBinding): RecyclerView.ViewHolder(binding.root)

    class MyAdapter(val datas: MutableList<String>): RecyclerView.Adapter<RecyclerView.ViewHolder>(){

        override fun getItemCount(): Int{
            return datas.size
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder
                = MyViewHolder(ItemRecyclerviewBinding.inflate(LayoutInflater.from(parent.context),
            parent, false))

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            val binding=(holder as MyViewHolder).binding
            binding.itemData.text= datas[position]
        }
    }

    // 목록 아이템 3개 씩 나열 하다가, 중간에 조금더 간격을주고 하는 부분.
    class MyDecoration(val context: Context): RecyclerView.ItemDecoration() {

        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            super.getItemOffsets(outRect, view, parent, state)
            val index = parent.getChildAdapterPosition(view) + 1

            if (index % 3 == 0) //left, top, right, bottom
                outRect.set(10, 10, 10, 60)
            else
                outRect.set(10, 10, 10, 0)

            view.setBackgroundColor(Color.parseColor("#28A0FF"))
            ViewCompat.setElevation(view, 20.0f)

        }
    }
}