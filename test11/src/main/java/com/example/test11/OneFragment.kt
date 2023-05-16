package com.example.test11

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.test11.databinding.FragmentOneBinding

class OneFragment : Fragment() {
    //뷰 바인딩 기법으로 해당 뷰 객체를 선택하기 위한 도구
    //함수 밖에서 선언만 되었고, 실제로 onCreateView 함수로 화면을 그릴때, binding 사용시 할당(초기화) 됨.
    //이런 방식을 많이 사용함
    lateinit var binding: FragmentOneBinding

    //실제로 프래그먼트가 화면에 그려지는 함수
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? { //<-프래그먼트 반환값(View는 최상위 계층,범위가 가장 큰타입으로 지정하여 모두가 사용할 수 있게함)

        //자동생성, FragmentOneBinding
        binding = FragmentOneBinding.inflate(inflater, container, false)
        return binding.root
    }

}