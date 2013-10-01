// package nlp.a2

// import nlpclass.NaiveBayesModelToImplement
// import nlpclass.ProbabilityDistributionToImplement
// import nlpclass.ConditionalProbabilityDistributionToImplement
// import nlp.a1.ProbabilityDistribution
// import nlp.a1.ConditionalProbabilityDistribution

// class NaiveBayesModel[Label,Feature,Value](
// 		labels: Set[Label], 
// 		pLabel: ProbabilityDistributionToImplement[Label], 
// 		pValue: Map[Feature,
// 			ConditionalProbabilityDistributionToImplement[Label,Value]]
// 	) 
// 	extends NaiveBayesModelToImplement[Label,Feature,Value] {

// 	def predict(features: Vector[(Feature,Value)]): Label = {
// 		var map = features.groupBy(_._1).mapValues(_.map(_._2))
// 		// val ProbabilityDistribution
// 		println(map); 
// 		val label: Label = null.asInstanceOf[Label]; 

// 		return label
// 	}
// }