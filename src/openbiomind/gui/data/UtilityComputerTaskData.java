/* $Id$ */
/*
 *  OpenBiomind-GUI: GUI for OpenBiomind
 *  Copyright (C) 2008  Bhavesh Sanghvi
 *
 *  This file (UtilityComputerTaskData.java) is part of OpenBiomind-GUI.
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

package openbiomind.gui.data;

import openbiomind.gui.common.Argument;
import openbiomind.gui.project.TaskDataFolder;

/**
 * The class UtilityComputerTaskData is used by the UtilityComputer task. The syntax of this task is as follows:
 * 
 * <pre>
 * task.UtilityComputer &lt;-r result dir&gt; &lt;-o output file&gt; &lt;-o base dataset&gt; [-targetCategory target category]
 * </pre>
 * 
 * @author bsanghvi
 * @since Jul 9, 2008
 * @version Aug 18, 2008
 */
public class UtilityComputerTaskData extends AbstractTaskData {

   /** Name of this task is <code>task.UtilityComputer</code>. */
   private static final String TASK_NAME = "task.UtilityComputer"; //$NON-NLS-1$

   /** Argument <code>-r</code> for specifying the name of meta task result directory. */
   private static final Argument ARG_R = new Argument("r", Messages.UtilComp_ArgR_FName); //$NON-NLS-1$

   /**
    * Instantiates a new utility computer task.
    */
   public UtilityComputerTaskData() {
      super(TASK_NAME);
   }

   /**
    * Gets the meta task result directory (argument = <code>-r</code>).
    * 
    * @return the meta task result directory
    */
   public String getMetaTaskResultDir() {
      return getPairedArgument().get(ARG_R.argument());
   }

   /**
    * Sets the meta task result directory (argument = <code>-r</code>).
    * 
    * @param metaTaskResultDir the meta task result directory
    */
   public void setMetaTaskResultDir(final String metaTaskResultDir) {
      getPairedArgument().put(ARG_R.argument(), metaTaskResultDir);
   }

   /**
    * Gets the output file (argument = <code>-o</code>).
    * 
    * @return the output file
    */
   public String getOutputFile() {
      return getPairedArgument().get(ARG_O.argument());
   }

   /**
    * Sets the output file (argument = <code>-o</code>).
    * 
    * @param outputFile the output file
    */
   public void setOutputFile(final String outputFile) {
      getPairedArgument().put(ARG_O.argument(), outputFile);
   }

   /**
    * Gets the base dataset (argument = <code>-d</code>).
    * 
    * @return the base dataset
    */
   public String getBaseDataset() {
      return getPairedArgument().get(ARG_D.argument());
   }

   /**
    * Sets the base dataset (argument = <code>-d</code>).
    * 
    * @param baseDataset the base dataset
    */
   public void setBaseDataset(final String baseDataset) {
      getPairedArgument().put(ARG_D.argument(), baseDataset);
   }

   /**
    * Gets the target category (argument = <code>-targetCategory</code>).
    * 
    * @return the target category
    */
   public String getTargetCategory() {
      return getPairedArgument().get(ARG_TARGET_CATEGORY.argument());
   }

   /**
    * Sets the target category (argument = <code>-targetCategory</code>).
    * 
    * @param targetCategory the target category
    */
   public void setTargetCategory(final String targetCategory) {
      getPairedArgument().put(ARG_TARGET_CATEGORY.argument(), targetCategory);
   }

   /*
    * @see openbiomind.gui.data.AbstractTaskData#createInputFolder()
    */
   @Override
   protected TaskDataFolder createInputFolder() {
      final TaskDataFolder taskDataFolder = new TaskDataFolder(Messages.Folder_In);
      taskDataFolder.add(createTaskDataFile(getBaseDataset(), true, false, Resources.TAB_EXTENSION));
      taskDataFolder.add(createTaskDataFolder(getMetaTaskResultDir(), true));
      return taskDataFolder;
   }

   /*
    * @see openbiomind.gui.data.AbstractTaskData#createOutputFolder()
    */
   @Override
   protected TaskDataFolder createOutputFolder() {
      final TaskDataFolder taskDataFolder = new TaskDataFolder(Messages.Folder_Out);
      taskDataFolder.add(createTaskDataFile(getOutputFile(), true, true, Resources.TAB_EXTENSION));
      return taskDataFolder;
   }

}
