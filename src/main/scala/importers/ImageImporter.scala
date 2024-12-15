package importers

import image.Image
import pixel.RGBPixel

/**
 * Imports and image of RGB pixels
 */
trait ImageImporter extends Importer[Image[RGBPixel]] {
}
