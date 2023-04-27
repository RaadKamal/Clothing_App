package com.example.clothingappframework;

import com.example.clothingappframework.Application.ApplicationTest;
import com.example.clothingappframework.Logic.QueryProductTesting;
import com.example.clothingappframework.Logic.ValidityTesting;
import com.example.clothingappframework.Objects.CartItemTesting;
import com.example.clothingappframework.Objects.CartTesting;
import com.example.clothingappframework.Objects.ProductTesting;
import com.example.clothingappframework.Objects.VariantTesting;
import com.example.clothingappframework.Persistence.DBControllerTest;
import com.example.clothingappframework.Persistence.DBMockTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import clothingapp.business.Validity;


@RunWith(Suite.class)
@Suite.SuiteClasses({
        ApplicationTest.class,
        CartItemTesting.class,
        CartTesting.class,
        ProductTesting.class,
        VariantTesting.class,
        QueryProductTesting.class,
        ValidityTesting.class,
        DBControllerTest.class,
        DBMockTest.class,
})

public class AllTests {}
