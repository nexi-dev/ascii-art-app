package importers.file

import java.io.File

/**
 * Import png image to RGB pixel images
 * @param source the file to import from
 */
class PNGImageFileImporter(source: File) extends ImageFileImporter(source) {
  if (!isValidExtension(source, "png")) {
    throw new IllegalArgumentException(s"Invalid file extension for png file: ${source.getName}")
  }
}
