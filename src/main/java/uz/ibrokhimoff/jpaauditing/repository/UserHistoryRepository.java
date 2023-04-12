package uz.ibrokhimoff.jpaauditing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import uz.ibrokhimoff.jpaauditing.entity.UserHistory;

import java.util.Optional;

@Repository
public interface UserHistoryRepository extends JpaRepository<UserHistory, Long>{
    @Transactional(readOnly = true, propagation = Propagation.REQUIRES_NEW)
    public Optional<UserHistory> findByUserId(Long userId);
}