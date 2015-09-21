

/* First created by JCasGen Mon Sep 21 09:23:45 EDT 2015 */
package type;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.cas.FSArray;


/** The result of evaluation, which records the ranked list of AnswerScore and a precision for a certain question.
 * Updated by JCasGen Mon Sep 21 09:23:45 EDT 2015
 * XML source: /home/ruochenx/git/pi3-ruochenx/pi3-ruochenx/src/main/resources/typeSystem.xml
 * @generated */
public class RankedAnswerScore extends ComponentAnnotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(RankedAnswerScore.class);
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
  protected RankedAnswerScore() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public RankedAnswerScore(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public RankedAnswerScore(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public RankedAnswerScore(JCas jcas, int begin, int end) {
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
  //* Feature: rankedListOfAnswerScore

  /** getter for rankedListOfAnswerScore - gets The descending ranked list of AnswerScore instances accroding to scores.
   * @generated
   * @return value of the feature 
   */
  public FSArray getRankedListOfAnswerScore() {
    if (RankedAnswerScore_Type.featOkTst && ((RankedAnswerScore_Type)jcasType).casFeat_rankedListOfAnswerScore == null)
      jcasType.jcas.throwFeatMissing("rankedListOfAnswerScore", "type.RankedAnswerScore");
    return (FSArray)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((RankedAnswerScore_Type)jcasType).casFeatCode_rankedListOfAnswerScore)));}
    
  /** setter for rankedListOfAnswerScore - sets The descending ranked list of AnswerScore instances accroding to scores. 
   * @generated
   * @param v value to set into the feature 
   */
  public void setRankedListOfAnswerScore(FSArray v) {
    if (RankedAnswerScore_Type.featOkTst && ((RankedAnswerScore_Type)jcasType).casFeat_rankedListOfAnswerScore == null)
      jcasType.jcas.throwFeatMissing("rankedListOfAnswerScore", "type.RankedAnswerScore");
    jcasType.ll_cas.ll_setRefValue(addr, ((RankedAnswerScore_Type)jcasType).casFeatCode_rankedListOfAnswerScore, jcasType.ll_cas.ll_getFSRef(v));}    
    
  /** indexed getter for rankedListOfAnswerScore - gets an indexed value - The descending ranked list of AnswerScore instances accroding to scores.
   * @generated
   * @param i index in the array to get
   * @return value of the element at index i 
   */
  public AnswerScore getRankedListOfAnswerScore(int i) {
    if (RankedAnswerScore_Type.featOkTst && ((RankedAnswerScore_Type)jcasType).casFeat_rankedListOfAnswerScore == null)
      jcasType.jcas.throwFeatMissing("rankedListOfAnswerScore", "type.RankedAnswerScore");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((RankedAnswerScore_Type)jcasType).casFeatCode_rankedListOfAnswerScore), i);
    return (AnswerScore)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((RankedAnswerScore_Type)jcasType).casFeatCode_rankedListOfAnswerScore), i)));}

  /** indexed setter for rankedListOfAnswerScore - sets an indexed value - The descending ranked list of AnswerScore instances accroding to scores.
   * @generated
   * @param i index in the array to set
   * @param v value to set into the array 
   */
  public void setRankedListOfAnswerScore(int i, AnswerScore v) { 
    if (RankedAnswerScore_Type.featOkTst && ((RankedAnswerScore_Type)jcasType).casFeat_rankedListOfAnswerScore == null)
      jcasType.jcas.throwFeatMissing("rankedListOfAnswerScore", "type.RankedAnswerScore");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((RankedAnswerScore_Type)jcasType).casFeatCode_rankedListOfAnswerScore), i);
    jcasType.ll_cas.ll_setRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((RankedAnswerScore_Type)jcasType).casFeatCode_rankedListOfAnswerScore), i, jcasType.ll_cas.ll_getFSRef(v));}
   
    
  //*--------------*
  //* Feature: precision

  /** getter for precision - gets The precision@N, where N is the number of true answers.
   * @generated
   * @return value of the feature 
   */
  public double getPrecision() {
    if (RankedAnswerScore_Type.featOkTst && ((RankedAnswerScore_Type)jcasType).casFeat_precision == null)
      jcasType.jcas.throwFeatMissing("precision", "type.RankedAnswerScore");
    return jcasType.ll_cas.ll_getDoubleValue(addr, ((RankedAnswerScore_Type)jcasType).casFeatCode_precision);}
    
  /** setter for precision - sets The precision@N, where N is the number of true answers. 
   * @generated
   * @param v value to set into the feature 
   */
  public void setPrecision(double v) {
    if (RankedAnswerScore_Type.featOkTst && ((RankedAnswerScore_Type)jcasType).casFeat_precision == null)
      jcasType.jcas.throwFeatMissing("precision", "type.RankedAnswerScore");
    jcasType.ll_cas.ll_setDoubleValue(addr, ((RankedAnswerScore_Type)jcasType).casFeatCode_precision, v);}    
  }

    