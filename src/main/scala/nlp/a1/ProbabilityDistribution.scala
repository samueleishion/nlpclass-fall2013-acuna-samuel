package nlp.a1

import nlpclass.ProbabilityDistributionToImplement
import nlp.a0.Process

class ProbabilityDistribution[B](labels: Map[B,Int]) extends ProbabilityDistributionToImplement[B] {
	def apply(x: B): Double = {
		val total = labels.foldLeft(0)(_+_._2).toDouble
		var label = 0.0; 
		if(labels.contains(x)) label = labels(x).toDouble

		return label/total
	}

	def sample(): B = {
		val b: B = null.asInstanceOf[B]

		val random = scala.util.Random.nextDouble 
		var probs = collection.mutable.Map[B,Double]()
		var label: B = null.asInstanceOf[B]
		var sum = 0.0
		for(label <- labels.keys) {
			probs += (label -> apply(label))
			sum += probs(label) 
			if(sum>random) return label
		}

		return b
	}
}