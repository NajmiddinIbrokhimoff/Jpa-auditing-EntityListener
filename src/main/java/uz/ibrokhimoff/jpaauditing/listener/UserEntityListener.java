package uz.ibrokhimoff.jpaauditing.listener;

import jakarta.persistence.*;
import lombok.extern.slf4j.Slf4j;
import uz.ibrokhimoff.jpaauditing.entity.User;
import uz.ibrokhimoff.jpaauditing.entity.UserHistory;
import uz.ibrokhimoff.jpaauditing.repository.UserHistoryRepository;

@Slf4j
public class UserEntityListener {

    @PrePersist
    public void prePersist(Object o) {
        log.info("\n<<<<<<PrePersist\n {}\n <<<<<<<<<", o.toString());
    }


    @PreUpdate
    public void preUpdate(Object o) {
        log.info("\n<<<<<<preUpdate\n {}\n <<<<<<<<<", o.toString());
    }

    @PreRemove
    public void preRemove(Object o) {
        log.info("\n<<<<<<preRemove\n {}\n <<<<<<<<<", o.toString());
    }

    @PostPersist
    public void postPersist(User o) {
        //inject bean => Listener cannot use @component annotation in JPA, so @Autowired annotation cannot use too.
        UserHistoryRepository userHistoryRepository = BeanUtils.getBean(UserHistoryRepository.class);
        UserHistory userHistory = new UserHistory();

        userHistory.setMessage(getMessageKey(o) + " updated");
        userHistory.setMessageKey(getMessageKey(o));
        userHistory.setMessageValue(getMessageValue(o));

        userHistory.setCreatedBy(o.getCreatedBy());
        userHistory.setCreatedAt(o.getCreatedAt());
        userHistory.setUpdatedBy(o.getUpdatedBy());
        userHistory.setUpdatedAt(o.getUpdatedAt());
        userHistory.setDeleted(false);

        userHistoryRepository.save(userHistory);

        log.info("\n<<<<<<postPersist\n {}\n <<<<<<<<<", o.toString());
    }

    @PostUpdate
    public void postUpdate(User o) {
        UserHistoryRepository userHistoryRepository = BeanUtils.getBean(UserHistoryRepository.class);

        UserHistory userHistory = userHistoryRepository.findByUserId(o.getId()).get();

        userHistory.setMessage(getMessageKey(o) + " updated");
        userHistory.setMessageKey(getMessageKey(o));
        userHistory.setMessageValue(getMessageValue(o));
        userHistory.setUpdatedBy(o.getUpdatedBy());
        userHistory.setUpdatedAt(o.getUpdatedAt());

        userHistoryRepository.save(userHistory);
        log.info("{}", userHistory);

        log.info("\n<<<<<<postUpdate\n {}\n <<<<<<<<<", o.toString());
    }

    @PostRemove
    public void postRemove(User o) {
        UserHistoryRepository userHistoryRepository = BeanUtils.getBean(UserHistoryRepository.class);
        UserHistory userHistory = new UserHistory();
        userHistory.setMessage(getMessageKey(o) + " removed");
        userHistory.setMessageKey(getMessageKey(o));
        userHistory.setMessageValue(getMessageValue(o));
        userHistory.setDeleted(true);
        userHistoryRepository.save(userHistory);
    }

    @PostLoad
    public void postLoad(Object o) {
        log.info("\n<<<<<<postLoad\n {}\n <<<<<<<<<", o.toString());
    }

    private String getMessageKey(Object o) {
        if (o instanceof User) {
            return "user";
        } else {
            return null;
        }
    }

    private Long getMessageValue(Object o) {
        if (o instanceof User) {
            return ((User) o).getId();
        } else {
            return null;
        }
    }
}