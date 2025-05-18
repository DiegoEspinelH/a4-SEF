import java.util.HashMap;
import java.util.Map;
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
		this.demeritPoints = new HashMap<>();
		this.isSuspended = false;
	}
	public void printPersonalDetails(){
		System.out.println(this.personID);
		System.out.println(this.firstname);
		System.out.println(this.address);
		System.out.println(this.birthdate);
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
	public void setDemeritPoints(String dateString, int demeritPoints){
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		try {
			Date date = sdf.parse(dateString);
			this.demeritPoints.put(date, demeritPoints);
		} catch (Exception e) {
			System.out.println("Invalid date format in set demerit points.");
		}
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
		//check all assignment conditions
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
				//parse error
				return false;
        	}
		}else{
			//invalid date format
			return false;
		}

	}
	
	public boolean isUnderAge(String date) {
		String[] parts = date.split("\\-");
		String year = parts[2];
		int yearNum = Integer.parseInt(year);
		int res = 2025 - yearNum;
		if(res < 18) {
			System.out.println("under 18: " + res);
			return true;
		}
		else {
			System.out.println("over 18");
			return false;
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
	public boolean canChangeAddress(){
		if(this.isUnderAge(this.getBirthdate())) {
			return false;
		}
		else{
			return true;
		}
	}
	public boolean wasBirthdateChanged(String newBirthdate){
		if(this.getBirthdate().equals(newBirthdate)) {
			return false;
		}else{
			return true;
		}
	}
	public boolean canChangeID(){
		if(this.isFirstDigitEven(this.getID()) == true) {
			return false;
		}
		else{
			return true;
		}
	}
	
	public boolean updatePersonalDetails(String newID, String newName, String newAddress, String newBirthdate ) {
//		TODO: Update all details of a person in a .txt file
		
		//TODO: get person details from .txt file
//		All conditions to add person must be considered.
//		1) under 18s cannot change their address
//		2) If birthday is to be changed, no other detail can be changed.[DONE]
//		3) if personID[0] is even, ID cannot be changed [DONE]
//		After meeting all conditions the details should be updated in the .txt file and funtion returns true
//		otherwise, don't update and return false
		
		if(this.wasBirthdateChanged(newBirthdate)){
			if(!this.getID().equals(newID) || !this.getName().equals(newName) || !this.getAddress().equals(newAddress)) {
				System.out.println("Your birthdate is different, no other detail can be updated.");
				return false;
			}else{
				this.setBirthdate(newBirthdate);
			}
		}else{
			if(!this.getID().equals(newID) && !this.canChangeID() ){
				System.out.println("Your ID cannot be modified.");
				return false;
			}else{
				this.setID(newID);
			}
		}
		if(!this.getAddress().equals(newAddress) && !this.canChangeAddress()) {
			System.out.println("Under 18s cannot change their addresses");
			return false;
		}
		else{
			this.setName(newName);
			this.setAddress(newAddress);
		}
		this.setName(newName);
		
		//Perform all checks to add person, and add the new person to a .txt file
		return this.addPerson();
	}
	
	public String addDemeritPoints(String offenceDate, int demeritPoints) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		
		this.setDemeritPoints(offenceDate, demeritPoints);

		if(this.isWithinTwoYears(offenceDate)){
			//System.out.println("is within two years");
		}
		// for (Map.Entry<Date, Integer> entry : this.demeritPoints.entrySet()) {
        //         String formattedDate = sdf.format(entry.getKey());
        //         int points = entry.getValue();
        //         System.out.println(formattedDate + ": " + points);
        //     }


//		TODO: function adds demerit points for a given person in a .txt file
//		1) format of the date of offense should be DD-MM-YYYY
//		2) demerit points should be int between 1-6
//		3) If age < 21, isSuspended set to true if demerit points total within 2 years > 6
//		if age >=21, isSuspended sets to true if within 2 years deperit points > 12
//		Demerit points should then be inserted in a .txt if conditions met. returns "success", otherwise, returns "Failed"
		return "";
	}


	public static void main(String[] args) {
		//good input should return true
		Person example = new Person("26d#w@fvRQ", "john", "23|malvern road|malvern|Victoria|Australia", "23-05-2009");
		
		example.printPersonalDetails();
		System.out.println("");
		example.updatePersonalDetails( "26d#w@fvRQ", "john", "23|malvern road|malvern|Victoria|Australia", "23-06-2009");
		example.printPersonalDetails();
	}
}
