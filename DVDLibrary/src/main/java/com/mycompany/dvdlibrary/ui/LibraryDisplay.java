/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dvdlibrary.ui;

import com.mycompany.dvdlibrary.dto.DVD;
import java.util.List;

/**
 *
 * @author carlo
 */
public class LibraryDisplay {

    public LibraryDisplay(UserIO io) {
        this.io = io;

    }
    private UserIO io;

    public void displayAddDVDToLibraryBanner() {
        io.print("==ADD New DVD to Library==");
    }

    public void displayRemoveResultBanner() {
        io.print("===Remove DVD===");
    }

    public void displayExitBanner() {
        io.print("Exiting Library");
    }

    public void displayUnkownCommandBanner() {
        io.print("***NOT VALID COMMAND***");
    }

    //Edit so it displays Actual DVD title on the banner.
    public void displaySuccessfullyAddedDVDBanner() {
        io.print("==Successfully added DVD to Liabrary==");
    }

    public void displayDisplayAllBanner() {
        io.print("=== Display All DVD's===");
    }

    public void displayDisplayDVDBanner() {
        io.print("=== DVD ===");
    }

    public String getDVDTitleSearch() {
        return io.readString("Please enter the DVD title.");
    }

    public void displayErrorMessage(String errorMsg) {
        io.print("===ERROR===");
        io.print(errorMsg);
    }

    public int displayAndReturnMenuOptions() {

        io.print("Library Menu");
        io.print("1. List all DVD in Library");
        io.print("2. Add DVD");
        io.print("3. Remove DVD");
        io.print("4. Edit Existing DVD");
        io.print("5. Search for DVD");
        io.print("6. EXIT LIBRARY");

        return io.readInt("Please Select from the above choices", 1, 6);
    }

    public DVD getNewDVDInfo() {

        String title = io.readString("Please Enter New DVD's Title");
        String releaseYear = io.readString("Please Enter DVD's Releas Date");
        String mpaaRating = io.readString("Please enter the DVD MPAA Rating"
                + "('PG, PG-13,R, etc')");
        String directorName = io.readString("Please Enter the Directors Name");
        String studio = io.readString("Please Enter the Studio that Created "
                + "this DVD");
        String userComments = io.readString("Enter any additional comments"
                + " or Information Here.");

        DVD currentDVD = new DVD(title);
        currentDVD.setTitle(title);
        currentDVD.setReleaseYear(releaseYear);
        currentDVD.setDirectorName(directorName);
        currentDVD.setMpaaRating(mpaaRating);
        currentDVD.setStudio(studio);
        currentDVD.setUserComments(userComments);

        return currentDVD;

    }

    public void displayDVDList(List<DVD> library) {
        for (DVD currentDVD : library) {
            String dvdInfo = String.format("%s : #%s %s %s %s %s",
                    currentDVD.getTitle(),
                    currentDVD.getReleaseYear(),
                    currentDVD.getDirectorName(),
                    currentDVD.getMpaaRating(),
                    currentDVD.getStudio(),
                    currentDVD.getUserComments());
            io.print(dvdInfo);
        }
        io.readString("Please hit enter to continue.");

    }

    public void displayDVD(DVD title) {
        if (title != null) {
            String currentDVDInfo = String.format("%s : #%s %s %s %s %s",
                    title.getTitle(),
                    title.getReleaseYear(),
                    title.getDirectorName(),
                    title.getMpaaRating(),
                    title.getStudio(),
                    title.getUserComments());
            io.print(currentDVDInfo);

        } else {
            io.print("No DVD by that title in Library");
        }
        io.readString("Please hit enter to continue.");
    }

    public void displayRemoveResult(DVD titleRecord) {
        if (titleRecord != null) {
            io.print("DVD successfully Removed from Library.");
        } else {
            io.print("No DVD by that title to Remove");
        }
        io.readString("Please hit Enter to Continue.");

    }

    public void displayEditBanner() {
        io.print("===Edit Existing DVD===");
    }

    public void displaySuccessfullyEditBanner() {
        io.print("===Successfully Editted DVD===");
    }
    
    public String displayEditDVD(){
        String title = io.readString("Please enter title for DVD you wish to EDIT"); 
        return title;
    }

    public DVD displayEditOptions(String titleOnFile,DVD editDVD) {
        if (titleOnFile != null) {
            io.print("Edit existing DVD information");
            String title = io.readString("Please Edit DVD's Title");
            String releaseYear = io.readString("Please Edit DVD's Releas Date");
            String mpaaRating = io.readString("Please Edit the DVD MPAA Rating"
                    + "('PG, PG-13,R, etc')");
            String directorName = io.readString("Please Edit the Directors Name");
            String studio = io.readString("Please Edit the Studio that Created "
                    + "this DVD");
            String userComments = io.readString("Enter any additional comments"
                    + " or Information Here.");

            DVD editedDVD = new DVD(title);
            editedDVD.setTitle(title);
            editedDVD.setReleaseYear(releaseYear);
            editedDVD.setDirectorName(directorName);
            editedDVD.setMpaaRating(mpaaRating);
            editedDVD.setStudio(studio);
            editedDVD.setUserComments(userComments);

            return editedDVD;

        } else {
            io.print("No DVD by that title in Library to edit");
            io.readString("Please hit Enter to Continue.");
            return null;
        }
    }
}
