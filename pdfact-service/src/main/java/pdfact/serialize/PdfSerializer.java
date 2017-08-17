package pdfact.serialize;

import java.util.Set;

import pdfact.exception.PdfActException;
import pdfact.models.PdfDocument;
import pdfact.models.PdfRole;
import pdfact.models.PdfElementType;

/**
 * A serializer to serialize a PDF document in a specific format.
 *
 * @author Claudius Korzen
 */
public interface PdfSerializer {
  /**
   * Serializes the given PDF document.
   * 
   * @param pdf
   *        The PDF document to serialize.
   * @return The serialization.
   * @throws PdfActException
   *         If the serilization failed.
   */
  byte[] serialize(PdfDocument pdf) throws PdfActException;

  // ==========================================================================

  /**
   * Returns the element types filter.
   * 
   * @return The element types filter.
   */
  Set<PdfElementType> getElementTypesFilter();

  /**
   * Sets the element types filter to include on serialization.
   * 
   * @param types
   *        The element types filter to include on serialization.
   */
  void setElementTypesFilter(Set<PdfElementType> types);

  // ==========================================================================

  /**
   * Returns the semantic roles filter.
   * 
   * @return The semantic roles filter.
   */
  Set<PdfRole> getSemanticRolesFilter();

  /**
   * Sets the semantic roles filter.
   * 
   * @param roles
   *        The semantic roles filter.
   */
  void setSemanticRolesFilter(Set<PdfRole> roles);

  // ==========================================================================

  /**
   * The factory to create instances of PdfSerializer.
   * 
   * @author Claudius Korzen
   */
  public interface PdfSerializerFactory {
    /**
     * Creates a new PdfSerializer.
     * 
     * @return An instance of PdfSerializer.
     */
    PdfSerializer create();

    /**
     * Creates a new PdfSerializer.
     * 
     * @param types
     *        The element types filter.
     * @param roles
     *        The semantic roles filter.
     * 
     * @return An instance of PdfSerializer.
     */
    PdfSerializer create(Set<PdfElementType> types, Set<PdfRole> roles);
  }
}