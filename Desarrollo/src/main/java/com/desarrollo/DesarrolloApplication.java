package com.desarrollo;

import com.desarrollo.model.PermisoEntity;
import com.desarrollo.model.RoleEnum;
import com.desarrollo.model.RolesEntity;
import com.desarrollo.model.UserEntity;
import com.desarrollo.repository.userRepository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Set;

@SpringBootApplication
public class DesarrolloApplication {

	public static void main(String[] args) {

		SpringApplication.run(DesarrolloApplication.class, args);
	}

	/*@Bean
	CommandLineRunner init(UserRepository userRepository) {
		return args -> {
			PermisoEntity createPermission = PermisoEntity.builder()
					.name("CREATE")
					.build();

			PermisoEntity readPermission = PermisoEntity.builder()
					.name("READ")
					.build();

			PermisoEntity updatePermission = PermisoEntity.builder()
					.name("UPDATE")
					.build();

			PermisoEntity deletePermission = PermisoEntity.builder()
					.name("DELETE")
					.build();

			//crear Rol de admin
			RolesEntity roleAdmin = RolesEntity.builder()
					.name(RoleEnum.ADMIN)
					.permisoEntities(Set.of(createPermission, readPermission, updatePermission, deletePermission))
					.build();

			UserEntity admin = UserEntity.builder()
					.username("admin")
					.password("$2a$12$xVLjCPQMKqfCCeMUlxCDE.77hvdFLsjk3AHFWCsTqq5C/o0dwWsmG") //1234
					.nombreCliente("Sandro Cordova")
					.dni("06204810")
					.email("sandro@gmail.com")
					.isEnabled(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialNoExpired(true)
					.rolesEntitySet(Set.of(roleAdmin))
					.build();
			userRepository.save(admin);
		};
	}*/
}
