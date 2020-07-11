package org.sattv.service.impl;

import org.junit.Test;
import org.sattv.beans.Package;
import org.sattv.exception.InvalidInputException;
import org.sattv.service.PackageService;

import java.util.List;

import static org.junit.Assert.*;

public class PackageServiceImplTest {

    private PackageService packageService = new PackageServiceImpl();

    @Test
    public void testA_itShouldGiveSuccess_getAllPackages() {
        //given
        int expectPackageCount = 2;

        //when
        List<Package> packageList = packageService.getAllPackages();

        //then
        assertEquals(expectPackageCount, packageList.size());
        assertNotNull(packageList);
        assertEquals("Silver Pack", packageList.get(0).getName());
        assertEquals("Gold Pack", packageList.get(1).getName());
    }

    @Test
    public void testB_itShouldGiveSuccessMatchingObjectReturned_getAllPackages() {
        //given

        //when
        List<Package> packages = packageService.getAllPackages();
        //then
        assertTrue(packages instanceof List);
    }

    @Test
    public void testC_itShouldGiveSuccess_getPackageByName() {
        //given
        String packageName = "S";

        //when
        Package silver = packageService.getPackageByName(packageName);

        //then
        assertNotNull(silver);
        assertEquals("Silver Pack", silver.getName());
    }

    @Test(expected = InvalidInputException.class)
    public void testD_itShouldGiveExceptionWhenPackageNameIsNull_getPackageByName() {
        //given

        //when
        packageService.getPackageByName(null);

        //then
    }

    @Test(expected = InvalidInputException.class)
    public void testE_itShouldGiveExceptionWhenPackageNameIsNotValid_getPackageByName() {
        //given
        String packageName = "R";

        //when
        packageService.getPackageByName(packageName);

        //then
    }

    @Test(expected = InvalidInputException.class)
    public void testF_itShouldGiveExceptionWhenPackageNameIsMoreThanOneChar_getPackageByName() {
        //given
        // valid input is only S or G for Silver and Gold respectively
        String packageName = "Silver";

        //when
        packageService.getPackageByName(packageName);

        //then
    }
}
