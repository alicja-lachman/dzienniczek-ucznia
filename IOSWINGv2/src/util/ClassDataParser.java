/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 *
 * @author alachman
 */
public class ClassDataParser {
    
    public static String getClassYear(String classData){
        int beginYearIndex = classData.length()-4;
        return classData.substring(beginYearIndex);
    }
    
    public static String getClassName(String classData){
        int beginYearIndex = classData.length()-4;
        return classData.substring(0,beginYearIndex-1).trim();
    }
    
}
