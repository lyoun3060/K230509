package com.example.test11

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.test11.databinding.ActivityMain4Binding
import com.example.test11.databinding.Item342Binding

//참고 코드 : MainActivity342
//리사이클 리뷰가 재료로 다른 액티비티에서 재활용 될 예정
class MainActivity4 : AppCompatActivity() {

//설정(순서2)
//설정이 필요한 부분 (1.뷰 홀더, 2.어댑터, 3.레이아웃/전부 클래스임)

//뷰홀더 만들기
//(item.342.xml의 자동생성된 바인딩 파일 사용)
//여기에 항목은 목록의 구성 요소들의 뷰 객체를 모아 두는곳
//샘플로 텍스트 뷰 한개만 구성됨 Item342Binding
    class MyViewHolder(val binding: Item342Binding) : RecyclerView.ViewHolder(binding.root)

//(어댑터 만들기)
//데이터 연동 -> 현재 임시 데이터 리스트 9개 값을 -> 해당 뷰 홀더의 아이템 요소의 값으로 사용
//나중에, 텍스트, 이미지 등 여러 데이터를 해당 뷰에 할당 작업
//보통 데이터는 API 서버에서 받아온 값( 중간데이터로 JSON 형식으로 받아서, 필요한 욧만 사용할 예정)
//예를들어, 공공데이터, 한아이템의 요소의 값이 10개있으면, 그중에서 4개정도 선탠
//썸네일 이미지1개, 제목1개, 위치1개, 전화번호 1개
//해당 뷰에 하나씩 재할당 함.
    class MyAdapter(val datas:MutableList<String>):RecyclerView.Adapter<RecyclerView.ViewHolder>(){
        //필수적으로 재정의 해야하는 함수들있음
        //다 적어도 되지만, 자동완성으로 해당클래스에서 선택하면됨(자동구현 링크 클릭하기)
        //재정의한 함수는 다 자동호출, 순서는 상관X

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        //MyViewHolder()를 이부분에 정의,

        //실제 xml파일명 : item_342 참조
        //순서는 : 목록(리스트), 구성품(아이템)
        //설계순서는, 큰것 -> 작은것으로 진행
        //개발순서는 반대로 작은것 -> 큰것
            MyViewHolder(Item342Binding.inflate(LayoutInflater.from(parent.context), parent, false))

        override fun getItemCount(): Int {
            Log.d("KSJ", "init datas size 크기 : ${datas.size}")
            return datas.size
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {

            //holder: RecyclerView.ViewHoloder형, 부모클래스 타입
            //holder형변환 ->다운캐스팅 MyViewHolder
            val binding = (holder as MyViewHolder).binding

            //뷰에 데이터 출력 : 아이템의 하나의 요소, 어댑터는 아이템을 해당뷰에 연결해주는것
            binding.itemData.text = datas[position]

            //뷰에 이벤트 추가, itemRoot = 리사이클러 뷰그룹 레이아웃
            // 여기부터 로그까지는 사실상 옵션임.
            binding.itemRoot.setOnClickListener{

//            어댑터 클래스는, 액티비티 컴포넌트 클래스가 아니라서, 이것을 사용하려면, 부모님 클래스에서 상속받아 사용해야합니다., 일단 로그캣출력으로 대체
//            Toast.makeText(this@MyAdapter, "메세지 요소 인덱스 :$position", Toast.LENGTH_SHORT).show()

                Log.d("KSJ", "클릭시 이벤트 발생, 해당 아이템의 요소: $position")

            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMain4Binding.inflate(layoutInflater)
        setContentView(binding.root)

        //임시데이터(순서1)
        val datas = mutableListOf<String>()
        for(i in 1..9){
            datas.add("Item $i")
        }

        //설정이 적용이 되는 부분(순서3)

        //출력을 위한 목록의 틀이  필요함 -> activity_main4 여기에 아이디를 추가
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        //틀에 해당 어댑터 붙이기
        binding.recyclerView.adapter = MyAdapter(datas)

        //옵션, 아이템 간의 구분선을 넣는 옵션
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(this, LinearLayoutManager.VERTICAL)
        )
    }
}
