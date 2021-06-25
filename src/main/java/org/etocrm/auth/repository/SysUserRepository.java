package org.etocrm.auth.repository;


import org.etocrm.auth.dto.ac.entity.SysUser;
import org.etocrm.database.repository.BaseRepository;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 人员表 Mapper 接口
 * </p>
 *
 * @author admin
 * @since 2021-01-19
 */
@Repository
public interface SysUserRepository extends BaseRepository<SysUser, Long> {

    SysUser findByUid(String userName);

}