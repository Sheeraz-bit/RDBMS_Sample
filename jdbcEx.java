package jdbcdemo;
 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
 
public class JDBC_Demo_PostgreSQL {
private static final String URL="jdbc:postgresql://localhost:5432/mydb";
private static final String USER="postgres";
private static final String PASSWD="123";
 
private static Connection getConnection() throws SQLException{
	 return DriverManager.getConnection(URL, USER, PASSWD);
}
 
public static void createTbl(String tbl,String sql) {
	 try(Connection con=JDBC_Demo_PostgreSQL.getConnection()){
		 Statement stmt=con.createStatement();
		 stmt.executeUpdate(sql);
		 System.out.println(tbl+" created sucessfully");
	 }
	 catch(SQLException e) {e.printStackTrace();}
}//createTbl
 
public static void insertTblRow(String tblName,Collection<Data> dataCollection,String query) throws SQLException{
	 try(Connection con=JDBC_Demo_PostgreSQL.getConnection()){
		PreparedStatement pstm=con.prepareStatement(query);
		for(Data d:dataCollection) {
			pstm.setInt(1,d.getId());
			pstm.setString(2,d.getName());
			pstm.setInt(3, d.getAge());
			pstm.setFloat(4, d.getSalary());
			pstm.addBatch();
		}//for
		int[] rowInserted=pstm.executeBatch();
		System.out.println(rowInserted.length+" rows inserted sucessfully into "+tblName);
	 }
}//insertTblRow
	public static void main(String[] args) {
		String table="empdetails";
		/*String query="create table if not exists "+table+
				"("+"id serial primary key,"+
				"name varchar(100) not null,"+
				"age int not null,"+"salary decimal(10,2) not null)"; */
		//JDBC_Demo_PostgreSQL.createTbl(table,query);
		Collection<Data> dataCollection=new ArrayList<>();
		dataCollection.add(new Data(2,"Anirudha",40,200000));
		dataCollection.add(new Data(3,"Dinesh",30,20000));
		dataCollection.add(new Data(4,"Priyanka",50,250000));
		String sql="insert into "+table+" values(?,?,?,?)";
		try {
			JDBC_Demo_PostgreSQL.insertTblRow(table,dataCollection,sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 
	}
 
}//JDBC_Demo_PostgreSQL
 
 
class Data{
	private int id;
	private String name;
	private int age;
	private float salary;
	public Data(int id, String name, int age, float salary) {
		this.id = id;
		this.name = name;
		this.age = age;
		this.salary = salary;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public float getSalary() {
		return salary;
	}
	public void setSalary(float salary) {
		this.salary = salary;
	}
	
	@Override
	public String toString() {
		return "Data [id=" + id + ", name=" + name + ", age=" + age + ", salary=" + salary + "]";
	}
	
	
}//Data
 
