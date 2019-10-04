/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.Teofilo.DAO;

import java.sql.*;

/**
 *
 * @author User
 */
public abstract class DAO {

    protected Connection con = null;
    protected PreparedStatement stmt = null;
    protected ResultSet rs = null;
    protected String sql = null;

}
