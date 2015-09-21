package annotator;

import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.cas.FSIndex;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.cas.FSArray;

import type.AnswerScore;
import type.RankedAnswerScore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class EvaluationAnnotator extends JCasAnnotator_ImplBase {
  public void process(JCas aJCas) {
    // Find all AnswerScore instances and add the, to an ArrayList, count the number of truth
    FSIndex answerScoreIndex = aJCas.getAnnotationIndex(AnswerScore.type);
    Iterator answerScoreIterator = answerScoreIndex.iterator();
    ArrayList<AnswerScore> answerScoreList = new ArrayList<AnswerScore>();
    int numberOfTruth = 0;
    while (answerScoreIterator.hasNext()) {
      AnswerScore answerScore = (AnswerScore) answerScoreIterator.next();
      answerScoreList.add(answerScore);
      if (answerScore.getAnswer().getLabel() == true) {
        numberOfTruth++;
      }
    }
    RankedAnswerScore rankedAnswerScore = new RankedAnswerScore(aJCas);
    rankedAnswerScore.setBegin(answerScoreList.get(0).getBegin());
    rankedAnswerScore.setEnd(answerScoreList.get(answerScoreList.size() - 1).getEnd());
    Collections.sort(answerScoreList);
    // compute precision
    double precision = computePrecision(answerScoreList, numberOfTruth);
    rankedAnswerScore.setScore(1.0f);
    rankedAnswerScore.setComponentId(this.getClass().getName());
    rankedAnswerScore.setPrecision(precision);
    FSArray rankedAnswerScoreList = new FSArray(aJCas, answerScoreList.size());
    for(int i=0; i<answerScoreList.size(); i++) {
      rankedAnswerScoreList.set(i, answerScoreList.get(i));
    }
    rankedAnswerScore.setRankedListOfAnswerScore(rankedAnswerScoreList);
    rankedAnswerScore.addToIndexes();
  }
  
  public double computePrecision(ArrayList<AnswerScore> l, int n) {
    double hitCount = 0;
    for(int i=0; i<n; i++) {
      AnswerScore answerScore = l.get(i);
      if (answerScore.getAnswer().getLabel() == true) {
        hitCount++;
      }
    }
    return hitCount/n;
  }
}
