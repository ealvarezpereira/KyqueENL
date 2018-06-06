/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quique.controlador;

import com.quique.calculollaves.ContarLlaves;
import java.sql.SQLException;

/**
 *
 * @author quique
 */
public class CTRLYabestruz {  
    
    public static void recibirFicheroJSON(String json) throws SQLException{
        ContarLlaves.cargarPortales(json);
    }
}
