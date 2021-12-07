/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FindIPAddress;

import java.util.*;

/**
 *
 * @author Admin
 */
public class test{
    public static void main(String[] args) {
        try (java.util.Scanner s = new java.util.Scanner(new java.net.URL("https://api.ipdata.co/country_name").openStream(), "UTF-8").useDelimiter("\\A")) {
            System.out.println("My current country is " + s.next());
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }
}