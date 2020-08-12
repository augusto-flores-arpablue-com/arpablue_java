/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arpablue.interfaces;

/**
 * This Interface is used by classes that us necessary start and stop a process.
 * @author Augusto Flores
 */
public interface IStarStop {
    /**
     * It is used to take action during the starting process.
     */
    public void start();
    /**
     * It is used to take actions during the stop process.
     */
    public void stop();
    /**
     * It return the current status of the object.
     * @return It is true if the object is running the process.
     */
    public boolean isRunning();
}
