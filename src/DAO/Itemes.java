package DAO;

import java.math.BigDecimal;
import java.sql.*;
import java.util.Random;


public class Itemes
{
	/*private int item_id ;
	private String title ;
	private String author;
	private String publisher ;
	private int year;
	private String isbn;
	private String ee ;
	private String cite ;
	private String pages;
	private String time_offered ;
	private String time_bought;
	private int Seller_id;
	private int buyer_id;
	private int state ; // o = not blocked , 1 = blocked
	private int price ;*/
	
	
	String connectionString = "jdbc:mysql://localhost:3306/9321ass2?autoReconnect=true&useSSL=false";
	Connection c = null;
	Statement cmd = null;
	ResultSet data = null;
	  PreparedStatement pst=null;
	  ResultSetMetaData row;
	
	  
	  public boughtList[] getBoughtItemsById(int id)
		{
			boughtList [] list = new boughtList[2000];
			for(int i=0;i<100;i++)
			{
				list[i]=new boughtList();
			}
			int count = 0;
			int nxt=0;
			try{
				Class.forName("com.mysql.jdbc.Driver");
				c = (Connection) DriverManager.getConnection(connectionString, "root", "12345");
				cmd = c.createStatement();
				if(c!= null){
					data=cmd.executeQuery("select title , time_bought , author5  from items where buyer_id="+id+";");
					row= data.getMetaData();
					int colNum = row.getColumnCount();
					while(data.next()){
						for(int j = 1; j < colNum+1; j++)
						{
							if(nxt==0)
							{
								list[count].title=data.getString(j);
							}else if(nxt ==1)
							{
								list[count].time_b=data.getString(j);
							}else if(nxt ==2)
							{
								nxt=-1;
								list[count].price=data.getString(j);
								count++;
							}
							nxt++;
						}
					}
				}
			}catch(Exception e){
				System.out.println(e);
				return null;
			}
			
			boughtList [] temp = new boughtList[count];
			for(int i=0;i<count;i++)
			{
				temp[i]=new boughtList();
				temp[i].title=list[i].title;
				temp[i].time_b=list[i].time_b;
				temp[i].price=list[i].price;
				
			}
			
			return temp;
		}
		
		public sellerList[] getOfferedItemsBySellerId(int id)
		{
			sellerList [] list = new sellerList[2000];
			for(int i=0;i<100;i++)
			{
				list[i]=new sellerList();
			}
			int count = 0;
			int nxt=0;
			try{
				Class.forName("com.mysql.jdbc.Driver");
				c = (Connection) DriverManager.getConnection(connectionString, "root", "12345");
				cmd = c.createStatement();
				if(c!= null){
					data=cmd.executeQuery("select title , time_offered , author5 , register , item_ID from items where seller_ID="+id+";");
					row= data.getMetaData();
					int colNum = row.getColumnCount();
					while(data.next()){
						for(int j = 1; j < colNum+1; j++)
						{
							if(nxt==0)
							{
								list[count].title=data.getString(j);
							}else if(nxt ==1)
							{
								list[count].time_b=data.getString(j);
							}else if(nxt ==2)
							{
								list[count].price=data.getString(j);
							}else if(nxt ==3)
							{
								
								list[count].register=Integer.parseInt(data.getString(j));
								
							}
							else if(nxt ==4)
							{
								nxt=-1;
								list[count].item_id=Integer.parseInt(data.getString(j));
								count++;
							}
							nxt++;
						}
					}
				}
			}catch(Exception e){
				System.out.println(e);
				return null;
			}
			
			sellerList [] temp = new sellerList[count];
			for(int i=0;i<count;i++)
			{
				temp[i]=new sellerList();
				temp[i].title=list[i].title;
				temp[i].time_b=list[i].time_b;
				temp[i].price=list[i].price;
				temp[i].register=list[i].register;
				temp[i].item_id=list[i].item_id;
			}
			
			return temp;
		}
		
		public String[] generate()
		{
			 Random randomGenerator = new Random();
			 int id = randomGenerator.nextInt(100);
			 
			 String [] d = new String[10];
				int count = 0;
				try{
					Class.forName("com.mysql.jdbc.Driver");
					c = (Connection) DriverManager.getConnection(connectionString, "root", "12345");
					cmd = c.createStatement();
					if(c!= null){
						while(count<10)
						{
							id = randomGenerator.nextInt(100);
						data=cmd.executeQuery("select title from items where item_ID="+id+";");
						row= data.getMetaData();
						int colNum = row.getColumnCount();
						while(data.next()){
							     System.out.println(data.getString(1));
								d[count++]=data.getString(1);
							
						}
						}
						}
				}catch(Exception e){
					System.out.println(e);
					return null;
				}
				
				return d;
		}
	  
	  
	//----------------------isBlocked
		
