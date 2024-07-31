package th.co.ait.microservices.iotonix.tenant.management.ioxtenantmanagement.jpa.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table( name = "tenant_profile")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TenantProfile {

    @Id
    @org.hibernate.annotations.Type(type="uuid-char")
    @GeneratedValue( generator = "uuid2" )
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private UUID tenantId;

    @Column
    private String  tenantKey;

    @Column
    private String tenantName;

    @Column
    private String domain;

    @Column
    private String company;

    @Column
    private String tel;

    @Column
    private String email;

    @Column
    private Boolean isactive;

    @Column
    private LocalDateTime createdDate;

}
