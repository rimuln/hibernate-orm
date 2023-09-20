package org.hibernate.bugs.model;

import java.io.Serializable;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.MappedSuperclass;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@MappedSuperclass
@IdClass(BaseReferenceEdge.class)
public abstract class BaseReferenceEdge implements Serializable {

  private static final long serialVersionUID = 1L;

  private UUID sourceId;
  private UUID targetId;

  public BaseReferenceEdge() {
  }

  public BaseReferenceEdge(UUID sourceId, UUID targetId) {
    this.sourceId = sourceId;
    this.targetId = targetId;
  }

  @Id
  @Column(name = "SOURCE_ID")
  public UUID getSourceId() {
    return sourceId;
  }

  public void setSourceId(UUID sourceId) {
    this.sourceId = sourceId;
  }

  @Id
  @Column(name = "TARGET_ID")
  public UUID getTargetId() {
    return targetId;
  }

  public void setTargetId(UUID upperId) {
    this.targetId = upperId;
  }

  @Override
  public boolean equals(Object otherObject) {
    if (otherObject == null) {
      return false;
    }
    if (otherObject == this) {
      return true;
    }
    if (otherObject.getClass() != getClass()) {
      return false;
    }
    BaseReferenceEdge other = (BaseReferenceEdge) otherObject;
    return new EqualsBuilder()
        .append(sourceId, other.sourceId)
        .append(targetId, other.targetId)
        .isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder()
        .append(sourceId)
        .append(targetId)
        .toHashCode();
  }
}