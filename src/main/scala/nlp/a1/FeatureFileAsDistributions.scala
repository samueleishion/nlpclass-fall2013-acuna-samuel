package nlp.a1

import nlpclass.FeatureFileAsDistributionsToImplement
import nlp.a1.ProbabilityDistribution
import nlp.a1.ConditionalProbabilityDistribution
import nlp.a0.Process

object FeatureFileAsDistributions extends FeatureFileAsDistributionsToImplement {
	def main(args: Array[String]) {
		println("\nTesting... ")
		val (labels,pLabel,pFeatureValueGivenLabelByFeature) = 
			FeatureFileAsDistributions.fromFile("data/data2.txt")
		
		println(labels) 

		println(f"p(label=negative) = ${pLabel("negative")}%.2f")
		println(f"p(label=neutral) = ${pLabel("neutral")}%.2f")
		println(f"p(label=positive) = ${pLabel("positive")}%.2f")

		println()
	}

	def fromFile(filename: String): (Set[String], ProbabilityDistribution[String], Map[String, ConditionalProbabilityDistribution[String,String]]) = {
		val lines = Process.textToString(filename).split('\n')
		val categories = Process.countCategories(lines).toMap // Map[String,Int]
		var features = Process.getFeatures(lines) 		// Map[String,Map[String,Map[String,Int]]]

		// Set returning variables
		var set = Set[String]()
		var prob = new ProbabilityDistribution[String](categories,features)
		var map = Map[String,ConditionalProbabilityDistribution[String,String]]()

		// Modify set
		var cat = ""
		for(cat <- categories.keys) {
			set = set + cat
		}
		set.toSeq.toSet

		// Modify prob

		return (set, prob, map)
	}
}