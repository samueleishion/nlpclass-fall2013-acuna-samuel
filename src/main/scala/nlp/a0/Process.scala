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
		val stops = new Array[String](1)
		return countUniqueFrequenciesWithStop(words, stops)
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

	// def getFeatures(lines: Array[String]) : Map[String,Map[String,Map[String,Int]]] = {
	// // def getFeatures(lines: Array[String]) : Int = {
	// 	// var map = Map[String,Map[String,Map[String,Int]]]()
	// 	var map = Map[String,Map[String,Map[String,Int]]]() 
	// 	var i:Int = 0
	// 	var j:Int = 0
	// 	var line: Array[String] = Array[String]()
	// 	var cat = ""
	// 	var feat = ""
	// 	var value = ""

	// 	for(i <- 0 to lines.length-1) {
	// 		line = lines(i).split(',') 
	// 		cat = line(line.length-1) 
	// 		if(map.contains(cat)) {
	// 			for(j <- 0 to line.length-2) {
	// 				var set = line(j).split('=')
	// 				feat = set(0)
	// 				value = set(1)
	// 				if(map(cat).contains(feat)) {
	// 					// map(cat)(feat) += value
	// 					if(map(cat)(feat).contains(value)) {
	// 						// println("%s-%s-%s-%d".format(cat,feat,value,map(cat)(feat)(value)+1))
	// 						map(cat)(feat)(value) += 1
	// 					} else {
	// 						// println("%s-%s-%s-%d".format(cat,feat,value,1))
	// 						map(cat)(feat) += (value -> 1)
	// 					}
	// 				} else {
	// 					// println("%s-%s".format(cat,feat)
	// 					map(cat) += (feat -> Map())
	// 					map(cat)(feat) += (value -> 1)
	// 				}
	// 			}
	// 			// map(cat)
	// 		} else { 
	// 			map += (cat -> Map())
	// 			for(j <- 0 to line.length-2) {
	// 				var set = line(j).split('=')
	// 				feat = set(0)
	// 				value = set(1)
	// 				if(map(cat).contains(feat)) {
	// 					if(map(cat)(feat).contains(value)) {
	// 						map(cat)(feat)(value) += 1
	// 					} else {
	// 						map(cat)(feat) += (value -> 1)
	// 					}
	// 				} else {
	// 					map(cat) += (feat -> Map())
	// 					map(cat)(feat) += (value -> 1)
	// 				}

	// 			}
	// 		}
	// 	}

	// 	// sorting yes-no
	// 	map.toList.sortBy(_._1).toMap
	// 	for(cat <- map.keys) {
	// 		// sorting humidity... features
	// 		map(cat).toList.sortBy(_._1).toMap
	// 		for(feat <- map(cat).keys) {
	// 			// sorting rain, cool... values 
	// 			map(cat)(feat) = collection.mutable.Map(map(cat)(feat).toList.sortBy(_._1).toMap.toSeq: _*)
	// 		}
	// 	}

	// 	// println(map.size)
	// 	// println("No \t%x".format(map("No").size))
	// 	// println("Yes\t%x".format(map("Yes").size))
		
	// 	// println("HUMIDITY")
	// 	// println(map("No")("Humidity"))
	// 	// println(map("Yes")("Humidity"))

	// 	// println("OUTLOOK")
	// 	// println(map("No")("Outlook"))
	// 	// println(map("Yes")("Outlook"))

	// 	// println("TEMPERATURE")
	// 	// println(map("No")("Temperature"))
	// 	// println(map("Yes")("Temperature"))

	// 	// println("WIND")
	// 	// println(map("No")("Wind"))
	// 	// println(map("Yes")("Wind"))

	// 	return map
	// }
}