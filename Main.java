public class Main {
    public static void main(String[] args) {
        String email = "your.email@goes.here!";
        int[] hash = {1,66,13};
        String result = "";
        int start = 0;
        for(int i = 0; i < email.length(); i++) {
            int n = start++ % hash.length;
            char ch = email.charAt(i);
            int h = hash[n];
            result += (char)(ch - h);
        }
        System.out.println(result);
        String hemail = result;
        start = 0;
        result = "";
        for(int i = 0; i < hemail.length(); i++) {
            int n = start++ % hash.length;
            char ch = hemail.charAt(i);
            int h = hash[n];
            result += (char)(ch + h);
        }
        System.out.println("\"" + result + "\"");
    }
}
