import java.io.IOException;
import java.net.URI;
import java.util.*;

class Handler implements URLHandler {

    ArrayList<String> lst = new ArrayList<>();
    int count = 0;

    public String handleRequest(URI url) {
        if (url.getPath().equals("/")) {
            return "Hello";
        }
        else if (url.getPath().contains("/add")) {
            String[] newstr = url.getQuery().split("=");
            for (int i = 1; i<newstr.length; i+=1){
                lst.add(newstr[i]);
                count += 1;
            }
            return count + " string(s) added";
        }
        else {
            System.out.println("Path: " + url.getPath());
            if (url.getPath().contains("/search")) {
                String[] parameters = url.getQuery().split("=");
                String toReturn = "";
                if (parameters[0].equals("s")) {
                    for (String s : lst){
                        if (s.contains(parameters[1])){toReturn += " " + s;}
                        else {continue;}
                    }
                    return toReturn;
                }
            }
            return "404 Not Found";
        }
    }
}

class SearchEngine {
    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new Handler());
    }
}
