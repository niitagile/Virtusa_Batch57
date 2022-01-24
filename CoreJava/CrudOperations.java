package IO;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;
class Employee{
	public void AddRecord() throws IOException {
		
		BufferedWriter bw = new BufferedWriter( new FileWriter("naldrix_db.txt",true) );
		Scanner strInput = new Scanner(System.in);
		
		String ID, name, age, addr;
		
		System.out.print("Enter the Employee ID: ");
		ID = strInput.nextLine();
		System.out.print("Enter the Employee Name: ");
		name = strInput.nextLine();
		System.out.print("Enter the Employee Age: ");
		age = strInput.nextLine();
		System.out.print("Enter the Employee Address: ");
		addr = strInput.nextLine();    		
		   		
		bw.write(ID+","+name+","+age+","+addr);
		bw.flush();
		bw.newLine();
		bw.close();		
	
}   
	
	public void ViewAllRecord() throws IOException {
    	BufferedReader br = new BufferedReader( new FileReader("naldrix_db.txt") );
    		
    	String record;
    		
    	System.out.println(" ------------------------------------------------------------- ");
    	System.out.println("|	ID		Name 				Age			Address 		  |");
    	System.out.println(" ------------------------------------------------------------- ");
    		
    	while( ( record = br.readLine() ) != null ) {
    			
    		StringTokenizer st = new StringTokenizer(record,",");
    			
    		System.out.println("|	"+st.nextToken()+"	"+st.nextToken()+" 		"+st.nextToken()+"			"+st.nextToken()+"      |");
	
    	}
    		
    	System.out.println("|	                                            	          |");
    	System.out.println(" ------------------------------------------------------------- ");
    	br.close();    		
    		
    }
	
	public void SearchRecordbyID() throws IOException {
		String ID,record;
		Scanner strInput = new Scanner(System.in);
		
		BufferedReader br = new BufferedReader( new FileReader("naldrix_db.txt") );
		
		System.out.println("\t\t Search Employee Record\n");
	
		
		System.out.println("Enter the Employee ID: ");
		ID = strInput.nextLine();
		
		System.out.println(" ------------------------------------------------------------- ");
		System.out.println("|	ID		Name 				Age			Address 		  |");
		System.out.println(" ------------------------------------------------------------- ");
		boolean flag=false;
		while( ( record = br.readLine() ) != null ) {
			
			StringTokenizer st = new StringTokenizer(record,",");
			if( record.contains(ID) ) {
				System.out.println("|	"+st.nextToken()+"	"+st.nextToken()+" 		"+st.nextToken()+"			"+st.nextToken()+"      |");
				flag=true;
			}
			
			
			
		}
		if(flag==false)
			System.out.println("Record not found");
		System.out.println("|	                                            	          |");
		System.out.println(" ------------------------------------------------------------- ");
		
		br.close();
		
		
		
} 
	public void updateRecordbyID() throws IOException {
		String newName, newAge, newAddr, record,id,record2;
		
		
		
		
		File tempDB = new File("naldrix_db_temp.txt");
		File db = new File("naldrix_db.txt");
			
		BufferedReader br = new BufferedReader( new FileReader( db ) );
		BufferedWriter bw = new BufferedWriter( new FileWriter( tempDB ) );
		    		
		Scanner strInput = new Scanner(System.in);
		
		System.out.println("\t\t Update Employee Record\n\n");   
        System.out.println("Enter ID to be serached");
        id=strInput.nextLine(); 	
		System.out.println("Enter the new Name: ");
		newName = strInput.nextLine();    		
		System.out.println("Enter the new Age: ");
		newAge = strInput.nextLine();  
		System.out.println("Enter the new Address: ");
		newAddr = strInput.nextLine();  
		
		
			
		while( (record2 = br.readLine() ) != null ) {    			
			if(record2.contains(id)) {
				bw.write(id+","+newName+","+newAge+","+newAddr);
			} else {
			
				bw.write(record2);	
			}    			
			bw.flush();
			bw.newLine();
		}
		
		bw.close();
		br.close();    		
		    		
		boolean success = tempDB.renameTo(db);    		
		tempDB.delete();
		System.out.println(success);    		
		
}
	public void DeleteRecordByID() throws IOException {
		Scanner strInput =  new Scanner(System.in);
		String ID, record;
		
		File tempDB = new File("naldrix_db_temp.txt");
		File db = new File("naldrix_db.txt");
			
		BufferedReader br = new BufferedReader( new FileReader( db ) );
		BufferedWriter bw = new BufferedWriter( new FileWriter( tempDB ) );
		System.out.println("\t\t Delete Employee Record\n");
		
		System.out.println("Enter the Employee ID: ");
		ID =  strInput.nextLine();
		
		while( ( record = br.readLine() ) != null ) {
					
			if( record.contains(ID) ) 
				continue;// to skip the record which we want to delete

			bw.write(record);
			bw.flush();
			bw.newLine();
		}
		br.close();
		bw.close();
		db.delete();
		
		tempDB.renameTo(db);
}
}
public class CrudOperations {
public static void main(String[] args) throws IOException{
	 Scanner strInput = new Scanner(System.in);
     String choice,cont = "y";        
     Employee naldrixClass=new Employee();
     while( cont.equalsIgnoreCase("y") ) {        	
        System.out.println("\t\t Employee Information System\n\n");
     
	        System.out.println("1 ===> Add New Employee Record ");
	        System.out.println("2 ===> View All Employee Record ");	
	        System.out.println("3 ===> Delete Employee Record ");
	        System.out.println("4 ===> Search Specific Record ");
	        System.out.println("5 ===> Update Specific Record ");	        
	    
	        System.out.print("\n\n");
	        System.out.println("Enter your choice: ");
	        choice = strInput.nextLine();
	        
	        if( choice.equals("1") ) {
	        		naldrixClass.AddRecord();
	        } else if( choice.equals("2") ) {
	        		naldrixClass.ViewAllRecord();
	        } else if( choice.equals("3") ) {
	        		naldrixClass.DeleteRecordByID();
	        }	else if( choice.equals("4") ) {
	        		naldrixClass.SearchRecordbyID();
	        }	else if( choice.equals("5") ) {
	        		naldrixClass.updateRecordbyID();
	        }	
		        	
	        System.out.println("Do you want to continue? Y/N");
	        cont = strInput.nextLine();
	       	
     }
     }
     
}

