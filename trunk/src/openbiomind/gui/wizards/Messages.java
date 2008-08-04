/**
 * Messages.java
 *
 * This class contains messages used in the openbiomind.gui.wizards package.
 *
 * $Id$
 */
package openbiomind.gui.wizards;

import org.eclipse.osgi.util.NLS;

/**
 * This class contains messages used in the openbiomind.gui.wizards package.
 *
 * @author bsanghvi
 * @since Jul 2, 2008
 * @version Aug 3, 2008
 */
class Messages extends NLS {

   /** The constant bundle name. */
   private static final String BUNDLE_NAME = "openbiomind.gui.wizards.messages"; //$NON-NLS-1$

   /** Executing task */
   public static String AbsTaskWiz_ExecTask;

   /** Preparing process... */
   public static String AbsTaskWiz_PrepProc;

   /** Preparing task data... */
   public static String AbsTaskWiz_PrepTaskData;

   /** Use this task to run clustering-related data transforms */
   public static String ClustTransWiz_Desc;

   /** Clustering Transformer */
   public static String ClustTransWiz_Name;

   /** Use this task for clustering data */
   public static String ClustWiz_Desc;

   /** Clusterize */
   public static String ClustWiz_Name;

   /** Complete Pipeline Task */
   public static String CompPipeWiz_Name;

   /** Use this task to perform all the tasks together */
   public static String CompPipeWiz_P1_Desc;

   /** Complete Pipeline Task */
   public static String CompPipeWiz_P1_Name;

   /** Use this page to override the properties in {0} */
   public static String CompPipeWiz_P2_Desc;

   /** Complete Pipeline Task */
   public static String CompPipeWiz_P2_Name;

   /** Use this task to fold a dataset */
   public static String DataTransWiz_Desc;

   /** Dataset Transformer Task */
   public static String DataTransWiz_Name;

   /** Select one of these */
   public static String Detail_SelectOne;

   /** Use this task to add new features */
   public static String EnhDataWiz_Desc;

   /** Enhance Dataset Task */
   public static String EnhDataWiz_Name;

   /** Use the original dataset directory */
   public static String EnhDataWizPg_UseOriginalDataDir;

   /** Specified directory already exists and files inside it may be overwritten */
   public static String Err_DirAlreadyExist;

   /** Please specify an existing file */
   public static String Err_FileNotExist;

   /** Fix the errors to continue */
   public static String Err_FixErrToContinue;

   /** Set Graphviz dot utility in Preferences to automatically convert dot files to images */
   public static String Err_GraphvizDotUtility;

   /** Invalid directory (required argument transform is MUTIC or MOBRA) */
   public static String Err_InvalidDir;

   /** Invalid directory */
   public static String Err_InvalidDir_MetaResult;

   /** Invalid file */
   public static String Err_InvalidFile;

   /** Error occurred while creating project */
   public static String Err_UnableToCreateProject;

   /** Use this task for integrated graph visualization */
   public static String GraFeatureWiz_Desc;

   /** Graph Features */
   public static String GraFeatureWiz_Name;

   /** Time will be appended at end of name to make it unique */
   public static String Info_ProjName;

   /** Classification method */
   public static String Label_ClassMethod;

   /** Clustering colors */
   public static String Label_ClustColor;

   /** Clustering dataset */
   public static String Label_ClustDataResult;

   /** Clustering result */
   public static String Label_ClustResult;

   /** Dataset clustering metric */
   public static String Label_DataClustMetric;

   /** Dataset transformer result */
   public static String Label_DataTransResultDir;

   /** Directory */
   public static String Label_Dir;

   /** Feature selection method */
   public static String Label_FeatureSelecMethod;

   /** File name */
   public static String Label_File;

   /** Path */
   public static String Label_FilePath;

   /** Horizontal dataset */
   public static String Label_HData;

   /** Is feature selected */
   public static String Label_IsFeatureSel;

   /** Is folded */
   public static String Label_IsFolded;

   /** Maximum co-expression edges */
   public static String Label_MaxCoExpEdge;

   /** Maximum co-occurrence edges */
   public static String Label_MaxCoOccEdge;

   /** MetaTask result */
   public static String Label_MetaResultDir;

