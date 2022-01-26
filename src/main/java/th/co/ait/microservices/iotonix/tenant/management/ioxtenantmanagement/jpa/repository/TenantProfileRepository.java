package th.co.ait.microservices.iotonix.tenant.management.ioxtenantmanagement.jpa.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import th.co.ait.microservices.iotonix.tenant.management.ioxtenantmanagement.jpa.model.TenantProfile;

import java.util.UUID;

@Repository
public interface TenantProfileRepository extends CrudRepository<TenantProfile, UUID> {

    @Query( value = "SELECT DISTINCT A.tenant_name FROM tenant_profile A WHERE A.tenant_name = :tenant_name", nativeQuery = true )
     Boolean existsTenantProfileByTenantName( @Param( "tenant_name") String tenant_name );

}
