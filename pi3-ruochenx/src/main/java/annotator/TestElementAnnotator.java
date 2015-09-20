package annotator;

import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.cas.FSArray;

import type.InputDocument;
import type.Question;
import type.Answer;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestElementAnnotator extends JCasAnnotator_ImplBase {
  private Pattern questionPattern = Pattern.compile("Q\\s(.*)");

  private Pattern answerPattern = Pattern.compile("A(\\d+)\\s(\\d+)\\s(.*)");

  public void process(JCas aJCas) {
    // construct InputDocument instance to contain question and answers
    InputDocument inputDocumentAnnot = new InputDocument(aJCas);
    inputDocumentAnnot.setComponentId(this.getClass().getName());
    inputDocumentAnnot.setScore(1.0f);
    inputDocumentAnnot.setBegin(0);
    // get document text
    String docText = aJCas.getDocumentText();
    inputDocumentAnnot.setEnd(docText.length());
    // search for question
    Matcher matcher = questionPattern.matcher(docText);
    // found one question - create annotation
    Question questionAnnot = new Question(aJCas);
    if (matcher.find()) { 
      questionAnnot.setBegin(matcher.start(1));
      questionAnnot.setEnd(matcher.end(1));
      questionAnnot.setId("0");
      questionAnnot.setSentence(matcher.group(1));
      questionAnnot.setComponentId(this.getClass().getName());
      questionAnnot.setScore(1.0f);
    }
    // save question to InputDocument instance
    inputDocumentAnnot.setQuestion(questionAnnot);
    // use ArrayList of answer to store answers
    ArrayList<Answer> answerArray = new ArrayList<Answer>();
    // search for answers
    matcher = answerPattern.matcher(docText);
    while (matcher.find()) {
      // found one - create annotation
      Answer answerAnnot = new Answer(aJCas);
      answerAnnot.setBegin(matcher.start(3));
      answerAnnot.setEnd(matcher.end(3));
      answerAnnot.setId(matcher.group(1));
      if (matcher.group(2).equals("0")) {
        answerAnnot.setLabel(false);
      } else {
        answerAnnot.setLabel(true);
      }
      answerAnnot.setSentence(matcher.group(3));
      answerAnnot.setComponentId(this.getClass().getName());
      answerAnnot.setScore(1.0f);
      answerArray.add(answerAnnot);
    }
    // save answers to InputDocument instance
    FSArray answers = new FSArray(aJCas, answerArray.size());
    for (int i = 0; i < answerArray.size(); i++) {
      answers.set(i, answerArray.get(i));
    }
    inputDocumentAnnot.setAnswers(answers);
    inputDocumentAnnot.addToIndexes();
  }
}
