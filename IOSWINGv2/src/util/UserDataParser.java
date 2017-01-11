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
public class UserDataParser {

    public static String getName(String userdata) {

        String[] splitStr = userdata.split("\\s+");
        return splitStr[0];
    }

    public static String getSurname(String userdata) {
        String[] splitStr = userdata.split("\\s+");
        return splitStr[splitStr.length - 1];
    }

}
