package com.desarrollo.repository.userRepository;

import com.desarrollo.model.RoleEnum;
import com.desarrollo.model.RolesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository  extends JpaRepository<RolesEntity,Long> {
    Optional<RolesEntity> findByName(RoleEnum name);
}