		public boolean isBlocked(int item_ID)
		{
			try
			{
				Class.forName("com.mysql.jdbc.Driver");
				c = (Connection) DriverManager.getConnection(connectionString,"root","12345");
				cmd = c.createStatement();
				if(c != null)
				{
					//cmd.execute("zeroDateTimeBehavior=convertToNull");
					data=cmd.executeQuery("select register from items where item_ID='"+item_ID+"'; ");
					
						
				}
				while(data.next())
				{
				
				if((data.getString(1).equals("0")))
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
		
	//---------------------block
		
		public boolean changeItemStateByItemId(int id, int s)
		{
			try
			{
				Class.forName("com.mysql.jdbc.Driver");
				c = (Connection) DriverManager.getConnection(connectionString,"root","12345");
				cmd = c.createStatement();
				if(c != null)
				{
					//cmd.execute("zeroDateTimeBehavior=convertToNull");
					cmd.executeUpdate("update items set register='"+s+"' " +
							"where item_ID='"+id+"' ;");
				}
				
				
			}catch(Exception e)
			{
				System.out.println(e);
				return false;
			}
			
			return true;
		}
		
		//--------------search
	public synchronized String [] searchByAuthor(String item) {
		
		String[] itemData =new String[10000];
		String [] temp;
		temp= new String[1];
		temp[0]= "Items not found !!";
		int itemCounter=0;
		if(!isAuthor(item))
		{
			temp= new String[1];
			temp[0]= "Items not found !!";
			System.out.print("here");
			return temp;
		}
		System.out.println("here");
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			c = (Connection) DriverManager.getConnection(connectionString,"root","12345");
			cmd = c.createStatement();
			//cmd.execute("INSERT INTO user_passward (ID , email,passward)VALUES (123,'ab@a.a',123 )");
		//			"VALUES (1234,'3d vision or neural networks' , 2-2-2000 , 3-3-2000,0 ,5432 , 6789)");
			if(c != null)
			{
				
				
				System.out.println(" conntected");
				//pst=c.prepareStatement("SELECT * FROM items where items.title like %"+item+"%");
			//	pst.setString(1, item);
				//data= pst.executeQuery();
				data=cmd.executeQuery("SELECT * FROM items where (items.author like '%"+item+"%' " +
						"or items.author2 like '%"+item+"%' or items.author3 like '%"+item+"%'" +
								"or items.author4 like '%"+item+"%' or items.author5 like '%"+item+"%');");
				
				row=data.getMetaData();
				int colNum = row.getColumnCount();
				
				//System.out.println("here6");
				while(data.next())
				{
				
					for(int j=1; j<colNum+1 ; j++)
					{
						
						itemData[itemCounter++]=data.getString(j);
						System.out.print(itemData[itemCounter-1]);
					}
					
					
				}
				System.out.println("here7");
				
				 temp = new String[++itemCounter];
				for(int i =0; i<itemCounter ;i++)
				{
					temp[i]=itemData[i];
				}
					
				
			}
			
		}catch(Exception e)
		{
			System.out.println(e);
			
		}
		
		
		return temp;
	}
	
	//--------------------delete item by id
	//delete from orders 
	//where id_users = 1 and id_product = 2
	//		limit 1
	public boolean deleteItemById(int id) {
		
		try
		{
		c = (Connection) DriverManager.getConnection(connectionString,"root","12345");
		cmd = c.createStatement();
		if(c != null)
		{
			cmd.execute("delete from items where item_ID="+id+"");
						
				
		}
		
		
	}catch(Exception e)
	{
		System.out.println(e);
		return false;
	}
	return true;
	}
	
	//--------------------item offered by 
	
	public String itemsListOfferedBy(String username)
	{
		User usr = new User();
		String d="";
		if(!usr.isUsername(username) && !usr.checkType(username))
		{
			return "Erorr";
		}
		int id = usr.getId(username);
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			c = (Connection) DriverManager.getConnection(connectionString,"root","12345");
			cmd = c.createStatement();
			if(c != null)
			{
				//cmd.execute("zeroDateTimeBehavior=convertToNull");
				data=cmd.executeQuery("select item_ID  from items where seller_ID='"+id+"'; ");
				
					
			}
			
			row=data.getMetaData();
			int colNum = row.getColumnCount();
			while(data.next())
			{
				for(int j=1; j<colNum+1 ; j++)
				{
					d=d+" "+data.getString(j);
				}
			}
			
			
			
		}catch(Exception e)
		{
			System.out.println(e);
			//return false;
		}
		
		return d;
	}
	
	//--------------------delete  item by title
	
	public boolean deleteItemByTitle(String title)
	{try
	{
		Class.forName("com.mysql.jdbc.Driver");
		c = (Connection) DriverManager.getConnection(connectionString,"root","12345");
		cmd = c.createStatement();
		if(c != null)
		{
			cmd.execute("delete from items where title='"+title+"' limit 1");
						
				
		}
		
		
	}catch(Exception e)
	{
		System.out.println(e);
		return false;
	}
	return true;
	}
	//--------------------addItems
	
	
	/*private int item_id ;
	private String title ;
	private String author;
	private String publisher ;
	private int year;
	private String isbn;
	private String ee ;
	private String cite ;
	private String pages;
	private String time_offered ;
	private String time_bought;
	private int Seller_id;
	private int buyer_id;
	private int state ; // o = not blocked , 1 = blocked
	private int price ;*/
	
	public synchronized boolean addItems( String title,String author,String publisher,int year
			,String isbn,String ee,String cite ,String pages , String offered , int seller_id , int price)
	{
		 int id =generatedId();
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			c = (Connection) DriverManager.getConnection(connectionString,"root","12345");
			cmd = c.createStatement();
			if(c != null)
			{
				cmd.execute("INSERT INTO items (item_ID ,title,author,publisher,year,isbn,ee,cite,pages,time_offered,seller_ID,price,register)"
							+"VALUES("+id+",'"+title+"','"+author+"','"+publisher+"',"+year+",'"+isbn+"','"+ee+
							"','"+cite+"','"+pages+"','"+offered+"' , "+seller_id+",'"+price+"',1) ");
					
			}
			
			
		}catch(Exception e)
		{
			System.out.println(e);
			return false;
		}
		return true;
		
	}

	
	
