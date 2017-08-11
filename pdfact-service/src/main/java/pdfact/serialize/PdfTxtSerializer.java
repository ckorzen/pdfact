package pdfact.serialize;

import java.util.Set;

import com.google.inject.assistedinject.Assisted;

import pdfact.models.PdfRole;
import pdfact.models.PdfElementType;

/**
 * A serializer to serialize a PDF document in TXT format.
 *
 * @author Claudius Korzen
 */
public interface PdfTxtSerializer extends PdfSerializer {
  /**
   * The factory to create instances of PdfTxtSerializer.
   * 
   * @author Claudius Korzen
   */
  public interface PdfTxtSerializerFactory extends PdfSerializerFactory {
    /**
     * Creates a new PdfTxtSerializer.
     * 
     * @return An instance of PdfTxtSerializer.
     */
    PdfTxtSerializer create();

    /**
     * Creates a new PdfTxtSerializer.
     * 
     * @param types
     *        The element types filter.
     * @param roles
     *        The semantic roles filter.
     * 
     * @return An instance of PdfTxtSerializer.
     */
    PdfTxtSerializer create(@Assisted Set<PdfElementType> types,
        @Assisted Set<PdfRole> roles);
  }
}
