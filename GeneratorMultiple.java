import java.io.FileNotFoundException;
import java.util.Scanner;

public class GeneratorMultiple {
    public static void main(String[] args){
        WebSite[] home;
        WebPage copy = null;
        String choice = "S";
        WebPage[] cursorA;
        WebPage[] cursor;
        WebPage[] parentCursor;
        int TreeIndex = 0;
        int marker = -1;
        int removeIndex = 0;

        Scanner info = new Scanner(System.in);
        Scanner in = new Scanner(System.in);

        System.out.print("How many trees would you like to manage: ");
        int numOfTrees = in.nextInt();
        home = new  WebSite[numOfTrees];
        cursor = new WebPage[numOfTrees];
        parentCursor = new WebPage[numOfTrees];

        for(int i = 0; i < numOfTrees; i++) {
            cursor[i] = new WebPage();
            cursor[i] = null;
            parentCursor[i] = new WebPage();
            parentCursor[i] = null;
            home[i] = new WebSite();


        }

        while (!choice.equals("Q")) {
            try {

                if(marker < 0) {
                    System.out.println("Which tree would you like to manage: ");
                    for (int i = 0; i < numOfTrees; i++) {
                        System.out.println("(" + (i + 1) + ")" + home[i].getHomepage().getName());
                    }

                    marker = 1;
                    System.out.print("Please enter your choice: ");
                    TreeIndex = in.nextInt() - 1;

                }




                System.out.println("\nS. - Switch Trees \nI. - Import .txt file \nA. - Add Department \nR. - Remove Department " +
                        "\nC. - Current Department \nG. - Go to Sub-department \nH. - Head Department \nP. - Print Format " +
                        "\nE. - Empty Tree \nCO. - Copy Department \nPA. - PASTE \nQ. - Quit"   );
                System.out.print("Please select an option: ");

                choice = info.nextLine();


                switch (choice) {
                    case "PA":
                        System.out.println(copy.getName() + " Has been pasted");
                        if(home[TreeIndex].getHomepage().getName() == null){
                            home[TreeIndex] = new WebSite();
                            home[TreeIndex].setHomepage(copy);
                            home[TreeIndex].setManyItems(copy.getCounter());
                        }
                        else{
                            home[TreeIndex].pasteDepartment(cursor[TreeIndex], copy);
                        }

                        break;

                    case "CO":
                        copy = new WebPage();
                        copy.setName(cursor[TreeIndex].getName());
                        copy.setLinks(cursor[TreeIndex].modifiedGetLinks());
                        copy.setCounter(cursor[TreeIndex].getCounter());
                        System.out.println(cursor[TreeIndex].getName() + " Has been copied");
                        break;

                    case "S":
                        System.out.println("Which tree would you like to manage: ");
                        for (int i = 0; i < numOfTrees; i++) {
                            if(home[i].getHomepage().getName() != null){
                            System.out.println("(" + (i + 1) + ")" + home[i].getHomepage().getName());
                            }
                            else{
                                System.out.println("(" + (i + 1) + ") has been emptied!");
                            }

                        }
                        System.out.print("Please enter your choice: ");
                        TreeIndex = in.nextInt() - 1;
                        cursor[TreeIndex] = null;
                        break;

                    case "Q":
                        System.out.println("Sorry to see you go. Have a good day! **Dabs**");
                        break;

                    case "E":
                        home[TreeIndex].getHomepage().setName(null);
                        home[TreeIndex].getHomepage().setCounter(0);
                        cursor[TreeIndex] = null;
                        System.out.println("The tree has been emptied");
                        break;

                    case "P":
                        home[TreeIndex].printFormat();
                        break;

                    case "H":
                        if(cursor[TreeIndex] != null) {
                            cursor[TreeIndex] = parentCursor[TreeIndex];
                            System.out.println("You are currently in " + cursor[TreeIndex].getName());

                            if(cursor[TreeIndex] == home[TreeIndex].getHomepage()){
                                cursor[TreeIndex] = null;
                            }

                            for (int i = 0; i < home[TreeIndex].getManyItems(); i++) {                    //Sets the cursor equal
                                cursorA = home[TreeIndex].getDepart();                                    //to it's parent, only if the
                                if (parentCursor[TreeIndex].getName() == cursorA[i].getName()) {          //is a sub-department
                                    parentCursor[TreeIndex] = home[TreeIndex].getHomepage();
                                    break;
                                }
                            }
                        }

                        else {
                            System.out.println("There are no more head department");
                        }

                        break;

                    case "G":
                        if(cursor[TreeIndex] == null){                                                       //If currently at root.
                            parentCursor[TreeIndex] = home[TreeIndex].getHomepage();
                            cursorA = home[TreeIndex].getDepart();
                            if(cursorA[0] == null){
                                System.out.println("The are no more sub department sorry :(");
                                break;
                            }
                            System.out.println("Which department would you like to go to?:");
                            for(int i = 0; i < home[TreeIndex].getManyItems(); i++){                         //Prints out all the
                                System.out.println((i + 1) + "-"+cursorA[i].getName());           //links of the cursor.
                            }
                            System.out.print("Enter your selection: ");
                            int index = in.nextInt();
                            cursor[TreeIndex] = cursorA[index-1];                                             //Moves the cursor to a link.
                            removeIndex = index - 1;
                        }

                        else{

                            cursorA = cursor[TreeIndex].getLinks();
                            if(cursorA[0] == null){
                                System.out.println("The are no more sub department sorry :(");
                                break;
                            }
                            parentCursor[TreeIndex] = cursor[TreeIndex];

                            System.out.println("Which department would you like to go to?:");
                            for(int i = 0; i < cursor[TreeIndex].getCounter(); i++){                          //Prints out all the
                                System.out.println((i + 1) + "-"+cursorA[i].getName());            //links of the cursor.
                            }
                            System.out.print("Enter your selection: ");
                            int index = in.nextInt();
                            cursor[TreeIndex] = cursorA[index-1];                                            //moves cursor to a link.

                        }

                        System.out.println("You are currently at " + cursor[TreeIndex].getName());
                        break;

                    case "C":
                        if(cursor[TreeIndex] == null){                                                       //Only if cursor at root.
                            System.out.println("You are currently in " + home[TreeIndex].getHomepage().getName() + "." );
                        }
                        else {
                            System.out.println("You are currently in " + cursor[TreeIndex].getName() + ".");
                        }
                        break;

                    case "R":

                        System.out.println(cursor[TreeIndex].getName() + " has been removed.");
                        if(cursor[TreeIndex] == null){                                                       //Only execute if cursor at root.
                            home[TreeIndex].getHomepage().setName(null);                                     //Empties the tree.
                        }

                        else if(parentCursor[TreeIndex] == home[TreeIndex].getHomepage()){            //Removes department for root
                            String name = cursor[TreeIndex].getName();
                            home[TreeIndex].removeDepartment(name, removeIndex);

                            if(cursor[TreeIndex] != null) {
                                cursor[TreeIndex] = parentCursor[TreeIndex];
                                System.out.println("You are currently in " + cursor[TreeIndex].getName());

                                if(cursor[TreeIndex] == home[TreeIndex].getHomepage()){
                                    cursor[TreeIndex] = null;
                                }

                                for (int i = 0; i < home[TreeIndex].getManyItems(); i++) {                    //Sets the cursor equal
                                    cursorA = home[TreeIndex].getDepart();                                    //to it's parent, only if the
                                    if (parentCursor[TreeIndex].getName() == cursorA[i].getName()) {          //is a sub-department
                                        parentCursor[TreeIndex] = home[TreeIndex].getHomepage();
                                        break;
                                    }
                                }
                            }

                        }
                        else{
                            parentCursor[TreeIndex].removeLink(cursor[TreeIndex].getName());        //removes department from non-root

                            if(cursor[TreeIndex] != null) {
                                cursor[TreeIndex] = parentCursor[TreeIndex];
                                System.out.println("You are currently in " + cursor[TreeIndex].getName());

                                if(cursor[TreeIndex] == home[TreeIndex].getHomepage()){
                                    cursor[TreeIndex] = null;
                                }

                                for (int i = 0; i < home[TreeIndex].getManyItems(); i++) {                    //Sets the cursor equal
                                    cursorA = home[TreeIndex].getDepart();                                    //to it's parent, only if the
                                    if (parentCursor[TreeIndex].getName() == cursorA[i].getName()) {          //is a sub-department
                                        parentCursor[TreeIndex] = home[TreeIndex].getHomepage();
                                        break;
                                    }
                                }
                            }


                        }


                        break;
                    case "A":
                        if(home[TreeIndex].getHomepage().getName() == null){
                            System.out.println("Please enter the name of the website: ");
                            String url = in.next();
                            home[TreeIndex] = new WebSite();
                            home[TreeIndex].getHomepage().setName(url);
                        }
                        else{
                            home[TreeIndex].addDepartment(cursor[TreeIndex]);

                            if(cursor[TreeIndex] == null){
                                WebPage[] linkC = home[TreeIndex].getDepart();
                                cursor[TreeIndex] = linkC[home[TreeIndex].getManyItems() - 1];
                            }
                            else {
                                int i = 0;
                                WebPage[] linkC = cursor[TreeIndex].getLinks();
                                parentCursor[TreeIndex] = cursor[TreeIndex];
                                while (linkC[i] != null){
                                    cursor[TreeIndex] = linkC[i];
                                    i++;
                                }
                            }
                        }

                        System.out.println("The department has been added.");

                        break;
                    case "I":
                        System.out.print("Please enter the name of the .txt file: ");
                        String file = in.next();
                        

                        home[TreeIndex] = home[TreeIndex].buildTree(file);

                        System.out.println("\nThe tree is loading...");
                        System.out.println("\n\nThe tree has been loaded.\n");
                        break;
                }
            }catch (FileNotFoundException e){
                System.out.println("\n\nIncorrect File! Please try again.\n");
            }

            catch (Exception e){
                System.out.println("Please check your input!");
            }
        }

    }


}
