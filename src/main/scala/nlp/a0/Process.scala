package nlp.a0

import scala.io.Source
import scala.collection.mutable.Map
import scala.collection.immutable.ListMap
import scala.collection.immutable.IndexedSeq
import scala.collection.immutable.ListMap 

object Process {

	// Turns text into a string. 
	def textToString(book: String) : String = {
		val source = Source.fromFile(book) 
		val string = source.getLines mkString "\n"
		source.close() 
		return string 
	}

	// Filters punctuation and numbers on a String and 
	// turns it into an Array of Strings split by lines
	def filterText(text: String) : Array[String] = {
		var string = text.replaceAll("[^A-Za-z]"," ")
		string = string.replaceAll(" +"," ")
		string = string.toLowerCase()
		val words = string.split(" ") 
		return words
	}

	// Counts unique frequencies without stop words
	def countUniqueFrequencies(words: Array[String]) : IndexedSeq[(String,Int)] = {
		val stops = new Array[String](1)
		return countUniqueFrequenciesWithStop(words, stops)
	}

	// Counts unique frequencies given a file with 
	// stopwords 
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

	// Counts the frequency of frequencies by mapping 
	// the frequency to its count 
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

		return ListMap(map.toList.sortBy{_._1}.reverse.sortBy{_._2}:_*).toIndexedSeq.reverse 
	}

	// Returns the number form of a noun given the 
	// amount/quentity of objects it represents 
	// (excludes exceptions)
	def pluralOrSingular(singularForm: String, quantity: Int) : String = {
		if(quantity>1) 
			return singularForm+"s" 
		return singularForm 
	}

	// Conjugates a verb based on the number of 
	// a given noun (excludes exceptions)
	def conjugate(verb: String, given: String) : String = {
		if(given.substring(given.length()-1)=='s') 
			return verb
		return verb+"s"
	}

	// Counts categories on a formatted file
	def countCategories(lines: Array[String]) : collection.immutable.Map[String,Int] = {
		var i = 0
		var line: Array[String] = Array[String]()
		var map = Map[String,Int]()
		var cat = ""
		for(i <- 0 to lines.length-1) {
			line = lines(i).split(',')
			cat = line(line.length-1) 
			if(map.contains(cat)) {
				map(cat) += 1
			} else {
				map += (cat -> 1)
			}
		}
		return map.toList.sortBy(_._1).toMap
	}

	// Gets features and values of a formatted file
	def getFeatures(lines: Array[String]) : collection.immutable.Map[String,Map[String,Map[String,Int]]] = {
		var map = Map[String,Map[String,Map[String,Int]]]() 
		var i = 0
		var j = 0

		for(i <- 0 to lines.length-1) {
			val line = lines(i).split(',')
			val cat = line(line.length-1) 
			for(j <- 0 to line.length-2) {
				val temp = line(j).split("=")
				val feat = temp(0)
				val value = temp(1)
				if(map.contains(feat)) {
					if(map(feat).contains(cat)) {
						if(map(feat)(cat).contains(value)) {
							map(feat)(cat)(value) += 1
						} else {
							map(feat)(cat) += (value -> 1)
						}
					} else {
						map(feat) += (cat -> Map())
						map(feat)(cat) += (value -> 1)
					}
				} else {
					map += (feat -> Map())
					map(feat) += (cat -> Map())
					map(feat)(cat) += (value -> 1)
				}
			}
		}

		
		return map.toList.sortBy(_._1).toMap
	}
}