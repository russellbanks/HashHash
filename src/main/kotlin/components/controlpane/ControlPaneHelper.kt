package components.controlpane

import com.appmattus.crypto.Algorithm
import components.Root
import components.screens.compare.CompareFilesComponent
import components.screens.file.FileScreenComponent
import io.klogging.Klogging
import java.io.File

object ControlPaneHelper : Klogging {

    const val BoxHeight = 32

    suspend fun setFiles(
        fileScreenComponent: FileScreenComponent,
        compareFilesComponent: CompareFilesComponent,
        activeComponent: Root.Child,
        file: File?,
        buttonIndex: Int
    ) {
        if (file != null) {
            if (activeComponent is Root.Child.File) {
                if (fileScreenComponent.file != file) {
                    fileScreenComponent.file = file
                    logger.info("Set user selected file ${file.absolutePath} as main file")
                }
            } else if (activeComponent is Root.Child.CompareFiles) {
                if (buttonIndex == 0) {
                    if (compareFilesComponent.fileOne != file) {
                        compareFilesComponent.fileOne = file
                        logger.info("Set user selected file ${file.absolutePath} as 1st comparison file")
                    }
                } else {
                    if (compareFilesComponent.fileTwo != file) {
                        compareFilesComponent.fileTwo = file
                        logger.info("Set user selected file ${file.absolutePath} as 2nd comparison file")
                    }
                }
            }
        }
    }

    suspend fun onAlgorithmClick(algorithm: Algorithm, component: FileScreenComponent) {
        if (algorithm != component.algorithm) {
            component.algorithm = algorithm
            component.resultMap[algorithm]?.let {
                if (component.resultMap.containsKey(algorithm)) {
                    component.resultMap[algorithm] = it
                    if (component.hashedTextUppercase) component.resultMap[algorithm] = it.uppercase()
                    else component.resultMap[algorithm] = it.lowercase()
                }
            }
            logger.info("Set algorithm as ${algorithm.algorithmName}")
        }
    }
}
