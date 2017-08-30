package pdfact.pipes.translate.ligatures;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pdfact.model.Character;
import pdfact.model.Page;
import pdfact.model.PdfDocument;
import pdfact.util.exception.PdfActException;

// FIXME: Adjust bounding box.

/**
 * A plain implementation of {@link SplitLigaturesPipe}.
 * 
 * @author Claudius Korzen
 */
public class PlainSplitLigaturesPipe implements SplitLigaturesPipe {
  /**
   * A map with the unicodes of ligatures and its individual characters.
   */
  protected static final Map<String, String> LIGATURES;

  static {
    // Fill the ligatures. // TODO: Move to PdfCharacterUtils.
    LIGATURES = new HashMap<String, String>();
    LIGATURES.put("\u00C6", "AE");
    LIGATURES.put("\u00E6", "ae");
    LIGATURES.put("\u0152", "OE");
    LIGATURES.put("\u0153", "oe");
    LIGATURES.put("\u0132", "IJ");
    LIGATURES.put("\u0133", "ij");
    LIGATURES.put("\u1D6B", "ue");
    LIGATURES.put("\uA728", "TZ");
    LIGATURES.put("\uA729", "tz");
    LIGATURES.put("\uA732", "AA");
    LIGATURES.put("\uA733", "aa");
    LIGATURES.put("\uA734", "AO");
    LIGATURES.put("\uA735", "ao");
    LIGATURES.put("\uA736", "AU");
    LIGATURES.put("\uA737", "au");
    LIGATURES.put("\uA738", "AV");
    LIGATURES.put("\uA739", "av");
    LIGATURES.put("\uA73C", "AY");
    LIGATURES.put("\uA73D", "ay");
    LIGATURES.put("\uA74E", "OO");
    LIGATURES.put("\uA74F", "oo");
    LIGATURES.put("\uAB50", "ui");
    LIGATURES.put("\uFB00", "ff");
    LIGATURES.put("\uFB01", "fi");
    LIGATURES.put("\uFB02", "fl");
    LIGATURES.put("\uFB03", "ffi");
    LIGATURES.put("\uFB04", "ffl");
    LIGATURES.put("\uFB06", "st");
  }

  // ==========================================================================

  @Override
  public PdfDocument execute(PdfDocument pdf) throws PdfActException {
    splitLigatures(pdf);
    return pdf;
  }

  // ==========================================================================

  /**
   * Splits the ligatures of the given PDF document into its individual
   * characters.
   * 
   * @param pdf
   *        The PDF document to process.
   */
  protected void splitLigatures(PdfDocument pdf) {
    if (pdf != null) {
      List<Page> pages = pdf.getPages();
      if (pages != null) {
        for (Page page : pages) {
          List<Character> characters = page.getCharacters();
          if (characters != null) {
            for (Character character : characters) {
              splitLigature(character);
            }
          }
        }
      }
    }
  }

  /**
   * Checks if the given character is a ligature and if so, translates it into
   * its individual characters and changes the textual content of the given
   * character accordingly. Does nothing if the character is not a ligature.
   * 
   * @param character
   *        The PDF character to process.
   */
  protected void splitLigature(Character character) {
    if (isLigature(character)) {
      character.setText(getResolvedLigatureText(character));
    }
  }

  /**
   * Returns true if the given character is a ligature; false otherwise.
   * 
   * @param character
   *        The PDF character to check.
   * 
   * @return True if the given character is a ligature; false otherwise.
   */
  protected boolean isLigature(Character character) {
    return character != null && LIGATURES.containsKey(character.getText());
  }

  /**
   * Returns the text consisting of the individual characters of the given
   * ligature. Returns null if the given character is not a ligature.
   * 
   * @param character
   *        The PDF character to process.
   * 
   * @return The text consisting of the individual characters of the given
   *         ligature or null if the given character is not a ligature.
   */
  protected String getResolvedLigatureText(Character character) {
    return LIGATURES.get(character.getText());
  }
}