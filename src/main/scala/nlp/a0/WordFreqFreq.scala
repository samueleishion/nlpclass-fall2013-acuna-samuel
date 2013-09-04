package nlp.a0

import nlp.a0.Process

object WordFreqFreq {
	def main(args: Array[String]) {
		if(args(0)==null) {
			println("Please enter a proper file.") 
			throw new Exception("Please enter a proper file.")
		} else {
			val bookStr = Process.textToString(args(0))
			val bookWords = Process.filterText(bookStr)

			println("Top 10 most frequent frequencies:") 
			val bookFreqs = Process.countUniqueFrequencies(bookWords)
			val freqFreqs = Process.countFrequencyFrequencies(bookFreqs) 
			var i = 0
			for(freq <- freqFreqs) { 
				var words = Process.pluralOrSingular("word",freq._2)
				var times = Process.pluralOrSingular("time",freq._1)
				var appear = Process.conjugate("appear",words)
				if(i < 10) {
					println("%d %s %s %d %s".format(freq._2,words,appear,freq._1,times))
				}
				i += 1
			}

			println("\nBottom 5 most frequent frequencies:")
			val lim = freqFreqs.length-1
			i = 0
			for(freq <- freqFreqs) {
				var words = Process.pluralOrSingular("word",freq._2)
				var times = Process.pluralOrSingular("time",freq._1)
				var appear = Process.conjugate("appear",words)
				if(i >= lim-5 && i <= lim) {
					println("%d %s %s %d %s".format(freq._2,words,appear,freq._1,times))
				}
				i += 1
			}
		}
	}
}
