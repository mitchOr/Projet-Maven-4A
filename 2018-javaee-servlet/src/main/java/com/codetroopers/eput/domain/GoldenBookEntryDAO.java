/*
 * Copyright 2016 Code-Troopers.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.codetroopers.eput.domain;

import com.codetroopers.eput.domain.entities.GoldenBookEntry;
import com.codetroopers.eput.domain.entities.User;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Stateless
public class GoldenBookEntryDAO {
    @Inject
    EntityManager em;

   /* public List<GoldenBookEntry> all() {
        List<GoldenBookEntry> bookEntries = new ArrayList<>();
        bookEntries.add(new GoldenBookEntry("John", "C'est trop bien, je peux plus m'en passer"));
        bookEntries.add(new GoldenBookEntry("Henry", "waaaaaa, j'adore"));
        bookEntries.add(new GoldenBookEntry("Marc", "Je veux la mÃªme chez moi !"));
        return bookEntries;
    }*/
    
    public List<GoldenBookEntry> all() {
    	// List<GoldenBookEntry> bookEntries= em.createQuery("SELECT g FROM GOLDENBOOKENTRY g", GoldenBookEntry.class).getResultList();
		//insert into  GOLDENBOOKENTRY values (1,'mitch','it s awesome',CURRENT_TIMESTAMP)
    	 
    	 Query query = em.createNativeQuery("SELECT id, author, content, createdat,note FROM GOLDENBOOKENTRY");
    	 List<Object[]> malist = query.getResultList();
    	 List<GoldenBookEntry> bookEntries = new ArrayList<>();
    	
    	 for (Object[] object : malist) 
    	 {   
    		 //on recupère dans la bd et on cree un objet java
    			Date date = new Date(((Timestamp)object[3]).getTime());
    			bookEntries.add(new GoldenBookEntry((String) object[1], (String)object[2],(int)object[4],date));  
    			
    	 }
        return bookEntries;
    }
	
    /**
	 * create and persist an GoldenBookEntry
	 * @param author
	 * @param content
	 * @param date
	 * @return
	 */
    public GoldenBookEntry create(String author, String content,int i, Date date) {
    
    	GoldenBookEntry entry = new GoldenBookEntry(author,content,i,date);
           em.persist(entry);
           return entry;
    }
    
    /**
     * supprimer commentaire ayant le id suivant
     * @param id
     */
    public void delete(int id )
    {
    	em.createNativeQuery("delete  FROM GOLDENBOOKENTRY where id = "+id);
    }
}
