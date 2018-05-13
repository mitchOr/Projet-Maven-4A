package com.codetroopers.eput.services;

import com.codetroopers.eput.domain.GoldenBookEntryDAO;
import com.codetroopers.eput.domain.entities.GoldenBookEntry;
import com.codetroopers.eput.models.UserInfo;

import javax.ejb.Stateless;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * This class is annotated with the Stateless marker. It allows to automatically handle transactions.
 */
@Stateless
public class GoldenBookEntryService {
    @Inject
    UserInfo userInfo;
    @Inject
    GoldenBookEntryDAO bookEntryDAO;
    @Inject
    FacesContext facesContext;


    public void insertNewGoldenBookEntry(final GoldenBookEntry entry) {
    	//add the book entry only if the user is connected
    	if(userInfo.getLoggedIn())
    	{
    		//vérifie que la note est bien entre 0 et 10
    		if(entry.getNote()<0 || entry.getNote()>10)
    		{
    			facesContext.addMessage("Invalid note", new FacesMessage("Invalid note !"));
    		}
    		bookEntryDAO.create(entry.getAuthor(),entry.getContent(),entry.getNote(),entry.getCreatedAt());
    	}
    	
    }
   
    //delete a GoldenBookEntry 
	public void deleteGoldenBookEntry(final int id)
	{
		bookEntryDAO.delete(id);
	}
    

    @Produces
    @Named
    public List<GoldenBookEntry> loadGoldenBookEntries() {
        return bookEntryDAO.all();
    }
}
