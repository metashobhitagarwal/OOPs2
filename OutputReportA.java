package OOPS2;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 * This class is used to print output ReportA
 * on console screen
 */
public class OutputReportA {

	/**
	 * This method will display overall percentage rating
	 * given by all participants
	 * @param totalParticipants
	 */
	void display(int totalParticipants){
		 FileInputStream fis = null;
	     BufferedReader reader = null;
	     String line= "";
	     int avgRating= 0;
	     int[] temp= new int[5];
	        
	     try{
        	fis = new FileInputStream(".//src//OOPS2//AnswerDataFile");
        	reader = new BufferedReader(new InputStreamReader(fis));
            line = reader.readLine();
   
            while(line != null){
            	String arr1[]= line.split(",");
            	int value= Integer.parseInt(arr1[1]);
            	temp[value-1]+= value;
            	line= reader.readLine();
            }
            
            System.out.println("Overall Rating, Single Select, (1/2/3/4/5)");
            for(int i=0;i<5;i++){
            	avgRating= (temp[i] / (i+1)) * 100 / totalParticipants;  //Calculating overall percentage rating
            	System.out.println((i+1) + "-" + avgRating + "%");
            }
	     }
	     catch(Exception e){
	    	 e.printStackTrace();
	     }
	}
}