	//-------------------------------title
	
	
	
public synchronized String[]  searchByTitle(String item) {
		
		String[] itemData =new String[10000];
		String [] temp;
		temp= new String[1];
		temp[0]= "Items not found !!";
		int itemCounter=0;
		if(!isTitle(item))
		{
			
			return temp;
		}
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			c = (Connection) DriverManager.getConnection(connectionString,"root","12345");
			cmd = c.createStatement();
			if(c != null)
			{
				
				
				
				data=cmd.executeQuery("SELECT * FROM items where items.title like '%"+item+"%' && register=0 " );
						
				row=data.getMetaData();
				int colNum = row.getColumnCount();
				
				//System.out.println("here6");
				while(data.next())
				{
				
					for(int j=1; j<colNum+1 ; j++)
					{
						
						itemData[itemCounter++]=data.getString(j);
						
					}
					
					
				}
				
				
				 temp = new String[++itemCounter];
				for(int i =0; i<itemCounter ;i++)
				{
					temp[i]=itemData[i];
				}
					
				
			}
			
		}catch(Exception e)
		{
			System.out.println(e);
			
		}
		
		
		return temp;
	}


//-------------------get/set register

public int getRegister(int item_id)
{
	int d =0;
	try
	{
		Class.forName("com.mysql.jdbc.Driver");
		c = (Connection) DriverManager.getConnection(connectionString,"root","12345");
		cmd = c.createStatement();
		if(c != null)
		{
			data=cmd.executeQuery("select register from items where item_id='"+item_id+"' ; ");
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


public boolean pause(int item_id)
{
	try
	{
		Class.forName("com.mysql.jdbc.Driver");
		c = (Connection) DriverManager.getConnection(connectionString,"root","12345");
		cmd = c.createStatement();
		if(c != null)
		{
			//cmd.execute("zeroDateTimeBehavior=convertToNull");
			cmd.executeUpdate("update items set register='"+1+"' " +
					"where item_ID='"+item_id+"' ;");
		}
		
		
	}catch(Exception e)
	{
		System.out.println(e);
		return false;
	}
	
	return true;
}

public boolean unpause(int item_id)
{
	try
	{
		Class.forName("com.mysql.jdbc.Driver");
		c = (Connection) DriverManager.getConnection(connectionString,"root","12345");
		cmd = c.createStatement();
		if(c != null)
		{
			//cmd.execute("zeroDateTimeBehavior=convertToNull");
			cmd.executeUpdate("update items set register='"+0+"' " +
					"where item_ID='"+item_id+"' ;");
		}
		
		
	}catch(Exception e)
	{
		System.out.println(e);
		return false;
	}
	
	return true;
}
//---------------------searchByCite





public synchronized String[]  searchByCite(String item) {
		
		String[] itemData =new String[10000];
		String [] temp;
		temp= new String[1];
		temp[0]= "Items not found !!";
		int itemCounter=0;
		if(!isCite(item))
		{
			
			return temp;
		}
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			c = (Connection) DriverManager.getConnection(connectionString,"root","12345");
			cmd = c.createStatement();
			if(c != null)
			{
				
				
				
				data=cmd.executeQuery("SELECT * FROM items where items.cite like '%"+item+"%'  && register=0" );
						
				row=data.getMetaData();
				int colNum = row.getColumnCount();
				
				//System.out.println("here6");
				while(data.next())
				{
				
					for(int j=1; j<colNum+1 ; j++)
					{
						
						itemData[itemCounter++]=data.getString(j);
						
					}
					
					
				}
				
				
				 temp = new String[++itemCounter];
				for(int i =0; i<itemCounter ;i++)
				{
					temp[i]=itemData[i];
				}
					
				
			}
			
		}catch(Exception e)
		{
			System.out.println(e);
			
		}
		
		
		return temp;
	}

//--------------------searcgByTitle_multi



public synchronized String[]  searchByTitle_multi(String item , String item2) {
		
		String[] itemData =new String[10000];
		String [] temp;
		temp= new String[1];
		temp[0]= "Items not found !!";
		int itemCounter=0;
		if(!isTitle(item)&& !isTitle(item2) )
		{
			
			return temp;
		}
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			c = (Connection) DriverManager.getConnection(connectionString,"root","12345");
			cmd = c.createStatement();
			if(c != null)
			{
				
				
				
				data=cmd.executeQuery("SELECT * FROM items where items.title like ('%"+item+"%') && items.title like ('%"+item2+"%') && register=0 " );
						
				row=data.getMetaData();
				int colNum = row.getColumnCount();
				
				//System.out.println("here6");
				while(data.next())
				{
				
					for(int j=1; j<colNum+1 ; j++)
					{
						
						itemData[itemCounter++]=data.getString(j);
						
					}
					
					
				}
				
				
				 temp = new String[++itemCounter];
				for(int i =0; i<itemCounter ;i++)
				{
					temp[i]=itemData[i];
				}
					
				
			}
			
		}catch(Exception e)
		{
			System.out.println(e);
			
		}
		
		
		return temp;
	}

//---------------------------searchByYear



public synchronized String[]  searchByYear(String item) {
		
		String[] itemData =new String[10000];
		String [] temp;
		temp= new String[1];
		temp[0]= "Items not found !!";
		int itemCounter=0;
		if(!IsYear(item))
		{
			
			return temp;
		}
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			int itm = Integer.parseInt(item);
			c = (Connection) DriverManager.getConnection(connectionString,"root","12345");
			cmd = c.createStatement();
			if(c != null)
			{
				
				
				
				data=cmd.executeQuery("SELECT * FROM items where CAST(items.year as CHAR) LIKE '"+itm+"%'  && register=0" );
						
				row=data.getMetaData();
				int colNum = row.getColumnCount();
				
				//System.out.println("here6");
				while(data.next())
				{
				
					for(int j=1; j<colNum+1 ; j++)
					{
						
						itemData[itemCounter++]=data.getString(j);
						
					}
					
					
				}
				
				
				 temp = new String[++itemCounter];
				for(int i =0; i<itemCounter ;i++)
				{
					temp[i]=itemData[i];
				}
					
				
			}
			
		}catch(Exception e)
		{
			System.out.println(e);
			
		}
		
		
		return temp;
	}


