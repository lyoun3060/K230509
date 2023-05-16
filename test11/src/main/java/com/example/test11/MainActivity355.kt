package com.example.test11

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.test11.databinding.ActivityMain354Binding
import com.example.test11.databinding.ActivityMain355Binding
import com.example.test11.databinding.Item354Binding

class MainActivity355 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main354)

        val binding= ActivityMain355Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val datas = mutableListOf<String>()
        for(i in 1..3){
            datas.add("Item $i")
        }

        //뷰 페이저 2 설정방식중에서, 프래그먼트 방식의 어댑터 설정 및 적용 부분
        val adapter= MyFragmentPagerAdapter(this)
        binding.viewpager.adapter = adapter
    }


    //부모클래스 -> FragmentActivity
    class MyFragmentPagerAdapter(activity: FragmentActivity): FragmentStateAdapter(activity){
        val fragments: List<Fragment>
        init { //init: 해당 MyFragmentPagerAdapter 클래스가 호출 될 때마다, 반드시 실행되는 코드 부분
            fragments= listOf(OneFragment(), TwoFragment(), ThreeFragment())
            Log.d("KSJ" ,"fragments size : ${fragments.size}")
        }
        override fun getItemCount(): Int = fragments.size

        override fun createFragment(position: Int): Fragment = fragments[position]
    }
}