package org.sattv.dao.impl;

import org.sattv.beans.Package;
import org.sattv.dao.ChannelDao;
import org.sattv.dao.PackageDao;

import java.util.Arrays;
import java.util.List;

public class PackageDaoImpl implements PackageDao {

    private ChannelDao channelDao = new ChannelDaoImpl();

    @Override
    public List<Package> getAllPackages() {
        return getAllPackagesFromDB();
    }

    @Override
    public Package getPackageByName(String pack) {
        return getAllPackagesFromDB()
                .stream()
                .filter(p -> p.getAbbreviation().equalsIgnoreCase(pack))
                .findFirst().get();
    }

    /**
     *  This method is used to fetch all the packages
     *  from the database and will also includes corresponding channels bundled
     *  in the package
     *
     *  TODO: fetch the originals from Database
     *  @return List<Package>
     */
    private List<Package> getAllPackagesFromDB() {
        Package silver = new Package(1, "Silver Pack", "S", 50D, channelDao.getChannelsByPackageId(1));
        Package gold = new Package(2, "Gold Pack", "G",100D, channelDao.getChannelsByPackageId(2));
        return Arrays.asList(silver, gold);
    }
}
