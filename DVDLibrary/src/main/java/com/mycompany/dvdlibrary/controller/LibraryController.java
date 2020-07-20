/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dvdlibrary.controller;

import com.mycompany.dvdlibrary.dao.LibraryDao;
import com.mycompany.dvdlibrary.dao.LibraryDaoException;
import com.mycompany.dvdlibrary.dto.DVD;
import com.mycompany.dvdlibrary.ui.LibraryDisplay;
import java.util.List;

/**
 *
 * @author carlo
 */
public class LibraryController {

    private LibraryDisplay display;
    private LibraryDao dao;

    public LibraryController(LibraryDao dao, LibraryDisplay display) {
        this.dao = dao;
        this.display = display;

    }

    public void run() {

        boolean runLibrary = true;

        int menuSelection = 0;

        try {

            while (runLibrary) {
                menuSelection = getMenuSelection();

                switch (menuSelection) {
                    case 1:
                        listDVDLibrary();

                        break;
                    case 2:
                        addDVDToLibrary();
                        break;
                    case 3:
                        removeDVD();
                        break;
                    case 4:
                        editDVD();
                        break;
                    case 5:
                        displayDVDSearchResult();
                        break;
                    case 6:
                        runLibrary = false;
                        break;
                    default:
                        unknownCommand();

                }

            }
            exitMessage();

        } catch (LibraryDaoException ex) {
            display.displayErrorMessage(ex.getMessage());
        }

    }

    private int getMenuSelection() throws LibraryDaoException {
        return display.displayAndReturnMenuOptions();
    }

    private void addDVDToLibrary() throws LibraryDaoException {
        display.displayAddDVDToLibraryBanner();
        DVD newDVD = display.getNewDVDInfo();
        dao.addDVD(newDVD.getTitle(), newDVD);
        display.displaySuccessfullyAddedDVDBanner();
    }

    private void listDVDLibrary() throws LibraryDaoException {
        display.displayDisplayAllBanner();
        List<DVD> library = dao.getAllDVD();
        display.displayDVDList(library);
    }

    private void displayDVDSearchResult() throws LibraryDaoException {
        display.displayDisplayDVDBanner();
        String title = display.getDVDTitleSearch();
        DVD dvd = dao.getDVD(title);
        display.displayDVD(dvd);
    }

    private void removeDVD() throws LibraryDaoException {
        display.displayRemoveResultBanner();
        String title = display.getDVDTitleSearch();
        DVD removedDVD = dao.removeDVD(title);
        display.displayRemoveResult(removedDVD);
    }
    
    private void editDVD() throws LibraryDaoException {
        display.displayEditBanner();
        String title = display.displayEditDVD();
        DVD editDVD = dao.editDVD(title);
        DVD edittedDVD = display.displayEditOptions(title, editDVD);
        dao.addDVD(title,edittedDVD);
        display.displaySuccessfullyEditBanner(); 
    }

    private void unknownCommand() {
        display.displayUnkownCommandBanner();

    }

    private void exitMessage() {
        display.displayExitBanner();
    }

}
