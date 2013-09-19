package nlp.a1

import nlpclass.ConditionalProbabilityDistributionToImplement
import nlp.a0.Process

class ConditionalProbabilityDistribution[A,B](features: Map[A,Map[B,Int]]) extends ConditionalProbabilityDistributionToImplement[A,B] {
	def apply(a: B, given: A): Double = {
		var feature = 0.0; 
		if(features.contains(given)) {
			val total = features(given).foldLeft(0)(_+_._2).toDouble
			
			if(features(given).contains(a)) 
				feature = features(given)(a).toDouble
			
			return feature/total
		} else return 0.0
	}

	def sample(given: A): B = {
		val b: B = null.asInstanceOf[B]	

		val random = scala.util.Random.nextDouble 
		var probs = collection.mutable.Map[B,Double]()
		var label: B = null.asInstanceOf[B]
		var sum = 0.0
		if(features.contains(given)) {
			for(label <- features(given).keys) {
				probs += (label -> apply(label,given))
				sum += probs(label) 
				if(sum>random) return label
			}
		} 

		return b
	}
}