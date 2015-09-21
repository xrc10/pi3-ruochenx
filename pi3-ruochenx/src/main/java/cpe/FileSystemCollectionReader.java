package cpe;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.uima.cas.CAS;
import org.apache.uima.cas.CASException;
import org.apache.uima.collection.CollectionException;
import org.apache.uima.collection.CollectionReader_ImplBase;
import org.apache.uima.examples.SourceDocumentInformation;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceConfigurationException;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.util.FileUtils;
import org.apache.uima.util.Progress;
import org.apache.uima.util.ProgressImpl;

public class FileSystemCollectionReader extends CollectionReader_ImplBase {

  public static final String PARAM_INPUTDIR = "InputDirectory";
  public static final String PARAM_ENCODING = "Encoding";
  public static final String PARAM_LANGUAGE = "Language";
  public static final String PARAM_SUBDIR = "BrowseSubdirectories";
  private ArrayList<File> mFiles;

  private String mEncoding;

  private String mLanguage;
  
  private Boolean mRecursive;

  private int mCurrentIndex;
  
  public void initialize() throws ResourceInitializationException {
    File directory = new File(((String) getConfigParameterValue(PARAM_INPUTDIR)).trim());
    mEncoding  = (String) getConfigParameterValue(PARAM_ENCODING);
    mLanguage  = (String) getConfigParameterValue(PARAM_LANGUAGE);
    mRecursive = (Boolean) getConfigParameterValue(PARAM_SUBDIR);
    if (null == mRecursive) { // could be null if not set, it is optional
      mRecursive = Boolean.FALSE;
    }
    mCurrentIndex = 0;

    // if input directory does not exist or is not a directory, throw exception
    if (!directory.exists() || !directory.isDirectory()) {
      throw new ResourceInitializationException(ResourceConfigurationException.DIRECTORY_NOT_FOUND,
              new Object[] { PARAM_INPUTDIR, this.getMetaData().getName(), directory.getPath() });
    }

    // get list of files in the specified directory, and subdirectories if the
    // parameter PARAM_SUBDIR is set to True
    mFiles = new ArrayList<File>();
    addFilesFromDir(directory);
  }
  
  private void addFilesFromDir(File dir) {
    File[] files = dir.listFiles();
    for (int i = 0; i < files.length; i++) {
      if (!files[i].isDirectory()) {
        mFiles.add(files[i]);
      } else if (mRecursive) {
        addFilesFromDir(files[i]);
      }
    }
  }
  
  @Override
  public void getNext(CAS aCAS) throws IOException, CollectionException {
    // TODO Auto-generated method stub
    JCas jcas;
    try {
      jcas = aCAS.getJCas();
    } catch (CASException e) {
      throw new CollectionException(e);
    }

    // open input stream to file
    File file = (File) mFiles.get(mCurrentIndex++);
    String text = FileUtils.file2String(file, mEncoding);
      // put document in CAS
    jcas.setDocumentText(text);

    // set language if it was explicitly specified as a configuration parameter
    if (mLanguage != null) {
      jcas.setDocumentLanguage(mLanguage);
    }

    // Also store location of source document in CAS. This information is critical
    // if CAS Consumers will need to know where the original document contents are located.
    // For example, the Semantic Search CAS Indexer writes this information into the
    // search index that it creates, which allows applications that use the search index to
    // locate the documents that satisfy their semantic queries.
    SourceDocumentInformation srcDocInfo = new SourceDocumentInformation(jcas);
    srcDocInfo.setUri(file.getAbsoluteFile().toURL().toString());
    srcDocInfo.setOffsetInSource(0);
    srcDocInfo.setDocumentSize((int) file.length());
    srcDocInfo.setLastSegment(mCurrentIndex == mFiles.size());
    srcDocInfo.addToIndexes();
  }

  @Override
  public void close() throws IOException {
  }

  @Override
  public Progress[] getProgress() {
    return new Progress[] { new ProgressImpl(mCurrentIndex, mFiles.size(), Progress.ENTITIES) };
  }

  @Override
  public boolean hasNext() throws IOException, CollectionException {
    // TODO Auto-generated method stub
    return mCurrentIndex < mFiles.size();
  }

}
