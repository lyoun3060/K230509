//최상위 영역
// java에서는 int num = 1;형식으로 썻으나
// 코틀린에서는 val(or var) 변수명 : 타입 = 값;
val num : Int = 1; //해당 함수 사용하면 푸른색, 사용안하면 회색
//우리가 왜 IDE를 사용하나 -> 편의성, 기본적인 문법체크해줌, (문법을 다 외울려고 안했으면 좋겠다./ 최소한의 기본문법 정도 알고진행 ㄱㄱ), 일단 자주 많이 사용하는거 부터 하자
//val num2 : String; //<-선언만해서 오류가 났음
//최상위 영역에서는 선언만 하면 오류가 난다, 일단 ide문법 체크를 최대한 이용하자.


class Test {
}

class User3 (name:String){ //선언부
    //본문
    //주 생성자 생략 ->디폴트 생성자를 만들어줌.
    //보조 생성자를 이용

    //문제점 -> 주 생성자와, 보조 생성자가 같이 있는경우, (해결책= 보조생성자에서, 주 생성자로 연결하는 부분이 필요)
    //this
    //결론 = 보조생성자를 이용하면 되지만, 주 생성자를 이용해라라는 말임 / 작업할때도 주생성자에서 작업을 더 많이함 /
    constructor(name: String, count: Int) : this(name){

    }
}

class User2(val name: String, count:Int){
    //클래스 매개변수에 val/var을 지정해주면 전역변수가 됨
    //주생성자는 class 이름 옆에선언이 되고, constrictor생략을 많이함

    // val name = "KSJ" /<-여기서 사용하는것은 지역변수

    init {
        //init 함수 안에서는 주 생성자의 매개변수를 사용가능.
        //하지만, init사용시 class 내부에서 멤버변수로서는 사용이 불가능
        //User2클래스 호출시 무조건 init함수는 사용됨
        println("init 호출, 주생성자 매개변수 사용: $name") //<-여기서 쓰는 $name는 주 생성자의 매개변수사용
    }
    fun someFun(){
        println("init 호출, 주생성자 매개변수 사용: $name") //<-여기서 쓰는 $name는 클래스 내부의것 사용,/클래스 매개변수를 전역변수를 지정하면 여기서도 사용가능
    }
}

class User{ //여기서는 주생성자가 생략이 되어있음, 보조 생성자를 사용한것임
    //주 생성자라는 것은 클래스 선언할때 선언함

    var name = "KSJ"
    //생성자(constructor) = 초기화하는 역할
    //코틀린에서는 생성자 키워드 constructor가 존재함
    constructor(name : String){
        //보조 생성자는 클래스 내부에 선언
        this.name = name
    }
    fun someFun(){
        println("someFun의 name: $name")
    }
}

//코틀린에서는 기본적으로 상속X ->open상속가능하게하는 키워드
open class Super{ //부모클래스
    open var superData = 10
    protected var protectedData = 20
    //ㄴ접근 지정자 protected만 확인
    //결론
    open fun superFun(){
        println("superFun 호출됨")
    }
}

//부모것을 사용하려면반드시 초기화 필요

//Super() 주생성자 호출해야함
//생성자 = 초기화를 하겠다.
//class Sub :Super(){ //자식클래스
//
//}
//

//부모의 것을 값을 바꿔서 사용하겠다.
class Sub : Super(){
    //부모의 멤버를 다시 재정의 해서 사용하려는데 안됨 Why?
    //이유 : 키워드
    override var superData = 20

    override fun superFun(){
        protectedData++ //자식 클래스에서는 protected 접근 가능하나/ 함수에서는 불가
        println("재정의")

    }
}

//비 data class
//data라고 안쓰면 주소값이 나옴
class NonDataClass(val name: String, val pw : String){

}

//data class ->실제 값을 비교해 주는 변수는, 주생성자의 변수만 해줌(내부에 변수는 값비교 못함)
//data라고 쓰면 실제값이 나옴
data class DataClass(val name: String, val pw : String, val email : String){
//    lateinit var email:String
    //lateinit = 초기화를 늦게해도 되는
//    constructor(name:String, pw: String, email:String):this(name, pw){
//        this.email = email
//    }
}

open class Super2(){
    open var data = 10
    open fun some()
    {
        println("i am Super2 : $data")
    }
}

//타입 지정을 안하면 Any가 기본값
val obj = object : Super2() {
    override var data = 20
    override fun some()
    {
        println("i am Super2 Sub : $data")
    }

}

class companionClassTest{
    //자바의 static 키워드와 동일한 기능
    //멤버에 접근시, 클래스명에 점을 찍고 접근함
    companion object{
        var data= 10
        fun some(){
            println("companion에서 data의 값: $data")
        }
    }
}

