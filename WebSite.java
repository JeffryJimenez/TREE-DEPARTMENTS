import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class WebSite {
    private WebPage homepage;
    private int manyItems;
    private WebPage[] depart = new WebPage[10];

    /**
     * This constructor creates a new WebSite object.
     *
     * This Constructor specifically makes a WeSite object with the name of homePage set to null.
     */
    public WebSite(){
        homepage = new WebPage();
        manyItems = 0;
    }

    /**
     * This constructor creates a new WebSite object.
     *
     * This Constructor specifically makes a WeSite object with a specific name for the homepage.
     *
     * @param name The name of the homepage.
     */
    public WebSite(String name){
        homepage = new WebPage(name);
        manyItems = 0;
    }

    /**
     * Modifier that adds a link/Sub-department to homepage.
     *
     * This add department method is only meant to be use in conjunction with homepage.
     *
     * @param name The name of the new link/Sub-department.
     */
    private void addDepartment(String name){

        depart[manyItems] = new WebPage(name);                                              //Adds link to the next available position.
        manyItems++;

        homepage.setLinks(depart);
    }

    /**
     * Modifier that adds a link/Sub-department to homepage or its links/Sub-department.
     *
     * This add department method asks the user for the name of the new Sub-department and can
     * add a new link to a the homepage or A sub-department with the help of a cursor.
     *
     * @param cursor The Department where the new link/sub-department will be created.
     */
    public void addDepartment(WebPage cursor){
        Scanner in = new Scanner(System.in);
        System.out.print("Please enter the name of the new department: ");

        String name = in.nextLine();

        if(cursor == null){
            depart[manyItems] = new WebPage(name);                                          //Adds link to the next available position.
            manyItems++;

            homepage.setLinks(depart);
        }

        else {
            cursor.modifiedSetLink(name);
        }
    }

    /**
     * Creates a child a new child for any node.
     *
     * This method takes the information from a WebPage object and allocate a child.
     *
     * @param cursor Where will the new node be place.
     * @param copy The information of the copy.
     */
    public void pasteDepartment(WebPage cursor, WebPage copy){

        if(cursor == null){
            depart[manyItems] = copy;                                          //Adds link to the next available position.
            manyItems++;

            homepage.setLinks(depart);
        }

        else {
            cursor.modifiedSetLink(copy.getName(), copy.getLinks());
        }
    }

    /**
     * This method removes one of the links/Sub-departments of the homepage instance variable.
     *
     * This Method is only ment to be use in conjunction the the homepage instance variable.
     *
     * @param name The name of the Department that is being removed.
     */
    public void removeDepartment(String name, int index){
        WebPage[] cursor = homepage.getLinks();
        boolean done = false;


        for(int i = 0; i < manyItems; i++){
            if(cursor[i] != null && cursor[i].getName().equalsIgnoreCase(name) && i >= index && !done){
                for(int j = i; j < manyItems; j++){
                    cursor[j] = cursor[j+1];                                            //Fixes all links

                }

                done = true;
            }
        }

        manyItems--;


    }

    /**
     * This method prints out all nodes in a tree.
     *
     * If by any chance the tree is non-existence this method will notify the user.
     *
     */
    public void printFormat(){

        if(homepage.getName() == null) {
            System.out.println("There is currently no Website's format to print.");

        }
        else{

            System.out.println(homepage.getName());
            for(int i = 0; i < manyItems; i++){                                               //transverses a node and its children.
                System.out.println("    + " + depart[i].getName());
                WebPage[] cursor = depart[i].getLinks();

                int j = 0;
                while (cursor[j] != null){
                    System.out.println("        - " + cursor[j].getName());
                    j++;
                }

            }
        }
    }

    /**
     * This method creates a tree from a .txt file.
     *
     * This method uses keys of the .txt file to correctly build a tree, meaning
     * all sub-Department are place on the respective head-department
     *
     * @param fileName The name of the .txt file.
     * @return A tree of type Website.
     * @throws FileNotFoundException If the file is non-existence.
     */
   public WebSite buildTree(String fileName) throws FileNotFoundException {


        int index = 0;
        String[] info = new String[100];
        WebSite newWebsite;

       File inFile = new File(fileName);
       try (Scanner console = new Scanner(inFile)) {

           while (console.hasNextLine()){

               info[index] = console.nextLine();                                    //All the information in the .txt
               index++;                                                             //is stored.

           }

           String[] holder;
           String[] keyWords = new String[index];
           String[] departments = new String[index];
           int[] key = new int[index];



           for(int i = 0; i < index; i++){

               holder = info[i].split("\\s+",2);                        //Keys and Departments are split apart.
               keyWords[i] = holder[0];                                            //Any keywords in index i has a respective
               departments[i] = holder[1];                                         //department in departments at the same index i.

           }

           for(int i = 0; i < index; i++){
               key[i] = Integer.parseInt(keyWords[i]);                             //keyWords are turned into actual int keys.
           }


           newWebsite = new WebSite(departments[0]);                               //root of the tree is created.
           WebPage[] cursor = newWebsite.getDepart();                              //cursor contains the links iof the root.
           int j = 0;

           for(int i = 1; i < index; i++){

               if(key[i] < 10 && key[i] != 0){                                     //if key[i] is a Sub-department of root
                   newWebsite.addDepartment(departments[i]);
                   j = key[i];
               }
               else{                                                              //Key is not a Sub-department of root

                   cursor[j-1].modifiedSetLink(departments[i]);


               }
           }

       }

       return newWebsite;
   }

    /**
     *
     * @return The amount of links of homepage
     */
    public int getManyItems() {
        return manyItems;
    }

    /**
     *
     * @return The reference to the homepage WebPage object
     */
    public WebPage getHomepage() {
        return homepage;
    }


    /**
     *
     * @param homepage The new homePage object.
     */
    public void setHomepage(WebPage homepage) {
        this.homepage = new WebPage(homepage.getName());
        depart = homepage.modifiedGetLinks();
    }

    /**
     *
     * @param manyItems Sets many items.
     */
    public void setManyItems(int manyItems) {
        this.manyItems = manyItems;
    }

    /**
     *
     * @return All the links/Sub-departments of homepage
     */
    public WebPage[] getDepart() {
        return depart;
    }
}
