import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
	public void writeDetailsToFile(){
		try{
			//created a buffered writer so it makes a "paragraph" instead of pushing line by line
			BufferedWriter writer = new BufferedWriter(new FileWriter("person.txt"));
			String id = this.getID();
			String name = this.getName();
			String address = this.getAddress();
			String birthdate = this.getBirthdate();
			writer.write(id);
			writer.newLine();
			writer.write(name);
			writer.newLine();
			writer.write(address);
			writer.newLine();
			writer.write(birthdate);
			writer.newLine();
			writer.write("Licence suspended? " + this.getLicenceStatus());
			writer.newLine();
			writer.close();
			System.out.println("Person written to file success!");
		}catch (Exception e){
			System.out.println("Error writing file!");
		}
	}
	public void writeDemeritPointsToFile(){

		try (BufferedWriter writer = new BufferedWriter(new FileWriter("person.txt"))) {
			//stantiated the date format to write in the .txt file
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
			//for every entry in the person's demerit points hashmap
            for (Map.Entry<Date, Integer> entry : this.getDemeritPoints().entrySet()) {
                //write each entry in the format Date: Integer
                writer.write(sdf.format(entry.getKey()) + ": " + entry.getValue());
                //move to the next line
				writer.newLine();  
            }
            System.out.println("Demerit points successfully added.");
        } catch (IOException e) {
			//error
        }
	}
	//setter methods:
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
	public void setSuspended(boolean value){
		this.isSuspended = value;
	}
	
	public void setDemeritPoints(String dateString, int demeritPoints){
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		try {
			//parse the date string to a Date object
			Date date = sdf.parse(dateString);
			//add to the person's demerit ponts in format <Date, Integer>
			this.demeritPoints.put(date, demeritPoints);
		} catch (Exception e) {
			System.out.println("Invalid date format in set demerit points.");
		}
	}
	
	//getter methods:
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
	public boolean getLicenceStatus(){
		return this.isSuspended;
	}
	public HashMap<Date, Integer> getDemeritPoints(){
		return this.demeritPoints;
	}
	

	public boolean isDigit2and9(char c) {
		//check if passed character is a digit between 2 and 9
		return c >= '2' && c <= '9';
	}

	public boolean isSpecialChar3to8(String id){
		//make a string containing special characters
		String specialChars = "!@#$%^&*():;\\{}[]\"'.,`~-_+=<>?/";
		int counter = 0;
		for(int i = 2; i <=7; i++){
			//check if a character matches with special characters string at given indexes
			char c = id.charAt(i);
			//if match, add to counter
			if(specialChars.indexOf(c) != -1){
				counter++;
			}
		}
		//check if there is 2 or more special chars in ID
		if (counter >= 2) {
			return true;	
		}
		else{
			System.out.println("less than two special chars from 3 - 8");
			return false;
		}
		
	}

	public boolean isUppercaseLastTwo(String id){
		//check if last two characters are uppercase
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
			return false;
		}
		if(!state.equals("Victoria")) {
			//state must be Victoria
			return false;
		}
		if(streetName.isEmpty() && city.isEmpty() && country.isEmpty()) {
			//Every other part needs to have a value
			return false;
		}
		//if we reach this line, all checks have passed, so return true
		return true;
		
	}
	
	public boolean isValidDateFormat(String date) {
		
		if(date.isEmpty()) {
			//check if date is empty
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
			return false;
		}
		
		int dayNum = Integer.parseInt(day);
		int monthNum = Integer.parseInt(month);
		int yearNum = Integer.parseInt(year);
		if(monthNum < 1 || monthNum > 12) {
			//invalid month
			return false;
		}
		
		if(monthNum == 2 && (dayNum < 1 || dayNum > 28)){
			//check for february, ignore leap years
			return false;
		}
		if((monthNum == 1 || monthNum == 3 || monthNum == 5 || monthNum == 7 || monthNum == 8 || monthNum == 10 || monthNum == 12) && (dayNum < 1 || dayNum > 31)) {
			//check for months with 31 days
			return false;
		}
		if((monthNum == 4 || monthNum == 6 || monthNum == 9 || monthNum == 11) && (dayNum < 1 || dayNum > 30)) {
			//check for months with 30 days
			return false;
		}
		if(yearNum > 2025 || yearNum < 1900) {
			//check for values greater than current year
			return false;
		}
		
		//if this line is reached, all checks passed
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
				//parsed date turns a string to a Date object if format is as specified with 'sdf'
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
			//if person is under 18
			return true;
		}
		else {
			//if person is over 18
			return false;
		}
		
	}
	public boolean isUnder21(String date) {
		String[] parts = date.split("\\-");
		String year = parts[2];
		int yearNum = Integer.parseInt(year);
		int res = 2025 - yearNum;
		if(res < 21) {
			//if person is under 21
			return true;
		}
		else {
			//if person is over 21
			return false;
		}
		
	}
	public boolean isFirstDigitEven(String id) {
		//check if the remainder of id / 2 is 0, meaning it is even
		if((id.charAt(0)%2) == 0 ) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean addPerson() {
		boolean canAddPerson = false;
		if(this.personID.length() == 10) {
			//if ID length is strictly 10, compute
			if(this.getName().isEmpty()){
				//check if name is empty
				return canAddPerson;
			}
			if(isValidID(this.getID()) && isValidAddressFormat(this.getAddress()) && isValidDateFormat(this.getBirthdate()) ) {
				canAddPerson = true;
				//write person's details to the file
				this.writeDetailsToFile();
				return canAddPerson;
			}
		}
		//if this line is reached, canAddPerson is false and person is not added
		return canAddPerson;
	}	
	public boolean canChangeAddress(){
		if(this.isUnderAge(this.getBirthdate())) {
			//if under 18, cannot change address
			return false;
		}
		else{
			return true;
		}
	}
	public boolean wasBirthdateChanged(String newBirthdate){
		//check if current birthdate is the same as passed
		if(this.getBirthdate().equals(newBirthdate)) {
			//if both are equal, means it was not changed, returns false
			return false;
		}else{
			//otherwise, it was changed and return true
			return true;
		}
	}

	public boolean canChangeID(){
		//check if first digit of person's ID is even
		if(this.isFirstDigitEven(this.getID()) == true) {
			//if even, ID cannot be changed
			return false;
		}
		else{
			return true;
		}
	}
	
	public boolean updatePersonalDetails(String newID, String newName, String newAddress, String newBirthdate ) {
		//if birthday was changed, compute
		if(this.wasBirthdateChanged(newBirthdate)){
			//check if any other detail was changed
			if(!this.getID().equals(newID) || !this.getName().equals(newName) || !this.getAddress().equals(newAddress)) {
				//if anything besides the birthdate was changed, change nothing and exit
				return false;
			}else{
				//otherwise, only birthdate was changed and update birthdate
				this.setBirthdate(newBirthdate);
			}
		}else{ //if birthdate was not changed
			//check if ID was modified
			if(!this.getID().equals(newID) && !this.canChangeID() ){
				//if ID was modified and cannot be changed, change nothing and exit
				return false;
			}else{
				//otherwise, update ID
				this.setID(newID);
			}
			//check if address was changed, and if person is allowed to change address
			if(!this.getAddress().equals(newAddress) && !this.canChangeAddress()) {
				//if it was changed, and person is underage, change nothing and exit
				return false;
			}
			else{//otherwise, after all checks, can change address and update name if required
				//if name is the same, still update
				this.setName(newName);
				this.setAddress(newAddress);
			}
		}
		//Perform all checks to add person, and add the updated person to the .txt file
		return this.addPerson();
	}
	public boolean isValidDemerit(int demerit){
		//demerit points should only be integers, within 1 and 6 inclusive
		return (demerit > 0 && demerit <= 6);
	}
	
	public String addDemeritPoints(String offenceDate, int demeritPoints) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		//check if demerit points are valid
		if(!isValidDemerit(demeritPoints)){
			//if outside range, exit and return Failed
			return "Failed";
		}
		if(!isValidDateFormat(offenceDate)){
			//check if date is written correctly
			return "Failed";
		}else{
			//add demerit points to the persons demerit point map
			this.setDemeritPoints(offenceDate, demeritPoints);
			
			//create a new hashmap to check if licence is going to be suspended
			HashMap<Date, Integer> withinTwo = new HashMap<>();
			
			//for every entry in the person's demerit points, check if it is within two years of current date
			for (Map.Entry<Date, Integer> entry : this.demeritPoints.entrySet()) {

				String entryDateStr = sdf.format(entry.getKey());
				//if within two years, add to checker hashmap
				if (this.isWithinTwoYears(entryDateStr)) {
					withinTwo.put(entry.getKey(), entry.getValue());
				}
			}
			
			//summation of points within two years, initialized at 0
			int pointsWithinTwoYears = 0;
			for (int points : withinTwo.values()) {
				//add all points
				pointsWithinTwoYears += points;
			}
			if(this.isUnder21(this.getBirthdate())){
				//if person is under 21, check for a maximum of 6 demerit points
				if(pointsWithinTwoYears > 6){
					//if over, suspend
					this.setSuspended(true);
				}
			}else{ //check for people over 21 years of age
				//over 21s have a limit of 21 demerit points
				if(pointsWithinTwoYears > 12){
					//if over, suspend
					this.setSuspended(true);
				}
			}
			//if this line is reached, all checks were done
			//write to the txt file the valid points and date of occurrences
			this.writeDemeritPointsToFile();
			return "Success";
		}
	}

	
	public static void main(String[] args) {
		//Code was written in main to make developing checks
		//As checks were implemented, the code was deleted
		//TODO: write tests to check conditions
		
		Person correctInput = new Person("73@#!#abPA", "james", "24|melbourne street|melbourne|Victoria|Australia", "25-05-2001");
		correctInput.writeDetailsToFile();
		
	}
}
