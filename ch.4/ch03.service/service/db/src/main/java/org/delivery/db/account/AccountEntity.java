package org.delivery.db.account;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import org.delivery.db.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@SuperBuilder //부모의 필드도 빌더에 포함
@Entity
@Data
@EqualsAndHashCode(callSuper = true) //부모까지 포함해서 객체를 비교함
@Table(name = "account")
public class AccountEntity extends BaseEntity {
}
