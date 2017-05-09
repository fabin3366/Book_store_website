package DAO;

import java.math.BigDecimal;
import java.sql.*;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class User {

String connectionString = "jdbc:mysql://localhost:3306/9321ass2?autoReconnect=true&useSSL=false";
	Connection c = null;
	Statement cmd = null;
	ResultSet data = null;
	ResultSetMetaData row;
	
	/*private int id;
	private String first name;
	private String lastname ;
	private char type;
	private String email;
	private String dob ;
	private String address;
	private String city;
	private String country;
	private String postcode;
	private BigDecimal mobile ;
	private int ccn ;
	private int state ;*/
	
	
	private String [] att = {"ID","fname","type","email","DoB","address","city","country","post_code","mobile","password","state","creditcard","lname","username"};
	
	
	public static void main(String args [])
	{
		System.out.print(new User().set("fabin33", "2000", 5));
	}
	//------------set
	public String getByL(String url)
	{
String d ="";
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			c = (Connection) DriverManager.getConnection(connectionString,"root","12345");
			cmd = c.createStatement();
			if(c != null)
			{
				//cmd.execute("zeroDateTimeBehavior=convertToNull");
				data=cmd.executeQuery("select username from confirm where conf_link='"+url+"' ; ");
				row=data.getMetaData();
				int colNum = row.getColumnCount();
				while(data.next())
				{
					   
						d=data.getString(1);
					
				}
					
			}
		}catch(Exception e)
		{
			System.out.println(e);
			return "error";
		}
		return d;
	}
	
	
	
	public boolean checkUrl(String url)
	{
		
		String d ="";
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			c = (Connection) DriverManager.getConnection(connectionString,"root","12345");
			cmd = c.createStatement();
			if(c != null)
			{
				//cmd.execute("zeroDateTimeBehavior=convertToNull");
				data=cmd.executeQuery("select username from confirm where conf_link='"+url+"' ; ");
				row=data.getMetaData();
				int colNum = row.getColumnCount();
				while(data.next())
				{
					    set(data.getString(1),"0",12);
						return true;
					
				}
					
			}
		}catch(Exception e)
		{
			System.out.println(e);
			return false;
		}
		return false;
	}
	
	
	
	public void setUrl(String username ,String url)
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			c = (Connection) DriverManager.getConnection(connectionString,"root","12345");
			cmd = c.createStatement();
			if(c != null)
			{
				cmd.execute("INSERT INTO confirm (username , conf_link)VALUES('"+username+"','"+url+"')");
			}
		}catch(Exception e)
		{
			System.out.println(e);
		}
	}
	public synchronized String set(String username , String str,int pos)
	{
		if(!isUsername(username))
		{
			return "ID not exsist !!";
		}
		String d ="";
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			c = (Connection) DriverManager.getConnection(connectionString,"root","12345");
			cmd = c.createStatement();
			if(c != null)
			{
				//cmd.execute("zeroDateTimeBehavior=convertToNull");
				cmd.execute("update user_info set "+att[pos-1]+"='"+str+"' where username='"+username+"' ");
				row=data.getMetaData();
				int colNum = row.getColumnCount();
				
			}
			
			
		}catch(Exception e)
		{
			System.out.println(e);
			return"ERROR";
		}
		return "Done";
	}
	
	//--------------------get
	
	public synchronized String get(String username , int pos)
	{
		if(!isUsername(username))
		{
			return "ID not exsist !!";
		}
		String d ="";
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			c = (Connection) DriverManager.getConnection(connectionString,"root","12345");
			cmd = c.createStatement();
			if(c != null)
			{
				//cmd.execute("zeroDateTimeBehavior=convertToNull");
				data=cmd.executeQuery("select "+att[pos-1]+" from user_info where username='"+username+"' ; ");
				row=data.getMetaData();
				int colNum = row.getColumnCount();
				while(data.next())
				{
					
						d=data.getString(1);
					
				}
					
			}
			
			
		}catch(Exception e)
		{
			System.out.println(e);
			return"ERROR";
		}
		return d;
	}
	
	//-----------------------get all info
	
	public synchronized String getAllInfo(int id)
	{
		if(!isId(id))
		{
			return "ID not exsist !!";
		}
		String d ="";
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			c = (Connection) DriverManager.getConnection(connectionString,"root","12345");
			cmd = c.createStatement();
			if(c != null)
			{
				//cmd.execute("zeroDateTimeBehavior=convertToNull");
				data=cmd.executeQuery("select * from user_info where ID='"+id+"' ; ");
				row=data.getMetaData();
				int colNum = row.getColumnCount();
				while(data.next())
				{
					for(int j=1; j<colNum+1 ; j++)
					{
						d=d+" "+data.getString(j);
					}
				}
					
			}
			
			
		}catch(Exception e)
		{
			System.out.println(e);
			return"ERROR";
		}
		return d;
	}
	//--------------------selectIDByUserName--------
	
	public synchronized int getId(String username)
	{
		if(!isUsername(username))
		{
			return -1;
		}
		int d =0;
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			c = (Connection) DriverManager.getConnection(connectionString,"root","12345");
			cmd = c.createStatement();
			if(c != null)
			{
				//cmd.execute("zeroDateTimeBehavior=convertToNull");
				data=cmd.executeQuery("select ID from user_info where username='"+username+"' ; ");
				row=data.getMetaData();
				int colNum = row.getColumnCount();
				while(data.next())
				{
					
						d=Integer.parseInt(data.getString(1));
					
				}
					
			}
			
			
		}catch(Exception e)
		{
			System.out.println(e);
			return -1;
		}
		return d;
	}
	
	// -----------------get username by id------------
	
	
	public synchronized String getUsername(int id)
	{
		if(!isId(id))
		{
			return "id not found!!";
		}
		String d ="";
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			c = (Connection) DriverManager.getConnection(connectionString,"root","12345");
			cmd = c.createStatement();
			if(c != null)
			{
				//cmd.execute("zeroDateTimeBehavior=convertToNull");
				data=cmd.executeQuery("select username from user_info where username='"+id+"' ; ");
				row=data.getMetaData();
				int colNum = row.getColumnCount();
				while(data.next())
				{
					
						d=data.getString(2);
					
				}
					
			}
		}catch(Exception e)
		{
			System.out.println(e);
			return "Error";
		}
		return d;
	}
	
	//---------------------newUser------------------
	
	public synchronized boolean newUser(String fname ,String lname ,String username ,char type,String email,String dob
			,String add,String city,String country ,String post ,BigDecimal ccn,String passward, BigDecimal mob)
	{
		int id =generatedId();
		
		try
		{
			byte [] pass =enc(passward);
			Class.forName("com.mysql.jdbc.Driver");
			c = (Connection) DriverManager.getConnection(connectionString,"root","12345");
			cmd = c.createStatement();
			if(c != null)
			{
				String q ="INSERT INTO user_info (ID , fname,lname,username,password,creditCard,type,email,DoB,address,city,country,post_code,mobile)"
						+ " values (?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
				PreparedStatement pst = c.prepareStatement(q);
				pst.setInt(1, id);
				pst.setString(2, fname);
				pst.setString(3, lname);
				pst.setString(4, username);
			
				pst.setBytes(5, pass);
				pst.setBigDecimal(6, ccn);
				pst.setString(7, String.valueOf(type));
				pst.setString(8, email);
				pst.setString(9, dob);
				pst.setString(10, add);
				pst.setString(11, city);
				pst.setString(12, country);
				pst.setString(13, post);
				pst.setBigDecimal(14, mob);
				pst.execute();
				//cmd.execute("INSERT INTO user_info (ID , fname,lname,username,password,creditCard,type,email,DoB,address,city,country,post_code,mobile)"
		//					+"VALUES("+id+",'"+fname+"','"+lname+"','"+username+"','"+pass+"',"+ccn+",'"+type+"','"+email+"','"+dob+"','"+add+"','"+city+
				//			"','"+country+"','"+post+"',"+mob+") ");
					
			}
			
			
		}catch(Exception e)
		{
			System.out.println(e);
			return false;
		}
		return true;
		
	}
	//---------------------block
	
	public boolean changeUserStateByUsername(String username)
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			c = (Connection) DriverManager.getConnection(connectionString,"root","12345");
			cmd = c.createStatement();
			if(c != null)
			{
				//cmd.execute("zeroDateTimeBehavior=convertToNull");
				cmd.executeUpdate("update user_info set state='"+1+"' " +
						"where username='"+username+"' ;");
			}
			
			
		}catch(Exception e)
		{
			System.out.println(e);
			return false;
		}
		
		return true;
	}
	//----------------------isBlocked
	
	public boolean isBlocked(String username)
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			c = (Connection) DriverManager.getConnection(connectionString,"root","12345");
			cmd = c.createStatement();
			if(c != null)
			{
				//cmd.execute("zeroDateTimeBehavior=convertToNull");
				data=cmd.executeQuery("select state  from user_info where name='"+username+"'; ");
				
					
			}
			while(data.next())
			{
			
			if((data.getString(1).equals("1")))
			{
				System.out.println(data.getString(1));
				return true;
			}
			}
			
			
		}catch(Exception e)
		{
			System.out.println(e);
			return false;
		}
		
		return false;
	}
	
	
	
	//-------------------id-----------------------
	
	public synchronized boolean isId(int id)
	{
			try
			{
				Class.forName("com.mysql.jdbc.Driver");
				c = (Connection) DriverManager.getConnection(connectionString,"root","12345");
				cmd = c.createStatement();
				if(c != null)
				{
					//cmd.execute("zeroDateTimeBehavior=convertToNull");
					data=cmd.executeQuery("select count(*)  from user_info where ID='"+id+"' ; ");
					
						
				}
				data.next();
				
				if((data.getInt(1)==0))
				{
					System.out.println(data.getInt(1));
					return false;
				}
				
				
				
			}catch(Exception e)
			{
				System.out.println(e);
				return false;
			}
			return true;
	}
	
	//--------------------isusername
	
	public synchronized boolean isUsername(String username)
	{
			try
			{
				Class.forName("com.mysql.jdbc.Driver");
				c = (Connection) DriverManager.getConnection(connectionString,"root","12345");
				cmd = c.createStatement();
				if(c != null)
				{
					//cmd.execute("zeroDateTimeBehavior=convertToNull");
					data=cmd.executeQuery("select count(*)  from user_info where username='"+username+"' ; ");
					
						
				}
				data.next();
				
				if((data.getInt(1)==0))
				{
					return false;
				}
				
			}catch(Exception e)
			{
				System.out.println(e);
				return false;
			}
			return true;
	}
	
	
	
	//---------------------login---------------------
	
	public synchronized boolean login(String username , String passward)
	{
		try
		{ 
		
			Class.forName("com.mysql.jdbc.Driver");
			c = (Connection) DriverManager.getConnection(connectionString,"root","12345");
			cmd = c.createStatement();
			if(c != null)
			{
				//cmd.execute("zeroDateTimeBehavior=convertToNull");
				data=cmd.executeQuery("select password  from user_info where username='"+username+"' ; ");
				}System.out.println("here is the problem   ="+data);
			while(data.next())
			{
				System.out.println("------------------before "+dec(data.getBytes(1)));
				//	System.out.println("condition ="+passward==dec(data.getBytes(1)));
				if(passward.equalsIgnoreCase(dec(data.getBytes(1))))
				{
					System.out.println("------------------inside ");
					return true;
				}
				System.out.println("--------------------after");
			}
		}catch(Exception e)
		{
			System.out.println(e);
			return false;
		}
		
		return false;
	}
	//---------------------name---------------------------------
	
	public synchronized boolean isName(String name)
	{
			try
			{
				Class.forName("com.mysql.jdbc.Driver");
				c = (Connection) DriverManager.getConnection(connectionString,"root","12345");
				cmd = c.createStatement();
				if(c != null)
				{
					//cmd.execute("zeroDateTimeBehavior=convertToNull");
					data=cmd.executeQuery("select count(*)  from user_info where name='"+name+"' ; ");
					
						
				}
				data.next();
				
				if((data.getInt(1)==0))
				{
					System.out.println(data.getInt(1));
					return false;
				}
				
				
				
			}catch(Exception e)
			{
				System.out.println(e);
				return false;
			}
			return true;
	}
	
	//----------set name
	public synchronized String setName(String username ,String name) {
		if(!isUsername(username))
		{
			return "ID not exsist !!";
		}
		String d ="";
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			c = (Connection) DriverManager.getConnection(connectionString,"root","12345");
			cmd = c.createStatement();
			if(c != null)
			{
				
				cmd.executeUpdate("update user_info set name='"+name+"' " +
						"where username='"+username+"' ;");
				}
		}catch(Exception e)
		{
			System.out.println(e);
			return"ERROR";
		}
		return "done";
		
	}
	
	//----------------get name
	
	
	
	//---------------------------type-----------------------
	
	public synchronized boolean isType(char type)
	{
			try
			{
				Class.forName("com.mysql.jdbc.Driver");
				c = (Connection) DriverManager.getConnection(connectionString,"root","12345");
				cmd = c.createStatement();
				if(c != null)
				{
					//cmd.execute("zeroDateTimeBehavior=convertToNull");
					data=cmd.executeQuery("select count(*)  from user_info where type='"+type+"' ; ");
					
						
				}
				data.next();
				
				if((data.getInt(1)==0))
				{
					System.out.println(data.getInt(1));
					return false;
				}
				
				
				
			}catch(Exception e)
			{
				System.out.println(e);
				return false;
			}
			return true;
	}
	public synchronized String setType(int id ,char type) {
		if(!isId(id))
		{
			return "ID not exsist !!";
		}
		String d ="";
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			c = (Connection) DriverManager.getConnection(connectionString,"root","12345");
			cmd = c.createStatement();
			if(c != null)
			{
				//cmd.execute("zeroDateTimeBehavior=convertToNull");
				//data=cmd.executeQuery("select * from user_info where ID='"+id+"' ; ");
				cmd.executeUpdate("update user_info set type='"+type+"' " +
						"where ID='"+id+"' ;");
				}
		}catch(Exception e)
		{
			System.out.println(e);
			return"ERROR";
		}
		return "done";
		
	}
	
	public boolean checkType(String username)
	{
		if(!isUsername(username))
		{
			return false;
			
		}
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			c = (Connection) DriverManager.getConnection(connectionString,"root","12345");
			cmd = c.createStatement();
			if(c != null)
			{
				//cmd.execute("zeroDateTimeBehavior=convertToNull");
				data=cmd.executeQuery("select count(*)  from user_info where username='"+username+"' and type='s' ; ");
				
					
			}
			data.next();
			
			if((data.getInt(1)==0))
			{
				System.out.println(data.getInt(1));
				return false;
			}
			
			
			
		}catch(Exception e)
		{
			System.out.println(e);
			return false;
		}
		return true;
	}
	
	
	
	
	//-------------------------email---------------------------------------
	
	public synchronized boolean isEmail(String email)
	{
			try
			{
				Class.forName("com.mysql.jdbc.Driver");
				c = (Connection) DriverManager.getConnection(connectionString,"root","12345");
				cmd = c.createStatement();
				if(c != null)
				{
					//cmd.execute("zeroDateTimeBehavior=convertToNull");
					data=cmd.executeQuery("select count(*)  from user_info where email='"+email+"' ; ");
					
						
				}
				data.next();
				
				if((data.getInt(1)==0))
				{
					System.out.println(data.getInt(1));
					return false;
				}
				
				
				
			}catch(Exception e)
			{
				System.out.println(e);
				return false;
			}
			return true;
	}
	public synchronized String setEmail(int id ,String email) {
		if(!isId(id))
		{
			return "ID not exsist !!";
		}
		String d ="";
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			c = (Connection) DriverManager.getConnection(connectionString,"root","12345");
			cmd = c.createStatement();
			if(c != null)
			{
				//cmd.execute("zeroDateTimeBehavior=convertToNull");
				//data=cmd.executeQuery("select * from user_info where ID='"+id+"' ; ");
				cmd.executeUpdate("update user_info set email='"+email+"' " +
						"where ID='"+id+"' ;");
				}
		}catch(Exception e)
		{
			System.out.println(e);
			return"ERROR";
		}
		return "done";
		
	}
	
	
	
	//-----------------------dob----------------------------
	
	public synchronized boolean isDob(String dob)
	{
			try
			{
				Class.forName("com.mysql.jdbc.Driver");
				c = (Connection) DriverManager.getConnection(connectionString,"root","12345");
				cmd = c.createStatement();
				if(c != null)
				{
					//cmd.execute("zeroDateTimeBehavior=convertToNull");
					data=cmd.executeQuery("select count(*)  from user_info where DoB='"+dob+"' ; ");
					
						
				}
				data.next();
				
				if((data.getInt(1)==0))
				{
					System.out.println(data.getInt(1));
					return false;
				}
				
				
				
			}catch(Exception e)
			{
				System.out.println(e);
				return false;
			}
			return true;
	}
	public synchronized String setDob(int id ,String dob) {
		if(!isId(id))
		{
			return "ID not exsist !!";
		}
		String d ="";
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			c = (Connection) DriverManager.getConnection(connectionString,"root","12345");
			cmd = c.createStatement();
			if(c != null)
			{
				//cmd.execute("zeroDateTimeBehavior=convertToNull");
				//data=cmd.executeQuery("select * from user_info where ID='"+id+"' ; ");
				cmd.executeUpdate("update user_info set dob='"+dob+"' " +
						"where ID='"+id+"' ;");
				}
		}catch(Exception e)
		{
			System.out.println(e);
			return"ERROR";
		}
		return "done";
		
	}
	
	
	
	//-----------------------------address-----------------------
	
	public synchronized boolean isAdd(String add)
	{
			try
			{
				Class.forName("com.mysql.jdbc.Driver");
				c = (Connection) DriverManager.getConnection(connectionString,"root","12345");
				cmd = c.createStatement();
				if(c != null)
				{
					//cmd.execute("zeroDateTimeBehavior=convertToNull");
					data=cmd.executeQuery("select count(*)  from user_info where address='"+add+"' ; ");
					
						
				}
				data.next();
				
				if((data.getInt(1)==0))
				{
					System.out.println(data.getInt(1));
					return false;
				}
				
				
				
			}catch(Exception e)
			{
				System.out.println(e);
				return false;
			}
			return true;
	}
	public synchronized String setAddress(int id ,String address) {
		if(!isId(id))
		{
			return "ID not exsist !!";
		}
		String d ="";
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			c = (Connection) DriverManager.getConnection(connectionString,"root","12345");
			cmd = c.createStatement();
			if(c != null)
			{
				//cmd.execute("zeroDateTimeBehavior=convertToNull");
				//data=cmd.executeQuery("select * from user_info where ID='"+id+"' ; ");
				cmd.executeUpdate("update user_info set address='"+address+"' " +
						"where ID='"+id+"' ;");
				}
		}catch(Exception e)
		{
			System.out.println(e);
			return"ERROR";
		}
		return "done";
		
	}
	
	
	
	//---------------------------city--------------------------------

	public synchronized boolean isCity(String city)
	{
			try
			{
				Class.forName("com.mysql.jdbc.Driver");
				c = (Connection) DriverManager.getConnection(connectionString,"root","12345");
				cmd = c.createStatement();
				if(c != null)
				{
					//cmd.execute("zeroDateTimeBehavior=convertToNull");
					data=cmd.executeQuery("select count(*)  from user_info where city='"+city+"' ; ");
					
						
				}
				data.next();
				
				if((data.getInt(1)==0))
				{
					System.out.println(data.getInt(1));
					return false;
				}
				
				
				
			}catch(Exception e)
			{
				System.out.println(e);
				return false;
			}
			return true;
	}
	public synchronized String setCity(int id ,String city) {
		if(!isId(id))
		{
			return "ID not exsist !!";
		}
		String d ="";
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			c = (Connection) DriverManager.getConnection(connectionString,"root","12345");
			cmd = c.createStatement();
			if(c != null)
			{
				//cmd.execute("zeroDateTimeBehavior=convertToNull");
				//data=cmd.executeQuery("select * from user_info where ID='"+id+"' ; ");
				cmd.executeUpdate("update user_info set city='"+city+"' " +
						"where ID='"+id+"' ;");
				}
		}catch(Exception e)
		{
			System.out.println(e);
			return"ERROR";
		}
		return "done";
		
	}
	
	
	
	//-----------------------country------------------------------
	
	public synchronized boolean isCountry(String count)
	{
			try
			{
				Class.forName("com.mysql.jdbc.Driver");
				c = (Connection) DriverManager.getConnection(connectionString,"root","12345");
				cmd = c.createStatement();
				if(c != null)
				{
					//cmd.execute("zeroDateTimeBehavior=convertToNull");
					data=cmd.executeQuery("select count(*)  from user_info where country='"+count+"' ; ");
					
						
				}
				data.next();
				
				if((data.getInt(1)==0))
				{
					System.out.println(data.getInt(1));
					return false;
				}
				
				
				
			}catch(Exception e)
			{
				System.out.println(e);
				return false;
			}
			return true;
	}
	
	
	//----------------set country------------------------
	
	
	public synchronized String setCountry(int id ,String country) {
		if(!isId(id))
		{
			return "ID not exsist !!";
		}
		String d ="";
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			c = (Connection) DriverManager.getConnection(connectionString,"root","12345");
			cmd = c.createStatement();
			if(c != null)
			{
				//cmd.execute("zeroDateTimeBehavior=convertToNull");
				//data=cmd.executeQuery("select * from user_info where ID='"+id+"' ; ");
				cmd.executeUpdate("update user_info set country='"+country+"' " +
						"where ID='"+id+"' ;");
				}
		}catch(Exception e)
		{
			System.out.println(e);
			return"ERROR";
		}
		return "done";
		
	}
	
	
	
	//--------------------postcode---------------------------------
	public synchronized boolean isPostcode(String post)
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			c = (Connection) DriverManager.getConnection(connectionString,"root","12345");
			cmd = c.createStatement();
			if(c != null)
			{
				//cmd.execute("zeroDateTimeBehavior=convertToNull");
				data=cmd.executeQuery("select count(*)  from user_info where post_code='"+post+"' ; ");
				
					
			}
			data.next();
			
			if((data.getInt(1)==0))
			{
				System.out.println(data.getInt(1));
				return false;
			}
			
			
			
		}catch(Exception e)
		{
			System.out.println(e);
			return false;
		}
		return true;
}
	public synchronized String setPostcode(int id ,String postcode) {
		if(!isId(id))
		{
			return "ID not exsist !!";
		}
		String d ="";
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			c = (Connection) DriverManager.getConnection(connectionString,"root","12345");
			cmd = c.createStatement();
			if(c != null)
			{
				//cmd.execute("zeroDateTimeBehavior=convertToNull");
				//data=cmd.executeQuery("select * from user_info where ID='"+id+"' ; ");
				cmd.executeUpdate("update user_info set post_code='"+postcode+"' " +
						"where ID='"+id+"' ;");
				}
		}catch(Exception e)
		{
			System.out.println(e);
			return"ERROR";
		}
		return "done";
		
	}
	
	
	
	
	//-----------------mobile------------------------------------
	
	public synchronized boolean isMobile(BigDecimal mob)
	{
			try
			{
				Class.forName("com.mysql.jdbc.Driver");
				c = (Connection) DriverManager.getConnection(connectionString,"root","12345");
				cmd = c.createStatement();
				if(c != null)
				{
					//cmd.execute("zeroDateTimeBehavior=convertToNull");
					data=cmd.executeQuery("select count(*)  from user_info where mobile='"+mob+"' ; ");
					
						
				}
				data.next();
				
				if((data.getInt(1)==0))
				{
					System.out.println(data.getInt(1));
					return false;
				}
				
				
				
			}catch(Exception e)
			{
				System.out.println(e);
				return false;
			}
			return true;
	}
	public synchronized String setMobile(int id ,BigDecimal mobile) 
	{
		if(!isId(id))
		{
			return "ID not exsist !!";
		}
		String d ="";
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			c = (Connection) DriverManager.getConnection(connectionString,"root","12345");
			cmd = c.createStatement();
			if(c != null)
			{
				//cmd.execute("zeroDateTimeBehavior=convertToNull");
				//data=cmd.executeQuery("select * from user_info where ID='"+id+"' ; ");
				cmd.executeUpdate("update user_info set mobile='"+mobile+"' " +
						"where ID='"+id+"' ;");
				}
		}catch(Exception e)
		{
			System.out.println(e);
			return"ERROR";
		}
		return "done";
		
	}
	
	public int generatedId()
	{
		int d=0;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			c = (Connection) DriverManager.getConnection(connectionString,"root","12345");
			cmd = c.createStatement();
			if(c != null)
			{
				data=cmd.executeQuery("SELECT ID FROM user_info ORDER BY id DESC LIMIT 1;  ");
				row=data.getMetaData();
				int colNum = row.getColumnCount();
				while(data.next())
				{
					
						d=Integer.parseInt(data.getString(1));
						d=d+1;
					
				}	
			}
		}catch(Exception e)
		{
			System.out.println(e);
			return -1;
		}
		return d;
	}

	
	
	public byte[] enc(String pas)
	{
		char [] r = pas.toCharArray();
		byte[] input = new byte[r.length];
		for(int i=0;i<r.length;i++)
		{
			 input[i]=(byte)r[i];
			 System.out.println("r= "+(char)input[i]);
			 System.out.println("input= "+input[i]);
			 
		}
		
		byte[] keyBytes = new byte[8];
		byte[] ivBytes = new byte[8];
		
		
		SecretKeySpec key = new SecretKeySpec(keyBytes,  "DES");
		IvParameterSpec ivSpec = new IvParameterSpec(ivBytes);
		byte[] encrypted=null;
		try
		{
            Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, key, ivSpec);
			 encrypted= new byte[cipher.getOutputSize(input.length)];
			int enc_len = cipher.update(input, 0, input.length, encrypted, 0);
			enc_len += cipher.doFinal(encrypted, enc_len);
			
		}catch(Exception e)
		{
			System.out.println(e);
		}
		System.out.println("encrypted= "+encrypted);
		System.out.println("enc len= "+encrypted.length);
		return encrypted;
	}
	
	
	public String dec(byte[] en)
	{
		System.out.println("en   = "+en);
		System.out.println("en len  = "+en.length);
		String pass="";
		try
		{
			Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
			byte[] keyBytes = new byte[8];
			byte[] ivBytes = new byte[8];
			SecretKeySpec key = new SecretKeySpec(keyBytes, "DES");
			IvParameterSpec ivSpec = new IvParameterSpec(ivBytes);
			cipher.init(Cipher.DECRYPT_MODE, key, ivSpec);
			byte[] decrypted = new byte[cipher.getOutputSize(en.length)];
			int dec_len = cipher.update(en, 0, en.length, decrypted, 0);
			dec_len += cipher.doFinal(decrypted, dec_len);
			System.out.println("dec   = "+decrypted.length);
			System.out.println("dec len   = "+decrypted);
			for(int i=0;i<dec_len;i++)
			{
				if(decrypted[i]<10)
				{
			     pass+=decrypted[i];
			     System.out.println("first "+pass);
				}
				System.out.println("here 1 ");
				if(decrypted[i]>9)
				{
					
				pass+=(char)decrypted[i];
				System.out.println("seconed "+pass);
				}
			}
		}catch(Exception e)
		{
			System.out.println(e);
		}
		System.out.println("finall   ="+pass);
		return pass;
	}
	

}
