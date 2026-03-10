package dev.arpit.splitwise.models;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseModel {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;
  @CreatedDate
  private LocalDateTime createdAt;
  @CreatedBy
  private String createdBy;
  @LastModifiedDate
  private LocalDateTime updatedAt;
  @LastModifiedBy
  private String updatedBy;
}
