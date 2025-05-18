import java.util.HashMap;
import java.util.Calendar;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Person {
	private String personID;
	private String firstname;
	private String address;
	private String birthdate;
	private HashMap<Date, Integer> demeritPoints;
	private boolean isSuspended;
	
	public Person(String personID, String firstname, String address, String birthdate) {
		this.personID = personID;
		this.firstname = firstname;
		this.address = address;
		this.birthdate = birthdate;
	}
	
	public void setID(String newID) {
		this.personID = newID;
	}
	public void setName(String newName) {
		this.firstname = newName;
	}
	public void setAddress(String newAddress) {
		this.address = newAddress;
	}
	public void setBirthdate(String newBirthdate) {
		this.birthdate = newBirthdate;
	}
	public String getID() {
		return this.personID;
	}
	public String getName() {
		return this.firstname;
	}
	public String getAddress() {
		return this.address;
	}
	public String getBirthdate() {
		return this.birthdate;
	}
	public void setSuspended(boolean value){
		this.isSuspended = value;
	}
	public boolean getLicenceStatus(){
		return this.isSuspended;
	}
	public void setDemeritPoints(HashMap<Date, Integer> pointsList){

	}
	public HashMap<Date, Integer> getDemeritPoints(){
		return this.demeritPoints;
	}
	
	public boolean isDigit2and9(char c) {
		//check if passed character is a digit between 2 and 9
		return c >= '2' && c <= '9';
	}

	public boolean isSpecialChar3to8(String id){
		String specialChars = "!@#$%^&-_+=<>/";
		int counter = 0;
		for(int i = 2; i <=7; i++){
			char c = id.charAt(i);
			if(specialChars.indexOf(c) != -1){
				counter++;
			}
		}
		if (counter >= 2) {
			System.out.println(counter);
			return true;	
		}
		else{
			System.out.println("less than two special chars from 3 - 8");
			return false;
		}
		
	}

	public boolean isUppercaseLastTwo(String id){
		return Character.isUpperCase(id.charAt(8)) && Character.isUpperCase(id.charAt(9)) ;
	}

	public boolean isValidID(String id){

		return isDigit2and9(id.charAt(0)) && isDigit2and9(id.charAt(1)) && isSpecialChar3to8(id) && isUppercaseLastTwo(id);
	}
	public boolean isValidAddressFormat(String address) {
		//if there is no address, return false
		if(address.isEmpty()) {
			System.out.println("address is empty");
			return false;
		}
		
		//if there is an address, check the formatting
		//split the address with the part separator character "|"
		String[] parts = address.split("\\|");
		
		if(parts.length != 5) {
			//if the parts split are not 5, incorrect format
			System.out.println("address split error");
			return false;
		}
		
		//use of trim() to remove leading and trailing whitespace for format checking
		String streetNum = parts[0].trim();
		String streetName = parts[1].trim();
		String city = parts[2].trim();
		String state = parts[3].trim();
		String country = parts[4].trim();
		
		//check validity of parts
		if(!streetNum.matches("\\d+")) {
			//if streetNum is not a digit
			System.out.println("stret number expected in first position");
			return false;
		}
		if(!state.equals("Victoria")) {
			//state must be Victoria
			System.out.println(state);
			System.out.println("Victoria state not match");
			return false;
		}
		if(streetName.isEmpty() && city.isEmpty() && country.isEmpty()) {
			System.out.println("street, city and country are empty");
			//Every other part needs to have a value
			return false;
		}
		
		//if we reach this line, all checks have passed, so return true
		return true;
		
	}
	
	public boolean isValidDateFormat(String date) {
		
		if(date.isEmpty()) {
			System.out.println("date is empty");
			return false;
		}
		String[] parts = date.split("\\-");
		
		if(parts.length != 3) {
			//should be split in day/month/year
			System.out.println("date is longer than expected");
			return false;
		}
		
		String day = parts[0].trim();
		String month = parts[1].trim();
		String year = parts[2].trim();
		
		if(day.isEmpty() || month.isEmpty() || year.isEmpty()) {
			//if there is no values in any of the parts, invalid format
			System.out.println("date is empty");
			return false;
		}
		
		int dayNum = Integer.parseInt(day);
		int monthNum = Integer.parseInt(month);
		int yearNum = Integer.parseInt(year);
		if(monthNum < 1 || monthNum > 12) {
			//invalid month
			System.out.println("month invalid");
			return false;
		}
		
		if(monthNum == 2 && (dayNum < 1 || dayNum > 28)){
			//check for february, ignore leap years
			System.out.println("february checked invalid day");
			return false;
		}
		if((monthNum == 1 || monthNum == 3 || monthNum == 5 || monthNum == 7 || monthNum == 8 || monthNum == 10 || monthNum == 12) && (dayNum < 1 || dayNum > 31)) {
			//check for months with 31 days
			System.out.println("31 day months checked invalid day");
			return false;
		}
		if((monthNum == 4 || monthNum == 6 || monthNum == 9 || monthNum == 11) && (dayNum < 1 || dayNum > 30)) {
			//check for months with 30 days
			System.out.println("30 day months checked invalid day");
			return false;
		}
		if(yearNum > 2025 || yearNum < 1900) {
			//check for values greater than current year
			System.out.println("year is future or too far back");
			return false;
		}
		
		//if this line is reached, all checks passed
		System.out.println("date format correct");
		return  true;
	}

	public boolean isWithinTwoYears(String date){
		//new date sets the variable to current date
		Date now = new Date();
		Calendar cal = Calendar.getInstance();
		//correct date format
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		//set calendar to current date
		cal.setTime(now);
		//set calendar to two years back
		cal.add(Calendar.YEAR, -2);
		//create a new date holding two years ago 
		Date twoYearsAgo = cal.getTime();
		
		if(this.isValidDateFormat(date)){
			try {
				System.out.println("custom valid date format checked and passed");
				Date parsedDate = sdf.parse(date);
				if(parsedDate.after(twoYearsAgo) && parsedDate.before(now)){
					//it is in range within two years of current date
					return true;
				}
				else{
					//it is not within range
					return false;
				}

        	} catch (ParseException e) {
            	System.out.println("Invalid date format. Please use dd-MM-yyyy.");
				return false;
        	}
		}else{
			return false;
		}

	}
	
	public boolean isUnderAge(String date) {
		String[] parts = date.split("\\-");
		String year = parts[2];
		int yearNum = Integer.parseInt(year);
		if((2025 - yearNum) < 18) {
			return false;
		}
		else {
			return true;
		}
		
	}
	public boolean isFirstDigitEven(String id) {
		if((id.charAt(0)%2) == 0 ) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean addPerson() {
		//TODO: method adds information about a person to a .txt file
		//After meeting conditions, write to a .txt file and return true.
		//otherwise, don't write to file and return false.
		
		if(this.personID.length() == 10) {
			System.out.println("Id is correct length");
			if(this.getName().isEmpty()){
				System.out.println("name is empty");
				return false;
			}
			if(isValidID(this.getID()) && isValidAddressFormat(this.getAddress()) && isValidDateFormat(this.getBirthdate()) ) {
				//TODO: write to a file
				return true;
			}
		}
		System.out.println("Not all checks passed");
		return false;
		
	}	
			
	
	public boolean updatePersonalDetails(Person prevDetails) {
//		TODO: Update all details of a person in a .txt file
		
		//TODO: get person details from .txt file
		//set variable names to details retrieved from file.
		String prevID = prevDetails.getID();
		String prevName = prevDetails.getName();
		String prevAddress = prevDetails.getAddress();
		String prevBirthdate = prevDetails.getBirthdate();
		Person toUpdate = new Person(prevID, prevName, address, birthdate);
		
		//get new details like a form. If same details, get them anyway.
		String newID = "";
		String newName = "";
		String newAddress = "";
		String newBirthdate = "";
		
		//1)
		if(toUpdate.isUnderAge(toUpdate.getBirthdate())) {
			if(!address.equals(newAddress)) {
				System.out.println("You are under 18, you cannot change your address.");
				return false;
			}
		}
		
		//2)
		if(!birthdate.equals(newBirthdate)) {
			if(!prevID.equals(newID) || !prevName.equals(newName) || !prevAddress.equals(newAddress)) {
				System.out.println("Your birthdate is different, no other detail can be updated.");
				return false;
			}
		}
		
		//3)
		if(toUpdate.isFirstDigitEven(toUpdate.getID()) == true) {
			if(!prevID.equals(newID)) {
				System.out.println("Your ID cannot be modified.");
				return false;
			}
		}
		
		
//		All conditions to add person must be considered.
//		1) under 18s cannot change their address
//		2) If birthday is to be changed, no other detail can be changed.[DONE]
//		3) if personID[0] is even, ID cannot be changed [DONE]
//		After meeting all conditions the details should be updated in the .txt file and funtion returns true
//		otherwise, don't update and return false
		
		//Perform all checks to add person, and add the new person to a .txt file
		return toUpdate.addPerson();
	}
	
	public String addDemeritPoints() {
		//create a calendar object 
		Calendar cal = Calendar.getInstance();
		Date now = new Date();
		cal.setTime(now);

		//simple date format to write dates in a specified format
		//SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

		String dateOne = "12-05-202";
		System.out.println(this.isWithinTwoYears(dateOne));

//		TODO: function adds demerit points for a given person in a .txt file
//		1) format of the date of offense should be DD-MM-YYYY
//		2) demerit points should be int between 1-6
//		3) If age < 21, isSuspended set to true if demerit points total within 2 years > 6
//		if age >=21, isSuspended sets to true if within 2 years deperit points > 12
//		Demerit points should then be inserted in a .txt if conditions met. returns "success", otherwise, returns "Failed"
		return "Success";
	}


	public static void main(String[] args) {
		//good input should return true
		Person example = new Person("26d#w@fvRQ", "john", "23|malvern road|malvern|Victoria|Australia", "23-05-1990");
		//incorrect input: name is empty
		Person badExample1 = new Person("26d###svRQ", "", "23|malvern road|malvern|Victoria|Australia", "23-05-1990");
		//incorrect input: street number is not digit
		Person badExample2 = new Person("20d###svRQ", "john", "twentythree|malvern road|malvern|Victoria|Australia", "23-05-1990");
		//incorrect input: no special char
		Person badExample3 = new Person("26sdfsvRQ", "john", "23|malvern road|malvern|Victoria|Australia", "23-05-1990");
		//incorrect input: incorrect id length
		Person badExample4 = new Person("26d###vrQ", "john", "23|malvern road|malvern|Victoria|Australia", "23-05-1990");
		//incorrect input: incorrect date format
		Person badExample5 = new Person("26d###svrQ", "john", "23|malvern road|malvern|Victoria|Australia", "23/05/1990");

		// System.out.println(example.addPerson());
		// System.out.println(badExample1.addPerson());
		// System.out.println(badExample2.addPerson());
		// System.out.println(badExample3.addPerson());
		// System.out.println(badExample4.addPerson());
		// System.out.println(badExample5.addPerson());
		System.out.println(example.addDemeritPoints());

	}
}
