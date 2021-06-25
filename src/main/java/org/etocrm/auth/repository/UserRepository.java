package org.etocrm.auth.repository;

import org.etocrm.auth.dto.SysUserPermission;
import org.etocrm.auth.dto.SysUserPermissionDTO;
import org.etocrm.auth.dto.UserPermission;
import org.etocrm.auth.dto.sass.User;
import org.etocrm.database.repository.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * <p>
 * saas人员表 Mapper 接口
 * </p>
 *
 * @author admin
 * @since 2021-01-19
 */
@Repository
public interface UserRepository extends BaseRepository<User, Long> {

    Integer countByUid(String uid);

    User findByUid(String uid);

    List<User> findAllByOrgId(Long orgId);

    List<User> findByOrgIdAndStatus(Long orgId, Integer status);

    @Query("SELECT a.id as id,a.menuName as menuName,a.menuRoute as menuRoute from Permission a  where a.id in (:ids) and a.status=1")
    List<SysUserPermission> loadUserAuthorities(Long ids);
}