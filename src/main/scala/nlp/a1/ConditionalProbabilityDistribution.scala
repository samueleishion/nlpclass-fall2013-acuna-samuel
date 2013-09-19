package nlp.a1

import nlpclass.ConditionalProbabilityDistributionToImplement
import nlp.a0.Process

class ConditionalProbabilityDistribution[A,B](features: Map[String,Map[String,Int]]) extends ConditionalProbabilityDistributionToImplement[A,B] {
	def apply(a: B, given: A): Double = {
		return 0.0
	}

	def sample(given: A): B = {
		val b: B = null.asInstanceOf[B]	
		return b
	}
}