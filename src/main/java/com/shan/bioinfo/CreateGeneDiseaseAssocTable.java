package com.shan.bioinfo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;

public class CreateGeneDiseaseAssocTable {
	

	

	public static void main(String[] args) throws IOException, SQLException {
		// TODO Auto-generated method stub
		Connection connection = null;
		connection = DriverManager.getConnection(
			"jdbc:oracle:thin:@localhost:1521:xe","system","123");
		       
        Statement statement = connection.createStatement();

        connection.close();
    }
	}
