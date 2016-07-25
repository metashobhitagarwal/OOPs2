package OOPS2;

/**
 * This class is used to check input values
 * provided by participants during survey
 */
public class QuestionType {
	/**
	 * This method will check input value
	 * of Single Select type question
	 * @param ans Answer provided by participant
	 * @param range It is the max value of options 
	 * of Single Select type questions
	 * @return Returns true or false according to the answered value 
	 */
	boolean QSingleSelect(String ans, int optionRange){
		try{
			int ans1 = Integer.parseInt(ans);
			
			if(ans1 >= 1 && ans1 <= optionRange){
				return true;
			}else{
				return false;
			}	
		}
		catch(Exception e){
			System.out.println("Enter respective number Only");
		}
		return false;
	}
	
	boolean QMultiSelect(String ans, int optionRange){

		try{
			int ans1 = Integer.parseInt(ans);
			
			if(ans1 >=1 && ans1 <= optionRange){
				return true;
			}else{
				return false;
			}	
		}
		catch(Exception e){
			System.out.println("Enter Respective Number Only");
			
		}
		return false;
	}
	/**
	 * It prints the questions with proper format
	 * @param question
	 */
	static void printQuestion(String[] question){
		String[] options= question[3].split("/");
		System.out.print(question[0]+""+question[1]+","+question[2]);
		System.out.println();
		for(int i=0;i<options.length;i++){
			System.out.println("Option "+(i+1)+"->"+options[i]);
		}
		System.out.print("Enter Answer:");
	}
}