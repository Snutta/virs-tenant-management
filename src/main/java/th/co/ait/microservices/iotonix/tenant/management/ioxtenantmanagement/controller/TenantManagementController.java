package th.co.ait.microservices.iotonix.tenant.management.ioxtenantmanagement.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import th.co.ait.microservices.iotonix.tenant.management.ioxtenantmanagement.dto.TenantNameDto;
import th.co.ait.microservices.iotonix.tenant.management.ioxtenantmanagement.dto.TenantProfileRequest;
import th.co.ait.microservices.iotonix.tenant.management.ioxtenantmanagement.jpa.model.TenantProfile;
import th.co.ait.microservices.iotonix.tenant.management.ioxtenantmanagement.jpa.service.TenantManagementService;

import java.util.UUID;

@RestController
@RequestMapping( "/api/v1/tenantprofile")
public class TenantManagementController {


    @Autowired
    private TenantManagementService tenantManagementService;

    @GetMapping("/getall")
    public ResponseEntity<Object> getAllTenantProfiles(  ){
        return ResponseEntity.ok(   tenantManagementService.getAllTenantProfiles()  );
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Object> getTenantProfileById(@PathVariable("id") String id ){
       try{
           UUID uuid = UUID.fromString( id );
           return  ResponseEntity.ok( tenantManagementService.getTenantProfileById(  uuid  ) );

       }catch (Exception e ){
           return ResponseEntity.internalServerError().body( e.getMessage() );
       }
    }

    @PostMapping( value = "/profile" , produces =  { "application/json"} )
    public ResponseEntity<TenantProfile> getTenantProfileByName(@RequestBody TenantNameDto tenantNameDto ){
          TenantProfile tenantProfile  =  tenantManagementService.getTenantProfileByTenantName(  tenantNameDto.getTenantname() );
        return  ResponseEntity.ok(  tenantProfile );
    }

    @PostMapping( value = "/create")
    public ResponseEntity<Object> createTenantProfile(@RequestBody TenantProfileRequest tenantProfileRequest ){
        TenantProfile tenantProfile = convertDto( tenantProfileRequest  );
        return  ResponseEntity.ok(  tenantManagementService.addTenantProfile( tenantProfile ) );
    }

    @PostMapping( value = "/update")
    public ResponseEntity<Object> updateTenantProfile( @RequestBody TenantProfileRequest tenantProfileRequest  ){
        TenantProfile tenantProfile = convertDto( tenantProfileRequest  );
        tenantManagementService.updateTenantProfile( tenantProfile );
        return  ResponseEntity.ok().build();
    }

    private TenantProfile convertDto( TenantProfileRequest tenantProfileRequest ){
        return  TenantProfile.builder()
                .tenantName(tenantProfileRequest.getName() )
                .tenantId(  tenantProfileRequest.getId() != null ?  UUID.fromString( tenantProfileRequest.getId() ) : null )
                .company( tenantProfileRequest.getCompany() )
                .tel( tenantProfileRequest.getTel())
                .domain( tenantProfileRequest.getDomain() )
                .email( tenantProfileRequest.getEmail() )
                .isactive( tenantProfileRequest.getIsactive() == null ?  true :  tenantProfileRequest.getIsactive() )
                .build();
    }



}
