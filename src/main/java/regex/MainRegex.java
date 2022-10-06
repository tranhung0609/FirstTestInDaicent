package regex;

public class MainRegex {
    private static Validate validate;
    public static final String[] validAccount = new String[] { "hung123", "hungtran12", "______", "123456","hungtran1@" };

    public static final String[] password = new String[] { ".@", "12345", "abcde","Tranvanhung1@","TranHung123#","TRANVANHUNG1@" };

    public static final String[] phone = new String[] { ".@", "12345", "0674578333","0967984321","096-777-4373","0965596698","+84965596698" };
    public static final String[] email = new String[] { ".@", "12312","hasdhhas@","shdf@gmail.com","hdsfhd@yahoo.com","@gmail.com","yahoo.com" };

    public static void main(String[] args) {

        validate = new Validate();


        for (String account : validAccount) {
            boolean isvalid = validate.validateAccount(account);
            System.out.println("Account is " + account +" is valid: "+ isvalid);
        }


        System.out.println("----------------------------------------------------------");
        for (String pass : password) {
            boolean isvalid = validate.validatePassword(pass);
            System.out.println("Pass is " + pass +" is valid: "+ isvalid);
        }
        System.out.println("----------------------------------------------------------");

        for (String phone : phone) {
            boolean isvalid = validate.validatePhone(phone);
            System.out.println("Phone is " + phone +" is valid: "+ isvalid);
        }

        System.out.println("----------------------------------------------------------");
        for (String email : email) {
            boolean isvalid = validate.validateEmail(email);
            System.out.println("Email is " + email +" is valid: "+ isvalid);
        }
    }
}
