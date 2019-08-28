import java.io.FileNotFoundException;
import java.util.Scanner;

public class Generator {
    public static void main(String[] args){
        WebSite home = new WebSite();
        String choice = "S";
        WebPage[] cursorA;
        WebPage cursor = null;
        WebPage parentCursor = null;

        int removeIndex = 0;

        Scanner info = new Scanner(System.in);
        Scanner in = new Scanner(System.in);

        while (!choice.equals("Q")) {
            try {

                System.out.println("\nI. - Import .txt file \nA. - Add Department \nR. - Remove Department " +
                        "\nC. - Current Department \nG. - Go to Sub-department \nH. - Head Department \nP. - Print Format " +
                        "\nE. - Empty Tree \nQ. - Quit"   );
                System.out.print("Please select an option: ");

                choice = info.nextLine();


                switch (choice) {

                    case "Q":
                        System.out.println("Sorry to see you go. Have a good day! **Dabs**");
                        break;

                    case "E":
                        home.getHomepage().setName(null);
                        home.getHomepage().setCounter(0);
                        cursor = null;
                        System.out.println("The tree has been emptied");
                        break;

                    case "P":
                        home.printFormat();
                        break;

                    case "H":
                        if(cursor != null) {
                            cursor = parentCursor;
                            System.out.println("You are currently in " + cursor.getName());

                            if(cursor == home.getHomepage()){
                                cursor = null;
                            }

                            for (int i = 0; i < home.getManyItems(); i++) {                    //Sets the cursor equal
                                cursorA = home.getDepart();                                    //to it's parent, only if the
                                if (parentCursor.getName() == cursorA[i].getName()) {          //is a sub-department
                                    parentCursor = home.getHomepage();
                                    break;
                                }
                            }
                        }

                        else {
                            System.out.println("There are no more head department");
                        }

                        break;

                    case "G":
                        if(cursor == null){                                                       //If currently at root.
                            parentCursor = home.getHomepage();
                            cursorA = home.getDepart();
                            if(cursorA[0] == null){
                                System.out.println("The are no more sub department sorry :(");
                                break;
                            }
                            System.out.println("Which department would you like to go to?:");
                            for(int i = 0; i < home.getManyItems(); i++){                         //Prints out all the
                                System.out.println((i + 1) + "-"+cursorA[i].getName());           //links of the cursor.
                            }
                            System.out.print("Enter your selection: ");
                            int index = in.nextInt();
                            cursor = cursorA[index-1];                                             //Moves the cursor to a link.
                            removeIndex = index - 1;
                        }

                        else{

                            cursorA = cursor.getLinks();
                            if(cursorA[0] == null){
                                System.out.println("The are no more sub department sorry :(");
                                break;
                            }
                            parentCursor = cursor;

                            System.out.println("Which department would you like to go to?:");
                            for(int i = 0; i < cursor.getCounter(); i++){                          //Prints out all the
                                System.out.println((i + 1) + "-"+cursorA[i].getName());            //links of the cursor.
                            }
                            System.out.print("Enter your selection: ");
                            int index = in.nextInt();
                            cursor = cursorA[index-1];                                            //moves cursor to a link.

                        }

                        System.out.println("You are currently at " + cursor.getName());
                        break;

                    case "C":
                        if(cursor == null){                                                       //Only if cursor at root.
                            System.out.println("You are currently in " + home.getHomepage().getName() + "." );
                        }
                        else {
                            System.out.println("You are currently in " + cursor.getName() + ".");
                        }
                        break;

                    case "R":

                        System.out.println(cursor.getName() + " has been removed.");
                        if(cursor == null){                                                       //Only execute if cursor at root.
                            home.getHomepage().setName(null);                                     //Empties the tree.
                        }

                        else if(parentCursor == home.getHomepage()){            //Removes department for root
                            String name = cursor.getName();
                            home.removeDepartment(name, removeIndex);

                            if(cursor != null) {
                                cursor = parentCursor;
                                System.out.println("You are currently in " + cursor.getName());

                                if(cursor == home.getHomepage()){
                                    cursor = null;
                                }

                                for (int i = 0; i < home.getManyItems(); i++) {                    //Sets the cursor equal
                                    cursorA = home.getDepart();                                    //to it's parent, only if the
                                    if (parentCursor.getName() == cursorA[i].getName()) {          //is a sub-department
                                        parentCursor = home.getHomepage();
                                        break;
                                    }
                                }
                            }

                        }
                        else{
                            parentCursor.removeLink(cursor.getName());        //removes department from non-root

                            if(cursor != null) {
                                cursor = parentCursor;
                                System.out.println("You are currently in " + cursor.getName());

                                if(cursor == home.getHomepage()){
                                    cursor = null;
                                }

                                for (int i = 0; i < home.getManyItems(); i++) {                    //Sets the cursor equal
                                    cursorA = home.getDepart();                                    //to it's parent, only if the
                                    if (parentCursor.getName() == cursorA[i].getName()) {          //is a sub-department
                                        parentCursor = home.getHomepage();
                                        break;
                                    }
                                }
                            }


                        }


                        break;
                    case "A":
                        if(home.getHomepage().getName() == null){
                            System.out.println("Please enter the name of the website: ");
                            String url = in.next();
                            home = new WebSite();
                            home.getHomepage().setName(url);


                        }
                        else{
                            home.addDepartment(cursor);

                            if(cursor == null){
                                WebPage[] linkC = home.getDepart();
                                cursor = linkC[home.getManyItems() - 1];
                            }
                            else {
                                int i = 0;
                                WebPage[] linkC = cursor.getLinks();
                                parentCursor = cursor;
                                while (linkC[i] != null){
                                    cursor = linkC[i];
                                    i++;
                                }
                            }

                        }

                        System.out.println("The department has been added.");

                        break;
                    case "I":
                        System.out.print("Please enter the name of the .txt file: ");
                        String file = in.next();


                        home = home.buildTree(file);

                        System.out.println("\nThe tree is loading...");
                        System.out.println("\n\n\nThe tree has been loaded.\n");
                        break;
                }
            }catch (FileNotFoundException e){
                System.out.println("File not found! Please try again.");
            }
            catch (Exception e){
                System.out.println("Please check your input!");
            }
        }

    }
}