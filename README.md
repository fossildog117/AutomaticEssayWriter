# AutomaticEssayWriter
This is an app that automatically writes essays

Essays are very boring to write for engineering and science students. So no more essays for STEM students ever again!

Possible changes:

1) Implement BFS on references and use information on those sites to enhance the essay

2) Improve synonyms parser

3) Perhaps conduct BFS directly from Bing instead of wikipedia

4) Implement a separate classes for an introduction, essay body and conclusion such that these classes inherit from an abstract Paragraph class


FYI: If the essay appears to be terrible go into the EssayWrite.java file and change "words.size() / 2" on line 68 and increase it


To run the application simply go to EssayWriterV1.java and enter the essay topic on line 18:

EssayWrite myEssay = new EssayWrite("Essay Topic");

Then compile and run the code.
