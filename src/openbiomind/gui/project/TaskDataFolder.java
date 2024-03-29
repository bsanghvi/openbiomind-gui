/* $Id$ */
/*
 *  OpenBiomind-GUI: GUI for OpenBiomind
 *  Copyright (C) 2008  Bhavesh Sanghvi
 *
 *  This file (TaskDataFolder.java) is part of OpenBiomind-GUI.
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

package openbiomind.gui.project;

import static openbiomind.gui.common.Constants.EMPTY;

import java.util.HashSet;
import java.util.Set;

/**
 * The class TaskDataFolder.
 * 
 * @author bsanghvi
 * @since Jun 30, 2008
 * @version Aug 18, 2008
 */
public class TaskDataFolder {

   /** The name. */
   private String name = null;

   /** The task data folder set. */
   private Set<TaskDataFolder> taskDataFolderSet = null;

   /** The task data file set. */
   private Set<TaskDataFile> taskDataFileSet = null;

   /** The linked. */
   private boolean linked = false;

   /** The path. */
   private String path = null;

   /**
    * Instantiates a new task data folder.
    */
   public TaskDataFolder() {
      this(EMPTY);
   }

   /**
    * Instantiates a new task data folder.
    * 
    * @param name the name of the folder
    */
   public TaskDataFolder(final String name) {
      this(name, EMPTY, false);
   }

   /**
    * Instantiates a new task data folder.
    * 
    * @param name the name
    * @param path the path
    * @param linked the linked
    */
   public TaskDataFolder(final String name, final String path, final boolean linked) {
      setName(name);
      setLinked(linked);
      setPath(path);
      setTaskDataFolderSet(null);
      setTaskDataFileSet(null);
   }

   /**
    * Gets the name.
    * 
    * @return the name
    */
   public String getName() {
      return this.name;
   }

   /**
    * Sets the name.
    * 
    * @param name the name to set
    */
   public void setName(final String name) {
      if (name != null) {
         this.name = name;
      } else {
         this.name = EMPTY;
      }
   }

   /**
    * Gets the task data folder set.
    * 
    * @return the taskDataFolderSet
    */
   public Set<TaskDataFolder> getTaskDataFolderSet() {
      return this.taskDataFolderSet;
   }

   /**
    * Sets the task data folder set.
    * 
    * @param taskDataFolderSet the new task data folder set
    */
   public void setTaskDataFolderSet(final Set<TaskDataFolder> taskDataFolderSet) {
      if (taskDataFolderSet != null) {
         this.taskDataFolderSet = taskDataFolderSet;
      } else {
         this.taskDataFolderSet = new HashSet<TaskDataFolder>();
      }
   }

   /**
    * Gets the task data file set.
    * 
    * @return the task data file set
    */
   public Set<TaskDataFile> getTaskDataFileSet() {
      return this.taskDataFileSet;
   }

   /**
    * Sets the task data file set.
    * 
    * @param taskDataFileSet the task data file set
    */
   public void setTaskDataFileSet(final Set<TaskDataFile> taskDataFileSet) {
      if (taskDataFileSet != null) {
         this.taskDataFileSet = taskDataFileSet;
      } else {
         this.taskDataFileSet = new HashSet<TaskDataFile>();
      }
   }

   /**
    * Checks if the folder must be linked.
    * 
    * @return true, if is linked
    */
   public boolean isLinked() {
      return this.linked;
   }

   /**
    * Sets if the folder must be linked.
    * 
    * @param linked the new linked
    */
   public void setLinked(final boolean linked) {
      this.linked = linked;
   }

   /**
    * Gets the path.
    * 
    * @return the path
    */
   public String getPath() {
      return this.path;
   }

   /**
    * Sets the path.
    * 
    * @param path the path to set
    */
   public void setPath(final String path) {
      if (path != null) {
         this.path = path;
      } else {
         this.path = EMPTY;
      }
   }

   /**
    * Adds the task data folder.
    * 
    * @param taskDataFolder the task data folder
    * 
    * @return true, if add is successful
    * 
    * @see java.util.Set#add(java.lang.Object)
    */
   public boolean add(final TaskDataFolder taskDataFolder) {
      if (taskDataFolder != null) {
         return this.taskDataFolderSet.add(taskDataFolder);
      } else {
         return false;
      }
   }

   /**
    * Adds the task data file.
    * 
    * @param taskDataFile the task data file
    * 
    * @return true, if add is successful
    * 
    * @see java.util.Set#add(java.lang.Object)
    */
   public boolean add(final TaskDataFile taskDataFile) {
      if (taskDataFile != null) {
         return getTaskDataFileSet().add(taskDataFile);
      } else {
         return false;
      }
   }

   /*
    * @see java.lang.Object#toString()
    */
   @Override
   public String toString() {
      return getName();
   }

   /*
    * @see java.lang.Object#equals(java.lang.Object)
    */
   @Override
   public boolean equals(final Object otherObject) {
      if (otherObject != null && otherObject instanceof TaskDataFolder) {
         final TaskDataFolder otherTaskDataFolder = (TaskDataFolder) otherObject;
         return (getName().equals(otherTaskDataFolder.getName()) && getPath().equals(otherTaskDataFolder.getPath())
               && getTaskDataFileSet().equals(otherTaskDataFolder.getTaskDataFileSet()) && getTaskDataFolderSet()
               .equals(otherTaskDataFolder.getTaskDataFolderSet()));
      }

      return false;
   }

}
