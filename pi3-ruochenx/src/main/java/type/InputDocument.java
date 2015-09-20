

/* First created by JCasGen Sat Sep 19 13:28:07 EDT 2015 */
package type;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.cas.FSArray;


import org.apache.uima.jcas.cas.FSList;


/** Stores a question and its associated answer candidates.
 * Updated by JCasGen Sat Sep 19 18:13:45 EDT 2015
 * XML source: /home/ruochenx/git/pi3-ruochenx/pi3-ruochenx/src/main/resources/typeSystem.xml
 * @generated */
public class InputDocument extends ComponentAnnotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(InputDocument.class);
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int type = typeIndexID;
  /** @generated
   * @return index of the type  
   */
  @Override
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected InputDocument() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public InputDocument(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public InputDocument(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public InputDocument(JCas jcas, int begin, int end) {
    super(jcas);
    setBegin(begin);
    setEnd(end);
    readObject();
  }   

  /** 
   * <!-- begin-user-doc -->
   * Write your own initialization here
   * <!-- end-user-doc -->
   *
   * @generated modifiable 
   */
  private void readObject() {/*default - does nothing empty block */}
     
 
    
  //*--------------*
  //* Feature: question

  /** getter for question - gets 
   * @generated
   * @return value of the feature 
   */
  public Question getQuestion() {
    if (InputDocument_Type.featOkTst && ((InputDocument_Type)jcasType).casFeat_question == null)
      jcasType.jcas.throwFeatMissing("question", "type.InputDocument");
    return (Question)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((InputDocument_Type)jcasType).casFeatCode_question)));}
    
  /** setter for question - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setQuestion(Question v) {
    if (InputDocument_Type.featOkTst && ((InputDocument_Type)jcasType).casFeat_question == null)
      jcasType.jcas.throwFeatMissing("question", "type.InputDocument");
    jcasType.ll_cas.ll_setRefValue(addr, ((InputDocument_Type)jcasType).casFeatCode_question, jcasType.ll_cas.ll_getFSRef(v));}    
   
    
  //*--------------*
  //* Feature: answers

  /** getter for answers - gets 
   * @generated
   * @return value of the feature 
   */
  public FSArray getAnswers() {
    if (InputDocument_Type.featOkTst && ((InputDocument_Type)jcasType).casFeat_answers == null)
      jcasType.jcas.throwFeatMissing("answers", "type.InputDocument");
    return (FSArray)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((InputDocument_Type)jcasType).casFeatCode_answers)));}
    
  /** setter for answers - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setAnswers(FSArray v) {
    if (InputDocument_Type.featOkTst && ((InputDocument_Type)jcasType).casFeat_answers == null)
      jcasType.jcas.throwFeatMissing("answers", "type.InputDocument");
    jcasType.ll_cas.ll_setRefValue(addr, ((InputDocument_Type)jcasType).casFeatCode_answers, jcasType.ll_cas.ll_getFSRef(v));}    
    
  /** indexed getter for answers - gets an indexed value - 
   * @generated
   * @param i index in the array to get
   * @return value of the element at index i 
   */
  public Answer getAnswers(int i) {
    if (InputDocument_Type.featOkTst && ((InputDocument_Type)jcasType).casFeat_answers == null)
      jcasType.jcas.throwFeatMissing("answers", "type.InputDocument");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((InputDocument_Type)jcasType).casFeatCode_answers), i);
    return (Answer)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((InputDocument_Type)jcasType).casFeatCode_answers), i)));}

  /** indexed setter for answers - sets an indexed value - 
   * @generated
   * @param i index in the array to set
   * @param v value to set into the array 
   */
  public void setAnswers(int i, Answer v) { 
    if (InputDocument_Type.featOkTst && ((InputDocument_Type)jcasType).casFeat_answers == null)
      jcasType.jcas.throwFeatMissing("answers", "type.InputDocument");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((InputDocument_Type)jcasType).casFeatCode_answers), i);
    jcasType.ll_cas.ll_setRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((InputDocument_Type)jcasType).casFeatCode_answers), i, jcasType.ll_cas.ll_getFSRef(v));}
  }

    