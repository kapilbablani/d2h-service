package org.sattv.dao;

import org.sattv.beans.Package;

import java.util.List;

/**
 *  This layer of abstraction will be used for the packages provided
 *  by the service operator.
 *
 *  If any provider provides package concept then must implement it.
 *
 *  @author kapilb
 *  @version 1.0.0.RELEASE
 */
public interface PackageDao {

    /**
     * This method is to get all the packages available
     * to the customer of d2h service.
     * @return List<Package>
     */
    List<Package> getAllPackages();

    /**
     * This is responsible to fetch the package details based on the package
     * name provided in the input
     * @param pack
     * @return Package
     */
    Package getPackageByName(String pack);
}
