import java.awt.geom.Point2D;

public class WebPage {
    private String name;
    private WebPage[] links;
    private int counter;

    /**
     * Constructor for WebPage object.
     *
     * This constructor creates and WebPage object with the name of the WebPage set to null,
     * the constructor also initializes all the instance variables.
     */
    public WebPage(){
        this. name = null;
        this.links = new WebPage[10];
        counter = 0;
    }

    /**
     * Constructor for WebPage object.
     *
     * This constructor creates and WebPage object with a given name for the object,
     * the constructor also initializes all the instance variables.
     *
     * @param name The name of the Department/Sub-department.
     */
    public WebPage(String name){
        this. name = name;
        this.links = new WebPage[10];
        counter = 0;
    }

    /**
     * The following method helps set links to Departments.
     *
     * @param name The name of the new department
     * @param link The departments.
     *
     */
    public void modifiedSetLink(String name, WebPage[] link){
        WebPage object = new WebPage(name);
        WebPage[] linksObj = object.getLinks();
        int counterObj = 0;
        for(int i = 0; i < object.getLinks().length; i++){
            linksObj[i] = new WebPage();
            linksObj[i] = link[i];
            if(link[i] != null){
                counterObj++;
            }
        }
        object.setCounter(counterObj);
        links[counter] = object;                                                            //Adds link to the next available position.
        counter++;
    }

    /**
     * The following method helps set links to Departments.
     *
     * @param name The name of the new department
     *
     */
    public void modifiedSetLink(String name){
        WebPage object = new WebPage(name);
        links[counter] = object;                                                            //Adds link to the next available position.
        counter++;
    }


    /**
     * This is a modified version of get links.
     *
     * @return Returns links array with new address.
     */
    public WebPage[] modifiedGetLinks(){
        WebPage[] newLinks = new WebPage[10];

        if(links == null)
            return newLinks;
        else{
            for(int i = 0; i < counter; i++){
                newLinks[i] = new WebPage();
                newLinks[i].setName(links[i].getName());
                newLinks[i].setLinks(links[i].modifiedGetLinks());
            }
        }


        return newLinks;



    }



    /**
     * This method removes links/departments from a given WebPage object.
     *
     * @param name The name of the link being removed.
     */
    public void removeLink(String name) {
        for (int i = 0; i < counter; i++) {
            if (links[i].getName().equalsIgnoreCase(name)) {
                for (int j = i; j < counter; j++) {
                    links[j] = links[j + 1];                                                 //Fixes the array.
                }

                break;
            }
        }

        counter--;
    }

    /**
     *
     * @return The number of elements in the links of a given WebPage object.
     */
    public int getCounter() {
        return counter;
    }


    /**
     *
     * @return The name of the WebPage object / The name of the Department.
     */
    public String getName() {
        return name;
    }

    /**
     * This method modifies the name of a given department.
     *
     * @param name The names of the department.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return All the links/Sub-departments of a given object.
     */
    public WebPage[] getLinks() {
        return links;
    }



    /**
     * This method modifies the links of a given WebPage object
     *
     * @param links Then new links/Sub-departments
     */
    public void setLinks(WebPage[] links) {
        this.links = links;
    }

    /**
     * This method modifies the counter
     *
     * @param counter The number of link/Sub-Departments
     */
    public void setCounter(int counter) {
        this.counter = counter;
    }
}
