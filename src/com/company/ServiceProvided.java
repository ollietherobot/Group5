//Author: Meera Murali
package com.company;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import  java.util.*;

public class ServiceProvided extends Service {
    protected Date currentDateTime; //Date of entry
    protected Date serviceDate;     //Date of service
    protected int memberID;         //ID of member that service is provided to
    protected String memberName;    //Name of member that service is provided to
    protected int providerID;       //ID of provider providing the service
    protected String providerName;  //Name of provider providing the service
    protected String comments;      //optional comments



    //Default constructor
    public ServiceProvided()
    {
        //Initializes idNum to 0,
        //            name, next, left, right to null
        //            fee to 0.0
        super();

        //Sets current Date (to when current object is created)
        this.currentDateTime = new Date();

        //Initializes remaining fields to null/0
        this.serviceDate = null;
        this.memberID = this.providerID = 0;
        this.memberName = this.providerName = this.comments = null;
    }



    //Constructor with arguments
    public ServiceProvided (Service newService,                     //to copy Service details
                           String dateOfService,                    //date when service was provided (MM/DD/YYYY)
                           int aMemberID, String aMemberName,       //member that service is provided to
                           int aProviderID, String aProviderName,   //provider providing the service
                           String newComments)                      //optional (null if no comments)
            throws ParseException                                   //if dateOfService is not in the format (MM/DD/YYYY)
    {
        //Set current object's idNum, name and fee to those of argument Service
        //Initializes left, right and next fields to null
        super(newService);

        //Set current Date (to when current object is created)
        this.currentDateTime = new Date();

        //Set service date based on argument
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        this.serviceDate = new Date (formatter.parse(dateOfService).getTime());

        //Set member and provider names and ids
        this.memberID = aMemberID;
        this.memberName = aMemberName;
        this.providerID = aProviderID;
        this.providerName = aProviderName;

        //Copy comments
        this.comments = newComments;
    }



    //Copy Constructor
    //Sets current object's idNum, name, fee, memberID, serviceDate, memberName,
    //providerID, providerName, comments to those of argument
    //Initializes left, right and next fields to null
    public ServiceProvided (ServiceProvided toCopy)
    {
        //Copy idNum, name, fee
        //set left, right and next to null
        super(toCopy);

        //Set current date (to when current object is created)
        this.currentDateTime = new Date();

        //Copy date of service
        this.serviceDate = new Date((toCopy.serviceDate).getTime());

        //Copy member and provider names and ids
        this.memberID = toCopy.memberID;
        this.memberName = toCopy.memberName;
        this.providerID = toCopy.providerID;
        this.providerName = toCopy.providerName;

        //Copy comments
        this.comments = toCopy.comments;
    }





    //Displays current date, service date, service code, name, fee,
    //member ID and name, provider ID and name, comments (each on a new line, indented)
    //No return value
    @Override
    public void display()
    {

        //Display date MM/dd/yyyy and time HH:mm:ss for current date
        SimpleDateFormat currentDateFormatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        System.out.println("\tCurrent date and time: " + currentDateFormatter.format(this.currentDateTime));

        //Display only mm/dd/yyyy for service date
        SimpleDateFormat serviceDateFormatter = new SimpleDateFormat("MM/dd/yyyy");
        System.out.println("\tService date: " + serviceDateFormatter.format(this.serviceDate));

        //Display service code, name and fee
        super.display();

        //Display member and provider details
        System.out.println("\tMember ID: " + this.memberID);
        System.out.println("\tMember name: " + this.memberName);
        System.out.println("\tProvider ID: " + this.providerID);
        System.out.println("\tProvider name: " + this.providerName);

        //Display comments if any
        if (this.comments != null)
            System.out.println("\tComments: " + this.comments);
        else
            System.out.println("\tComments: None");
    }



    //Compares current object's serviceDate with that of argument
    //Returns -2 if argument is null.
    //Returns -1 if current object's serviceDate is before,
    //         0 if equal to, and
    //         1 if after that of argument
    public int compareByServiceDate(ServiceProvided toCompare)
    {
        //null argument
        if (toCompare == null)
            return -2;

        if (this.serviceDate.before(toCompare.serviceDate))
            return -1;

        if (this.serviceDate.after(toCompare.serviceDate))
            return 1;

        return 0;
    }



