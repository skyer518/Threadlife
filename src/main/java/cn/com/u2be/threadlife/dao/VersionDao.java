package cn.com.u2be.threadlife.dao;

import cn.com.u2be.threadlife.entity.Version;

/**
 * Created by Ã÷ on 2015/11/26.
 */
public interface VersionDao extends BaseDao<Version, Long> {

    Version getById(Long id);

    Version getVersion(Integer versionType);

    void updataVersion(Integer versionType);
}
