package calculator.rms.reader;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class RMSCalculator {

	static String lineReader = ""; 
	public static ArrayList<String> list;
	
	public static void ReadFileAndGetRMS() 
	{
		BufferedWriter bw = null;
			try 
			{
				String allNumbers = "";
				File scanner = new File("input.txt");
				BufferedReader br = new BufferedReader(new FileReader(scanner));
				list = new ArrayList<String>();
				try {
					
					while ((lineReader = br.readLine()) != null) 
					{		
						
						int lineLength = lineReader.length();
						int characterCounter = 0;
						
						while(lineLength > 0)//Check each character in the line
						{
							char lineChar = lineReader.toString().charAt(characterCounter);
							
							if(CheckString(lineChar))
							{
								allNumbers += (""+lineChar);
								System.out.println(lineChar);
							}
							else if(lineChar == ' ')
							{
								break;
								
							}else if(CheckSign(lineChar) && characterCounter == 0) //Check for the negative (-) sign, it must only be in the beginning.
							{
								allNumbers += (""+lineChar);
								
							}else if(!CheckString(lineChar))
							{
								break;
								
							}else if(lineChar == ' ') 
							{
								break;
							}
							
							lineLength--;
							characterCounter++;
						}
						
						if(allNumbers.equals("") || allNumbers.equals("-")|| allNumbers.equals("+"))
						{
							//Why code doesnt get in here??							
						}else 
						{
							int checkZeros = 0;
							checkZeros = Integer.parseInt(allNumbers);
							
							if(checkZeros==0)
							{
								allNumbers = ""+0;
							}
								
							list.add(allNumbers);
							allNumbers = "";
						}
					
					}
					
					System.out.println("All terms" + list.toString());
					System.out.println("RMS" + ReturnRMS(list));
					String OutPut = "";
					OutPut = ReturnRMS(list).toString();
					//Now calculate the RMS using the correct formula.
					
					//Print to out file.
					try 
					{
						File file = new File("output.txt");
						  if (!file.exists()) 
						  {
							     file.createNewFile();
							  }
						
						  FileWriter fw = new FileWriter(file);
						  bw = new BufferedWriter(fw);	
						  
						  bw.write(OutPut);
					      System.out.println("File written Successfully");
						  
					}catch(Exception e) 
					{
						e.printStackTrace();
					}	finally
					{ 
						   try{
						      if(bw!=null)
							 bw.close();
						   }catch(Exception e){
							   e.printStackTrace();
						    }
						}
					
				}catch(Exception e) 
				{
					e.printStackTrace();
				}
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static Double ReturnRMS(ArrayList<String> input) 
	{
		double rms = 0;
		int arraySize = 0;
		arraySize = input.size();
		int termsCounter = 0;
		double sumOfArray = 0;
		int numberReader = 0;
		
		while(arraySize > 0)
		{		
			
			try 
			{
				numberReader = Integer.parseInt(input.get(termsCounter).toString());
				numberReader *= numberReader; 
				
				sumOfArray += numberReader;
								
			}catch(Exception e) 
			{
				e.printStackTrace();
			}		
			
			numberReader = 0;
			termsCounter++;
			arraySize--;
		}
		
		rms = Math.sqrt(sumOfArray/termsCounter);
		
		return rms;
	}

	public static Boolean CheckString(char c) 
	{
		boolean result = true;
		
		try 
		{ 
			Integer.parseInt(String.valueOf(c)); 
		}  
		catch (NumberFormatException e)  
		{ 
			result = false;
		} 
		
		return result;
	}
	
	public static Boolean CheckSign(char input) 
	{
		boolean result = false;
		
		if(input == '-'|| input == '+') 
		{
			result = true;
		}
		return result;
	}
		
	public static void main(String[] args) {

		ReadFileAndGetRMS();
	}

}
