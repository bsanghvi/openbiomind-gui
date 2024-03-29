/* $Id$ */
/*
 *  OpenBiomind-GUI: GUI for OpenBiomind
 *  Copyright (C) 2008  Bhavesh Sanghvi
 *
 *  This file (GraphFeaturesWizardPage.java) is part of OpenBiomind-GUI.
 *
 *  OpenBiomind-GUI is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  OpenBiomind-GUI is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with OpenBiomind-GUI.  If not, see <http://www.gnu.org/licenses/>.
 *
 *  Please visit the following pages to contact the author(s):
 *  Homepage: http://code.google.com/p/openbiomind-gui/
 *  Mailing list: http://groups.google.com/group/openbiomind-gui/
 */

package openbiomind.gui.wizards;

import java.io.File;

import openbiomind.gui.common.TextButtonComposite;
import openbiomind.gui.data.GraphFeaturesTaskData;
import openbiomind.gui.data.TransformEnum;
import openbiomind.gui.util.Utility;
import openbiomind.gui.util.WidgetHelper;

import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

/**
 * The class GraphFeaturesWizardPage.
 * 
 * @author bsanghvi
 * @since Jul 20, 2008
 * @version Aug 18, 2008
 */
public class GraphFeaturesWizardPage extends AbstractTaskWizardPage implements IWizardPage {

   /** The constant for page name (value = <code>openbiomind.gui.wizards.GraphFeaturesWizardPage</code>). */
   public static final String PAGE_NAME = "openbiomind.gui.wizards.GraphFeaturesWizardPage"; //$NON-NLS-1$

   /** The number of columns in various groups. */
   private static final int NUM_COLUMN_IN_GROUP = 3;

   /** The horizontal dataset text button composite. */
   private TextButtonComposite horizontalDatasetTBC = null;

   /** The MOBRA dataset text button composite. */
   private TextButtonComposite mobraDatasetTBC = null;

   /** The utility file text button composite. */
   private TextButtonComposite utilityFileTBC = null;

   /** The output file destination file text. */
   private Text outputFileDestFileText = null;

   /** The valid output file destination file name. */
   private boolean validOutputFileDestFileName = false;

   /** The output file destination extension combo. */
   private Combo outputFileDestExtCombo = null;

   /** The output file destination extension array. */
   private String[] outputFileDestExtArray = null;

   /** The output file destination directory text button composite. */
   private TextButtonComposite outputFileDestDirTBC = null;

   /** The output file path text. */
   private Text outputFilePathText = null;

   /** The valid output file path. */
   private boolean validOutputFilePath = false;

   /** The maximum co-occurrence edges text. */
   private Text maximumCoOccurrenceEdgesText = null;

   /** The maximum co-expression edges text. */
   private Text maximumCoExpressionEdgesText = null;

   /** The top useful features text. */
   private Text topUsefulFeaturesText = null;

   /**
    * Instantiates a new graph features wizard page.
    */
   public GraphFeaturesWizardPage() {
      this(Messages.GraFeatureWiz_Name, Messages.GraFeatureWiz_Desc);
   }

   /**
    * Instantiates a new graph features wizard page.
    * 
    * @param pageTitle the page title
    * @param pageDescription the page description
    */
   public GraphFeaturesWizardPage(final String pageTitle, final String pageDescription) {
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
      addProjectInformationFields(composite);
      createRequiredGroup(composite);
      createOptionalGroup(composite);

      return composite;
   }

   /**
    * Creates the required group.
    * 
    * @param parent the parent
    */
   private void createRequiredGroup(final Composite parent) {
      // Required Arguments
      addSection(parent, Messages.Label_ReqdArg, NUM_COLUMN_IN_GROUP);

      // Horizontal dataset
      WidgetHelper.createNewFieldLabel(parent, Messages.Label_HData, true);
      this.horizontalDatasetTBC = createSelectFileTBC(parent, Messages.Label_HData);

      // MOBRA dataset
      WidgetHelper.createNewFieldLabel(parent, Messages.Label_MobraData, true);
      this.mobraDatasetTBC = createSelectFileTBC(parent, Messages.Label_MobraData);

      // Utility file
      WidgetHelper.createNewFieldLabel(parent, Messages.Label_UtilFile, Messages.Tip_UtilFile, true);
      this.utilityFileTBC = createSelectFileTBC(parent, Messages.Tip_UtilFile);

      // Output file
      // - leave a blank row
      WidgetHelper.createNewBlankLabel(parent, NUM_COLUMN_IN_GROUP);
      // - Detail row: Specify the output file
      WidgetHelper.createNewDetailsLabel(parent, Messages.Tip_OutFile, NUM_COLUMN_IN_GROUP);
      // - File name
      WidgetHelper.createNewFieldLabel(parent, Messages.Label_File, Messages.Tip_OutFile, true);
      this.outputFileDestFileText = createOutputFileDestFileText(parent);
      this.outputFileDestExtCombo = createDefaultReadOnlyCombo(parent, getOutputFileDestExtArray(), false);
      // FIXME Presently only one format is supported, so this combo is disabled
      if (getOutputFileDestExtArray().length == 1) {
         getOutputFileDestExtCombo().setEnabled(false);
      }
      // - Directory
      WidgetHelper.createNewFieldLabel(parent, Messages.Label_Dir, Messages.Tip_LeaveBlankToUseCurrDirOrSpecifyDir);
      this.outputFileDestDirTBC = createOutputFileDestDirTBC(parent);
      // - Path
      WidgetHelper.createNewFieldLabel(parent, Messages.Label_FilePath, Messages.Tip_OutFilePath);
      this.outputFilePathText = createOutputFilePathText(parent);
   }

