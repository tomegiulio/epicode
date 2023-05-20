import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

/*
 * SQL CODE
 * 
 * CREATE TABLE IF NOT EXISTS public.school_students
(
    id integer NOT NULL DEFAULT nextval('school_students_id_seq'::regclass),
    name character varying COLLATE pg_catalog."default" NOT NULL,
    lastname character varying COLLATE pg_catalog."default" NOT NULL,
    gender "char" NOT NULL,
    birthdate date NOT NULL,
    avg double precision NOT NULL,
    "min_vote" integer,
    "max_vote" integer,
    CONSTRAINT school_students_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.school_students
    OWNER to postgres;
 */

public class Main3 {
	
	private static Connection conn;

   public static void main(String[] args) {
	  // connection
	  conn = connect();
    
	  // insert
	  boolean ins = false;
	  if( ins ) {
		  insertStudent(new Student(
		    "mario", "rossi", Gender.M, LocalDate.of(1980, 10, 10), 6.5, 4, 7 
		  ));
	  }
	  
	  // update
	  updateStudent(1, new HashMap<String, Object>() {{
		  put("name", "luigi");
		  put("lastname", "luigi");
		  put("gender", Gender.M);
		  put("birthdate", LocalDate.of(1980, 10, 10));
		  put("avg", 7.5);
		  put("min_vote", 6);
		  put("max_vote", 8);
	  }});
	  
	  // delete
	  boolean del = false;
	  if( del ) {
		  deleteStudent(1);
	  }
		  
	  // get best
	  System.out.println( getBest() );
	  
	  // get students
	  System.out.println( getVoteRange(4, 8) );
	  
      // disconnection
	  disconnect();
   }
   
   public static Connection connect() {
	  String url = "jdbc:postgresql://localhost:5432/j_theory3_db?useSSL=false";
      String username = "postgres";
      String password = "postgres";
      
      // connection
      conn = null;
      try {
         System.out.println("Connecting to database " + url);
         conn = DriverManager.getConnection(url, username, password);
         System.out.println("Connection is successful!");
      }
      catch(Exception e) {
         e.printStackTrace();
      }
      
      return conn;
   }
   
   public static void disconnect() {
	   if( conn != null ) {    	  
    	  try {
    		  conn.close();
    		  System.out.println("Disonnection is successful!");
    	  } catch (SQLException e) {
    		  e.printStackTrace();
    	  }
      }
   }
   
   public static void insertStudent(Student s) {
	   try {	  
    	  String sql = 
	        "INSERT INTO school_students (name, lastname, gender, birthdate, avg, min_vote, max_vote) "
	        + "VALUES (?, ?, ?, ?, ?, ?, ?)";
    	  PreparedStatement ps = conn.prepareStatement(sql);
    	  ps.setString (1, s.getName());
    	  ps.setString (2, s.getLastname());
    	  ps.setString (3, s.getGender().toString());
    	  ps.setObject (4, s.getBirthdate());
    	  ps.setDouble (5, s.getAvg());
    	  ps.setInt (6, s.getMinVote());
    	  ps.setInt (7, s.getMaxVote());
    	  ps.execute();
    	  
    	  System.out.println("Insert is successful!");
      } catch (SQLException e) {
         e.printStackTrace();
      } 
   }
   
   public static void updateStudent(int id, HashMap<String, Object> s) {
	   try {	  
    	  String sql = 
	        "UPDATE school_students "
	        + "SET name = ?, lastname = ?, gender = ?, birthdate = ?, avg = ?, min_vote = ?, max_vote = ?"
	        + " WHERE id = ?";
    	  PreparedStatement ps = conn.prepareStatement(sql);
    	  ps.setString (1, s.get("name").toString());
    	  ps.setString (2, s.get("lastname").toString());
    	  ps.setString (3, s.get("gender").toString());
    	  ps.setObject (4, s.get("birthdate"));
    	  ps.setDouble (5, (Double)s.get("avg"));
    	  ps.setInt (6, (Integer)s.get("min_vote"));
    	  ps.setInt (7, (Integer)s.get("max_vote"));
    	  ps.setInt (8, id);
    	  ps.execute();
    	  
    	  System.out.println("Update is successful!");
      } catch (SQLException e) {
         e.printStackTrace();
      } 
   }
   
   public static void deleteStudent(int id) {
	   try {
		 String sql = "DELETE FROM school_students WHERE id = ?";
		 PreparedStatement stmt = conn.prepareStatement(sql);
		 stmt.setInt(1, id);		      
	     stmt.executeUpdate(sql);
	 	
	      System.out.println("Delete is successful!");
	   } catch (SQLException e) {
	      e.printStackTrace();
	   } 
   }
   
   public static Student getBest() {
	   Student best = null;
	   
	   try {
		 String sql = "SELECT * FROM school_students WHERE avg = (SELECT MAX(avg) FROM school_students)";
		 PreparedStatement stmt = conn.prepareStatement(sql);
	     
	     ResultSet rs = stmt.executeQuery();
	     while ( rs.next() ) {
	    	 best = new Student(
	    		rs.getString("name"),
	    		rs.getString("lastname"),
	    		Gender.valueOf(rs.getString("gender")),
	    		LocalDate.parse(rs.getObject("birthdate").toString()),
	    		rs.getDouble("avg"),
	    		rs.getInt("min_vote"),
	    		rs.getInt("max_vote")
	    	  );
	     }
	     
	     rs.close();
	     stmt.close();
	   } catch (SQLException e) {
	      e.printStackTrace();
	   } 
	   
	   return best;
   }
   
   public static ArrayList<Student> getVoteRange(int min, int max) {
	     ArrayList<Student> students = new ArrayList<>();  
	   
		 try {
			 String sql = "SELECT * FROM school_students WHERE min_vote >= ? AND max_vote <= ?";
			 PreparedStatement stmt = conn.prepareStatement(sql);
			 stmt.setInt(1, min);
			 stmt.setInt(2, max);
		     
		     ResultSet rs = stmt.executeQuery();
		     while ( rs.next() ) {
		    	 students.add(
		    	  new Student(
		    		rs.getString("name"),
		    		rs.getString("lastname"),
		    		Gender.valueOf(rs.getString("gender")),
		    		LocalDate.parse(rs.getObject("birthdate").toString()),
		    		rs.getDouble("avg"),
		    		rs.getInt("min_vote"),
		    		rs.getInt("max_vote")
		    	  )
		    	 ); 
		     }
		     
		     rs.close();
		     stmt.close();
		   } catch (SQLException e) {
		      e.printStackTrace();
		   } 
		
		return students;
   }
   
}

