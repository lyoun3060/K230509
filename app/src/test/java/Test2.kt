class Test2 {
    fun testH(arg:(Int)->Boolean):()->String{
        val result = if(arg(10)){
            "valid"
        }else{
            "invalid"
        }
        return {"testH result 확인 : $result"}
    }


}
fun main(){
    val result1= testH({no -> no>0}) //no는 람다식에서 Int
    println(result1())


    //? null 허용 연산자 및, ?. null허용 변수 호출
    //(?: a) = (엘비스 연산자) = null이 아니면, 아닌값이 호출되고, null이면 지정한 값(여기서는 a)이 할당
    val data : String?= "KSJ"
    println("data의 길이 : ${data?.length ?: 0}") //null허용하겠다고 했으면 ${data}의 무언가를 구하려 할때 ?꼭 사용하자

}