package Project2;
/*
Autumn Capasso
UMGC
CMSC 350
PROJECT 2
 */

//import packages
import java.util.*;
import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;

//Main java class
public class Project2
{
    public static List<Polynomial> polyList = new ArrayList<>();

    //Project 2 main method
    public static void main(String[]args){
    polyListProcess();
}

//Open a file, tokenizes it into an Arraylist

    public static ArrayList<String> readfile()

    {
        //Create ArrayList and JFileChooser
        ArrayList<String> polyList = new ArrayList<>();
        JFileChooser chooser = new JFileChooser();

        //displays directory and files
        chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

        //uses current directory
        chooser.setCurrentDirectory(new File(System.getProperty("user.dir")));

        //this part reads file using java scanner class
        int response = chooser.showOpenDialog(null);
        if (response == JFileChooser.APPROVE_OPTION){
            File file = chooser.getSelectedFile();
            try {
                Scanner fileIn = new Scanner(file);
                if  (file.isFile()){
                    while (fileIn.hasNextLine()){
                        String expression = fileIn.nextLine();
                        polyList.add(expression);
                    }
                }
                //Exception methods
            }catch (NoSuchElementException nse){
                JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),"This file is empty!");
            }catch(FileNotFoundException fne){
                JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),"The file could not be found!");
            }
        }

        //returns expression list
        return polyList;
    }

    //This method will check the order
    public static boolean checkOrder(List<Polynomial> polyList){
        boolean orderCheck = true;
        Polynomial previous = polyList.get(polyList.size()-1);
        for(int i = polyList.size()-2; i > 0; i--){

            if (previous.comparePoly(polyList.get(i)) < 0){
                orderCheck = false;
            }
        }
        return orderCheck;
    }

    //This method will process the Polynomial Linked list
    public static void polyListProcess(){
        try {
            ArrayList<String> a = readfile();
            for (String element : a) {
                Polynomial p = new Polynomial(element);
                System.out.println(p);
                polyList.add(p);
            }
        }catch (InvalidPolynomialSyntax ex){
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),ex.getMessage());
        }
        System.out.println("The Strong Ordered Polynomial: " + OrderedList.checkSorted(polyList));
        System.out.println("The Weak Ordered Polynomial: " + checkOrder(polyList));
    }
}


