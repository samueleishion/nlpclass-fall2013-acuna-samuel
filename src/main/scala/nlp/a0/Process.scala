package nlp.a0

import scala.io.Source
import scala.collection.mutable.Map
import scala.collection.immutable.ListMap
import scala.collection.immutable.IndexedSeq
import scala.collection.immutable.ListMap 

object Process {

	def textToString(book: String) : String = {
		val source = Source.fromFile(book) 
		val string = source.getLines mkString "\n"
		source.close() 
		return string 
	}

	def filterText(text: String) : Array[String] = {
		var string = text.replaceAll("[^A-Za-z]"," ")
		string = string.replaceAll(" +"," ")
		string = string.toLowerCase()
		val words = string.split(" ") 
		return words
	}

	def countUniqueFrequencies(words: Array[String]) : IndexedSeq[(String,Int)] = {
		var map:Map[String,Int] = Map()

		var i = 0
		for(i <- 0 to words.length-1) {
		  if(map.contains(words(i))) {
		    map(words(i)) += 1
		  } else {
		    map += (words(i) -> 1)
		  }
		}

		val list = ListMap(map.toList.sortBy{_._2}:_*).toIndexedSeq.reverse

		return list 
	}

	def countUniqueFrequenciesWithStop(words: Array[String], stops: Array[String]) : IndexedSeq[(String,Int)] = {
		var map:Map[String,Int] = Map()

		var i = 0
		for(i <- 0 to words.length-1) {
		  if(map.contains(words(i))) {
		    map(words(i)) += 1
		  } else {
		    if(!stops.contains(words(i))) {
		      map += (words(i) -> 1)
		    }
		  }
		}

		return ListMap(map.toList.sortBy{_._2}:_*).toIndexedSeq.reverse
	}

	// def countFrequencyFrequencies(freqs: IndexedSeq[(String,Int)]) : Array[Int] = {
		// val largest: Int = freqs.head._2.asInstanceOf[Int]

		// val map:Map[Int,Int] = Map()

		// var seq = ("",0)
		// for(seq <- freqs) {
		// 	if(map.contains(freqs._1)) {
		// 		map(freqs._1) += 1
		// 	}
		// }

		// return 0
	// }

	def countFrequencyFrequencies(freqs: IndexedSeq[(String,Int)]) : IndexedSeq[(Int,Int)] = {
		val map:Map[Int,Int] = Map()

		var i = 0
		for(i <- 0 to freqs.length-1) {
			var v = freqs(i)._2 
			if(map.contains(v)) {
				map(v) += 1
			} else {
				map += (v -> 1)
			}
		}

		return ListMap(map.toList.sortBy{_._2}:_*).toIndexedSeq.reverse
	}

	def pluralOrSingular(singularForm: String, quantity: Int) : String = {
		if(quantity>1) 
			return singularForm+"s" 
		return singularForm 
	}

	def conjugate(verb: String, given: String) : String = {
		if(given.substring(given.length()-1)=='s') 
			return verb
		return verb+"s"
	}
}