    //Compares current object's serviceDate with string argument (MM/DD/YYYY format)
    //Returns -2 if argument is null.
    //Returns -1 if current object's serviceDate is before,
    //         0 if equal to, and
    //         1 if after argument date value
    public int compareByServiceDate(String toCompare) throws ParseException
    {
        //null argument
        if (toCompare == null)
            return -2;

        //convert argument string to date
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        Date dateToCompare = new Date (formatter.parse(toCompare).getTime());

        if (this.serviceDate.before(dateToCompare))
            return -1;

        if (this.serviceDate.after(dateToCompare))
            return 1;

        return 0;
    }



    //Appends data to argument File
    //Returns 0 (Failure; Null argument)
    //       -1 (Failure; IO Exception)
    //       -2 (Failure; Unable to close writer)
    //        1 (Success)
    @Override
    public int writeToFile(File writeFile)
    {
        int success = 0;
        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;

        //null argument
        if (writeFile == null)
            return 0;

        try {
            fileWriter = new FileWriter(writeFile, true);
            bufferedWriter = new BufferedWriter(fileWriter);

            //Append idNum
            bufferedWriter.write(Integer.toString(this.idNum));
            bufferedWriter.write("|");

            //Append name
            bufferedWriter.write(this.name);
            bufferedWriter.write("|");

            //Append fee
            bufferedWriter.write(Double.toString(this.fee));
            bufferedWriter.write("|");

            //Append current date
            SimpleDateFormat currentDateFormatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
            bufferedWriter.write(currentDateFormatter.format(this.currentDateTime));
            bufferedWriter.write("|");

            //Append service date
            SimpleDateFormat serviceDateFormatter = new SimpleDateFormat("MM/dd/yyyy");
            bufferedWriter.write(serviceDateFormatter.format(this.serviceDate));
            bufferedWriter.write("|");

            //Append Member ID and name
            bufferedWriter.write(Integer.toString(this.memberID));
            bufferedWriter.write("|");
            bufferedWriter.write(this.memberName);
            bufferedWriter.write("|");

            //Append Provider ID and name
            bufferedWriter.write(Integer.toString(this.providerID));
            bufferedWriter.write("|");
            bufferedWriter.write(this.providerName);
            bufferedWriter.write("|");

            //Append comments if any
            if (this.comments != null)
                bufferedWriter.write(this.comments);

            //Append newline
            bufferedWriter.write("\n");

            success = 1;
        }

        catch (IOException e)
        {
            success = -1;
        }

        //Close writers
        try
        {
            if (bufferedWriter != null)
                bufferedWriter.close();

            if (fileWriter != null)
                fileWriter.close();
        }
        catch (Exception e) {
            success = -2;
        }

        return success;
    }



    @Override
    public int writeReport(File writeFile)
    {
        int success = 0;

        return success;
    }



    //Reads a single line from file using argument Scanner object, and copies
    //data into current object's fields
    //Returns 0 (Failure; Null argument)
    //       -1 (Failure; Unable to read in data for all fields)
    //       -2 (Failure; Parse exception - Incorrect date format)
    //        1 (Success)
    @Override
    public int loadFromFile(Scanner fileReader) throws NumberFormatException
    {
        int success = 0;

        if (fileReader != null)
        {
            if (fileReader.hasNext())
            {
                //Read a line from file
                String data = fileReader.nextLine();

                //Split line using '|' as delimiter
                String [] fields = data.split("\\|", 10);

                //Copy fields into current object and flag success
                if (fields.length == 10) {

                    //Read in idNum, name and fee
                    this.idNum = Integer.parseInt(fields[0]);
                    this.name = fields[1];
                    this.fee = Double.parseDouble(fields[2]);

                    try {
                        //Read in current date and time
                        SimpleDateFormat currentDateFormatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
                        this.currentDateTime = new Date(currentDateFormatter.parse(fields[3]).getTime());

                        //Read in service date
                        SimpleDateFormat serviceDateFormatter = new SimpleDateFormat("MM/dd/yyyy");
                        this.serviceDate = new Date (serviceDateFormatter.parse(fields[4]).getTime());
                    }
                    catch (ParseException e)
                    {
                        return -2;
                    }

                    //Read in member ID and name
                    this.memberID = Integer.parseInt(fields[5]);
                    this.memberName = fields[6];

                    //Read in provider ID and name
                    this.providerID = Integer.parseInt(fields[7]);
                    this.providerName = fields[8];

                    //Read in comments if any
                    if (fields[9].length() > 0)
                        this.comments = fields[9];
                    else
                        this.comments = null;

                    success = 1;
                }

                else
                    success = -1;
            }
        }

        return success;
    }


}