/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author alachman
 */
public class ClassDataParserTest {

    @Test
    public void testGetClassYear() {
        String classData = "A 2016";
        String classData1 = "AE 2015";

        assertEquals("2016", ClassDataParser.getClassYear(classData));
        assertEquals("2015", ClassDataParser.getClassYear(classData1));
    }

    @Test
    public void testGetClassName() {
        String classData = "A 2016";
        String classData1 = "AE 2015";
        String classData2 = "ABC   2014";

        assertEquals("A", ClassDataParser.getClassName(classData));
        assertEquals("AE", ClassDataParser.getClassName(classData1));
        assertEquals("ABC", ClassDataParser.getClassName(classData2));
    }

}
