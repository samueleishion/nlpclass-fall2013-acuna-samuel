nlpclass-fall2013-acuna-samuel
==============================

Private repository for LIN 353N / CS 378 NLP class

All the scala files follow the sbt protocol: they are all located on 
src/main/scala/nlp/a0/

The data files that were used to test the scala files are on the data directory: 
data/ 


WordCount.scala <file> [--stopwords] <stopwords file>
	Takes in a file and counts its words.

	--stopwords <file> : filters the stop words specified on the 
		    stopwords file and counts the remaining words. 

WordFreqFreq.scala <file>
	Takes in a file and counts the most and the least frequent words
	in the given file.

NGramCounting.scala 
	Counts ngrams given a vector of tokens in a file. 

CountTrigrams.scala <file>
	Counts all 3-grams (ngrams of length 3) and displays the top 10.

CountFeatures.scala <file>
	Reads a file and parses the data in it based on a specific format: 
	FEATURE=VALUE,FEATURE=VALUE,...,FEATURE=VALUE,LABEL

Process.scala
	Contains a Process object that Processes files, counting, and 
	other shared functions and methods. 