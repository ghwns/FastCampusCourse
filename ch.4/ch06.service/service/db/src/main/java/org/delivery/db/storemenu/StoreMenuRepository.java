package org.delivery.db.storemenu;

import org.delivery.db.storemenu.enums.StoreMenuStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface StoreMenuRepository extends JpaRepository<StoreMenuEntity, Long> {

    // 유효한 메뉴
    Optional<StoreMenuEntity> findFirstByIdAndStatusOrderByIdDesc(Long id, StoreMenuStatus status);

    // 특정 가게의 메뉴

    List<StoreMenuEntity> findAllByStoreIdAndStatusOrderBySequenceDesc(Long storeId, StoreMenuStatus status);
}
