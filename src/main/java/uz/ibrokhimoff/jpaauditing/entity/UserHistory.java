package uz.ibrokhimoff.jpaauditing.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserHistory{
    
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private LocalDateTime actionAt;

    private String message;

    private String messageKey;

    private Long messageValue;
    
    private Long createdBy;

    private LocalDateTime createdAt;
    
    private Long updatedBy;
    
    private LocalDateTime updatedAt;

    private boolean isDeleted;
}