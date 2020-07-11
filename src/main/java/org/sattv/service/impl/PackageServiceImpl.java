package org.sattv.service.impl;

import org.sattv.beans.Package;
import org.sattv.dao.PackageDao;
import org.sattv.dao.impl.PackageDaoImpl;
import org.sattv.service.PackageService;

import static org.sattv.utility.PropertyUtil.validatePack;

import java.util.List;

public class PackageServiceImpl implements PackageService {

    private PackageDao packageDao = new PackageDaoImpl();

    @Override
    public List<Package> getAllPackages() {
        return packageDao.getAllPackages();
    }

    @Override
    public Package getPackageByName(String pack) {
        validatePack(pack);
        return packageDao.getPackageByName(pack);
    }
}
