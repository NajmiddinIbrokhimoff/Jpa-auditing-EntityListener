package uz.ibrokhimoff.jpaauditing;

import jakarta.persistence.EntityListeners;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import uz.ibrokhimoff.jpaauditing.listener.UserEntityListener;

/**
 * @author : ibrokhimoff
 **/
@EntityListeners({UserEntityListener.class, AuditingEntityListener.class})
public abstract class Monitoringable {
    protected abstract Long getId();

    protected abstract String getClassName();

}
