// usual includes
#include <stdio.h>
#include <stdlib.h>
#include <strings.h>

int main(int argc, char *argv[]){

  //Various printing methods
  printf("String : %s", stringVar);
  printf("Character : %c", charVar);
  printf("Decimal : %d", decimalVar);

  -------------------------------------------------

  //Get the length of a string
  char[50] str;
  int length;

  strcpy(str, "This is the string I'm testing");
  length = strlen(str);

  -------------------------------------------------

  //Compare two strings without case sensitivity

  char *str1 = "STRING ONE";
  char *str2 = "string TWO";
  int result;

  result = strncasecmp(str1, str2, 10);

  //Result < 0 -> string1 less than string2
  //Result > 0 -> string1 greater than string2
  //Result = 0 -> string1 equivalent to string2

  -------------------------------------------------

  //Find a substring within a string

  char* modeNum;
  modeNum = argv[1];

  if(strcasestr("encryption", modeNum))
    mode = 0;

  else if(strcasestr("decryption", modeNum))
    mode = 1;

  -------------------------------------------------

  //Converting a string (char array) to an int

  int val;
  char str[20];

  strcpy(str, "98993489");
  val = atoi(str);

  -------------------------------------------------

  //Copying part of a buffer to another buffer
  memcpy(coinbuf, &value, STANDARDLENGTH);
  memcpy(coinbuf+4, &newbits, STANDARDLENGTH);
  memcpy(coinbuf+8, &newkeylen, STANDARDLENGTH);

  -------------------------------------------------
  //Copy command line args to a string
  FILE *fptr;
  char filename[PATH_MAX];

  strncpy(filename,argv[1],PATH_MAX);

  //Can also do it from user input
  printf("Enter the filename to open \n");
  char *rv = fgets(filename,PATH_MAX,stdin);

  -------------------------------------------------

  //Opening a file

	fptr = fopen(filename, "r");

  if (fptr == NULL) {
		printf("Cannot open file: |%s|\n",filename);
		exit(5);
	}

  -------------------------------------------------

  // Reading contents from a file

  char c
  c = (char) fgetc(fptr);

  //While c != EndOfFile
  while (c != EOF) {

    //Prevents binary crap being printed
		if (!isprint(c) && !isspace(c) && c!='\n' && c!='\r') {
			printf ("0x%x", c);
		} else {
			printf ("%c", c);
		}
		c = fgetc(fptr);
    printf ("%c", c);
	}

	fclose(fptr); // close the file
}
