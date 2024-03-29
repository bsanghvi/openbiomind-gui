/* $Id$ */
/*
 *  OpenBiomind-GUI: GUI for OpenBiomind
 *  Copyright (C) 2008  Bhavesh Sanghvi
 *
 *  This file (ClusteringTransformerWizardPage.java) is part of OpenBiomind-GUI.
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
import openbiomind.gui.data.ClusteringTransformerTaskData;
import openbiomind.gui.data.TransformEnum;
import openbiomind.gui.util.Utility;
import openbiomind.gui.util.WidgetHelper;

import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

/**
 * The class ClusteringTransformerWizardPage.
 * 
 * @author bsanghvi
 * @since Jul 13, 2008
 * @version Aug 18, 2008
 */
public class ClusteringTransformerWizardPage extends AbstractTaskWizardPage implements IWizardPage {

   /** The constant for page name (value = <code>openbiomind.gui.wizards.ClusteringTransformerWizardPage</code>). */
   public static final String PAGE_NAME = "openbiomind.gui.wizards.ClusteringTransformerWizardPage"; //$NON-NLS-1$

   /** The number of columns in various groups. */
   private static final int NUM_COLUMN_IN_GROUP = 3;

   /** The dataset file text button composite. */
   private TextButtonComposite datasetFileTBC = null;

   /** The output file destination file text. */
   private Text outputFileDestFileText = null;

   /** The valid output file destination file name. */
   private boolean validOutputFileDestFileName = false;

   /** The output file destination directory text button composite. */
   private TextButtonComposite outputFileDestDirTBC = null;

   /** The output file path text. */
   private Text outputFilePathText = null;

   /** The valid output file path. */
   private boolean validOutputFilePath = false;

   /** The transform combo. */
   private Combo transformCombo = null;

   /** The transform array. */
   private String[] transformArray = null;

   /** The meta task result directory text button composite. */
   private TextButtonComposite metaTaskResultDirTBC = null;

   /** The meta task result directory error decoration. */
   private ControlDecoration metaTaskResultDirErrorDecoration = null;

   /**
    * Instantiates a new clustering transformer wizard page.
    */
   public ClusteringTransformerWizardPage() {
      this(Messages.ClustTransWiz_Name, Messages.ClustTransWiz_Desc);
   }

   /**
    * Instantiates a new clustering transformer wizard page.
    * 
    * @param pageTitle the page title
    * @param pageDescription the page description
    */
   public ClusteringTransformerWizardPage(final String pageTitle, final String pageDescription) {
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

      // Dataset file
      WidgetHelper.createNewFieldLabel(parent, Messages.Label_SrcData, Messages.Tip_SrcData, true);
      this.datasetFileTBC = createDatasetFileTBC(parent);

      // Transform
      WidgetHelper.createNewFieldLabel(parent, Messages.Label_Transform, true);
      this.transformCombo = createTransformCombo(parent);
      WidgetHelper.createNewBlankLabel(parent);

      // Output file
      // - leave a blank row
      WidgetHelper.createNewBlankLabel(parent, NUM_COLUMN_IN_GROUP);
      // - Detail row: Specify the output file
      WidgetHelper.createNewDetailsLabel(parent, Messages.Tip_OutFile, NUM_COLUMN_IN_GROUP);
      // - File name
      WidgetHelper.createNewFieldLabel(parent, Messages.Label_File, Messages.Tip_OutFile, true);
      this.outputFileDestFileText = createOutputFileDestFileText(parent);
      WidgetHelper.createNewBlankLabel(parent);
      // - Directory
      WidgetHelper.createNewFieldLabel(parent, Messages.Label_Dir, Messages.Tip_LeaveBlankToUseCurrDirOrSpecifyDir);
      this.outputFileDestDirTBC = createOutputFileDestDirTBC(parent);
      WidgetHelper.createNewFieldLabel(parent, Messages.Label_FilePath, Messages.Tip_OutFilePath);
      this.outputFilePathText = createOutputFilePathText(parent);
   }

