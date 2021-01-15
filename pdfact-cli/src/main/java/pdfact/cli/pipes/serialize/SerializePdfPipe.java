package pdfact.cli.pipes.serialize;

import java.io.OutputStream;
import java.nio.file.Path;
import java.util.Set;
import pdfact.cli.model.ExtractionUnit;
import pdfact.cli.model.SerializeFormat;
import pdfact.core.model.SemanticRole;
import pdfact.core.util.pipeline.Pipe;

/**
 * A pipe to serialize PDF documents.
 * 
 * @author Claudius Korzen
 */
public interface SerializePdfPipe extends Pipe {
  /**
   * Returns the serialization format.
   * 
   * @return The serialization format.
   */
  SerializeFormat getSerializationFormat();

  /**
   * Sets the serialization format.
   * 
   * @param format The serialization format.
   */
  void setSerializationFormat(SerializeFormat format);

  // ==============================================================================================

  /**
   * Returns the target stream.
   * 
   * @return The target stream.
   */
  OutputStream getTargetStream();

  /**
   * Sets the target stream.
   * 
   * @param stream The target stream.
   */
  void setTargetStream(OutputStream stream);

  // ==============================================================================================

  /**
   * Returns the target path.
   * 
   * @return The target path.
   */
  Path getTargetPath();

  /**
   * Sets the target path.
   * 
   * @param path The target path.
   */
  void setTargetPath(Path path);

  // ==============================================================================================

  /**
   * Returns the units to extract.
   * 
   * @return The units to extract.
   */
  Set<ExtractionUnit> getExtractionUnits();

  /**
   * Sets the units to extract.
   * 
   * @param units The units to extract.
   */
  void setExtractionUnits(Set<ExtractionUnit> units);

  // ==============================================================================================

  /**
   * Returns the semantic roles to include.
   * 
   * @return The semantic roles to include.
   */
  Set<SemanticRole> getSemanticRolesToInclude();

  /**
   * Sets the semantic roles to include.
   * 
   * @param roles The semantic roles to include.
   */
  void setSemanticRolesToInclude(Set<SemanticRole> roles);

  // ==============================================================================================

  /**
   * Returns the boolean flag indicating whether or not this serializer inserts a page break marker
   * between two PDF elements when a page break between the two elements occurs in the PDF.
   */
  public boolean isInsertPageBreakMarkers();

  /**
   * Sets the boolean flag indicating whether or not this serializer should insert a page break
   * marker between two PDF elements when a page break between the two elements occurs in the PDF.
   */
  public void setInsertPageBreakMarkers(boolean insertPageBreakMarkers);
}
