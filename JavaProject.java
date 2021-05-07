import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class JavaProject {
    static String dir;
    File fol_name;

    public JavaProject() {
        dir = System.getProperty("user.dir");
        fol_name = new File(dir+"/files");
        if (!fol_name.exists())
            fol_name.mkdirs();
        System.out.println("dir : "+ fol_name.getAbsolutePath());
    }

    private static final String WELCOME =
            "\n          SimpliLearn Phase1 Project                     "+
                    "\n           Jeevanantham M                \n";

    private static final String MAIN_MENU =
            "\nMAIN MENU - Select any of the following: \n"+
                    "1 : List files in dir\n"+
                    "2 : Add, Delete or Search\n"+
                    "3 : Exit Program";

    private static final String SECONDARY_MENU =
            "   \nSelect any of the following: \n"+
                    "   a : Add a file\n"+
                    "   b : Delete a file\n"+
                    "   c : Search a file\n"+
                    "   d : GoBack";

    void showPrimaryMenu() {
        System.out.println(MAIN_MENU);
        try{
            Scanner sc= new Scanner(System.in);
            int option = sc.nextInt();
            switch (option){
                case 1 : {
                    showFiles();
                    showPrimaryMenu();
                }
                case 2 : {
                    showSecondaryMenu();
                }
                case 3 : {
                    System.out.println("THANK YOU");
                    System.exit(0);
                }
                default: showPrimaryMenu();
            }
        }
        catch (Exception e){
            System.out.println("Please enter 1, 2 or 3");
            showPrimaryMenu();
        }
    }

    void showSecondaryMenu() {
        System.out.println(SECONDARY_MENU);
        try{
            Scanner sc= new Scanner(System.in);
            char[] input = sc.nextLine().toLowerCase().trim().toCharArray();
            char option = input[0];

            switch (option){
                case 'a' : {
                    System.out.print("Please Enter a File Name To ADD : ");
                    String filename = sc.next().trim().toLowerCase();
                    addFile(filename);
                    break;
                }
                case 'b' : {
                    System.out.print("Please Enter a File Name To DELETE : ");
                    String filename = sc.next().trim();
                    deleteFile(filename);
                    break;
                }
                case 'c' : {
                    System.out.print("Please Enter a File Name To SEARCH : ");
                    String filename = sc.next().trim();
                    searchFile(filename);
                    break;
                }
                case 'd' : {
                    System.out.println("Going Back to MAIN menu");
                    showPrimaryMenu();
                    break;
                }
                default : System.out.println("Please enter a, b, c or d");
            }
            showSecondaryMenu();
        }
        catch (Exception e){
            System.out.println("Please enter a, b, c or d");
            showSecondaryMenu();
        }
    }

    void showFiles() {
        if (fol_name.list().length==0)
            System.out.println("The folder is empty");
        else {
            String[] list = fol_name.list();
            System.out.println("The files in "+ fol_name +" are :");
            Arrays.sort(list);
            for (String str:list) {
                System.out.println(str);
            }
        }
    }

    void addFile(String filename) throws IOException {
        File filepath = new File(fol_name +"/"+filename);
        String[] list = fol_name.list();
        for (String file: list) {
            if (filename.equalsIgnoreCase(file)) {
                System.out.println("File " + filename + " already exists at " + fol_name);
                return;
            }
        }
        filepath.createNewFile();
        System.out.println("File "+filename+" added to "+ fol_name);
    }

    void deleteFile(String filename) {
        File filepath = new File(fol_name +"/"+filename);
        String[] list = fol_name.list();
        for (String file: list) {
            if (filename.equals(file) && filepath.delete()) {
                System.out.println("File " + filename + " deleted from " + fol_name);
                return;
            }
        }
        System.out.println("Delete Operation failed. FILE NOT FOUND");
    }

    void searchFile(String filename) {
        String[] list = fol_name.list();
        for (String file: list) {
            if (filename.equals(file)) {
                System.out.println("FOUND : File " + filename + " exists at " + fol_name);
                return;
            }
        }
        System.out.println("File NOT found (FNF)");
    }

    public static void main(String[] args) {
        System.out.println(WELCOME);
        JavaProject menu = new JavaProject();
        menu.showPrimaryMenu();
    }
}
