package DAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;


public class Cart 
{

String connectionString = "jdbc:mysql://localhost:3306/9321ass2?zeroDateTimeBehavior=convertToNull";
	Connection c = null;
	Statement cmd = null;
	ResultSet data = null;
	ResultSetMetaData row;
	
	//------------------add items to cart 
	public boolean addItems(String username , int items [])
	{
		try{
			for(int i =0; i<items.length;i++)
			{
				if(!checkMyCart(items[i] ,username))
				{
				addItemToCart(username ,  items[i]);
				}
			}
		}catch(Exception e)
		{
			System.out.println(e);
			return false;
		}
		return true;
	}
	//--------------helping method , never used in jsp !!!!
	public void addItemToCart(String username , int item_ID)
	{
		System.out.println("---------------------------- username= "+username);
		System.out.println("---------------------------- item_ID= "+item_ID);
			try
			{
				c = (Connection) DriverManager.getConnection(connectionString,"root","12345");
				cmd = c.createStatement();
				if(c != null)
				{
					cmd.execute("INSERT INTO cart (username ,item_ID )VALUES('"+username+"' , "+item_ID+" )");
					cmd.execute("INSERT INTO history (username ,item_ID )VALUES('"+username+"' , "+item_ID+" )");
						
				}
				
				
			}catch(Exception e)
			{
				System.out.println(e);
				
			}
	}
	public String[] getHistory(String username)
	{
		int [] ides = new int[100];
		int count=0;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			c = (Connection) DriverManager.getConnection(connectionString,"root","12345");
			cmd = c.createStatement();
			if(c != null)
			{
				
				data=cmd.executeQuery("select item_ID from history where username='"+username+"' ; ");
				row=data.getMetaData();
				
				while(data.next())
				{
					ides[count++]=Integer.parseInt(data.getString(1));
				}
			}
		}catch(Exception e)
		{
			System.out.println(e);
			
		}
		if(count==0)
		{
			String []t = {"none"};
			return t;
		}
		
		int [] temp = new int[count];
		for(int i=0;i<count;i++)
		{
			temp[i]=ides[i];
		}
		String [] titles = new String[temp.length];
		
		for(int i=0; i<temp.length;i++)
		{
			titles[i]=getTitle(temp[i]);
		}
		
		return titles;
	}
	
	
	/*public String getTitle(int id)
	{
		String title="";
		try
		{
			c = (Connection) DriverManager.getConnection(connectionString,"root","12345");
			cmd = c.createStatement();
			if(c != null)
			{
				//cmd.execute("zeroDateTimeBehavior=convertToNull");
				data=cmd.executeQuery("select title from items where item_ID='"+id+"' ; ");
				row=data.getMetaData();
				//int colNum = row.getColumnCount();
				while(data.next())
				{
					title=data.getString(1);
				}
			}
		}catch(Exception e)
		{
			System.out.println(e);
			
		}
		return title;
	}*/
	
	public Duplicate[] display(String username)
	{
		return checDuplicate(username);
	}
	public Duplicate[] checDuplicate(String username)
	{
		Duplicate [] obj;
		int ides[] = new int[100];
		String [] titles;
		int count=0;
		try
		{
			c = (Connection) DriverManager.getConnection(connectionString,"root","12345");
			cmd = c.createStatement();
			if(c != null)
			{
				//cmd.execute("zeroDateTimeBehavior=convertToNull");
				data=cmd.executeQuery("select item_ID from cart where username='"+username+"' ; ");
				row=data.getMetaData();
				//int colNum = row.getColumnCount();
				while(data.next())
				{
					ides[count++]=Integer.parseInt(data.getString(1));
				}
			}
		}catch(Exception e)
		{
			System.out.println(e);
			
		}
		
		
		int[] temp = new int[count];
		
		for(int i =0; i<count ;i++)
		{
			temp[i]=ides[i];
		}
		
		titles = new String[count];
		
		for(int i=0; i<count ;i++)
		{
			titles[i]=getTitle(temp[i]);
		}
		
		obj=new Duplicate[count];
		int inc=1;
		int index=0;
		
		for(int i=0;i<count;i++)
		{
			if(!titles[i].equals(""))
			{
			for(int j=0;j<count;j++)
			{
				if(titles[i].equals(titles[j]) && i != j)
				{
					inc++;
					titles[j]="";
				}
			}
			obj[index] = new Duplicate();
			obj[index].title=titles[i];
			titles[i]="";
			obj[index].count=inc;
			index++;
			inc=1;
			
			}
		}
		
		return obj;
	}
	//------helping method for duplicate method
	public String getTitle(int item_id)
	{
		String d ="";
		
		try
		{
			c = (Connection) DriverManager.getConnection(connectionString,"root","12345");
			cmd = c.createStatement();
			if(c != null)
			{
				//cmd.execute("zeroDateTimeBehavior=convertToNull");
				data=cmd.executeQuery("select title from items where item_ID='"+item_id+"' ; ");
				row=data.getMetaData();
				//int colNum = row.getColumnCount();
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
	
	//-----------------delete
	
	public boolean delete(String username , int ides[])
	{
		try
		{
			c = (Connection) DriverManager.getConnection(connectionString,"root","12345");
			cmd = c.createStatement();
			if(c != null)
			{
				//cmd.execute("zeroDateTimeBehavior=convertToNull");
				for(int i=0 ; i<ides.length ; i++)
				{
				cmd.executeQuery("delete from cart where item_ID ="+ides[i]+" && username="+username+"  limit 1 ; ");
				cmd.execute("INSERT INTO history (username ,item_ID )VALUES('"+username+"' , "+ides[i]+" )");
				}
				
			}
			
			
		}catch(Exception e)
		{
			System.out.println(e);
			return false;
		}
		
		return true;
	}
	
	public boolean checkMyCart(int item_ID ,String username)
	{
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			c = (Connection) DriverManager.getConnection(connectionString,"root","12345");
			cmd = c.createStatement();
			if(c != null)
			{
				//cmd.execute("zeroDateTimeBehavior=convertToNull");
				data=cmd.executeQuery("select item_ID  from cart where username='"+username+"' && item_ID="+item_ID+"; ");
				
					
			}
			while(data.next())
			{
			
				return true;
			
			}
			
			
		}catch(Exception e)
		{
			System.out.println(e);
			return false;
		}
		
		return false;
	}
	
}
