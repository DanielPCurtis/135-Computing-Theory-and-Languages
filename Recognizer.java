import java.io.*;
import java.util.Scanner;

//Created by Daniel Curtis CSC 135 Section 4


//Recognizer token(), advancePtr(), match(), error(), start(), and main()
//written by Scott Gordon 1/03
//minor modifications made 2/04, 9/06, 2/09, 9/11, 2/15
// 	  to run on Athena (linux) -
//    save as:  Recognizer.java
//    compile:  javac Recognizer.java
//    execute:  java Recognizer


public class Recognizer {
	static String inputString;
	static int index = 0;
	static int errorflag = 0;

	private char token(){
		return(inputString.charAt(index));
	}

	private void advancePtr(){
		if (index < (inputString.length()-1))
			index++;
	}

	private void match(char T){
		if (T == token())
			advancePtr();
		else error();
	}
	private void error(){
		System.out.println("error at position: " + index);
		errorflag = 1;
		advancePtr();
	}
//---------------------//
//Daniel's Contribution//

	private void bool()	{
		if ((token() == 'T') || (token() == 'F'))
			match(token());
		else error();
	}
	private void digit()	{
		if ((token() == '0') || (token() == '1'))
			match(token());
		else error();
	}
	private void letter()	{
		if ((token() == 'W') || (token() == 'X') || (token() == 'Y') || (token() == 'Z'))
			match(token());
		else error();
	}
	private void integer() {
		digit();
		while((token() == '0') || (token() == '1'))	{
			digit();
		}
	}
	private void character() {
		if ((token() == '0') || (token() == '1'))
			digit();
		else
			letter();
	}
	private void ident()	{
		letter();
		while((token() == '0') || (token() == '1') || (token() == 'W') || (token() == 'X')
				|| (token() == 'Y') || (token() == 'Z'))
			character();
	}
	private void opratr()	{
		if((token() == '<') || (token() == '=') || (token() == '>') || (token() == '!') || (token() == '^'))
			match(token());
		else error();
	}
	private void oprnd()	{
		if(token() == '0' || token() == '1')	{
			integer();
		}
		else if((token() == 'W') || (token() == 'X') || (token() == 'Y') || (token() == 'Z'))	{
			ident();
		}
		else if((token() == 'T') || (token() == 'F')) {
			bool();
		}
		else if(token() == '(')	{
			match('(');
			exprsn();
			match(')');
		}
	}
	private void factor()	{
		oprnd();
		while(token() == '*')	{
			match('*');
			oprnd();
		}
	}
	private void exprsn()	{
		factor();
		while(token() == '+') {
			match('+');
			factor();
		}
	}
	private void comprsn()	{
		if(token() == '(')
			match(token());
		oprnd();
		opratr();
		oprnd();
		if(token() == ')')
			match(token());
	}
	private void progcall()	{
		if(token() == 'C')
			match(token());
		program();
		if(token() == 'G')
			match(token());
	}
	private void iosym()	{
		if(token() == 'R' || token() == 'O')
			match(token());
	}
	private void inout()	{
		iosym();
		ident();
		while(token() == ',')	{
			match(',');
			ident();
		}
		if(token() == ';')
			match(token());
	}
	private void myDo()	{
		if(token() == 'D')
			match(token());
		else error();
		while(token() == 'W' || token() == 'X' || token() == 'Y' || token() == 'Z' || token() == 'I'
				|| token() == 'D' || token() == 'R' || token() == 'O' || token() == 'C')	{
			statemt();
		}
		if(token() == 'U')
			match(token());
		else error();
		comprsn();
		if(token() == 'E')
            match(token());
        else error();
	}
	private void ifstmt()	{
		if(token() == 'I')
			match(token());
		else error();
		comprsn();
		if(token() == '@')
			match(token());

		while(token() == 'W' || token() == 'X' || token() == 'Y' || token() == 'Z' || token() == 'I'
				|| token() == 'D' || token() == 'R' || token() == 'O' || token() == 'C')	{
			statemt();
		}

		if(token() == '%')	{
			match(token());
			while(token() == 'W' || token() == 'X' || token() == 'Y' || token() == 'Z' || token() == 'I'
					|| token() == 'D' || token() == 'R' || token() == 'O' || token() == 'C')	{
				statemt();
			}
		}
		if(token() == '&')
			match(token());
	}
	private void assnmt()	{
		ident();
		if(token() == '~')
			match(token());
		exprsn();
		if(token() == ';')
			match(token());
	}
	private void statemt()	{
		if((token() == 'W') || (token() == 'X') || (token() == 'Y') || (token() == 'Z'))
			assnmt();
		else if(token() == 'I')
			ifstmt();
		else if(token() == 'D')
			myDo();
		else if(token() == 'R' || token() == 'O')
			inout();
		else if(token() == 'C')
			progcall();
	}
	private void program()	{
		if (token() == 'S')
			match(token());
		while(token() == 'W' || token() == 'X' || token() == 'Y' || token() == 'Z' || token() == 'I'
				|| token() == 'D' || token() == 'R' || token() == 'O' || token() == 'C')	{
			statemt();
		}
	}


//----------------------

	private void start(){
		program();
		match('$');
		if (errorflag == 0)
			System.out.println("legal." + "\n");
		else
			System.out.println("errors found." + "\n");
	}

//----------------------

	public static void main (String[] args) throws IOException {
		Recognizer rec = new Recognizer();
		Scanner input = new Scanner(System.in);
		System.out.print("\n" + "enter an expression: ");
		inputString = input.nextLine();
		rec.start();
		input.close();
		return;
	}
}

