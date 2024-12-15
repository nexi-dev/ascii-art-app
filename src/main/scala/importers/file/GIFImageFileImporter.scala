package importers.file

import java.io.File

/**
 * Import gif image to RGB pixel images
 * @param source the file to import from
 */
class GIFImageFileImporter(source: File) extends ImageFileImporter(source) {
  if (!isValidExtension(source, "gif")) {
    throw new IllegalArgumentException(s"Invalid file extension for gif file: ${source.getName}")
  }
}
