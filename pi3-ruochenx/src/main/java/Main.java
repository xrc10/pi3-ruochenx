import java.io.IOException;
import java.util.List;

import org.apache.uima.UIMAFramework;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.collection.metadata.CpeDescription;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.util.InvalidXMLException;
import org.apache.uima.util.XMLInputSource;
import org.apache.uima.cas.CAS;
import org.apache.uima.collection.CollectionProcessingEngine;
import org.apache.uima.collection.CollectionReader;
import org.apache.uima.collection.EntityProcessStatus;
import org.apache.uima.collection.StatusCallbackListener;
import org.apache.uima.collection.base_cpm.CasProcessor;

public class Main {

  /**
   * This method is the main program and entry point of your system for PI3. It runs a Collection
   * Processing Engine (CPE).
   * 
   * @param args
   * @throws IOException 
   * @throws InvalidXMLException 
   * @throws ResourceInitializationException 
   */
  public static void main(String[] args) throws Exception {
    // ### A guideline for implementing this method ###
    // 1. Accept integer n (1, 2, or 3) as a positional argument, specifying the length of n-grams.
    // 2. Initialize a CPE by loading your CPE descriptor at 'src/main/resources/cpeDescriptor.xml'.
    // 3. Pass the parameter n to your analysis engine(s) properly.
    // 4. Run the CPE.
    // example command:"1 src/main/resources/inputData src/main/resources/outputData"

    // Implement your code from here.
    int numberOfGram = Integer.parseInt(args[0]);
    String inputDirectory = args[1];
    String outputDirectory = args[2];
    //parse CPE descriptor in file specified on command line
    CpeDescription cpeDesc = UIMAFramework.getXMLParser().
            parseCpeDescription(new XMLInputSource("src/main/resources/cpeDescriptor.xml"));
    //instantiate CPE
    CollectionProcessingEngine mCPE = UIMAFramework.produceCollectionProcessingEngine(cpeDesc);
    //configure parameters
    CollectionReader collectReader = (CollectionReader) mCPE.getCollectionReader();
    CasProcessor[] processors = mCPE.getCasProcessors();
    AnalysisEngine aggregatedAE = (AnalysisEngine) processors[0];
    AnalysisEngine casConsumer = (AnalysisEngine) processors[1];
    //set parameters
    collectReader.setConfigParameterValue("InputDirectory", inputDirectory);
    aggregatedAE.setConfigParameterValue("NumberOfNGram", numberOfGram);
    casConsumer.setConfigParameterValue("OutputDirectory", outputDirectory);
    //reconfiguration
    collectReader.reconfigure();
    aggregatedAE.reconfigure();
    casConsumer.reconfigure();
    //Create and register a Status Callback Listener
    mCPE.addStatusCallbackListener(new StatusCallbackListenerImpl());
    //Start Processing
    mCPE.process();
  }
}

class StatusCallbackListenerImpl implements StatusCallbackListener {
  int entityCount = 0;

  long size = 0;
  
  private CollectionProcessingEngine mCPE;

  /**
   * Start time of CPE initialization
   */
  private long mStartTime;
  
  /**
   * Start time of the processing
   */
  private long mInitCompleteTime;
  

  /**
   * Called when the initialization is completed.
   * 
   * @see org.apache.uima.collection.processing.StatusCallbackListener#initializationComplete()
   */
  public void initializationComplete() {      
    System.out.println("CPM Initialization Complete");
    mInitCompleteTime = System.currentTimeMillis();
  }

  /**
   * Called when the batchProcessing is completed.
   * 
   * @see org.apache.uima.collection.processing.StatusCallbackListener#batchProcessComplete()
   * 
   */
  public void batchProcessComplete() {
    System.out.print("Completed " + entityCount + " documents");
    if (size > 0) {
      System.out.print("; " + size + " characters");
    }
    System.out.println();
    long elapsedTime = System.currentTimeMillis() - mStartTime;
    System.out.println("Time Elapsed : " + elapsedTime + " ms ");
  }

  /**
   * Called when the collection processing is completed.
   * 
   * @see org.apache.uima.collection.processing.StatusCallbackListener#collectionProcessComplete()
   */
  public void collectionProcessComplete() {
    long time = System.currentTimeMillis();
    System.out.print("Completed " + entityCount + " documents");
    if (size > 0) {
      System.out.print("; " + size + " characters");
    }
    System.out.println();
    long initTime = mInitCompleteTime - mStartTime; 
    long processingTime = time - mInitCompleteTime;
    long elapsedTime = initTime + processingTime;
    System.out.println("Total Time Elapsed: " + elapsedTime + " ms ");
    System.out.println("Initialization Time: " + initTime + " ms");
    System.out.println("Processing Time: " + processingTime + " ms");
    
    // stop the JVM. Otherwise main thread will still be blocked waiting for
    // user to press Enter.
    System.exit(1);
  }

  /**
   * Called when the CPM is paused.
   * 
   * @see org.apache.uima.collection.processing.StatusCallbackListener#paused()
   */
  public void paused() {
    System.out.println("Paused");
  }

  /**
   * Called when the CPM is resumed after a pause.
   * 
   * @see org.apache.uima.collection.processing.StatusCallbackListener#resumed()
   */
  public void resumed() {
    System.out.println("Resumed");
  }

  /**
   * Called when the CPM is stopped abruptly due to errors.
   * 
   * @see org.apache.uima.collection.processing.StatusCallbackListener#aborted()
   */
  public void aborted() {
    System.out.println("Aborted");
    // stop the JVM. Otherwise main thread will still be blocked waiting for
    // user to press Enter.
    System.exit(1);
  }

  /**
   * Called when the processing of a Document is completed. <br>
   * The process status can be looked at and corresponding actions taken.
   * 
   * @param aCas
   *          CAS corresponding to the completed processing
   * @param aStatus
   *          EntityProcessStatus that holds the status of all the events for aEntity
   */
  public void entityProcessComplete(CAS aCas, EntityProcessStatus aStatus) {
    if (aStatus.isException()) {
      List exceptions = aStatus.getExceptions();
      for (int i = 0; i < exceptions.size(); i++) {
        ((Throwable) exceptions.get(i)).printStackTrace();
      }
      return;
    }
    entityCount++;
    String docText = aCas.getDocumentText();
    if (docText != null) {
      size += docText.length();
    }
  }
}


