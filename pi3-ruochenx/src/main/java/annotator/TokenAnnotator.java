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

import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TokenAnnotator extends JCasAnnotator_ImplBase {
  private Pattern tokenPattern = Pattern.compile("\\w+");
  
  public void process(JCas aJCas) {
    // find InputDocument instance
    FSIndex inputDocumentIndex = aJCas.getAnnotationIndex(InputDocument.type);
    Iterator inputDocumentIterator = inputDocumentIndex.iterator();
    while (inputDocumentIterator.hasNext()) {
      InputDocument inputDocumentAnnot = (InputDocument) inputDocumentIterator.next();
      // question tokenization
      Question questionAnnot = inputDocumentAnnot.getQuestion();
      String questionSentence = questionAnnot.getSentence();
      tokenizer(questionSentence, questionAnnot, aJCas);
      // answers tokenization
      FSArray answers = inputDocumentAnnot.getAnswers();
      for(int i=0; i<answers.size(); i++) {
        Answer answerAnnot = (Answer) answers.get(i);
        String answerSentence = answerAnnot.getSentence();
        tokenizer(answerSentence, answerAnnot, aJCas);
      }
    }
  }
  
  public void tokenizer(String inputStr, Annotation annot, JCas jCas) {
    Matcher matcher = tokenPattern.matcher(inputStr);
    while (matcher.find()) {
      Token token = new Token(jCas);
      token.setBegin(annot.getBegin() + matcher.start());
      token.setEnd(annot.getBegin() + matcher.end());
      token.setComponentId(this.getClass().getName());
      token.setScore(1.0f);
      token.addToIndexes();
    }
  }
}