   /** MetaTask shuffling */
   public static String Label_MetaShuffling;

   /** MOBRA dataset */
   public static String Label_MobraData;

   /** Number of folds */
   public static String Label_NumOfFolds;

   /** Number of selected features */
   public static String Label_NumOfSelecFeatures;

   /** Number of tasks */
   public static String Label_NumOfTasks;

   /** Ontology association file */
   public static String Label_OntAssoFile;

   /** Ontology description file */
   public static String Label_OntDescFile;

   /** Optional Arguments */
   public static String Label_OptionalArg;

   /** Output directory */
   public static String Label_OutDir;

   /** Execution name (optional) */
   public static String Label_ProjName;

   /** Alternate property file */
   public static String Label_PropFile;

   /** Required Arguments */
   public static String Label_ReqdArg;

   /** Specify the enhanced dataset file */
   public static String Label_SpecifyEnhData;

   /** Specify the output directory */
   public static String Label_SpecifyOutDir;

   /** Specify the output image file */
   public static String Label_SpecifyOutImgFile;

   /** Source dataset */
   public static String Label_SrcData;

   /** Target category */
   public static String Label_TargetCat;

   /** Test dataset */
   public static String Label_TestData;

   /** Top useful features */
   public static String Label_TopUsefulFeatures;

   /** Transform */
   public static String Label_Transform;

   /** Utility file */
   public static String Label_UtilFile;

   /** Perform Meta Task */
   public static String MetaWiz_Desc;

   /** Meta Task */
   public static String MetaWiz_Name;

   /** Select the base dataset file */
   public static String Tip_BaseData;

   /** Select the mapping from intensity to color used */
   public static String Tip_ClustColor;

   /** Select the result of Clustering Transformer */
   public static String Tip_ClustDataResult;

   /** Select the result of Clusterize */
   public static String Tip_ClustResult;

   /** Select a value from the list */
   public static String Tip_Combo_ReadOnly;

   /** Select a value from the list or enter a new value */
   public static String Tip_Combo_Simple;

   /** Select the directory containing validation fold(s) composed by train and test */
   public static String Tip_DataTransResultDir;

   /** Leave blank if you do not want to specify a number */
   public static String Tip_LeaveBlank_Number;

   /** Leave blank if you do not want to specify a value */
   public static String Tip_LeaveBlank_Value;

   /** Leave blank or specify a valid directory */
   public static String Tip_LeaveBlankOrSpecifyDir;

   /** Leave blank or specify a valid file */
   public static String Tip_LeaveBlankOrSpecifyFile;

   /** Leave blank or specify a valid properties file */
   public static String Tip_LeaveBlankOrSpecifyPropertiesFile;

   /** Leave blank to use current directory or specify an existing directory */
   public static String Tip_LeaveBlankToUseCurrDirOrSpecifyDir;

   /** Select the directory containing out files */
   public static String Tip_MetaResultDir;

   /** Specify the output directory */
   public static String Tip_OutDir;

   /** Specify the output file */
   public static String Tip_OutFile;

   /** Output file that will be generated */
   public static String Tip_OutFilePath;

   /** File that will be generated */
   public static String Tip_Path;

   /** Leave blank to auto-generate name */
   public static String Tip_ProjName;

   /** Default property file will be used, if alternate is not specified. */
   public static String Tip_PropFile;

   /** Select the source dataset file */
   public static String Tip_SrcData;

   /** Select the result of Utility Computer */
   public static String Tip_UtilFile;

   /** Use this task to compute feature utilities */
   public static String UtilCompWiz_Desc;

   /** Utility Computer Task */
   public static String UtilCompWiz_Name;

   /** Use this task for cluster visualization */
   public static String ViewClustWiz_Desc;

   /** View Clusters */
   public static String ViewClustWiz_Name;

   /** Specified directory does not exist and will be automatically created */
   public static String Warn_DirNotExist;

   /** File already exists and would be overwritten */
   public static String Warn_FileAlreadyExist;

   /*
    * Initialize the resource bundle
    */
   static {
      NLS.initializeMessages(BUNDLE_NAME, Messages.class);
   }

   /**
    * Instantiates a new wizard messages.
    */
   private Messages() {
      // left empty
   }

}
