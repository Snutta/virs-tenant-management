package th.co.ait.microservices.iotonix.tenant.management.ioxtenantmanagement.jpa.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import th.co.ait.microservices.iotonix.tenant.management.ioxtenantmanagement.jpa.model.TenantProfile;

import java.util.UUID;

@Repository
public interface TenantProfileRepository extends CrudRepository<TenantProfile, UUID> {

    @Query( "SELECT DISTINCT A.tenantName FROM TenantProfile A WHERE A.tenantName = :tenant_name")
     Boolean existsTenantProfileByTenantName( @Param( "tenant_name") String tenant_name );

    @Query
    TenantProfile findTenantProfileByTenantName( String tenant_name );

}
