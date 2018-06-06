/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quique.calculollaves;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author quique
 */
public class ConexionesBD {

    static Connection conn;

    /**
     * @param url Url de la base de datos.
     */
    public static void conexionBase(String url) throws SQLException {
        conn = DriverManager.getConnection(url);
    }

    public static ResultSet rs;

    /**
     * @param consulta Consulta que quieres realizar en la base de datos.
     */
    public static void resultSet(String consulta) throws SQLException {
        Statement st = conn.createStatement();
        rs = st.executeQuery(consulta);
    }

    public static PreparedStatement pst;

    /**
     * @param consulta Consulta que quieres realizar en la base de datos.
     */
    public static void preparedStatement(String consulta) throws SQLException {
        pst = conn.prepareStatement(consulta);
        pst.executeUpdate();
        pst.close();
    }
}
