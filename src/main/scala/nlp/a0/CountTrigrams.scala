package nlp.a0

import nlp.a0.Process
import nlp.a0.NGramCounting
import scala.collection.immutable.ListMap

object CountTrigrams {
	def main(args: Array[String]) {
		if(args(0)==None)
			throw new Exception("Please enter a proper file.")

		val ngrams = processBook(args(0))

		val i = 0
		for(i <- 0 to 9) {
			var str = ngrams(i)._1.mkString(" ")
			var tab = "\t\t"
			if(str.length > 15)
				tab = "\t" 
			else if(str.length < 8)
				tab = "\t\t\t"
			println("%s%s %d".format(str,tab,ngrams(i)._2))
		}

	}

	def processBook(text: String): Vector[(Vector[String],Int)] = {
		val bookStr = Process.textToString(text)
		val bookWords = Process.filterText(bookStr).toVector
		val ngrams = new NGramCounting(3).countNGrams(bookWords)

		val list = ListMap(ngrams.toList.sortBy{_._2}:_*)
		val output = list.toVector.reverse
		return output
	}
}