package th.co.ait.microservices.iotonix.tenant.management.ioxtenantmanagement.jpa.service;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import th.co.ait.microservices.iotonix.tenant.management.ioxtenantmanagement.jpa.model.TenantProfile;

import java.util.List;
import java.util.UUID;

public interface TenantManagementService {
    @Transactional( readOnly = true )
    TenantProfile getTenantProfileById(UUID id);

    @Transactional( readOnly = true )
    TenantProfile getTenantProfileByTenantName(String tenant_name);

    @Transactional( readOnly = true )
    List<TenantProfile> getAllTenantProfiles();

    @Transactional( propagation = Propagation.REQUIRES_NEW ,  rollbackFor = Exception.class )
    TenantProfile addTenantProfile(TenantProfile tenantProfile);

    @Transactional( propagation = Propagation.REQUIRES_NEW ,  rollbackFor = Exception.class )
    void updateTenantProfile(TenantProfile objT);
}
