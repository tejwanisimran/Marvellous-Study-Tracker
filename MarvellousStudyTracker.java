import java.util.*;
import java.time.LocalDate;
import java.io.*;

// DONE
class StudyLog
{
    private String Subject;
    private double Duration;
    private String Description;
    private LocalDate Date;

    public StudyLog(LocalDate a ,String b , double c , String d)
    {
        this.Date = a;
        this.Subject = b;
        this.Duration = c;
        this.Description = d;
    }

    public LocalDate getDate()
    {
        return this.Date;
    }

    public String getSubject()
    {
        return this.Subject;
    }

    public double getDuration()
    {
        return this.Duration;
    }

    public String getDescription()
    {
        return this.Description;
    }

    @Override
    public String toString()
    {
        return Date + " | "  + Subject + " | " + Duration + " | " + Description;
    }
}

// DONE
/////////////////////////////////////////////////////////////////////////////////
//
//  Class Name : StudyTracker
//
/////////////////////////////////////////////////////////////////////////////////

class StudyTracker
{
    public ArrayList <StudyLog> Database = new ArrayList<StudyLog>();

    /////////////////////////////////////////////////////////////////////////////////
    //
    //  Function Name : InsertLog() 
    //  Used for : User Interaction & Inserting the data in to the log i.e StudyLog
    //  Author : Simran Naveen Tejwani
    //  Date : 06/03/2026
    //
    /////////////////////////////////////////////////////////////////////////////////

    public void InsertLog()
    {
        Scanner sobj = new Scanner(System.in);
        
        System.out.println("---------------------------------------------------------");
        System.out.println("----------Enter Valid Details of your Study--------------");
        System.out.println("---------------------------------------------------------");
        
        LocalDate Dateobj = LocalDate.now();

        System.out.println("Please enter the name of the subject like C/C++/Java/Python");
        String sub = sobj.nextLine();

        System.out.println("Enter the time period of your study in hours : ");
        double dur = sobj.nextDouble();

        sobj.nextLine();

        System.out.println("Please provide the description of your study : ");
        String desc = sobj.nextLine();

        StudyLog studyobj = new StudyLog(Dateobj, sub, dur, desc);

        Database.add(studyobj);

        System.out.println("Study Log gets stored successfully...");
        System.out.println("---------------------------------------------------------");

    }// End of the InsertLog() Function

    /////////////////////////////////////////////////////////////////////////////////
    //
    //  Function Name : DisplayLog() 
    //  Used for : Used to Display the data entered by the user
    //  Author : Simran Naveen Tejwani
    //  Date : 06/03/2026
    //
    /////////////////////////////////////////////////////////////////////////////////
    public void DisplayLog()
    {
        System.out.println("---------------------------------------------------------");
        
        if(Database.isEmpty())
        {
            System.out.println("-----------------Nothing to display------------------");
            System.out.println("---------------------------------------------------------");
            return;            
        }
        System.out.println("----------Log Report of Marvellous Study Tracker---------");
        System.out.println("---------------------------------------------------------");

        for(StudyLog s : Database)
        {
            System.out.println(s);
        }

        System.out.println("---------------------------------------------------------");
    }// End of the DisplayLog() Function

    /////////////////////////////////////////////////////////////////////////////////
    //
    //  Function Name : ExportCSV() 
    //  Used for : Used to export all the data from the StudyLog to a CSV file
    //  Author : Simran Naveen Tejwani
    //  Date : 06/03/2026
    //
    /////////////////////////////////////////////////////////////////////////////////
    
    public void ExportCSV()
    {
        if(Database.isEmpty())
        {
            System.out.println("---------------------------------------------------------");
            System.out.println("-------------------Nothing to export---------------------");
            System.out.println("---------------------------------------------------------");
            return;            
        }

        String FileName = "MarvellousStudyTracker.csv";

        try(FileWriter fwobj = new FileWriter(FileName))
        {
            fwobj.write("Date,Subject,Duration,Description\n");
            for(StudyLog s : Database)
            {
                fwobj.write(s.getDate() + " , " +
                            s.getSubject().replace(",", " ") + " , " + 
                            s.getDuration() + " , " +
                            s.getDescription().replace(",", " ") + "\n");
            }

            System.out.println("Data gets exported in CSV : "+FileName);
        }
        catch(Exception eobj)
        {
            System.out.println("Exception occured in CSV handling");
        }
    }// End of ExportCSV() function

