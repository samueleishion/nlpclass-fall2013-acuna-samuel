package nlp.a1

import nlpclass.FeatureFileAsDistributionsToImplement
import nlp.a1.ProbabilityDistribution
import nlp.a1.ConditionalProbabilityDistribution
import nlp.a0.Process

import dhg.util.CollectionUtil._ // importing to test samples

object FeatureFileAsDistributions extends FeatureFileAsDistributionsToImplement {
	def main(args: Array[String]) {
		if(args.length>0) {
			args(0) match {
				case "-test" =>
					test("\nTesting...")
			}
		}
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

	def test(title: String) = {
		println(title)
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

		// Setup data to test required methods 
		val lines = Process.textToString("data/data2.txt").split('\n')
		val categories = Process.countCategories(lines).toMap
		val features = Process.getFeatures(lines) 
		var map = collection.mutable.Map[String,Map[String,Int]]()
		var feat = "word"
		var value = ""
		for(value <- features.get(feat).get.keys) { map += (value -> features.get(feat).get(value)) }
		val cond:collection.immutable.Map[String,Map[String,Int]] = map.toMap
		val pd = new ProbabilityDistribution[String](categories) 
		val cpd = new ConditionalProbabilityDistribution[String,String](cond)
		
		// These outputs test apply
		println(pd("Yes")) 
		println(pd("negative"))
		println(pd("positive")) 
		println()

		println(cpd("hello","Yes"))
		println(cpd("worst","negative"))
		println(cpd("bad","negative")) 
		println(cpd("best","positive"))
		println()

		// These outputs test samples
		println(Vector.fill(1000)(pd.sample).counts)
		println(Vector.fill(1000)(pd.sample).counts)
		println(Vector.fill(1000)(pd.sample).counts)
		println()

		println(Vector.fill(1000)(cpd.sample("positive")).counts)
		println(Vector.fill(1000)(cpd.sample("positive")).counts)
		println(Vector.fill(1000)(cpd.sample("negative")).counts)
		println(Vector.fill(1000)(cpd.sample("neutral")).counts)
		println() 
	}
}