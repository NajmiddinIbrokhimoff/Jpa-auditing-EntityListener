package uz.ibrokhimoff.jpaauditing.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import uz.ibrokhimoff.jpaauditing.listener.UserEntityListener;

@EntityListeners({UserEntityListener.class, AuditingEntityListener.class})
@Data
@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
public class User extends Auditable {

    private String username;
    
    private String password;
}