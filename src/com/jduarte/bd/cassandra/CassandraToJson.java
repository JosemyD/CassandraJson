package com.jduarte.bd.cassandra;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.google.gson.Gson;


public class CassandraToJson {
	
	private static String host;
	private static String keyspace;
	private static String user;
	private static String password;
	private static Session Conexion = null;
	
	/*Model of table in Cassandra*/
	private static ModelCassandra modelRow = new ModelCassandra();
	
	public static String getHost() {
		return host;
	}

	public static void setHost(String host) {
		CassandraToJson.host = host;
	}

	public static String getKeyspace() {
		return keyspace;
	}

	public static void setKeyspace(String keyspace) {
		CassandraToJson.keyspace = keyspace;
	}

	public static String getUser() {
		return user;
	}

	public static void setUser(String user) {
		CassandraToJson.user = user;
	}

	public static String getPassword() {
		return password;
	}

	public static void setPassword(String password) {
		CassandraToJson.password = password;
	}

	public static Session getConexion() {
		return Conexion;
	}

	public static void setConexion(Session conexion) {
		Conexion = conexion;
	}
	
	private static Session getSession(){
		if (getConexion() == null) {
			setConexion(createSesion());
		}
		return Conexion;
		
	}
	
	private static Session createSesion(){

	Cluster cluster = Cluster.builder()
	  .addContactPoint(getHost())
	  //.withCredentials(getUser(), getPassword())
	  .build();
	
	return cluster.connect(getKeyspace());
	}
	
	public static ResultSet Query(String query, String host,
		String keyspace) {		
		setHost(host);
		System.out.println(getHost());
		setKeyspace(keyspace);
		ResultSet results = getSession().execute(query);
		
		return results;
	}
	
	public static String toJson(Row row){
		Gson gson = new Gson(); 
	    
		modelRow.id = row.getInt("id");
		modelRow.name = row.getString("name");
		modelRow.values = row.getMap("values", String.class, String.class);
		String json = gson.toJson(modelRow); 		

		return json;
		
	}
	
	public static void main(String[] args) {
		setHost("localhost");
		setKeyspace("test");
		//setUser("cassandra");
		//setPassword("cassandra");		
		ResultSet rs = Query("SELECT * FROM test.test limit 1;", getHost(), getKeyspace());
		Row row;
		while(rs.isExhausted() == false){
			row=rs.one();
		    String json = toJson(row);
			System.out.println(json);
		}

	}

}
