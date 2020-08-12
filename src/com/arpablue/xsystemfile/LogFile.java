/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arpablue.xsystemfile;

import java.io.FileWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author augusto
 */
public class LogFile {
    public static LogFile LOG = new LogFile();

    protected String mFile;
    public static int DEEP = 7;//Contain the level of message to be showed.
    protected static DateFormat mDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    protected static DateFormat mTime = new SimpleDateFormat("yyyy.MM.dd");
    protected static DateFormat mTimeStamp = new SimpleDateFormat("yyyy.MM.dd_HH.mm.ss");
    
    public LogFile(){}
    public LogFile(String file){
        setLog(file);
    }
    
    public synchronized void writeln(String msg){
        if(mFile == null)
        {
            mFile = "c:\\application";
        }
        String file = mFile + "_" + mTime.format(new Date())+".log";
        try{
            boolean append = true;
            FileWriter fw = new FileWriter(file,append); 
            fw.write(mDateFormat.format(new Date())+" - "+msg+"\r\n");//appends the string to the file 
        fw.close(); 
        }catch(Exception e){
            System.out.println("Error (Logger - write): "+e.getMessage());
            System.out.println("\t\t"+e.getLocalizedMessage());
        }
    }
    public synchronized void write(String msg){
        if(mFile == null)
        {
            mFile = "c:\\application";
        }
        String file = mFile + "_" + mTime.format(new Date())+".log";
        try{
            boolean append = true;
            FileWriter fw = new FileWriter(file,append); 
            fw.write(msg);//appends the string to the file 
        fw.close(); 
        }catch(Exception e){
            System.out.println("Error (Logger - write): "+e.getMessage());
            System.out.println("\t\t"+e.getLocalizedMessage());
        }
    }
    /**
     * Write a message in the log file, with a number of tabs in front of the 
     * message, this number is specified by level, if level is a negative number
     * then the level will be converted to positive number.
     * @param level the number of tabs that will be put in front of the message
     * @param msg the message that will be written in the log file.
     */
    public synchronized static void writeln(int level, String msg)
    {
        
        if(level <0)
        {
            level = level * -1;
        }
        if(level > LogFile.DEEP)
        {
            return;
        }
//        for(int i=0; i< level; i++)
//        {
//            msg = "\t"+msg;
//        }
        LOG.writeln(msg);
    }
    public synchronized static void write(int level, String msg)
    {
        
        if(level <0)
        {
            level = level * -1;
        }
        if(level >= LogFile.DEEP)
        {
            return;
        }
        LOG.write(msg);
    }
    /**
     * Write an action in the log file.
     * @param msg 
     */
    public synchronized static void action(String msg)
    {
        LogFile.writeln(3,"ACTION: "+msg);
    }
    /**
     * Write a step in the log file.
     * @param msg 
     */
    public synchronized static void step(String msg)
    {
        LogFile.writeln(4,"STEP: "+msg);
    }
    /**
     * Write a title.
     * @param msg 
     */
    public synchronized static void title(String msg)
    {
        LogFile.writeln(0,"---------------- "+msg+" ----------------");
    }
    /**
     * Write a fail message.
     * @param msg 
     */
    public synchronized static void fail(String msg)
    {
        LogFile.writeln(2,"FAIL: "+msg);
    }
    /**
     * Write a pass message.
     * @param msg 
     */
    public synchronized static void pass(String msg)
    {
        LogFile.writeln(2,"PASS: "+msg);
    }
    
    /**
     * Write a error message.
     * @param msg 
     */
    public synchronized static void error(String msg)
    {
        LogFile.writeln(1,"ERROR: "+msg);
    }
    public synchronized static void exception(String msg)
    {
        LogFile.writeln(1,"EXCEPTION: "+msg);
    }
    /**
     * Write in the log file a QUERY
     * @param msg 
     */
    public synchronized static void query(String msg)
    {
        LogFile.writeln(6,"QUERY: "+msg);
    }
    /**
     * Write a success message.
     * @param msg 
     */
    public synchronized static void success(String msg)
    {
        LogFile.writeln(1,"SUCCESS: "+msg);
    }
    /**
     * Write a success message.
     * @param msg 
     */
    public synchronized static void message(String msg)
    {
        LogFile.writeln(1,"MESSAGE: "+msg);
    }
    /**
     * Write a warning message.
     * @param msg 
     */
    public synchronized static void warning(String msg)
    {
        LogFile.writeln(1,"WARNING: "+msg);
    }
    /**
     * Specify the log file.
     * @param file 
     */
    public synchronized static void setLogFile(String file)
    {
        LOG.setLog(file);
    }
    public synchronized void setLog(String file)
    {
        mFile = file;
    }
    public String getLog(){
        return mFile;
    }
}
