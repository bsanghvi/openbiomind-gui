/**
 * CompletePipelineWizardPage2.java
 *
 * The file CompletePipelineWizardPage2.java.
 *
 * $Id$
 */
package openbiomind.gui.wizards;

import openbiomind.gui.common.TextButtonComposite;
import openbiomind.gui.data.ClassificationMethodEnum;
import openbiomind.gui.data.ClusteringColorsEnum;
import openbiomind.gui.data.DatasetClusteringMetricEnum;
import openbiomind.gui.data.FeatureSelectionMethodEnum;
import openbiomind.gui.data.ShuffleEnum;
import openbiomind.gui.preferences.Preference;
import openbiomind.gui.util.Utility;
import openbiomind.gui.util.WidgetHelper;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

/**
 * The class CompletePipelineWizardPage2.
 *
 * @author bsanghvi
 * @since Aug 2, 2008
 * @version Aug 3, 2008
 */
public class CompletePipelineWizardPage2 extends AbstractTaskWizardPage implements IWizardPage {

   /**
    * The constant for page name (value = <code>openbiomind.gui.wizards.CompletePipelineWizardPage2</code>).
    */
   public static final String PAGE_NAME = "openbiomind.gui.wizards.CompletePipelineWizardPage2"; //$NON-NLS-1$

   /** The number of columns in various groups. */
   private static final int NUM_COLUMN_IN_GROUP = 3;

   /** The ontology description file text button composite. */
   private TextButtonComposite ontologyDescriptionFileTBC = null;

   /** The ontology association file text button composite. */
   private TextButtonComposite ontologyAssociationFileTBC = null;

   /** The target category combo. */
   private Combo targetCategoryCombo = null;

   /** The number of folds text. */
   private Text numberOfFoldsText = null;

   /** The number of selected features text. */
   private Text numberOfSelectedFeaturesText = null;

   /** The feature selection method combo. */
   private Combo featureSelectionMethodCombo = null;

   /** The number of tasks text. */
   private Text numberOfTasksText = null;

   /** The feature classification method combo. */
   private Combo classificationMethodCombo = null;

   /** The meta task shuffling combo. */
   private Combo metaTaskShufflingCombo = null;

   /** The dataset clustering metric combo. */
   private Combo datasetClusteringMetricCombo = null;

   /** The target category combo. */
   private Combo clusteringColorsCombo = null;

   /** The maximum co-occurrence edges text. */
   private Text maximumCoOccurrenceEdgesText = null;

   /** The maximum co-expression edges text. */
   private Text maximumCoExpressionEdgesText = null;

   /** The top useful features text. */
   private Text topUsefulFeaturesText = null;

   /** The is feature selected combo. */
   private Combo isFeatureSelectedCombo = null;

   /** The is folded combo. */
   private Combo isFoldedCombo = null;

   /**
    * Instantiates a new dataset transformer wizard page.
    *
    * @param pageTitle the page title
    * @param pageDescription the page description
    */
   public CompletePipelineWizardPage2(final String pageTitle, final String pageDescription) {
      super(PAGE_NAME, pageTitle, pageDescription);
   }

   /*
    * @see openbiomind.gui.wizards.AbstractTaskWizardPage#createBaseComposite(org.eclipse.swt.widgets.Composite)
    */
   @Override
   protected Composite createBaseComposite(final Composite parent) {
      final Composite composite = new Composite(parent, SWT.NULL);

      // apply layout
      GUI.GRID_DATA_FILL_H_GRAB_H.applyTo(composite);
      GUI.GRID_LAYOUT_WITH_MARGIN.copy().numColumns(NUM_COLUMN_IN_GROUP).applyTo(composite);

      // add components
      createGroup(composite);

      // this page is complete by default as it is optional
      setPageComplete(true);

      return composite;
   }

