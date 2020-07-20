/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dvdlibrary;

import com.mycompany.dvdlibrary.controller.LibraryController;
import com.mycompany.dvdlibrary.dao.LibraryDao;
import com.mycompany.dvdlibrary.dao.LibraryDaoFileImpl;
import com.mycompany.dvdlibrary.ui.LibraryDisplay;
import com.mycompany.dvdlibrary.ui.UserIO;
import com.mycompany.dvdlibrary.ui.UserIOConsoleImpl;

/**
 *
 * @author carlo
 */
public class App {
    
    public static void main(String[] args) {
        UserIO myIo = new UserIOConsoleImpl();
        LibraryDisplay myDisplay=new LibraryDisplay(myIo);
        LibraryDao myDao = new LibraryDaoFileImpl();
        LibraryController controller= new LibraryController(myDao, myDisplay);

        controller.run();
    }
    
}
