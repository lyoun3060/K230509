package com.example.test13

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import com.example.test13.databinding.ActivityAnrKoroutineBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.launch
import kotlin.concurrent.thread
import kotlin.system.measureTimeMillis

class ANR_KoroutineActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityAnrKoroutineBinding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.button.setOnClickListener {
//        //밑의 해결방법 사용하기전에 ANR을 보기위한 구문(ANR유발시키는 구문)
//            var sum = 0L
//            var time = measureTimeMillis {
//                for (i in 1..10_000_000_000) {
//                    sum += i
//                }
//            }
//            Log.d("KSJ", "time : $time")
//            binding.resultView.text = "sum : $sum"


            //(방법1)
            //ANR을 해결하기위한 핸들러 스레드비동기 처리
//            val handler=object: Handler(){
//                override fun handleMessage(msg: Message) {
//                    super.handleMessage(msg)
//                    binding.resultView.text = "sum : ${msg.arg1}"
//                }
//            }
//
//            thread {
//                var sum = 0L
//                var time = measureTimeMillis {
//                    for (i in 1..10_000_000_000) {
//                        sum += i
//                    }
//                    val message = Message()
//                    message.arg1=sum.toInt()
//                    handler.sendMessage(message)
//                }
//                Log.d("KSJ", "time : $time")
//            }



            //(방법2)
            // 코루틴으로 비동기 처리 //결과는 거의 비슷함
            //앞으로 계속지원하는 방법임, 권장
            //이미 많은 API 등에서 채택중 그래서 API등을 이용시 비동기 처리방식으로 빠른성능을 볼수 있음
            val channel = Channel<Int>()

            val backgroundScope = CoroutineScope(Dispatchers.Default + Job())
            backgroundScope.launch {
                var sum = 0L
                var time = measureTimeMillis {
                    for (i in 1..2_000_000_000) {
                        sum += i
                    }
                }
                Log.d("KSJ", "time : $time")
                channel.send(sum.toInt())
            }

            val mainScope= GlobalScope.launch(Dispatchers.Main) {
                channel.consumeEach {
                    binding.resultView.text = "sum : $it"
                }
            }



        }
    }
}