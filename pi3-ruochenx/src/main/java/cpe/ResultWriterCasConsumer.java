package cpe;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;

import org.apache.uima.cas.CAS;
import org.apache.uima.cas.CASException;
import org.apache.uima.cas.FSIndex;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.collection.CasConsumer_ImplBase;
import org.apache.uima.examples.SourceDocumentInformation;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.resource.ResourceProcessException;
import org.xml.sax.SAXException;

import type.AnswerScore;
import type.RankedAnswerScore;

public class ResultWriterCasConsumer extends CasConsumer_ImplBase {
  public static final String PARAM_OUTPUTDIR = "OutputDirectory";

  private File mOutputDir;

  private int mDocNum;
  
  public void initialize() throws ResourceInitializationException {
    mDocNum = 0;
    mOutputDir = new File((String) getConfigParameterValue(PARAM_OUTPUTDIR));
    if (!mOutputDir.exists()) {
      mOutputDir.mkdirs();
    }
  }
  
  public void processCas(CAS aCAS) throws ResourceProcessException {

    JCas jcas;
    try {
      jcas = aCAS.getJCas();
    } catch (CASException e) {
      throw new ResourceProcessException(e);
    }

    // retrieve the filename of the input file from the CAS
    FSIterator it = jcas.getAnnotationIndex(SourceDocumentInformation.type).iterator();
    File outFile = null;
    if (it.hasNext()) {
      SourceDocumentInformation fileLoc = (SourceDocumentInformation) it.next();
      File inFile;
      try {
        inFile = new File(new URL(fileLoc.getUri()).getPath());
        String outFileName = inFile.getName().replace("q", "a");
        if (fileLoc.getOffsetInSource() > 0) {
          outFileName += ("_" + fileLoc.getOffsetInSource());
        }
        outFile = new File(mOutputDir, outFileName);
      } catch (MalformedURLException e1) {
        // invalid URL, use default processing below
      }
    }
    if (outFile == null) {
      outFile = new File(mOutputDir, "doc" + mDocNum++);     
    }
    // serialize XCAS and write to output file
    try {
      writeResult(jcas, outFile);
    } catch (IOException e) {
      throw new ResourceProcessException(e);
    } catch (SAXException e) {
      throw new ResourceProcessException(e);
    }
  }
  
  private void writeResult(JCas aJCas, File name) throws IOException, SAXException {
    PrintWriter out = null;
    try {
      // write result in required format
      out = new PrintWriter(name);
    } finally {
      if (out != null) {
        FSIndex rankedAnswerScoreIndex = aJCas.getAnnotationIndex(RankedAnswerScore.type);
        Iterator rankedAnswerScoreIterator = rankedAnswerScoreIndex.iterator();
        while (rankedAnswerScoreIterator.hasNext()) {
          RankedAnswerScore rankedAnswerScore = (RankedAnswerScore) rankedAnswerScoreIterator.next();
          out.println(rankedAnswerScore.getPrecision());
          for(int i=0; i<rankedAnswerScore.getRankedListOfAnswerScore().size(); i++) {
            AnswerScore answerScore = (AnswerScore) rankedAnswerScore.getRankedListOfAnswerScore().get(i);
            out.print("A" + answerScore.getAnswer().getId() + " " + answerScore.getScore() + "\n");
          }
        }
        out.close();
      }
    }
  }

}
