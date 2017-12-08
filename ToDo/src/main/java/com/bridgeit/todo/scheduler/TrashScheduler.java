/*package com.bridgeit.todo.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.bridgeit.todo.service.NoteService;
@Component
public class TrashScheduler {

	@Scheduled(fixedDelay = 60000)
	public void scheduleFixedDelayTask() {
	
		
		int deleted = 0;
	    
	    // call trashing from noteService
		deleted = NoteService.deleteTrash();
		
	    
	}

}
*/