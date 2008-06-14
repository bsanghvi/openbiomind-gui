/**
 * Preference.java
 *
 * The file Preference.java.
 *
 * $Id$
 */
package openbiomind.gui.preferences;

import static openbiomind.gui.util.Constants.JAR_EXTENSION;
import static openbiomind.gui.util.Constants.PIPELINE_PROPERTIES_FILENAME;
import openbiomind.gui.Activator;
import openbiomind.gui.util.Constants;

/**
 * The class Preference methods to access the plug-in preferences. It also contains constant
 * definitions.
 *
 * @author bsanghvi
 * @since Jun 9, 2008
 * @version Jun 9, 2008
 */
public class Preference {

   /** The Constant for OpenBiomind JAR. */
   public static final String OPENBIOMIND_JAR = "openbiomind.gui.preferences.OpenBiomindJar";

   /** The Constant for pipeline.properties. */
   public static final String PIPELINE_PROPERTIES = "openbiomind.gui.preferences.PipelineProperties";

   /**
    * Gets the open biomind jar location.
    *
    * @return the open biomind jar location
    */
   public static String getOpenBiomindJarLocation() {
      return Activator.getDefault().getPreferenceStore().getString(Preference.OPENBIOMIND_JAR);
   }

   /**
    * Gets the pipeline properties home.
    *
    * @return the pipeline properties home
    */
   public static String getPipelinePropertiesHome() {
      final String pipelinePropertiesPath = Activator.getDefault().getPreferenceStore().getString(
            Preference.PIPELINE_PROPERTIES);
      return pipelinePropertiesPath.substring(0, pipelinePropertiesPath
            .lastIndexOf(Constants.PIPELINE_PROPERTIES_FILENAME));
   }

   /**
    * Checks if is open biomind jar preference valid.
    *
    * @param value the value
    *
    * @return true, if is open biomind jar preference valid
    */
   public static boolean isOpenBiomindJarPreferenceValid(final String value) {
      return (value != null && value.trim().toLowerCase().endsWith(JAR_EXTENSION));
   }

   /**
    * Checks if is pipeline properties preference valid.
    *
    * @param value the value
    *
    * @return true, if is pipeline properties preference valid
    */
   public static boolean isPipelinePropertiesPreferenceValid(final String value) {
      return (value != null && value.trim().toLowerCase().endsWith(PIPELINE_PROPERTIES_FILENAME));
   }

   /**
    * Checks if is preference valid.
    *
    * @return true, if is preference valid
    */
   public static boolean isPreferenceValid() {
      return (isOpenBiomindJarPreferenceValid(getOpenBiomindJarLocation()) && isPipelinePropertiesPreferenceValid(getPipelinePropertiesHome()));
   }

}