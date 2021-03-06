package com.bridgeit.todo.scheduler;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.bridgeit.todo.model.Note;
import com.bridgeit.todo.service.NoteService;

@Component
public class TrashScheduler {

	@Autowired
	NoteService noteService;
	
	@Scheduled(fixedDelay=200000)
	public void deleteTrashNote() {
		
		List<Note> notes=noteService.getNotesForTrash();
		/*System.out.println("insidse trash@@@@@@@@@@@@@");*/
		int size=notes.size();
		
		long sevenDayBefore=System.currentTimeMillis();
		sevenDayBefore=sevenDayBefore-(7*24*60*60*1000);
		Date current=new Date(sevenDayBefore);
	    for(int i=0;i<size;i++) {
			
			if(notes.get(i).getModifiedDate().before(current)) {
				noteService.deleteNoteById(notes.get(i).getNoteId());
			}
		}
		
	}

}
