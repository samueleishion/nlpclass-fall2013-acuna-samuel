package nlp.a0

import nlpclass.NGramCountingToImplement 
import nlp.a0.Process 

class NGramCounting(n: Int) extends NGramCountingToImplement {
	def countNGrams(ngrams: Vector[String]): Map[Vector[String],Int] = {
		var map = scala.collection.mutable.Map[Vector[String],Int]()

		for(token <- ngrams.sliding(n)) {
			if(map.contains(token)) {
				map(token) += 1
			} else {
				map += (token -> 1)
			}
		}

		val out = Map(map.toList:_*)
		return out 
	}
}