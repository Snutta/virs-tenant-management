package th.co.ait.microservices.iotonix.tenant.management.ioxtenantmanagement.dto;

import lombok.Data;

@Data
public class TenantProfileRequest {
    private String id;
    private String name;
    private String email;
    private String tel;
    private String domain;
    private String company;
    private Boolean isactive;
}
