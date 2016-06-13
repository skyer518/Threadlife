package cn.com.u2be.threadlife.dao.impl;

import cn.com.u2be.threadlife.dao.VersionDao;
import cn.com.u2be.threadlife.entity.Version;
import org.springframework.stereotype.Repository;

/**
 * Created by alek on 2016/6/13.
 */
@Repository
public class VersionDaoImpl extends BaseDaoImpl<Version, Long> implements VersionDao {

    @Override
    public Version getById(Long id) {
        return get(id);
    }

    @Override
    public Version getVersion(Integer versionType) {
        return findUniqueBy("type", versionType);
    }

    @Override
    public void updataVersion(Integer versionType) {
        Version version = getVersion(versionType);
        version.setVersionCode(version.getVersionCode() + 1);
        save(version);
    }


}
