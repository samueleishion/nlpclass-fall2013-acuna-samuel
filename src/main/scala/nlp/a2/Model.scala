package nlp.a2

import nlpclass.NaiveBayesModelToImplement
import nlpclass.NaiveBayesTrainerToImplement
import nlpclass.ProbabilityDistributionToImplement
import nlpclass.ConditionalProbabilityDistributionToImplement
import nlp.a1.FeatureFileAsDistributions
import nlp.a1.ProbabilityDistribution
import nlp.a1.ConditionalProbabilityDistribution
import nlp.a0.Process 

object Test {
	def main(args: Array[String]) {
		if(args.length>0) {
			args(0) match {
				case "-test" =>
					test("\nTesting...",args(1))
			}
		}
	}

	def test(title:String, filename:String) = {
		print(title)

		// Get Vectors for UnsmoothedNaiveBayesTrainer
		val file = Process.textToString(filename).split('\n')
		val instances = Process.getFeaturesToVectors(file)

		// Getting Sets, Maps, and distributions for NaiveBaysModel
		// val model = FeatureFileAsDistributions.fromFile(filename) 

		// Processing BayesModel
		val nbt = new UnsmoothedNaiveBayesTrainer[String,String,String](filename)
		val nbm = nbt.train(instances) 
	}
}

class NaiveBayesModel[Label,Feature,Value](
		labels: Set[Label], 
		pLabel: ProbabilityDistribution[Label], 
		pValue: Map[Feature,
			ConditionalProbabilityDistribution[Label,Value]]
	) 
	extends NaiveBayesModelToImplement[Label,Feature,Value] {

	def predict(features: Vector[(Feature,Value)]): Label = {
		var map = features.groupBy(_._1).mapValues(_.map(_._2))
		// val ProbabilityDistribution
		println(map); 
		val label: Label = null.asInstanceOf[Label]; 

		return label
	}
}

class UnsmoothedNaiveBayesTrainer[Label,Feature,Value](
		filename:String
	) 
	extends NaiveBayesTrainerToImplement[Label,Feature,Value] {

	def train(instances: Vector[(Label, Vector[(Feature,Value)])]): NaiveBayesModel[Label,Feature,Value] = {
		var (labels,pLabel,pFeatureValue) = FeatureFileAsDistributions.fromFile(filename)
		// labels.foreach(e=>e.asInstanceOf[Label])
		return new NaiveBayesModel[Label,Feature,Value](labels,pLabel,pFeatureValue)
	}
}

