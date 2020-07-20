/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dvdlibrary.dao;

/**
 *
 * @author carlo
 */
public class LibraryDaoException extends Exception{
    
    public LibraryDaoException(String message){
        super(message);
    }
    
    public LibraryDaoException(String message, Throwable cause){
        super(message, cause);
    }
}
