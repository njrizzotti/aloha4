package com.salesforce.tests.fs;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Place holder for your unit tests
 */
public class YourUnitTest {

    @Test
    public void testRegexForDirectoryNameOk(){
        Pattern patter = Pattern.compile("^[a-zA-Z0-9]*$");
        Matcher matcher = patter.matcher("asrsdfAA34Adc");
        boolean result = matcher.matches();
        Assert.assertEquals(true, result);
    }

    @Test
    public void testRegexNoOkByADotForDirectoryName(){
        Pattern patter = Pattern.compile("^[a-zA-Z0-9]*$");
        Matcher matcher = patter.matcher("asrsd.fAA34Adc");
        boolean result = matcher.matches();
        Assert.assertEquals(false, result);
    }

    @Test
    public void testRegexNoOkByAWhiteSpaceForDirectoryName(){
        Pattern patter = Pattern.compile("^[a-zA-Z0-9]*$");
        Matcher matcher = patter.matcher("asrsd_fAA34Adc");
        boolean result = matcher.matches();
        Assert.assertEquals(false, result);
    }

    @Test
    public void testRegexForCDCommand(){
        Pattern patter = Pattern.compile("^[a-zA-Z0-9]*$");
        Matcher matcher = patter.matcher("testA34");
        boolean result = matcher.matches();
        Assert.assertEquals(true, result);
    }

    @Test
    public void testRegexNoOkForCDCommand(){
        Pattern patter = Pattern.compile("^/[a-zA-Z0-9]*$");
        Matcher matcher = patter.matcher("testA34");
        boolean result = matcher.matches();
        Assert.assertEquals(false, result);
    }
}
