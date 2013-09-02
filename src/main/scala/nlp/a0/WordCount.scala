package nlp.a0

import scala.io.Source
import scala.collection.mutable.Map
import scala.collection.immutable.ListMap
import scala.collection.immutable.IndexedSeq

object WordCount {
  def main(args: Array[String]) {
    if(args(0)==null) {
      println("Please enter a proper file.") 
      throw new Exception("Please enter a proper file.")
    } else {
      var command = "default"
      
      if(args.length >= 2 && args(1)!=null) {
        command = args(1)
      } 

      command match {
        case "--stopwords" => 
          if(args(2)==null) {
            println("Please enter a file with stopwords.")
            throw new Exception("Please enter a file with stopwords.")
          } else { 
            var bookStr = textToString(args(0))
            var stopStr = textToString(args(2))
            var bookWords = filterText(bookStr)
            var stopWords = filterText(stopStr)
            println("Total number of words: "+bookWords.length.toString)

            var bookFreqs = countUniqueFrequenciesWithStop(bookWords,stopWords)
            println("Number of distinct words: "+bookFreqs.length.toString)
            println("Top 10 words: ")
            var i = 0
            for(freq <- bookFreqs) {
              if(i < 10) {
                var k: String = freq._1.asInstanceOf[String] 
                var v: Int = freq._2.asInstanceOf[Int]
                var p = (v.toFloat/bookWords.length)*100

                if(k.length>7) {
                  println("%s\t%d\t%.2f".format(k,v,p))
                } else {
                  println("%s\t\t%d\t%.2f".format(k,v,p))
                }
              }
              i += 1 
            }
          }
        case _ =>
          var bookStr = textToString(args(0))
          var bookWords = filterText(bookStr)
          println("Total number of words: "+bookWords.length.toString) 

          var bookFreqs = countUniqueFrequencies(bookWords) 
          println("Number of distinct words: "+bookFreqs.length.toString)
          println("Top 10 words: ")
          var i = 0
          for(freq <- bookFreqs) {
            if(i < 10) {
              var k: String = freq._1.asInstanceOf[String] 
              var v: Int = freq._2.asInstanceOf[Int]
              var p = (v.toFloat/bookWords.length)*100

              println("%s\t\t%d\t%.2f".format(k,v,p))
            }
            i += 1 
          }
      }
    }
  }

  def textToString(book: String) : String = {
    var source = Source.fromFile(book) 
    var string = source.getLines mkString "\n"
    source.close() 
    return string 
  }

  def filterText(text: String) : Array[String] = {
    var string = text.replaceAll("[^A-Za-z]"," ")
    string = string.replaceAll(" +"," ")
    string = string.toLowerCase()
    var words = string.split(" ") 
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

    var list = ListMap(map.toList.sortBy{_._2}:_*).toIndexedSeq
    list = list.reverse

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

    var list = ListMap(map.toList.sortBy{_._2}:_*).toIndexedSeq.reverse

    return list
  }
}
