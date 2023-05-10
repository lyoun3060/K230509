//최상위 영역
// java에서는 int num = 1;형식으로 썻으나
// 코틀린에서는 val(or var) 변수명 : 타입 = 값;
val num : Int = 1; //해당 함수 사용하면 푸른색, 사용안하면 회색
//우리가 왜 IDE를 사용하나 -> 편의성, 기본적인 문법체크해줌, (문법을 다 외울려고 안했으면 좋겠다./ 최소한의 기본문법 정도 알고진행 ㄱㄱ), 일단 자주 많이 사용하는거 부터 하자
//val num2 : String; //<-선언만해서 오류가 났음
//최상위 영역에서는 선언만 하면 오류가 난다, 일단 ide문법 체크를 최대한 이용하자.


class Test {
}

fun main(){

    var data9: Any = 5;
    when(data9){
        is String -> println("data9의 값은 문자열 : $data9")
        //is의 뜻은 해당 타입이 맞는지 확인하는 것.
        10 -> println("data9의 값은 10")
        in 1..10 ->println("data9의 값은 숫자 : $data9")
        else -> {
            println("data9의 값은 ??")
        }
    }

    var data8 ="abc"
    when(data8){
        "10" -> println("data8의 값은 10")
        "abc" -> println("data8의 값은 abc")
        else -> {
            println("data8의 값은 ??")
        }
    }

    var data7 = 40;
    when(data7){
        10 -> println("data7의 값은 10")
        20 -> println("data7의 값은 20")
        else -> {
            println("data7의 값은 ??")
        }
    }


    var data5 = 10;
    var data6 = if(data5>0){
        println("표현식 확인.")
        30
    }else{

    }
    println("data6 : $data6")


    var map = mapOf<String, String>(Pair("one", "hello"), "two" to "2")
    println("""
        map size : ${map.size}
        map data : ${map.get("one")}, ${map.get("two")}
    """.trimIndent()) //trimIndent는 공백을 제거해주는 것 """쓰면 자동으로 들어감

    var mL = mutableListOf<Int>(1,2,3)
    mL.add(3, 100) //[3]에 값을 100으로 넣음 / 가변형식이라 변경 가능
    println("""
        mL size : ${mL.size}
        mL data 인덱스 3 : ${mL[3]}
    """.trimIndent())

    var list = listOf<Int>(1,2,3) //<-불변의 리스트라서 변경불가
    //list[0] = 100 //<-해주면 오류,
    println("""
        list size : ${list.size}
        list data : ${list[0]}, ${list.get(1)}
    """.trimIndent())

    val data4 = intArrayOf(1,2,3)
    val data3 = arrayOf<Int>(1,2,3)
    //아래의 것을 다르게 적는방법

    val data2 : IntArray = IntArray(3, {0}) //기초타입 배열(int, long, boolean 등등)
    data2[0] = 100
    println("data2[0]의 값 ${data2[0]}")

    //배열 -> collection이라고 해서 list, map등이 있다.
    // 자바 : (동일한) 데이터 값을 할당함.
    // 코틀린 : 자바기반, 자바와 동일
    // 자바 스크립트 : (여러가지)의 데이터 타입값들을 할당

    val data1 : Array<Int> = Array(3, {0})
    data1[2] = 20
    //Array(배열의 갯수, 초기값) / 제너릭<>쓰는 이유  안정성을 위해 / 기본적으로 배열은 한번 선언하고 나면 불변함
    //람다식 문법 : { 매개변수 -> 실행될 문장 } / {}안의 문장 = 실행될 문장
    //람다식에서 매개변수가 없거나 1개이면, (화살표 , 매개변수) 생략
    println("data1의 값 조회 : ${data1[0]}")
    println("data1[2]의 값 조회 : ${data1[2]}")


    //함수의 매개변수에서는 var, val선언하지 말자 why?
    //자동으로 val가 들어가 있습니다.
//    fun sum3(var no:Int,val no2:Int){ //sum에도 Unit생략되어있음
//        val result = no + no2
//        println("no+no2=$result")
//    }

    fun some1():Nothing? {
        //Nothing의 뜻 = null이나 예외를 반환하는 함수(?없어서 null 불허용)
        //Nothing뒤에 ?의 뜻 = null을 허용하겠다라는 뜻
        return null
    }

    val num3 : Any = "김수장" //Any는 모든 타입 가능
    fun sum2(no:Int, no2:Int){
        val result = no + no2
        println("no+no2=$result")
    }
    sum2(no2 = 20, no = 10)  // 순서를 바꿔서 적어도 상관없음
    sum2(10, 20) // 순서 맞춰서 적어야함

    fun sum(no: Int):Int{
        //타입이 추론이 되면, 생략가능
        //sum함수에 no라는 Int의 매개변수 이용
        var sum = 0
        for(i in 1..no){
            //in 이후로는 범위를 알려줌
            sum += i
        }
        return sum

    }
    val result = sum(no = 10)
    println("result : $result")

    println("hi android")
    println("num의 값은 : $num")
}