   /**
    * Creates the required group.
    *
    * @param parent the parent
    */
   private void createGroup(final Composite parent) {
      // Ontology description file
      WidgetHelper.createNewFieldLabel(parent, Messages.Label_OntDescFile);
      this.ontologyDescriptionFileTBC = createNewOptionalFileTextButtonComposite(parent, NUM_COLUMN_IN_GROUP - 1);

      // Ontology association file
      WidgetHelper.createNewFieldLabel(parent, Messages.Label_OntAssoFile);
      this.ontologyAssociationFileTBC = createNewOptionalFileTextButtonComposite(parent, NUM_COLUMN_IN_GROUP - 1);

      // Target category
      // TODO Read from the given input files
      WidgetHelper.createNewFieldLabel(parent, Messages.Label_TargetCat);
      this.targetCategoryCombo = createDefaultDropDownCombo(parent, getTargetCategoryArray());
      WidgetHelper.createNewBlankLabel(parent);

      // Number of Folds
      WidgetHelper.createNewFieldLabel(parent, Messages.Label_NumOfFolds);
      this.numberOfFoldsText = createNewNumberOnlyText(parent);
      WidgetHelper.createNewBlankLabel(parent);

      // Number of selected features
      WidgetHelper.createNewFieldLabel(parent, Messages.Label_NumOfSelecFeatures);
      this.numberOfSelectedFeaturesText = createNewNumberOnlyText(parent);
      WidgetHelper.createNewBlankLabel(parent);

      // Feature selection method
      WidgetHelper.createNewFieldLabel(parent, Messages.Label_FeatureSelecMethod);
      this.featureSelectionMethodCombo = createDefaultReadOnlyCombo(parent, getFeatureSelectionMethodArray());
      WidgetHelper.createNewBlankLabel(parent);

      // Number of tasks
      WidgetHelper.createNewFieldLabel(parent, Messages.Label_NumOfTasks);
      this.numberOfTasksText = createNewNumberOnlyText(parent);
      WidgetHelper.createNewBlankLabel(parent);

      // Classification method
      WidgetHelper.createNewFieldLabel(parent, Messages.Label_ClassMethod);
      this.classificationMethodCombo = createDefaultReadOnlyCombo(parent, getClassificationMethodArray());
      WidgetHelper.createNewBlankLabel(parent);

      // MetaTask shuffling
      WidgetHelper.createNewFieldLabel(parent, Messages.Label_MetaShuffling);
      this.metaTaskShufflingCombo = createDefaultReadOnlyCombo(parent, getMetaTaskShufflingArray());
      WidgetHelper.createNewBlankLabel(parent);

      // Dataset clustering metric
      WidgetHelper.createNewFieldLabel(parent, Messages.Label_DataClustMetric);
      this.datasetClusteringMetricCombo = createDefaultReadOnlyCombo(parent, getDatasetClusteringMetricArray());
      WidgetHelper.createNewBlankLabel(parent);

      // Clustering colors
      WidgetHelper.createNewFieldLabel(parent, Messages.Label_ClustColor, Messages.Tip_ClustColor);
      this.clusteringColorsCombo = createDefaultReadOnlyCombo(parent, getClusteringColorsArray());
      WidgetHelper.createNewBlankLabel(parent);

      // Maximum co-occurrence edges
      WidgetHelper.createNewFieldLabel(parent, Messages.Label_MaxCoOccEdge);
      this.maximumCoOccurrenceEdgesText = createNewNumberOnlyText(parent);
      WidgetHelper.createNewBlankLabel(parent);

      // Maximum co-expression edges
      WidgetHelper.createNewFieldLabel(parent, Messages.Label_MaxCoExpEdge);
      this.maximumCoExpressionEdgesText = createNewNumberOnlyText(parent);
      WidgetHelper.createNewBlankLabel(parent);

      // Top useful features
      WidgetHelper.createNewFieldLabel(parent, Messages.Label_TopUsefulFeatures);
      this.topUsefulFeaturesText = createNewNumberOnlyText(parent);
      WidgetHelper.createNewBlankLabel(parent);

      // Is feature selected
      WidgetHelper.createNewFieldLabel(parent, Messages.Label_IsFeatureSel);
      this.isFeatureSelectedCombo = createDefaultReadOnlyCombo(parent, getBooleanValueArray());
      WidgetHelper.createNewBlankLabel(parent);

      // Is folded
      WidgetHelper.createNewFieldLabel(parent, Messages.Label_IsFolded);
      this.isFoldedCombo = createDefaultReadOnlyCombo(parent, getBooleanValueArray());
      WidgetHelper.createNewBlankLabel(parent);
   }

   /**
    * Gets the ontology description file.
    *
    * @return the ontology description file
    */
   public String getOntologyDescriptionFile() {
      return getOntologyDescriptionFileTBC().getText();
   }