     /////////////////////////////////////////////////////////////////////////////////
    //
    //  Function Name : SummaryByDate()
    //  Used for : Used to get the summary of the data by the date
    //  Author : Simran Naveen Tejwani
    //  Date : 06/03/2026
    //
    /////////////////////////////////////////////////////////////////////////////////
    public void SummaryByDate()
    {
        System.out.println("---------------------------------------------------------");

        if(Database.isEmpty())
        {
            System.out.println("---------------------------------------------------------");
            System.out.println("----------Nothing to display as database is empty--------");
            System.out.println("---------------------------------------------------------");
            return;
        }

        System.out.println("-----------Summary by Date from study tracker------------");
        System.out.println("---------------------------------------------------------");
        
        TreeMap <LocalDate , Double> tobj = new TreeMap<LocalDate , Double>();

        LocalDate lobj = null;
        double d = 0.0;
        double old = 0.0;

        for(StudyLog sobj : Database)
        {
            lobj = sobj.getDate();
            d = sobj.getDuration();

            if(tobj.containsKey(lobj))
            {
                old = tobj.get(lobj);
                tobj.put(lobj,d+old);
            }
            else
            {
                tobj.put(lobj,d);
            }
        }
        
        // Display the details as per date

        for(LocalDate l : tobj.keySet())
        {
            System.out.println("Date : "+l + "Total study duration : "+tobj.get(l));
        }
        System.out.println("---------------------------------------------------------");

    }// End of SummaryByDate()function

    /////////////////////////////////////////////////////////////////////////////////
    //
    //  Function Name : SummaryBySubject()
    //  Used for : Used to get the summary of the data by the subject
    //  Author : Simran Naveen Tejwani
    //  Date : 06/03/2026
    //
    /////////////////////////////////////////////////////////////////////////////////

    public void SummaryBySubject()
    {
        System.out.println("---------------------------------------------------------");

        if(Database.isEmpty())
        {
            System.out.println("---------------------------------------------------------");
            System.out.println("----------Nothing to display as database is empty--------");
            System.out.println("---------------------------------------------------------");
            return;
        }

        System.out.println("-----------Summary by Subject from study tracker-------------");
        System.out.println("---------------------------------------------------------");
        
        TreeMap <String , Double> tobj = new TreeMap<String , Double>();

        String s = null;

        double d = 0.0;
        double old = 0.0;

        for(StudyLog sobj : Database)
        {
            s = sobj.getSubject();
            d = sobj.getDuration();

            if(tobj.containsKey(s))
            {
                old = tobj.get(s);
                tobj.put(s,d+old);
            }
            else
            {
                tobj.put(s,d);
            }
        }
        
        // Display the details as per subject

        for(String str : tobj.keySet())
        {
            System.out.println("Subject : "+str + "Total study duration : "+tobj.get(str));
        }
        System.out.println("---------------------------------------------------------");

    }// End of SummaryBySubject()function

}

/////////////////////////////////////////////////////////////////////////////////
//
// Entry Point Class
//
/////////////////////////////////////////////////////////////////////////////////

class MarvellousStudyTracker
{
    /////////////////////////////////////////////////////////////////////////////////
    //
    //  Entery Point Function
    //
    /////////////////////////////////////////////////////////////////////////////////

    public static void main(String A[]) 
    {
       Scanner sobj = new Scanner(System.in);
       StudyTracker stobj = new StudyTracker();

        System.out.println("---------------------------------------------------------");
        System.out.println("-----------Welcome to Marvellous Study Tracker-----------");
        System.out.println("---------------------------------------------------------");

        int iChoice = 0;

        do
        {
            System.out.println("Please select appropriate option : ");
            System.out.println("1. Insert new study log");
            System.out.println("2. View all study log");
            System.out.println("3. Export study log to CSV file");
            System.out.println("4. Summary of study log by the date");
            System.out.println("5. Summary of study log by the subject");
            System.out.println("6. Exit the application");

            iChoice = sobj.nextInt();

            switch(iChoice)
            {
                // 1. Insert new study log
                case 1 : 
                    stobj.InsertLog();
                    break;

                // 2. View all study log
                case 2 :
                    stobj.DisplayLog();
                    break;

                // 3. Export study log to CSV file
                case 3 :
                    stobj.ExportCSV();
                    break;

                // 4. Summary of study log by the date
                case 4 :
                    stobj.SummaryByDate();
                    break;

                // 5. Summary of study log by the subject
                case 5 :
                    stobj.SummaryBySubject();
                    break;

                // Exit the application
                case 6 :
                    System.out.println("---------------------------------------------------------");
                    System.out.println("-------Thank You for using Marvelloud Study Tracker------");
                    System.out.println("---------------------------------------------------------");

                default :
                    System.out.println("Please enter valid option");
                    break;
            }
        }while(iChoice != 6);   // End of do-whike
    }// End of main function
}// End of main class