   /**
    * Creates the select file text button composite.
    * 
    * @param parent the parent
    * @param tooltip the tool tip
    * 
    * @return the text button composite
    */
   private TextButtonComposite createSelectFileTBC(final Composite parent, final String tooltip) {
      final TextButtonComposite textButtonComposite = new TextButtonComposite(parent) {

         @Override
         protected String buttonSelected() {
            return getFileDialog().open();
         }

      };
      textButtonComposite.setValid(false);
      if (!Utility.isEmpty(tooltip)) {
         textButtonComposite.setToolTipText(tooltip);
      }

      // apply layout
      GUI.GRID_DATA_FILL_H_GRAB_H.copy().span(NUM_COLUMN_IN_GROUP - 1, 1).applyTo(textButtonComposite);

      // create decorations
      final ControlDecoration errorDecoration = WidgetHelper.createNewErrorDecoration(textButtonComposite,
            Messages.Err_FileNotExist);
      errorDecoration.show();

      // apply listeners
      textButtonComposite.addModifyListenerOnTextField(new ModifyListener() {

         @Override
         public void modifyText(final ModifyEvent event) {
            handleModifyTextWhenEditable(textButtonComposite, errorDecoration, Utility.fileExists(textButtonComposite
                  .getText()));
         }

      });

      return textButtonComposite;
   }

   /**
    * Creates the output file destination file text.
    * 
    * @param parent the parent
    * 
    * @return the text
    */
   private Text createOutputFileDestFileText(final Composite parent) {
      final Text text = new Text(parent, SWT.SINGLE | SWT.BORDER);
      text.setToolTipText(Messages.Tip_OutFile);
      setValidOutputFileDestFileName(false);

      // apply layout
      GUI.GRID_DATA_DEFAULT.applyTo(text);

      // create decorations
      final ControlDecoration errorDecoration = WidgetHelper.createNewErrorDecoration(text, Messages.Err_InvalidFile);
      errorDecoration.show();

      // apply listeners
      text.addModifyListener(new ModifyListener() {

         @Override
         public void modifyText(final ModifyEvent event) {
            setValidOutputFileDestFileName(!Utility.isEmpty(text.getText()));
            handleErrorDecoration(errorDecoration, isValidOutputFileDestFileName());
            getOutputFilePathText().setText(getOutputFile());
            validatePage();
         }

      });

      return text;
   }

   /**
    * Creates the output file destination directory text button composite.
    * 
    * @param parent the parent
    * 
    * @return the text button composite
    */
   private TextButtonComposite createOutputFileDestDirTBC(final Composite parent) {
      final TextButtonComposite textButtonComposite = new TextButtonComposite(parent) {

         @Override
         protected String buttonSelected() {
            return getDirectoryDialog().open();
         }

      };
      textButtonComposite.setText(Properties.CURRENT_DIRECTORY);
      textButtonComposite.setValid(true);
      textButtonComposite.setToolTipText(Messages.Tip_LeaveBlankToUseCurrDirOrSpecifyDir);

      // apply layout
      GUI.GRID_DATA_FILL_H_GRAB_H.copy().span(NUM_COLUMN_IN_GROUP - 1, 1).applyTo(textButtonComposite);

      // create decorations
      final Text textField = textButtonComposite.getTextField();
      final ControlDecoration infoDecoration = WidgetHelper.createNewInformationDecoration(textField,
            Messages.Tip_LeaveBlankToUseCurrDirOrSpecifyDir);
      infoDecoration.setShowOnlyOnFocus(true);
      final ControlDecoration warningDecoration = WidgetHelper.createNewWarningDecoration(textField,
            Messages.Warn_DirNotExist);
      warningDecoration.hide();
      final ControlDecoration errorDecoration = WidgetHelper.createNewErrorDecoration(textField,
            Messages.Err_InvalidDir);
      errorDecoration.hide();

      // apply listeners
      textButtonComposite.addModifyListenerOnTextField(new ModifyListener() {

         @Override
         public void modifyText(final ModifyEvent event) {
            boolean inError = false;
            boolean inWarning = false;
            final String directoryPath = textButtonComposite.getText();

            if (!Utility.isEmpty(directoryPath)) {
               final File directory = new File(directoryPath);
               if (directory.isFile()) {
                  inError = true;
               } else if (!directory.exists()) {
                  inWarning = true;
               }
            }

            textButtonComposite.setValid(!inError);
            showErrorOrWarning(inError, errorDecoration, inWarning, warningDecoration, infoDecoration);

            getOutputFilePathText().setText(getOutputFile());

            validatePage();
         }

      });

      return textButtonComposite;
   }

