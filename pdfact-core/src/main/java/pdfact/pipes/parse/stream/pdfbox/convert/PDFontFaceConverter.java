package pdfact.pipes.parse.stream.pdfbox.convert;

import java.util.HashMap;
import java.util.Map;

import com.google.inject.Inject;

import pdfact.model.Font;
import pdfact.model.FontFace;
import pdfact.model.FontFace.FontFaceFactory;

/**
 * A converter that converts PDFont objects and font sizes to {@link FontFace}
 * objects.
 * 
 * @author Claudius Korzen
 */
public class PDFontFaceConverter {
  /**
   * The factory to create instances of {@link FontFace}.
   */
  protected FontFaceFactory fontFaceFactory;

  /**
   * The already known {@link FontFace} objects per name.
   */
  protected Map<String, FontFace> knownFontFaces;

  /**
   * Creates a new font face converter.
   * 
   * @param fontFaceFactory
   *        The factory to create instances of {@link FontFace}.
   */
  @Inject
  public PDFontFaceConverter(FontFaceFactory fontFaceFactory) {
    this.fontFaceFactory = fontFaceFactory;
    this.knownFontFaces = new HashMap<>();
  }

  // ==========================================================================

  /**
   * Converts the given {@link Font} object and font size to an PdfFontFace
   * object.
   * 
   * @param font
   *        The font to process.
   * @param fontSize
   *        The font size to process.
   * 
   * @return The converted font face.
   */
  public FontFace convert(Font font, float fontSize) {
    if (font == null) {
      return null;
    }

    // Check if the font face is already known.
    FontFace knownFontFace = getKnownFontFace(font, fontSize);
    if (knownFontFace != null) {
      return knownFontFace;
    }

    // The font face is not known. Create a new font face.
    FontFace newFontFace = this.fontFaceFactory.create(font, fontSize);

    // Add the new font face to the map of known font faces.
    this.knownFontFaces.put(font.getId() + ":" + fontSize, newFontFace);

    return newFontFace;
  }

  /**
   * Returns a {@link FontFace} object related to the given font and font size
   * if the font face is already known; null otherwise.
   * 
   * @param font
   *        The font to process.
   * @param fontSize
   *        The font size to process.
   * 
   * @return A {@link FontFace} object related to the given font and font size
   *         if the font is already known; null otherwise.
   */
  protected FontFace getKnownFontFace(Font font, float fontSize) {
    return this.knownFontFaces.get(font.getId() + ":" + fontSize);
  }
}