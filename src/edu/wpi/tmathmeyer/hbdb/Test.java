package edu.wpi.tmathmeyer.hbdb;

/**
 * 
 * @author Ted
 *
 */
public class Test {
	/*
	 * Here are some basic queries:
	 * {function:maketable; callID:maketable1; params:{JSON-table, JSON-ID, JSON-FUNCTION-CALL, JSON-STATUS}}
	 * {function:insert; callID:normalinsertion1; params:{JSON-table, JSON-ID, JSON-FUNCTION-CALL, thisIsASampleMethodCall}}
	 * {function:overwrite; callID:overwriteinsertion1; params:{JSON-table, JSON-ID, JSON-FUNCTION-CALL, thisIsANewMethod}}
	 * {function:get; callID:maketable1; params:{JSON-table, JSON-ID, JSON-FUNCTION-CALL}}
	 * {function:get; callID:maketable1; params:{JSON-table, JSON-ID, JSON-STATUS}}
	 * 
	 * NOTE: THIS WILL ONLY RUN ONCE, THEN YOU MUST DELETE THE DATABASE
	 * 
	 * the results should look like this: 
	 * 
	 * {function:maketable, callID:maketable1, result:{table created}}
	 * thisIsASampleMethodCall, {function:insert, callID:normalinsertion1, result:{SUCCESS, database written successfully}}
	 * thisIsANewMethod, {function:overwrite, callID:overwriteinsertion1, result:{SUCCESS, database written successfully}}
	 * {function:get, callID:get1, result:{SUCCESS, thisIsANewMethod}}
	 * {function:get, callID:get2, result:{SUCCESS,  }}
	 * 
	 */
	public static void main(String[] args){
		HomeBase db = HomeBase.getInstance("tableu", "D:/databases");
		try {

			System.out.println(db.query("{function:maketable; callID:maketable1; params:{JSON-table, JSON-ID, JSON-FUNCTION-CALL, JSON-STATUS}}"));
	 		System.out.println(db.query("{function:insert; callID:normalinsertion1; params:{JSON-table, JSON-ID, JSON-FUNCTION-CALL, thisIsASampleMethodCall}}"));
	 		System.out.println(db.query("{function:overwrite; callID:overwriteinsertion1; params:{JSON-table, JSON-ID, JSON-FUNCTION-CALL, thisIsANewMethod}}"));
	 		System.out.println(db.query("{function:get; callID:get1; params:{JSON-table, JSON-ID, JSON-FUNCTION-CALL}}"));
	 		System.out.println(db.query("{function:get; callID:get2; params:{JSON-table, JSON-ID, JSON-STATUS}}"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
}
