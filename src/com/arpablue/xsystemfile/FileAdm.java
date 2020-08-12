/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.arpablue.xsystemfile;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 *
 * @author Administrator
 */
public class FileAdm {
    protected RandomAccessFile mWrite;
    protected String mPath;
    public String getPaht(){ return mPath;}
    public void setWriter(RandomAccessFile writer){
        ///close();
        mWrite = writer;
    }
    public void open(){ open(mPath);}
    public void open(String url){
        try{
            mPath = url;
            if(mWrite !=null)
                mWrite.close();
            File f = new File(url);
            if( f.exists())
                f.delete();
            mWrite = new RandomAccessFile(url,"rw");
        }catch(Exception e){
            LogFile.error("(FileAdm - open):"+e.getMessage());
        }
    }
    public void close(){
        try{
            if(mWrite==null)
                mWrite.close();
        }catch(Exception e){
            LogFile.error("FileAdm - close):"+e.getMessage());
        }
    }
    public void writeln(String line){
        try {
            mWrite.writeBytes(line + "\r\n");
        } catch (IOException ex) {
            LogFile.error("(FileAdm - writeln):"+ex.getMessage());
        }
    }
    public void write(String line){
        try {
            mWrite.writeBytes(line);
        } catch (IOException ex) {
            LogFile.error("(FileAdm - writeln):"+ex.getMessage());
        }
    }
}