   /**
    * Gets the ontology description file text button composite.
    *
    * @return the ontology description file text button composite
    */
   private TextButtonComposite getOntologyDescriptionFileTBC() {
      return this.ontologyDescriptionFileTBC;
   }

   /**
    * Gets the ontology association file.
    *
    * @return the ontology association file
    */
   public String getOntologyAssociationFile() {
      return getOntologyAssociationFileTBC().getText();
   }

   /**
    * Gets the ontology association file text button composite.
    *
    * @return the ontology association file text button composite
    */
   private TextButtonComposite getOntologyAssociationFileTBC() {
      return this.ontologyAssociationFileTBC;
   }

   /**
    * Gets the target category.
    *
    * @return the target category
    */
   public String getTargetCategory() {
      return getTargetCategoryCombo().getText();
   }

   /**
    * Gets the target category combo.
    *
    * @return the target category combo
    */
   private Combo getTargetCategoryCombo() {
      return this.targetCategoryCombo;
   }

   /**
    * Gets the number of folds.
    *
    * @return the number of folds
    */
   public Integer getNumberOfFolds() {
      try {
         return Integer.valueOf(getNumberOfFoldsText().getText());
      } catch (final NumberFormatException e) {
         return null;
      }
   }

   /**
    * Gets the number of folds text.
    *
    * @return the number of folds text
    */
   private Text getNumberOfFoldsText() {
      return this.numberOfFoldsText;
   }

   /**
    * Gets the number of selected features.
    *
    * @return the number of selected features
    */
   public Integer getNumberOfSelectedFeatures() {
      try {
         return Integer.valueOf(getNumberOfSelectedFeaturesText().getText());
      } catch (final NumberFormatException e) {
         return null;
      }
   }

   /**
    * Gets the enhanced dataset file path text.
    *
    * @return the enhancedDatasetFilePathText
    */
   private Text getNumberOfSelectedFeaturesText() {
      return this.numberOfSelectedFeaturesText;
   }

   /**
    * Gets the feature selection method.
    *
    * @return the feature selection method
    */
   public FeatureSelectionMethodEnum getFeatureSelectionMethod() {
      return FeatureSelectionMethodEnum.parse(getFeatureSelectionMethodArray()[getFeatureSelectionMethodCombo()
            .getSelectionIndex()]);
   }

   /**
    * Gets the feature selection method combo.
    *
    * @return the feature selection method combo
    */
   private Combo getFeatureSelectionMethodCombo() {
      return this.featureSelectionMethodCombo;
   }

   /**
    * Gets the number of tasks.
    *
    * @return the number of tasks
    */
   public Integer getNumberOfTasks() {
      try {
         return Integer.valueOf(getNumberOfTasksText().getText());
      } catch (final NumberFormatException e) {
         return null;
      }
   }

   /**
    * Gets the number of tasks text.
    *
    * @return the number of tasks
    */
   private Text getNumberOfTasksText() {
      return this.numberOfTasksText;
   }

   /**
    * Gets the classification method.
    *
    * @return the classification method
    */
   public ClassificationMethodEnum getClassificationMethod() {
      return ClassificationMethodEnum.parse(getClassificationMethodArray()[getClassificationMethodCombo()
            .getSelectionIndex()]);
   }

   /**
    * Gets the classification method combo.
    *
    * @return the classification method combo
    */
   private Combo getClassificationMethodCombo() {
      return this.classificationMethodCombo;
   }

   /**
    * Gets the meta task shuffling.
    *
    * @return the meta task shuffling
    */
   public ShuffleEnum getMetaTaskShuffling() {
      return ShuffleEnum.parse(getMetaTaskShufflingArray()[getMetaTaskShufflingCombo().getSelectionIndex()]);
   }

   /**
    * Gets the meta task shuffling combo.
    *
    * @return the meta task shuffling combo
    */
   private Combo getMetaTaskShufflingCombo() {
      return this.metaTaskShufflingCombo;
   }

   /**
    * Gets the dataset clustering metric.
    *
    * @return the dataset clustering metric
    */
   public DatasetClusteringMetricEnum getDatasetClusteringMetric() {
      return DatasetClusteringMetricEnum.parse(getDatasetClusteringMetricArray()[getDatasetClusteringMetricCombo()
            .getSelectionIndex()]);
   }

   /**
    * Gets the dataset clustering metric combo.
    *
    * @return the dataset clustering metric combo
    */
   private Combo getDatasetClusteringMetricCombo() {
      return this.datasetClusteringMetricCombo;
   }

