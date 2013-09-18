package nlp.a1

import nlpclass.ProbabilityDistributionToImplement
import nlp.a0.Process

class ProbabilityDistribution[B]() extends ProbabilityDistributionToImplement[B] {
	def apply(x: B): Double = {
		return 0.0
	}

	def sample(): B = {
		val b: B = null.asInstanceOf[B]
		return b
	}
}