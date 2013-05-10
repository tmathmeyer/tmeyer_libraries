package edu.wpi.tmathmeyer.hbdb;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;

public class HomeBase {
	
	/**
	 * Singletons!
	 * 
	 * @param instanceName name of this database instance
	 * @param filePath the path for the database instance
	 * @return the database
	 */
	public static HomeBase getInstance(String instanceName, String filePath){
		HomeBase instance = instanceGroup.get(instanceName);
		if (instance == null){
			instance = new HomeBase(filePath, instanceName);
			instanceGroup.put(instanceName, instance);
		}
		return instance;
	}
	
	/*
	 * variables
	 */
	private String filePath;
	private String databaseName;
	private static HashMap<String, HomeBase> instanceGroup = new HashMap<String, HomeBase>();
	
	/**
	 * only for use in singleton
	 * 
	 * @param path the path for the database
	 */
	private HomeBase(String path, String name){
		this.setFilePath(path);
		this.setDatabaseName(name);
	}

	/**
	 * @return the filePath
	 */
	public String getFilePath() {
		return filePath;
	}

	/**
	 * @param filePath the filePath to set
	 */
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	
	//{function:put, callID:abc123, params:{"awesome", "tmathmeyer", "lastlogon", "NaN"}}
	public String query(String JSON){
		JSON = JSON.substring(JSON.indexOf("{")+1);
		JSON = JSON.substring(0, JSON.lastIndexOf("}"));
		String[] JSON_PARTS = JSON.split(";");
		String FUNCTION_NAME = null;
		String CALL_ID = null;
		String PARAMETERS = null;
		for(int i = 0; i < JSON_PARTS.length; i++){
			String[] components = JSON_PARTS[i].split(":");
			switch (components[0].toLowerCase().trim()){
				case "function": 	FUNCTION_NAME = components[1];
									break;
				case "callid": 		CALL_ID = components[1];
									break;
				case "params": 		PARAMETERS = components[1];
									break;
			}
		}
		Object indiParams = PARAMETERS.replaceAll("[{}\" ]", "").split(",");
		try {
			Method func = this.getClass().getMethod(FUNCTION_NAME, String[].class);
			String result = (String) func.invoke(this, indiParams);
			return "{function:"+FUNCTION_NAME+", callID:"+CALL_ID+", result:{"+result+"}}";
		} catch (Exception e) {
			return "{function:"+FUNCTION_NAME+", callID:"+CALL_ID+", result:{FAILURE, "+e.toString()+"}}";
		}
	}
	
	
	
	
	public String insert(String args[]) throws Exception{
		return put(args, false);
	}
	public String overwrite(String args[]) throws Exception{
		return put(args, true);
	}
	
	
	
	
	
	/**
	 * args are in the form {table-name, col-id, row-id, value}
	 * 
	 * 
	 * @param args
	 * @param force
	 * @throws Exception
	 */
	private String put(String[] args, boolean force) throws Exception{
		try{
			BufferedReader br = new BufferedReader(new FileReader(this.filePath+"/."+this.databaseName+"/.table-"+args[0]+"/.dex"));
			String[] tableHeaders = br.readLine().split(",");
			br.close();
			int colNumber = -1;
			for(int i = 1; i < tableHeaders.length; i++)
				colNumber = tableHeaders[i].equals(args[2])?i:colNumber;
			try{
				File f = new File(this.filePath+"/."+this.databaseName+"/.table-"+args[0]+"/."+args[1]);
				if (f.exists());
				else{
					f.createNewFile();
					FileWriter out = new FileWriter(this.filePath+"/."+this.databaseName+"/.table-"+args[0]+"/."+args[1]);
					for(int i = 0; i < tableHeaders.length-1; i++)out.write(i==tableHeaders.length-2?" ":" ,");
					out.close();
				}
				br = new BufferedReader(new FileReader(this.filePath+"/."+this.databaseName+"/.table-"+args[0]+"/."+args[1]));
				String line = br.readLine();
				line = line==null?"":line;
				String[] values = line.split(",");
				if (values[colNumber-1].equals(" ") || force){
					FileWriter out = new FileWriter(this.filePath+"/."+this.databaseName+"/.table-"+args[0]+"/."+args[1]);
					values[colNumber-1] = args[3];
					for(int i = 0; i < values.length; i++){
						out.write(values[i]+(i==values.length-1?"":","));
					}
					out.close();
					return "SUCCESS, database written successfully";
				}
				else return "FAILURE, spot already occupied";
			}
			catch(Exception e){
				throw e;
			}
		}
		catch(Exception e){
			throw e;
		}
	}
	
	/**
	 * args are in the form {table-name, col-id, row-id}
	 * 
	 * 
	 * @param args
	 * @throws Exception 
	 */
	public String get(String[] args) throws Exception{
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(this.filePath+"/."+this.databaseName+"/.table-"+args[0]+"/.dex"));
			String[] tableHeaders = br.readLine().split(",");
			br.close();

			int colNumber = -1;
			for(int i = 1; i < tableHeaders.length; i++)
				if (tableHeaders[i].equals(args[2]))
					colNumber = i;
			
			try{
				br = new BufferedReader(new FileReader(this.filePath+"/."+this.databaseName+"/.table-"+args[0]+"/."+args[1]));
				String[] values = br.readLine().split(",");
				return "SUCCESS, "+(values[colNumber-1].equals("")?null:values[colNumber-1]);
			}
			catch(Exception e){
				throw e;
			}
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * args are in the form {table-name {all col names}}
	 * 
	 * 
	 * @param args
	 * @throws DatabaseException 
	 * @throws IOException 
	 */
	public String maketable(String[] args) throws DatabaseException, IOException{
		File table = new File(this.filePath+"/."+this.databaseName+"/.table-"+args[0]);
		table.mkdirs();
		File raf = new File(this.filePath+"/."+this.databaseName+"/.table-"+args[0]+"/.dex");
		if (raf.exists())
			return "table exists";
		raf.createNewFile();
		FileWriter fw = new FileWriter(raf.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);
		for(int i = 1; i < args.length; i++)
			bw.write(args[i]+(i==args.length-1?"":","));
		bw.close();
		return "table created";
	}

	/**
	 * @return the databaseName
	 */
	public String getDatabaseName() {
		return databaseName;
	}

	/**
	 * @param databaseName the databaseName to set
	 */
	public void setDatabaseName(String databaseName) {
		this.databaseName = databaseName;
	}
	
	
}
