# 135-Computing-Theory-and-Languages

Consider the following EBNF grammar for a very simple programming language: program ::=  S {statemt} 
statemt ::=  assnmt | ifstmt | do | inout | progcall 
assnmt  ::=  ident  ~  exprsn  ; 
ifstmt  ::=  I comprsn @ {statemt} [% {statemt }] & 
do ::=  D { statemt } U comprsn  E 
inout    ::=  iosym ident { , ident } ; 
iosym  ::=  R | O 
progcall :=  C program G 
comprsn ::=  ( oprnd opratr oprnd ) 
exprsn ::=  factor {+ factor} 
factor  ::=  oprnd {* oprnd } 
oprnd   ::=  integer | ident | bool | ( expr ) 
opratr  ::=  < | = | > | ! | ^ 
ident   ::=  letter {char} 
char    ::=  letter | digit 
integer ::=  digit {digit} 
letter  ::=  W | X | Y | Z 
digit   ::= 0 | 1 
bool ::= T | F 
 
The tokens are: S I D U E R O C G W X Y Z 0 1 T F ; ~ @ % & , ( ) + * < = > ! ^ Non-terminals are shown as lowercase words. 
 
Note that the following characters are NOT tokens (they are EBNF meta-symbols):  [  ] |  {  }  
 
1. Compute the FIRST and FOLLOW for all the non-terminals in the above grammar. 
 
2. Show that the grammar satisfies the two requirements for predictive parsing (it does, you just need to prove it).  
Make sure that you use the supplement rules for EBNF grammars found in the notes and the example provided. 
 
3. Implement a recursive-descent recognizer. 
• Prompt the user for an input stream (e.g. a file). 
• The user enters an input stream of legal tokens, followed by a $. 
• You can assume: o the user enters no whitespace, o the user only enters legal tokens listed above, o the user enters one and only one $, at the end. 
• The start symbol is “program” (as defined above) 
• Your program should output "legal" or "errors found" (not both!). You can report additional information as well, if you want. For example, knowing where your program finds an error could be helpful to assign partial credit if it's wrong. 
• Assume the input stream is the TOKEN stream.  Assume that any whitespace has already been stripped out by the lexical scanner (i.e., each token is one character - lexical scanning has been completed) 
• Since the incoming token stream is terminated with a $, you will need to add the $ to the grammar and incorporate it in your answers to questions #1 and #2 above, where appropriate. 
• Use Java, C, or C++, or ask your instructor if you wish to use another language. 
• Limit your source code to ONE file. 
• Make sure your program works on ATHENA before submitting it. 
• INCLUDE INSTRUCTIONS FOR COMPILING AND RUNNING YOUR PROGRAM ON ATHENA IN A COMMENT BLOCK AT THE TOP OF YOUR PROGRAM.  Also, explain any input formatting that your program requires of the user entry. 
 
4. Collect the following submission materials into ONE folder: • your source code file • the FIRST and FOLLOW in the form of a table (see the notes) • a proof indicating that predictive parsing can be performed. (you can hand write it and scan it, or you can do it on the computer).  Make sure that it is clear and readable
