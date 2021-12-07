/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FindIPAddress;

import java.net.*;
import java.io.*;
import java.util.*;
import java.net.InetAddress;
 
public class JavaProgram
{
    public static void main(String args[]) throws Exception
    {
        // Returns the instance of InetAddress containing
        // local host name and address
        InetAddress localhost = InetAddress.getLocalHost();
        System.out.println("System IP Address : " +
                      (localhost.getHostAddress()).trim());
 
        // Find public IP address
        String systemipaddress = "";
        try
        {
            String url_name = new URL("https://ipinfo.io").getUserInfo();
            System.out.println(url_name);
//            BufferedReader sc =
//            new BufferedReader(new InputStreamReader(url_name.openStream()));
 
            // reads system IPAddress
//            systemipaddress = sc.readLine().trim();
        }
        catch (Exception e)
        {
            systemipaddress = "Cannot Execute Properly";
        }
        System.out.println("Public IP Address: " + systemipaddress +"\n");
    }
    
   
}