   /**
    * Gets the output file path text. This method uses {@link #getOutputFile()}.
    * 
    * @param parent the parent
    * 
    * @return the output file path text
    */
   private Text createOutputFilePathText(final Composite parent) {
      final Text text = new Text(parent, SWT.SINGLE | SWT.BORDER | SWT.READ_ONLY);
      text.setToolTipText(Messages.Tip_OutFilePath);

      // apply layout
      GUI.GRID_DATA_FILL_H_GRAB_H.copy().span(NUM_COLUMN_IN_GROUP - 1, 1).applyTo(text);

      // create decorations
      final ControlDecoration warningDecoration = WidgetHelper.createNewWarningDecoration(text,
            Messages.Warn_FileAlreadyExist);
      warningDecoration.hide();
      final ControlDecoration errorDecoration = WidgetHelper.createNewErrorDecoration(text, Messages.Err_InvalidFile);
      errorDecoration.hide();

      // apply listeners
      text.addModifyListener(new ModifyListener() {

         @Override
         public void modifyText(final ModifyEvent event) {
            boolean inError = false;
            boolean inWarning = false;
            final File file = new File(getOutputFile());

            if (file.isDirectory()) {
               inError = true;
            } else if (file.exists()) {
               inWarning = true;
            }

            setValidOutputFilePath(!inError);
            showErrorOrWarning(inError, errorDecoration, inWarning, warningDecoration);
            validatePage();
         }

      });

      return text;
   }

   /**
    * Creates the optional group.
    * 
    * @param parent the parent
    */
   private void createOptionalGroup(final Composite parent) {
      // Optional Arguments
      addSection(parent, Messages.Label_OptionalArg, NUM_COLUMN_IN_GROUP);

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
   }

   /**
    * Gets the horizontal dataset.
    * 
    * @return the horizontal dataset
    */
   public String getHorizontalDataset() {
      return getHorizontalDatasetTBC().getText();
   }

   /**
    * Gets the horizontal dataset text button composite.
    * 
    * @return the horizontal dataset text button composite
    */
   private TextButtonComposite getHorizontalDatasetTBC() {
      return this.horizontalDatasetTBC;
   }

   /**
    * Gets the MOBRA dataset.
    * 
    * @return the MOBRA dataset
    */
   public String getMobraDataset() {
      return getMobraDatasetTBC().getText();
   }

   /**
    * Gets the MOBRA dataset text button composite.
    * 
    * @return the MOBRA dataset text button composite
    */
   private TextButtonComposite getMobraDatasetTBC() {
      return this.mobraDatasetTBC;
   }

   /**
    * Gets the utility file.
    * 
    * @return the utility file
    */
   public String getUtilityFile() {
      return getUtilityFileTBC().getText();
   }

   /**
    * Gets the utility file text button composite.
    * 
    * @return the utility file text button composite
    */
   private TextButtonComposite getUtilityFileTBC() {
      return this.utilityFileTBC;
   }

   /**
    * Gets the output file destination file.
    * 
    * @return the output file destination file
    */
   private String getOutputFileDestFileName() {
      return getOutputFileDestFileText().getText();
   }

   /**
    * Gets the output file destination file text.
    * 
    * @return the output file destination file text
    */
   private Text getOutputFileDestFileText() {
      return this.outputFileDestFileText;
   }

   /**
    * Checks if is valid output file destination file name.
    * 
    * @return true, if is valid output file destination file name
    */
   private boolean isValidOutputFileDestFileName() {
      return this.validOutputFileDestFileName;
   }

   /**
    * Sets the valid output file destination file name.
    * 
    * @param validOutputFileDestFileName the new valid output file destination file name
    */
   private void setValidOutputFileDestFileName(final boolean validOutputFileDestFileName) {
      this.validOutputFileDestFileName = validOutputFileDestFileName;
   }

   /**
    * Gets the output file destination directory path.
    * 
    * @return the output file destination directory
    */
   private String getOutputFileDestDirPath() {
      return getOutputFileDestDirTBC().getText();
   }

