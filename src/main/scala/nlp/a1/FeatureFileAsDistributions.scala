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
		
		// These outputs should match the instructions page
		println(labels) 
		println()

		println(f"p(label=negative) = ${pLabel("negative")}%.2f")
		println(f"p(label=neutral) = ${pLabel("neutral")}%.2f")
		println(f"p(label=positive) = ${pLabel("positive")}%.2f")
		println()

		val featureNeg = pFeatureValueGivenLabelByFeature("neg")
		println(f"p(neg=bad | label=negative) = ${featureNeg("bad","negative")}%.2f")
		println()

		val featurePos = pFeatureValueGivenLabelByFeature("pos")
		println(f"p(pos=best | label=negative) = ${featurePos("best","negative")}%.2f")
		println(f"p(pos=best | label=positive) = ${featurePos ("best","positive")}%.2f")
		println()

		val featureWord = pFeatureValueGivenLabelByFeature("word")
		println(f"p(word=best | label=negative) = ${featureWord("best","negative")}%.2f")
		println(f"p(word=best | label=positive) = ${featureWord("best","positive")}%.2f")
		println()

		// These outputs should return zeros
		println(f"p(word=error | label=negative) = ${featureNeg("error","negative")}%.2f") 
		println(f"p(word=best | label=error) = ${featurePos("best","error")}%.2f") 
		println(f"p(word=error | label=error) = ${featureWord("error","error")}%.2f") 
		println()
	}

	def fromFile(filename: String): (Set[String], ProbabilityDistribution[String], Map[String, ConditionalProbabilityDistribution[String,String]]) = {
		val lines = Process.textToString(filename).split('\n')
		val categories = Process.countCategories(lines).toMap // Map[String,Int]
		var features = Process.getFeatures(lines) 		// Map[String,Map[String,Map[String,Int]]]

		// Set returning variables
		var set = Set[String]()
		var prob = new ProbabilityDistribution[String](categories)
		var map = collection.mutable.Map[String,ConditionalProbabilityDistribution[String,String]]()

		// Modify set
		var cat = ""
		for(cat <- categories.keys) {
			set = set + cat
		}
		set.toSeq.toSet

		// Modify map
		var feat = ""
		for(feat <- features.keys) {
			map += (feat -> new ConditionalProbabilityDistribution(features.get(feat).get))
		}
		val cond:collection.immutable.Map[String,ConditionalProbabilityDistribution[String,String]] = map.toMap

		return (set, prob, cond)
	}
}