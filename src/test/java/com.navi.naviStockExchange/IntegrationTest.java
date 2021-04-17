package com.navi.naviStockExchange;

import com.navi.stockexchangeapp.GeekTrust;
import com.navi.stockexchangeapp.parser.FileParser;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.util.List;
import java.util.Objects;

public class IntegrationTest {

    @Test
    public void testInputExample() throws Exception {
        File inputFile = readInputFile(TestConstants.IntegrationTestConstants.TEST_INPUT_FILE);
        List<String> result = GeekTrust.placeOrderNaviSE(inputFile);

        List<String> answer =  new FileParser().parse(readInputFile(TestConstants.IntegrationTestConstants.TEST_OUTPUT_FILE));
        Assert.assertEquals(answer, result);
    }

    private File readInputFile(String inputFileName) {
        ClassLoader classLoader = IntegrationTest.class.getClassLoader();
        return new File(Objects.requireNonNull(classLoader.getResource(inputFileName)).getFile());
    }
}