//----------------------------publisher
public synchronized String[]  searchByPublisher(String item) {
	
	String[] itemData =new String[10000];
	String [] temp;
	temp= new String[1];
	temp[0]= "Items not found !!";
	int itemCounter=0;
	if(isPublisher(item))
	{
		
		return temp;
	}
	
	try
	{
		Class.forName("com.mysql.jdbc.Driver");
		c = (Connection) DriverManager.getConnection(connectionString,"root","12345");
		cmd = c.createStatement();
		if(c != null)
		{
			
			
			
			data=cmd.executeQuery("SELECT * FROM items where items.publisher like '%"+item+"%' && register=0" );
					
			row=data.getMetaData();
			int colNum = row.getColumnCount();
			
			//System.out.println("here6");
			while(data.next())
			{
			
				for(int j=1; j<colNum+1 ; j++)
				{
					
					itemData[itemCounter++]=data.getString(j);
					
				}
				
				
			}
			
			
			 temp = new String[++itemCounter];
			for(int i =0; i<itemCounter ;i++)
			{
				temp[i]=itemData[i];
			}
				
			
		}
		
	}catch(Exception e)
	{
		System.out.println(e);
		
	}
	
	
	return temp;
}
	
	public synchronized boolean IsYear(String year) {
		 try { 
		        Integer.parseInt(year); 
		        Class.forName("com.mysql.jdbc.Driver");
		        c = (Connection) DriverManager.getConnection(connectionString,"root","12345");
				cmd = c.createStatement();
				//cmd.execute("INSERT INTO user_passward (ID , email,passward)VALUES (123,'ab@a.a',123 )");
			//			"VALUES (1234,'3d vision or neural networks' , 2-2-2000 , 3-3-2000,0 ,5432 , 6789)");
				if(c != null)
				{
					
					System.out.println(" conntected");
					data=cmd.executeQuery("SELECT * FROM items where items.year like '%"+year+"%' && register=0");
					while(data.next())
					{
					   //  System.out.println(data.getString(2));
					     return true;
					     
					}
						
				}
		    } catch(NumberFormatException e) { 
		        return false; 
		    } catch(NullPointerException e) {
		        return false;
		    } catch(Exception e) {
		        return false;
		    }
		    // only got here if we didn't return false
		    return true;
	}
	
	
	
	
	public boolean isAuthor(String item)
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			c = (Connection) DriverManager.getConnection(connectionString,"root","12345");
			cmd = c.createStatement();
			//cmd.execute("INSERT INTO user_passward (ID , email,passward)VALUES (123,'ab@a.a',123 )");
		//			"VALUES (1234,'3d vision or neural networks' , 2-2-2000 , 3-3-2000,0 ,5432 , 6789)");
			if(c != null)
			{
				
				System.out.println(" conntected");
				data=cmd.executeQuery("SELECT * FROM items where items.author like '%"+item+"%' && register=0");
				while(data.next())
				{
				   //  System.out.println(data.getString(2));
				     return true;
				     
				}
					
					
			}
			
		}catch(Exception e)
		{
			System.out.println(e);
		}
		return false;
	}
	public boolean isItem_id(int item)
	{
		return false;
	}
	public boolean isTitle(String item)
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			c = (Connection) DriverManager.getConnection(connectionString,"root","12345");
			cmd = c.createStatement();
			//cmd.execute("INSERT INTO user_passward (ID , email,passward)VALUES (123,'ab@a.a',123 )");
		//			"VALUES (1234,'3d vision or neural networks' , 2-2-2000 , 3-3-2000,0 ,5432 , 6789)");
			if(c != null)
			{
				
				System.out.println(" conntected");
				data=cmd.executeQuery("SELECT * FROM items where items.title like '%"+item+"%' && register=0");
				while(data.next())
				{
				   //  System.out.println(data.getString(2));
				     return true;
				     
				}
					
					
			}
			
		}catch(Exception e)
		{
			System.out.println(e);
		}
		return false;
	}
	
	//------------------------IsPublisher
	
	public boolean isPublisher(String item)
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			c = (Connection) DriverManager.getConnection(connectionString,"root","12345");
			cmd = c.createStatement();
			//cmd.execute("INSERT INTO user_passward (ID , email,passward)VALUES (123,'ab@a.a',123 )");
		//			"VALUES (1234,'3d vision or neural networks' , 2-2-2000 , 3-3-2000,0 ,5432 , 6789)");
			if(c != null)
			{
				
				System.out.println(" conntected");
				data=cmd.executeQuery("SELECT * FROM items where items.publisher like '%"+item+"%' && register=0");
				while(data.next())
				{
				   //  System.out.println(data.getString(2));
				     return true;
				     
				}
					
					
			}
			
		}catch(Exception e)
		{
			System.out.println(e);
		}
		return false;
	}
	//------------------------isCite
	
	public boolean isCite(String item)
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			c = (Connection) DriverManager.getConnection(connectionString,"root","12345");
			cmd = c.createStatement();
			//cmd.execute("INSERT INTO user_passward (ID , email,passward)VALUES (123,'ab@a.a',123 )");
		//			"VALUES (1234,'3d vision or neural networks' , 2-2-2000 , 3-3-2000,0 ,5432 , 6789)");
			if(c != null)
			{
				
				System.out.println(" conntected");
				data=cmd.executeQuery("SELECT * FROM items where items.cite like '%"+item+"%' && register=0");
				while(data.next())
				{
				   //  System.out.println(data.getString(2));
				     return true;
				     
				}
					
					
			}
			
		}catch(Exception e)
		{
			System.out.println(e);
		}
		return false;
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
				data=cmd.executeQuery("SELECT item_ID FROM items ORDER BY item_ID DESC LIMIT 1;  ");
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
	
}
