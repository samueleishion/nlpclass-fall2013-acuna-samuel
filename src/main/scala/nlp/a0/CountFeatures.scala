package nlp.a0

import nlp.a0.Process

object CountFeatures {
	def main(args:Array[String]) = {
		if(args(0)==None) 
			throw new Exception("Please enter a proper file")
		
		val lines = Process.textToString(args(0)).split('\n')
		val categories = Process.countCategories(lines)
		val features = Process.getFeatures(lines)

		var cat = ""
		var feat = ""
		var value = ""

		for(cat <- categories.keys) {
			println("%s\t%d".format(cat,categories(cat))) 
		}
		println("\n")

		for(feat <- features.keys) {
			println(feat)
			for(cat <- features(feat).keys) {
				println("\t%s".format(cat))
				for(value <- features(feat)(cat).keys) {
					var tab = "\t\t"
					if(value.length > 7) tab = "\t"
					println("\t\t%s%s%d".format(value,tab,features(feat)(cat)(value)))
				}
			}
		}
	}
}