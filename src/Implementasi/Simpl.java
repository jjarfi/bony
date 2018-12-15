/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Implementasi;

import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.util.Properties;

/**
 *
 * @author HellCat
 */
public class Simpl {

    Properties properties = new Properties();
    InputStream inputStream;
    OutputStream output = null;

    Connection con;

    String url;
    String user;
    String pass;
    String unicode = "?useUnicode=yes&characterEncoding=UTF-8";

}
