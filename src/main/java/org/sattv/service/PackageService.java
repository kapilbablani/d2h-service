package org.sattv.service;

import org.sattv.beans.Package;

import java.util.List;

/**
 * This service layer abstraction is responsible
 * for the package operations
 *
 * @author kapilb
 * @since 1.0.0.RELEASE
 */
public interface PackageService {
    List<Package> getAllPackages();

    Package getPackageByName(String pack);
}
