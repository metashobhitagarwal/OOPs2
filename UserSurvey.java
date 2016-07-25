package OOPS2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

/**
 * This class is will do a survey based on the
 *  questions provided in the QuestionDataFile
 */
public class UserSurvey {
	
	static Scanner sc= new Scanner(System.in);
	public static void main(String args[]) {
	      int participantId= 0;
	    
	      String ch;
	     
	      do{
	    	  surveyApp(++participantId);
	    	  
	    	  System.out.println("Enter Y to enter survey of next Participant=");
	    	  ch= sc.next();
	      }while(ch.equalsIgnoreCase("y"));
	      
	      new OutputReportA().display(participantId);
	}
	
	/**
	 * This method will read the questions from QuestionDataFile 
	 * and display them to the participant
	 * It will also record the participant's responses in AnswerDataFile 
	 * @param participantId
	 * @return It will return participant's id
	 */
	@SuppressWarnings("resource")
	static int surveyApp(int participantId){
		 //reading file line by line in Java using BufferedReader       
        FileInputStream fis = null;
        BufferedReader reader = null;
        BufferedWriter bufferedWriter = null;
        String line= "";
        
       try {
            fis = new FileInputStream(".//src//OOPS2//QuestionDataFile");
            reader = new BufferedReader(new InputStreamReader(fis));
          
            line = reader.readLine();
            
            while(line != null){
                //System.out.println(line);       
                String arr1[]= line.split(",");     
                bufferedWriter = new BufferedWriter(new FileWriter(".//src//OOPS2//AnswerDataFile", true));

                if(arr1[2].equalsIgnoreCase("Single select")){
                	QuestionType.printQuestion(arr1);
                	String[] arr2= arr1[3].split("/");
                	String ans= sc.next();
                	if(new QuestionType().QSingleSelect(ans, arr2.length)){
                		bufferedWriter.write("Participant"+participantId+","+ans+",");
                		bufferedWriter.flush();
                	}else{
                		System.out.println("Enter Correct input, Try again..!");
                		surveyApp(participantId);
                	}
                }
                else if(arr1[2].equalsIgnoreCase("Multi Select")){  
                	QuestionType.printQuestion(arr1);
                	String[] arr2= arr1[3].split("/");
                	String ans= sc.next();
                	String[] temp= new String[arr2.length];
                	int count= 0;
                	do{
                		if(!Arrays.asList(temp).contains(ans))
                		{
                			System.out.println("Enter n to move to next question or \n Enter other area of improvement:");
                			if(new QuestionType().QMultiSelect(ans, arr2.length)){
                				bufferedWriter.write(arr2[Integer.parseInt(ans)-1]+"/");
                    			bufferedWriter.flush();
	                    	}else{
	                    		System.out.println("Enter Correct input, Try again..!");
	                    	}	
	                    	if(count == arr2.length){
	                    		break;
	                    	}else{
	                    		temp[count++]= ans;
	                    	}
                		}else{
                			System.out.println("Already Entered");
                		}
                		ans= sc.next();
                	}while(!ans.equalsIgnoreCase("n") || count >= arr2.length);
                	
                }
                else if(arr1[2].equalsIgnoreCase("text")){
                	System.out.println(line);
                	System.out.print("Enter Answer:");
                    bufferedWriter.write("," + sc.next());
                    bufferedWriter.newLine();
                    bufferedWriter.flush();
                    System.out.println("Survey Completed..:)");
                    reader.close();
                    fis.close();
                    return participantId;
                }
                line = reader.readLine();   
            } 
            sc.close();
            bufferedWriter.close();
        }
       catch (Exception ex) {
    	   ex.printStackTrace();
       }
     return participantId;   
	}
}