   /**
    * Creates the dataset text file button composite.
    * 
    * @param parent the parent
    * 
    * @return the text button composite
    */
   private TextButtonComposite createDatasetFileTBC(final Composite parent) {
      final TextButtonComposite textButtonComposite = new TextButtonComposite(parent) {

         @Override
         protected String buttonSelected() {
            return getFileDialog().open();
         }

      };
      textButtonComposite.setValid(false);
      textButtonComposite.setToolTipText(Messages.Tip_SrcData);

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
            handleModifyText(textButtonComposite, errorDecoration, Utility.fileExists(textButtonComposite.getText()));
         }

      });

      return textButtonComposite;
   }

   /**
    * Creates the transform combo.
    * 
    * @param parent the parent
    * 
    * @return the combo
    */
   private Combo createTransformCombo(final Composite parent) {
      final Combo combo = createDefaultReadOnlyCombo(parent, getTransformArray());
      combo.addSelectionListener(new SelectionAdapter() {

         @Override
         public void widgetSelected(final SelectionEvent e) {
            validateMetaTaskResultDir();
         }

      });

      return combo;
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

      // MetaTask result directory
      WidgetHelper.createNewFieldLabel(parent, Messages.Label_MetaResultDir, Messages.Tip_MetaResultDir);
      this.metaTaskResultDirTBC = createMetaTaskResultDirTBC(parent);
   }

   /**
    * Creates the meta task result directory text button composite.
    * 
    * @param parent the parent
    * 
    * @return the text button composite
    */
   private TextButtonComposite createMetaTaskResultDirTBC(final Composite parent) {
      final TextButtonComposite textButtonComposite = new TextButtonComposite(parent) {

         @Override
         protected String buttonSelected() {
            return getDirectoryDialog().open();
         }

      };
      textButtonComposite.setValid(true);
      textButtonComposite.setToolTipText(Messages.Tip_MetaResultDir);

      // apply layout
      GUI.GRID_DATA_FILL_H_GRAB_H.copy().span(NUM_COLUMN_IN_GROUP - 1, 1).applyTo(textButtonComposite);

      // create decorations
      final ControlDecoration infoDecoration = WidgetHelper.createNewInformationDecoration(textButtonComposite
            .getTextField(), Messages.Tip_LeaveBlankOrSpecifyDir);
      infoDecoration.setShowOnlyOnFocus(true);
      setMetaTaskResultDirErrorDecoration(WidgetHelper.createNewErrorDecoration(textButtonComposite,
            Messages.Err_InvalidDir_MetaResult));
      getMetaTaskResultDirErrorDecoration().hide();

      // apply listeners
      textButtonComposite.addModifyListenerOnTextField(new ModifyListener() {

         @Override
         public void modifyText(final ModifyEvent event) {
            validateMetaTaskResultDir();
         }

      });

      return textButtonComposite;
   }

   /**
    * Gets the dataset file.
    * 
    * @return the dataset file
    */
   public String getDatasetFile() {
      return getDatasetFileTBC().getText();
   }

   /**
    * Gets the base dataset text button composite.
    * 
    * @return the base dataset text button composite
    */
   private TextButtonComposite getDatasetFileTBC() {
      return this.datasetFileTBC;
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
    * @return true, if checks if is valid output file destination file name
    */
   private boolean isValidOutputFileDestFileName() {
      return this.validOutputFileDestFileName;
   }

   /**
    * Sets the valid output file destination file name.
    * 
    * @param validOutputFileDestFileName the valid output file destination file name
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
      final String fileName = getOutputFileDestFileName();
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
    * @return the outputFilePathText
    */
   private Text getOutputFilePathText() {
      return this.outputFilePathText;
   }

   /**
    * Checks if is valid output file path.
    * 
    * @return the validOutputFilePath
    */
   private boolean isValidOutputFilePath() {
      return this.validOutputFilePath;
   }

   /**
    * Sets the valid output file path.
    * 
    * @param validOutputFilePath the valid output file path
    */
   private void setValidOutputFilePath(final boolean validOutputFilePath) {
      this.validOutputFilePath = validOutputFilePath;
   }

   /**
    * Gets the transform.
    * 
    * @return the transform
    */
   public TransformEnum getTransform() {
      return TransformEnum.parse(getTransformArray()[getTransformCombo().getSelectionIndex()]);
   }

   /**
    * Gets the transform combo.
    * 
    * @return the transform combo
    */
   private Combo getTransformCombo() {
      return this.transformCombo;
   }

   /**
    * Gets the transform array.
    * 
    * @return the transform array
    */
   private String[] getTransformArray() {
      if (this.transformArray == null) {
         this.transformArray = new String[] { TransformEnum.HORIZONTAL.toString(), TransformEnum.VERTICAL.toString(),
               TransformEnum.MUTIC.toString(), TransformEnum.MOBRA.toString() };
      }

      return this.transformArray;
   }

   /**
    * Gets the meta task result directory.
    * 
    * @return the meta task result directory
    */
   public String getMetaTaskResultDir() {
      return getMetaTaskResultDirTBC().getText();
   }

   /**
    * Gets meta task the result directory text button composite.
    * 
    * @return the meta task result directory text button composite
    */
   private TextButtonComposite getMetaTaskResultDirTBC() {
      return this.metaTaskResultDirTBC;
   }

   /**
    * Gets the meta task result directory error decoration.
    * 
    * @return the meta task result directory error decoration
    */
   private ControlDecoration getMetaTaskResultDirErrorDecoration() {
      return this.metaTaskResultDirErrorDecoration;
   }

   /**
    * Sets the meta task result directory error decoration.
    * 
    * @param metaTaskResultDirErrorDecoration the new meta task result directory error decoration
    */
   private void setMetaTaskResultDirErrorDecoration(final ControlDecoration metaTaskResultDirErrorDecoration) {
      this.metaTaskResultDirErrorDecoration = metaTaskResultDirErrorDecoration;
   }

   /**
    * Validate meta task result directory.
    */
   private void validateMetaTaskResultDir() {
      final boolean required;
      switch (getTransform()) {
         case MUTIC:
         case MOBRA:
            required = true;
            break;
         case HORIZONTAL:
         case VERTICAL:
         default:
            required = false;
            break;
      }

      final String directoryName = getMetaTaskResultDir();
      if (!required && Utility.isEmpty(directoryName)) {
         getMetaTaskResultDirTBC().setValid(true);
      } else {
         final File directory = new File(directoryName);
         getMetaTaskResultDirTBC()
               .setValid(
                     Utility.directoryExists(directory)
                           && Utility.listFileNames(directory, Resources.OUT_FILE_STARTS_WITH, Resources.TXT_EXTENSION).length > 0);
      }

      if (getMetaTaskResultDirTBC().isValid()) {
         getMetaTaskResultDirErrorDecoration().hide();
      } else {
         getMetaTaskResultDirErrorDecoration().show();
         getMetaTaskResultDirErrorDecoration()
               .showHoverText(getMetaTaskResultDirErrorDecoration().getDescriptionText());
      }

      validatePage();
   }

   /*
    * @see openbiomind.gui.wizards.AbstractTaskWizardPage#validatePage()
    */
   @Override
   protected void validatePage() {
      final boolean valid = isProjectInformationValid() && getDatasetFileTBC().isValid()
            && isValidOutputFileDestFileName() && getOutputFileDestDirTBC().isValid() && isValidOutputFilePath()
            && getMetaTaskResultDirTBC().isValid();
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
   public ClusteringTransformerTaskData prepareTaskData() {
      final ClusteringTransformerTaskData clusteringTransformerTaskData = new ClusteringTransformerTaskData();
      clusteringTransformerTaskData.setDatasetFile(getDatasetFile());
      clusteringTransformerTaskData.setOutputFile(getOutputFile());
      clusteringTransformerTaskData.setTransform(getTransform());
      clusteringTransformerTaskData.setMetaTaskResultDir(getMetaTaskResultDir());

      return clusteringTransformerTaskData;
   }

}
