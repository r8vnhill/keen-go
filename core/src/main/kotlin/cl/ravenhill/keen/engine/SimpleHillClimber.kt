/*
 * Copyright (c) 2025, Ignacio Slater M.
 * 2-Clause BSD License.
 */

package cl.ravenhill.keen.engine

import cl.ravenhill.keen.repr.ScalarFeature

class SimpleHillClimber(
    override val objectiveFunction: (ScalarFeature) -> Double,
    private val stepSize: Double = 0.1,
    private val maxIterations: Int = 100
) : OptimizationEngine<ScalarFeature> {

    override fun optimize(initialState: ScalarFeature): ScalarFeature {
        var current = initialState
        var bestScore = objectiveFunction(current)

        repeat(maxIterations) {
            val candidate = current.map { it + stepSize }
            val candidateScore = objectiveFunction(candidate)
            if (candidateScore > bestScore) {
                current = candidate
                bestScore = candidateScore
            } else {
                return current // No improvement → local optimum
            }
        }

        return current
    }
}

fun main() {
    val engine = SimpleHillClimber(objectiveFunction = { -(it.x * it.x) + 4 })
    val result = engine.optimize(ScalarFeature(x = 0.0))
    println("Best x: ${result.x}") // Should converge toward 0.0
}
