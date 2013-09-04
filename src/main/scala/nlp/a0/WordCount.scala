package nlp.a0

import nlp.a0.Process

object WordCount {
  def main(args:Array[String]) {
    if(args(0)==null) {
      throw new Exception("Please enter a proper file.")
    } else {
      var command = "default"

      if(args.length >= 2 && args(1)!=null) {
        command = args(1)
      }

      command match {
        case "--stopwords" =>
          if(args(2)==null) 
            throw new Exception("Please enter a file with stopwords.")
          else {
            processBook(args(0),args(2))
          } 
        case _ =>
          processBook(args(0),"")
      }
    }
  }

  def processBook(text: String, stop: String) {
    val bookStr = Process.textToString(text)
    var stopStr = ""
    if(stop!="") 
      stopStr = Process.textToString(stop)

    val bookWords = Process.filterText(bookStr)
    val stopWords = Process.filterText(stopStr)
    println("Total number of words: "+bookWords.length.toString)

    val bookFreqs = Process.countUniqueFrequenciesWithStop(bookWords,stopWords)
    println("Number of distinct words: "+bookFreqs.length.toString)
    println("Top 10 words: ")
    var i = 0
    var k = ""
    var v = 0
    var p = 0.0
    for(freq <- bookFreqs) {
      if(i < 10) {
        k = freq._1.asInstanceOf[String]
        v = freq._2.asInstanceOf[Int] 
        p = (v.toFloat/bookWords.length)*100

        if(k.length>7) 
          println("%s\t%d\t%.2f".format(k,v,p))
        else 
          println("%s\t\t%d\t%.2f".format(k,v,p))
      }
      i += 1
    }
  }
}