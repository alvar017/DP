
package services;

import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.NoteRepository;
import domain.Note;

@Service
@Transactional
public class NoteService {

	@Autowired
	private NoteRepository	noteRepository;

	@Autowired
	private RefereeService	refereeService;

	@Autowired
	private CustomerService	customerService;


	//37.4 (CARMEN) --> Write a note regarding any of the reports that a referees written regarding any of
	//the complaints in which he or shes involved.
	public Note create() {
		final Note note = new Note();

		final Date moment = new Date();
		final String commentCustomer = "";
		final String commentHandyWorker = "";
		final String commentReferee = "";

		note.setCommentCustomer(commentCustomer);
		note.setCommentHandyWorker(commentHandyWorker);
		note.setCommentReferee(commentReferee);
		note.setMoment(moment);

		return note;
	}
	//CARMEN

	//CARMEN
	public Note save(final Note note) {
		Assert.isTrue(note.getReport() != null);
		return this.noteRepository.save(note);
	}
	//CARMEN

	//CARMEN
	public Collection<Note> findAll() {
		return this.noteRepository.findAll();
	}
	//CARMEN

	//CARMEN
	public Note findOne(final Integer note) {
		return this.noteRepository.findOne(note);
	}
	//CARMEN

	//CARMEN
	public Note updateRefereeComment(final Note note) {

		Assert.isTrue(note.getReport().getComplaint().getReferee() != null);
		return this.noteRepository.save(note);
	}
	//CARMEN
}