   /**
    * Gets the is feature selected.
    *
    * @return the is feature selected
    */
   public Boolean getIsFeatureSelected() {
      final String value = getBooleanValueArray()[getIsFeatureSelectedCombo().getSelectionIndex()];
      return (!Utility.isEmpty(value) ? Boolean.valueOf(value) : null);
   }

   /**
    * Gets the is feature selected combo.
    *
    * @return the is feature selected combo
    */
   private Combo getIsFeatureSelectedCombo() {
      return this.isFeatureSelectedCombo;
   }

   /**
    * Gets the is folded.
    *
    * @return the is folded
    */
   public Boolean getIsFolded() {
      final String value = getBooleanValueArray()[getIsFoldedCombo().getSelectionIndex()];
      return (!Utility.isEmpty(value) ? Boolean.valueOf(value) : null);
   }

   /**
    * Gets the is folded combo.
    *
    * @return the is folded combo
    */
   private Combo getIsFoldedCombo() {
      return this.isFoldedCombo;
   }

   /**
    * Gets the clustering colors.
    *
    * @return the clustering colors
    */
   public ClusteringColorsEnum getClusteringColors() {
      return ClusteringColorsEnum.parse(getClusteringColorsCombo().getText());
   }

   /**
    * Gets the clustering colors combo.
    *
    * @return the clustering colors combo
    */
   private Combo getClusteringColorsCombo() {
      return this.clusteringColorsCombo;
   }

   /**
    * Gets the maximum co-occurrence edges.
    *
    * @return the maximum co-occurrence edges
    */
   public Integer getMaximumCoOccurrenceEdges() {
      try {
         return Integer.valueOf(getMaximumCoOccurrenceEdgesText().getText());
      } catch (final NumberFormatException e) {
         return null;
      }
   }

   /**
    * Gets the maximum co-occurrence edges text.
    *
    * @return the maximum co-occurrence edges text
    */
   private Text getMaximumCoOccurrenceEdgesText() {
      return this.maximumCoOccurrenceEdgesText;
   }

   /**
    * Gets the maximum co-expression edges.
    *
    * @return the maximum co-expression edges
    */
   public Integer getMaximumCoExpressionEdges() {
      try {
         return Integer.valueOf(getMaximumCoExpressionEdgesText().getText());
      } catch (final NumberFormatException e) {
         return null;
      }
   }

   /**
    * Gets the maximum co-expression edges text.
    *
    * @return the maximum co-expression edges text
    */
   private Text getMaximumCoExpressionEdgesText() {
      return this.maximumCoExpressionEdgesText;
   }

   /**
    * Gets the top useful features.
    *
    * @return the top useful features
    */
   public Integer getTopUsefulFeatures() {
      try {
         return Integer.valueOf(getTopUsefulFeaturesText().getText());
      } catch (final NumberFormatException e) {
         return null;
      }
   }

   /**
    * Gets the top useful features text.
    *
    * @return the top useful features text
    */
   private Text getTopUsefulFeaturesText() {
      return this.topUsefulFeaturesText;
   }

   /*
    * @see org.eclipse.jface.dialogs.DialogPage#setVisible(boolean)
    */
   @Override
   public void setVisible(final boolean visible) {
      if (visible) {
         final IWizardPage previousPage = getPreviousPage();
         if (previousPage instanceof CompletePipelineWizardPage1) {
            final CompletePipelineWizardPage1 completePipelineWizardPage1 = (CompletePipelineWizardPage1) previousPage;
            String propertyFile = completePipelineWizardPage1.getPropertyFile();
            if (Utility.isEmpty(propertyFile)) {
               propertyFile = Preference.getPipelinePropertiesPath();
            }
            setDescription(NLS.bind(Messages.CompPipeWiz_P2_Desc, propertyFile));
         }
      }

      super.setVisible(visible);
   }

   /*
    * @see openbiomind.gui.wizards.AbstractTaskWizardPage#validatePage()
    */
   @Override
   protected void validatePage() {
      final boolean valid = isProjectInformationValid() && getOntologyDescriptionFileTBC().isValid()
            && getOntologyAssociationFileTBC().isValid();
      setPageComplete(valid);
      if (!valid) {
         setErrorMessage(Messages.Err_FixErrToContinue);
      } else {
         setErrorMessage(null);
      }
   }

}
