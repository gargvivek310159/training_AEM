package training.core;

import java.time.LocalDate;

public class DateTime {
	 public String getTime() {
		    LocalDate myObj = LocalDate.now(); // Create a date object
		    return(myObj.toString()); // Display the current date
		  }

}
