package org.hibernate.bugs.model;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.xml.bind.annotation.XmlTransient;

import org.hibernate.annotations.Immutable;

@Immutable
@Entity
@Table(name = "FORMULA_REFERENCE_EDGE_VIEW")
@XmlTransient
public class FormulaReferenceEdge extends BaseReferenceEdge {

  private static final long serialVersionUID = 1L;

  public FormulaReferenceEdge() {
  }

  public FormulaReferenceEdge(UUID sourceId, UUID targetId) {
    super(sourceId, targetId);
  }
}