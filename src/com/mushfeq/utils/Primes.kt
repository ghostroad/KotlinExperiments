package com.mushfeq.utils

object Primes {

    fun isPrime(n: Long): Boolean {
        return isPrimeCache.getOrPut(n) { hasNoDivisorsIn(asSequence(), n) }
    }

    fun asSequence(): Sequence<Long> = object : Sequence<Long> {
        override fun iterator(): Iterator<Long> {
            return PrimesIterator()
        }

    }

    private val primeSequenceCache = mutableListOf(2L, 3L, 5L)

    private val isPrimeCache = mutableMapOf(1L to false, 2L to true, 3L to true, 4L to false, 5L to true)

    private fun sqrt(n: Long): Long = kotlin.math.floor(kotlin.math.sqrt(n.toDouble())).toLong()

    private fun hasNoDivisorsIn(seq: Sequence<Long>, n: Long) = seq.takeWhile { it <= sqrt(n) }.all { n % it != 0L }

    private class PrimesIterator : Iterator<Long> {
        var pos = 0

        override fun hasNext(): Boolean {
            return true
        }

        override fun next(): Long {
            val result: Long
            if (pos < primeSequenceCache.size) {
                result = primeSequenceCache[pos]
            } else {
                result = findNextPrime()
            }
            pos++
            return result
        }

        private fun findNextPrime(): Long {
            var n = primeSequenceCache.last() + 1
            while (true) {
                if (hasNoDivisorsIn(primeSequenceCache.asSequence(), n)) {
                    primeSequenceCache.add(n)
                    isPrimeCache[n] = true
                    return n
                } else {
                    isPrimeCache[n] = false
                }
                n++
            }
        }

    }

}