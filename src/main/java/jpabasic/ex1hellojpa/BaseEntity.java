package jpabasic.ex1hellojpa;

import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

// 상속관계 매핑X
// 엔티티X, 테이블과 매핑X
// 부모 클래스를 상속 받는 자식 클래스에 매핑 정보만 제공
// 조회, 검색 불가(em.find(BaseEntity) 불가)
// 작접 생성해서 사용할 일이 없으므로 추상 클래스 권장

@MappedSuperclass // 속성만 받을 때 사용 @Entity는 상속관계 매핑시 사용
public abstract class BaseEntity {

    private String createdBy;
    private LocalDateTime createdDate;
    private String lastModifiedBy;
    private LocalDateTime lastModifiedDate;

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public LocalDateTime getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(LocalDateTime lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }
}