//고차함수 사용예제
//고차함수는 : 매개변수 또는 결과 값 자리에 함수가 들어가는 형태
fun testH(arg:(Int)->Boolean):()->String{
    val result = if(arg(10)){
        "valid"
    }else{
        "invalid"
    }
    return {"testH result 확인 : $result"}
}

fun main(){

    //고차함수 사용예제
    val result15= testH({no -> no>0})
    println("result15의 값 조회 : $result15")

    val some2 = {no1:Int,no2:Int ->println("출력")
    no1*no2
    }
    println("익명함수 출력 확인: some2(1,2) : ${some2(1,2)}")

    // 매개변수가 1개인 람다식(익명함수), it으로 대체하기
    // 컴파일러가 자동으로 (자바문법->코틀린문법)으로 대체할때 it으로 사용
    // 자동변환시 매개변수가 1개인 경우 it으로 바로 대체함
    //request, 응답객체(response) 응답 객체 하나만 가리킬때에도 it을 많이 사용함
    val result3 = {no1:Int -> println("no1의 값은 $no1")}
    val y = result3(3545)

    val result4 : (Int)->Unit = {println(it)}
                  //여기 (Int)는 3545를가리킴
                        //Unit은 {}안에 값을 가리킴
                           //ㄴ> 만약 Int로 하고싶은경우 {}
    val aa = result4(3545)

    //변수에서 데이터 타입이 있듯이, 함수도 타입이 있음
    //익명 클래스 만들때, object
    //클래스 선언부 뒤에 생략(Any)
    //함수도 결과값의 타입을 생략한다면 -> Unit(=void)
    //익명 함수이지만, 이것의 타입을 명시


    //일반함수
    fun some(no1:Int, no2:Int):Int{
        return no1+ no2
    }

    //람다식 = 익명함수/ 중괄호{} 안에 (왼쪽= 매개변수/ 오른쪽 = 수행할 문장)
    val result2 = {no1:Int,no2:Int -> no1+ no2}
    val x = result2(1,2)
    println("x의 값은 : $x")



    companionClassTest.data = 100
    companionClassTest.some()

    //object 익명 클래스 사용 예제
    obj.data
    obj.some()

    //실제 값이 아닌, 메모리 주소값 비교 부분
    val none1 = NonDataClass("ksj", "1234")
    val none2 = NonDataClass("ksj", "1234")
    println("none1의 주소값 : $none1")
    println("none2의 주소값 : $none2")
    println("equals 이용한 값비교 : ${none1.equals(none2)}")

    //data클래스 실제 값 비교
    val data13 = DataClass("ksj", "1234", "email1")
    val data14 = DataClass("ksj", "1234", "email2")
    println("data13의 값, 주소값은 X : $data13")
    println("data14의 값, 주소값은 X : $data14")
    println("equals 이용한 값비교 : ${data13.equals(data14)}")




    val obj = Sub()
    println("obj.superData의 값: "+obj.superData)
    obj.superFun()
//    obj.protectedData()
//    protected는 자식에서만 접근가능


    val user2 = User2("ksj",10)
    //객체생성, 인스턴스 생성
    //java -> User user = new User("ksj");
    val user = User("김수장")
    println("User의 이름은 : "+user.name)
    user.someFun()

    //(while문=true/false에 많이 사용) / (for문=범위에 많이 사용)
    var data12 = arrayOf<Int>(1,2,3)
    for((index, value) in data12.withIndex()){ //(indices = index / 컬렉션 타입의 인텍스)
        print("인덱스의 값 = ")
        print(index)
        print(" 밸류의 값 = ")
        print(value)
        if(index !==data12.size-1)
            print(" , ")

    }

//     for (i in 1..10) { ... } → 1부터 10까지 1씩 증가
//     for (i in 1 until 10) { ... } → 1부터 9까지 1씩 증가(10은 미포함)
//     for (i in 2..10 step 2) { ... } → 2부터 10까지 2씩 증가
//     for (i in 10 downTo 1) { ... } → 10부터 1까지 1씩 감소

    fun sum10():Int {
        val result = 0
        for (i in 10 downTo 1) {
            val sum = 0
            val result = sum + i
            println("result의 값은 : $result")
        }
        return result
    }
    sum10()

    var data11 = arrayOf<Int>(1,2,3)
    for(i in data11.indices){ //(indices = index / 컬렉션 타입의 인텍스)
        print(data11[i])
        if(i !==data11.size-1) {
            print(" , ")
        }
    }


    var data10 = 5
    val result10 = when{
        data10 <10 -> "data10 < 10"
        else -> {
            "data10의 값은 ??"
        }
    }
    println("data10의 조건으로 result10 출력하기 : $result10")


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