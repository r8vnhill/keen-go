/*
 * Copyright (c) 2025, Ignacio Slater M.
 * 2-Clause BSD License.
 */

package cl.ravenhill.keen.repr

data class ScalarFeature(val x: Double) : Feature<Double, ScalarFeature> {

    override fun map(f: (Double) -> Double) = copy(x = f(x))

    override fun <T2, F2> flatMap(f: (Double) -> F2) where F2 : Feature<T2, F2> = f(x)
}
