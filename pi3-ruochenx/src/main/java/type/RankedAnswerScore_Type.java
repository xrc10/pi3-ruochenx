
/* First created by JCasGen Mon Sep 21 09:23:45 EDT 2015 */
package type;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.CASImpl;
import org.apache.uima.cas.impl.FSGenerator;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;

/** The result of evaluation, which records the ranked list of AnswerScore and a precision for a certain question.
 * Updated by JCasGen Mon Sep 21 09:23:45 EDT 2015
 * @generated */
public class RankedAnswerScore_Type extends ComponentAnnotation_Type {
  /** @generated 
   * @return the generator for this type
   */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (RankedAnswerScore_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = RankedAnswerScore_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new RankedAnswerScore(addr, RankedAnswerScore_Type.this);
  			   RankedAnswerScore_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new RankedAnswerScore(addr, RankedAnswerScore_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = RankedAnswerScore.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("type.RankedAnswerScore");
 
  /** @generated */
  final Feature casFeat_rankedListOfAnswerScore;
  /** @generated */
  final int     casFeatCode_rankedListOfAnswerScore;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getRankedListOfAnswerScore(int addr) {
        if (featOkTst && casFeat_rankedListOfAnswerScore == null)
      jcas.throwFeatMissing("rankedListOfAnswerScore", "type.RankedAnswerScore");
    return ll_cas.ll_getRefValue(addr, casFeatCode_rankedListOfAnswerScore);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setRankedListOfAnswerScore(int addr, int v) {
        if (featOkTst && casFeat_rankedListOfAnswerScore == null)
      jcas.throwFeatMissing("rankedListOfAnswerScore", "type.RankedAnswerScore");
    ll_cas.ll_setRefValue(addr, casFeatCode_rankedListOfAnswerScore, v);}
    
   /** @generated
   * @param addr low level Feature Structure reference
   * @param i index of item in the array
   * @return value at index i in the array 
   */
  public int getRankedListOfAnswerScore(int addr, int i) {
        if (featOkTst && casFeat_rankedListOfAnswerScore == null)
      jcas.throwFeatMissing("rankedListOfAnswerScore", "type.RankedAnswerScore");
    if (lowLevelTypeChecks)
      return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_rankedListOfAnswerScore), i, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_rankedListOfAnswerScore), i);
	return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_rankedListOfAnswerScore), i);
  }
   
  /** @generated
   * @param addr low level Feature Structure reference
   * @param i index of item in the array
   * @param v value to set
   */ 
  public void setRankedListOfAnswerScore(int addr, int i, int v) {
        if (featOkTst && casFeat_rankedListOfAnswerScore == null)
      jcas.throwFeatMissing("rankedListOfAnswerScore", "type.RankedAnswerScore");
    if (lowLevelTypeChecks)
      ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_rankedListOfAnswerScore), i, v, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_rankedListOfAnswerScore), i);
    ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_rankedListOfAnswerScore), i, v);
  }
 
 
  /** @generated */
  final Feature casFeat_precision;
  /** @generated */
  final int     casFeatCode_precision;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public double getPrecision(int addr) {
        if (featOkTst && casFeat_precision == null)
      jcas.throwFeatMissing("precision", "type.RankedAnswerScore");
    return ll_cas.ll_getDoubleValue(addr, casFeatCode_precision);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setPrecision(int addr, double v) {
        if (featOkTst && casFeat_precision == null)
      jcas.throwFeatMissing("precision", "type.RankedAnswerScore");
    ll_cas.ll_setDoubleValue(addr, casFeatCode_precision, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	 * @generated
	 * @param jcas JCas
	 * @param casType Type 
	 */
  public RankedAnswerScore_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_rankedListOfAnswerScore = jcas.getRequiredFeatureDE(casType, "rankedListOfAnswerScore", "uima.cas.FSArray", featOkTst);
    casFeatCode_rankedListOfAnswerScore  = (null == casFeat_rankedListOfAnswerScore) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_rankedListOfAnswerScore).getCode();

 
    casFeat_precision = jcas.getRequiredFeatureDE(casType, "precision", "uima.cas.Double", featOkTst);
    casFeatCode_precision  = (null == casFeat_precision) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_precision).getCode();

  }
}



    