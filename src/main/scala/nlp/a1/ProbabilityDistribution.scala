package nlp.a1

import nlpclass.ProbabilityDistributionToImplement
import nlp.a0.Process

class ProbabilityDistribution[B](labels: Map[B,Int],features: Map[B,Map[B,Map[B,Int]]]) extends ProbabilityDistributionToImplement[B] {
	def apply(x: B): Double = {
		val total = labels.foldLeft(0)(_+_._2) 
		val label = labels(x)
		// val out = (label/total) - ((label/total)%0.01)
		return label/total
	}

	def sample(): B = {
		val b: B = null.asInstanceOf[B]
		return b
	}
}