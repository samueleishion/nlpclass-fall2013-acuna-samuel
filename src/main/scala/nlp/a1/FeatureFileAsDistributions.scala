package nlp.a1

import nlpclass.FeatureFileAsDistributionsToImplement
import nlp.a1.ProbabilityDistribution
import nlp.a1.ConditionalProbabilityDistribution
import nlp.a0.Process

object FeatureFileAsDistributions extends FeatureFileAsDistributionsToImplement {
	def fromFile(filename: String): (Set[String], ProbabilityDistribution[String], Map[String, ConditionalProbabilityDistribution[String,String]]) = {
		val set = Set[String]()
		val prob = new ProbabilityDistribution[String]()
		val map = Map[String,ConditionalProbabilityDistribution[String,String]]()

		return (set, prob, map); 
	}
}