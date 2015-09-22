package annotator;

import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.cas.FSIndex;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.cas.FSArray;
import org.apache.uima.jcas.tcas.Annotation;

import type.InputDocument;
import type.Question;
import type.Answer;
import type.Token;
import type.Ngram;
import type.AnswerScore;

import java.util.ArrayList;
import java.util.Iterator;

public class AnswerScoreAnnotator extends JCasAnnotator_ImplBase {
  public void process(JCas aJCas) {
    // find InputDocument instance
    FSIndex inputDocumentIndex = aJCas.getAnnotationIndex(InputDocument.type);
    Iterator inputDocumentIterator = inputDocumentIndex.iterator();
    while (inputDocumentIterator.hasNext()) {
      InputDocument inputDocumentAnnot = (InputDocument) inputDocumentIterator.next();
      // find question
      Question questionAnnot = inputDocumentAnnot.getQuestion();
      ArrayList<Ngram> questionNGramList = findNGram(aJCas, questionAnnot);
      // answers tokenization
      FSArray answers = inputDocumentAnnot.getAnswers();
      for(int i=0; i<answers.size(); i++) {
        Answer answerAnnot = (Answer) answers.get(i);
        ArrayList<Ngram> answerNGramList = findNGram(aJCas, answerAnnot);
        double score = computeNGramScore(aJCas, questionNGramList, answerNGramList);
        AnswerScore answerScore = new AnswerScore(aJCas);
        answerScore.setAnswer(answerAnnot);
        answerScore.setBegin(answerAnnot.getBegin());
        answerScore.setEnd(answerAnnot.getEnd());
        answerScore.setComponentId(this.getClass().getName());
        answerScore.setScore(score);
        answerScore.addToIndexes();
      }
    }
  }
  
  public ArrayList<Ngram> findNGram(JCas aJCas, Annotation annot) {
    ArrayList<Ngram> nGramList = new ArrayList<Ngram>();
    FSIndex nGramIndex = aJCas.getAnnotationIndex(Ngram.type);
    Iterator nGramIterator = nGramIndex.iterator();
    while (nGramIterator.hasNext()) {
      Ngram ngram = (Ngram) nGramIterator.next();
      if (inAnnot(ngram, annot)) {
        nGramList.add(ngram);
      }
    }
    return nGramList;
  }
  
  public boolean isEqual(JCas aJCas, Ngram n1, Ngram n2) {
    String docText = aJCas.getDocumentText();
    if (n1.getN() == n2.getN()) {
      for(int i=0; i<n1.getN(); i++) {
        Token t1 = (Token) n1.getTokens().get(i);
        Token t2 = (Token) n2.getTokens().get(i);
        if (!docText.substring(t1.getBegin(), t1.getEnd()).
            equals(docText.substring(t2.getBegin(), t2.getEnd()))) {
          return false;
        }
      }
      return true;
    } else {
      return false;
    }
  }
  
  double computeNGramScore(JCas aJCas, ArrayList<Ngram> ngList1, ArrayList<Ngram> ngList2) {
    double overlapCount = 0;
    for(int i=0; i<ngList1.size(); i++) {
      for(int j=0; j<ngList2.size(); j++) {
        if (isEqual(aJCas, ngList1.get(i), ngList2.get(j))) {
          overlapCount += 1;
          break;
        }
      }
    }
    return overlapCount/(Math.max(ngList1.size(), ngList2.size()));
  }
  
  public boolean inAnnot(Ngram ngram, Annotation annot) {
    return (ngram.getBegin() >= annot.getBegin() && ngram.getEnd() <= annot.getEnd());
  }
}
