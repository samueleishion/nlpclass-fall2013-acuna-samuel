// package nlp.a2

// // import nlp.a0.Process
// import nlp.a1.FeatureFileAsDistributions
// import nlp.a2.UnsmoothedNaiveBayesTrainer

// object Test {
// 	def main(args: Array[String]) {
// 		if(args.length>0) {
// 			args(0) match {
// 				case "-test" =>
// 					test("\nTesting...",args(1))
// 			}
// 		}
// 	}

// 	def test(title: String,filename: String) = {
// 		println(title)

// 		// Get Vectors for UnsmoothedNaiveBayesTrainer
// 		val file = Process.textToString(filename).split('\n')
// 		val cats = Process.countCategories(file) 
// 		val instances = Process.getFeaturesToVectors(file)

// 		// Getting Sets, Maps, and distributions for NaiveBaysModel
// 		val model = FeautreFileAsDistributions.fromFile(filename) 

// 		// Processing BayesModel
// 		val nbt = new UnsmoothedNaiveBayesTrainer[String,String,String]()
// 		val nbt = new nbt.train(instances) 

// 		// println("==========")
// 		// println(cats)
// 		// println("==========")
// 		// println(feats)
// 		// println("==========")
// 		// println(feats.groupBy(_._1).mapValues(_.map(_._2)))


// 		// println(file.deep.mkString("\n")) 


// 		// val instances = ...from tennis training file... // Vector[(Label, Vector[(Feature,Value)])]
// 		// val nbt = new UnsmoothedNaiveBayesTrainer[String,String,String](...); 
// 		// val nbm = new nbt.train(instances); 
// 		// nbm.predict(Vector("Outlook"->"Sunny","Temperature"->"Cool","Humidity"->"High","Wind"->"Strong")); 
// 		// nbm.predict(Vector("Outlook"->"Overcast","Temperature"->"Cool","Humidity"->"Normal","Wind"->"Weak")); 
// 	} 
// }