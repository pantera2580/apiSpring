/*
 * package com.mec.mundoDisney.util;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.boot.CommandLineRunner; import
 * org.springframework.stereotype.Component;
 * 
 * import com.mec.mundoDisney.security.enumerados.RoleName; import
 * com.mec.mundoDisney.security.model.Rol; import
 * com.mec.mundoDisney.security.service.RolService;
 * 
 * 
 * @Component public class CreateRoles implements CommandLineRunner{
 * 
 * @Autowired RolService rolService;
 * 
 * @Override public void run(String... args) throws Exception { Rol rolAdmin =
 * new Rol(RoleName.ROLE_ADMIN); Rol rolUser = new Rol(RoleName.ROLE_USER);
 * rolService.save(rolAdmin); rolService.save(rolUser);
 * 
 * 
 * }
 * 
 * }
 */