//package lv.mlproject17.CreditApp.database.model;
//
//import org.springframework.data.annotation.CreatedDate;
//import org.springframework.data.annotation.LastModifiedDate;
//
//import javax.persistence.*;
//import java.time.LocalDateTime;
//import java.time.ZoneOffset;
//
//@MappedSuperclass
//public class BaseEntity {
//	@Version
//	@Column(name = "version", nullable = false)
//	private Long version = 0L;
//
//	@Column(name = "created_at", nullable = false, columnDefinition = "DATE")
//	@CreatedDate
//	private LocalDateTime createdAt;
//
//	@Column(name = "modified_at", columnDefinition = "DATE")
//	@LastModifiedDate
//	private LocalDateTime modifiedAt;
//
//	@PrePersist
//	protected void updateCreatedAt() {
//		createdAt = LocalDateTime.now(ZoneOffset.UTC);
//	}
//
//	@PreUpdate
//	protected void updateModifiedAt() {
//		modifiedAt = LocalDateTime.now(ZoneOffset.UTC);
//	}
//
//	public Long getVersion() {
//		return version;
//	}
//
//	public void setVersion(Long version) {
//		this.version = version;
//	}
//
//	public LocalDateTime getCreatedAt() {
//		return createdAt;
//	}
//
//	public void setCreatedAt(LocalDateTime createdAt) {
//		this.createdAt = createdAt;
//	}
//
//	public LocalDateTime getModifiedAt() {
//		return modifiedAt;
//	}
//
//	public void setModifiedAt(LocalDateTime modifiedAt) {
//		this.modifiedAt = modifiedAt;
//	}
//}
