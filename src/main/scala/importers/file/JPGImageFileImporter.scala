package importers.file

import java.io.File

/**
 * Import jpg image to RGB pixel images
 * @param source the file to import from
 */
class JPGImageFileImporter(source: File) extends ImageFileImporter(source) {
  if (!isValidExtension(source, "jpg") && !isValidExtension(source, "jpeg")) {
    throw new IllegalArgumentException(s"Invalid file extension for jpg/jpeg file: ${source.getName}")
  }
}
