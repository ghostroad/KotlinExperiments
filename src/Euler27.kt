import com.mushfeq.utils.Primes


fun primeSequenceLength(a: Int, b: Int): Int? = generateSequence(0) { it + 1 }.find (fun(n:Int):Boolean {
    val result = (n*n) + (a*n) + b
    return result < 2 || !Primes.isPrime(result.toLong())
})

fun main(args: Array<String>) {
    println((-999..999).flatMap { a -> (-1000..1000).map{b -> Pair(a, b)} }.maxBy { (a, b) -> primeSequenceLength(a, b) ?: 0})
}