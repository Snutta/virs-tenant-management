package th.co.ait.microservices.iotonix.tenant.management.ioxtenantmanagement.jpa.service.impl;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import th.co.ait.microservices.iotonix.tenant.management.ioxtenantmanagement.jpa.model.TenantProfile;
import th.co.ait.microservices.iotonix.tenant.management.ioxtenantmanagement.jpa.repository.TenantProfileRepository;
import th.co.ait.microservices.iotonix.tenant.management.ioxtenantmanagement.jpa.service.TenantManagementService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


@Service
public class TenantManagementServiceImpl  implements TenantManagementService  {

    @Autowired private TenantProfileRepository tenantProfileRepository;

    @Override
    @Transactional( readOnly = true )
    public TenantProfile getTenantProfileById(UUID id) {
        return tenantProfileRepository.findById( id ).orElse( null );
    }

    @Override
    @Transactional( readOnly = true )
    public List<TenantProfile> getAllTenantProfiles(){
        return (List<TenantProfile>) tenantProfileRepository.findAll();
    }

    @Override
    @Transactional( propagation = Propagation.REQUIRES_NEW ,  rollbackFor = Exception.class )
    public TenantProfile addTenantProfile( TenantProfile tenantProfile ){
        if( tenantProfileRepository.existsTenantProfileByTenantName(  tenantProfile.getTenantName()  )  ){
            return  null;
        }else{
            tenantProfile.setTenantKey(  RandomStringUtils.random(8, true, true)  );
            tenantProfile.setCreatedDate(  LocalDateTime.now()  );
            return tenantProfileRepository.save(  tenantProfile);
        }
    }

    @Override
    @Transactional( propagation = Propagation.REQUIRES_NEW ,  rollbackFor = Exception.class )
    public void updateTenantProfile(TenantProfile objT ){
        TenantProfile exists = tenantProfileRepository.findById(   objT.getTenantId() ).orElse( null );
        if( exists != null ){
            exists.setCompany(
                    objT.getCompany() != null && objT.getCompany().trim().length() > 0  ?
                    objT.getCompany() : exists.getCompany() );

            exists.setDomain(
                    objT.getDomain() != null && objT.getDomain().trim().length() > 0  ?
                            objT.getDomain() : exists.getDomain() );

            exists.setTel(
                    objT.getTel() != null && objT.getTel().trim().length() > 0  ?
                            objT.getTel() : exists.getTel() );

            exists.setEmail(
                    objT.getEmail() != null && objT.getEmail().trim().length() > 0  ?
                            objT.getEmail() : exists.getEmail() );

            exists.setIsactive(
                    objT.getIsactive() != null  ?
                            objT.getIsactive() : exists.getIsactive() );

            tenantProfileRepository.save(  exists );
        }


    }


}