   /**
    * Gets the output file destination extension.
    * 
    * @return the output file destination extension
    */
   private String getOutputFileDestFileExt() {
      return getOutputFileDestExtCombo().getText();
   }

   /**
    * Gets the output file destination extension combo.
    * 
    * @return the output file destination extension combo
    */
   private Combo getOutputFileDestExtCombo() {
      return this.outputFileDestExtCombo;
   }

   /**
    * Gets the output file destination extension array.
    * 
    * @return the output file destination extension array
    */
   private String[] getOutputFileDestExtArray() {
      if (this.outputFileDestExtArray == null) {
         this.outputFileDestExtArray = new String[] { Resources.DOT_EXTENSION }; // "png", "gif", "jpeg"
      }

      return this.outputFileDestExtArray;
   }

   /**
    * Gets the output file destination directory text button composite.
    * 
    * @return the output file destination directory text button composite
    */
   private TextButtonComposite getOutputFileDestDirTBC() {
      return this.outputFileDestDirTBC;
   }

   /**
    * Gets the output file.
    * 
    * @return the output file
    */
   public String getOutputFile() {
      final String directoryPath = getOutputFileDestDirPath();
      final String fileName = getOutputFileDestFileName() + getOutputFileDestFileExt();
      if (!Utility.exists(directoryPath)) {
         return Properties.CURRENT_DIRECTORY + File.separator + fileName;
      } else if (directoryPath.endsWith(File.separator)) {
         return directoryPath + fileName;
      } else {
         return directoryPath + File.separator + fileName;
      }
   }

   /**
    * Gets the output file path text.
    * 
    * @return the output file path text
    */
   private Text getOutputFilePathText() {
      return this.outputFilePathText;
   }

   /**
    * Checks if is valid output file path.
    * 
    * @return true, if is valid output file path
    */
   private boolean isValidOutputFilePath() {
      return this.validOutputFilePath;
   }

   /**
    * Sets the valid output file path.
    * 
    * @param validOutputFilePath the new valid output file path
    */
   private void setValidOutputFilePath(final boolean validOutputFilePath) {
      this.validOutputFilePath = validOutputFilePath;
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
    * @see openbiomind.gui.wizards.AbstractTaskWizardPage#setVisible(boolean)
    */
   @Override
   public void setVisible(final boolean visible) {
      if (visible) {
         final IWizardPage previousPage = getPreviousPage();
         if (previousPage instanceof UtilityComputerWizardPage) {
            disableComponent(getUtilityFileTBC(), ((UtilityComputerWizardPage) previousPage).getOutputFile());
         } else if (previousPage instanceof ClusteringTransformerWizardPage) {
            final ClusteringTransformerWizardPage clusteringTransformerWizardPage = (ClusteringTransformerWizardPage) previousPage;
            final TransformEnum transform = clusteringTransformerWizardPage.getTransform();
            final String outputFile = clusteringTransformerWizardPage.getOutputFile();
            if (transform == TransformEnum.HORIZONTAL) {
               disableComponent(getHorizontalDatasetTBC(), outputFile);
            } else if (transform == TransformEnum.MOBRA) {
               disableComponent(getMobraDatasetTBC(), outputFile);
            }
         }
      }

      super.setVisible(visible);
   }

   /*
    * @see openbiomind.gui.wizards.AbstractTaskWizardPage#validatePage()
    */
   @Override
   protected void validatePage() {
      final boolean valid = isProjectInformationValid() && getHorizontalDatasetTBC().isValid()
            && getMobraDatasetTBC().isValid() && getUtilityFileTBC().isValid() && isValidOutputFileDestFileName()
            && getOutputFileDestDirTBC().isValid() && isValidOutputFilePath();
      setPageComplete(valid);
      if (!valid) {
         setErrorMessage(Messages.Err_FixErrToContinue);
      } else {
         setErrorMessage(null);
      }
   }

   /*
    * @see openbiomind.gui.wizards.AbstractTaskWizardPage#prepareTaskData()
    */
   @Override
   public GraphFeaturesTaskData prepareTaskData() {
      final GraphFeaturesTaskData graphFeaturesTaskData = new GraphFeaturesTaskData();
      graphFeaturesTaskData.setHorizontalDataset(getHorizontalDataset());
      graphFeaturesTaskData.setMobraDataset(getMobraDataset());
      graphFeaturesTaskData.setUtilityFile(getUtilityFile());
      graphFeaturesTaskData.setOutputFile(getOutputFile());
      graphFeaturesTaskData.setMaximumCoOccurrenceEdges(getMaximumCoOccurrenceEdges());
      graphFeaturesTaskData.setMaximumCoExpressionEdges(getMaximumCoExpressionEdges());
      graphFeaturesTaskData.setTopUsefulFeatures(getTopUsefulFeatures());
      return graphFeaturesTaskData;
   }

}
