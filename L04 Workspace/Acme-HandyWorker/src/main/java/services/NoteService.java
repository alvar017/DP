
package services;

import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.NoteRepository;
import domain.Note;

@Service
@Transactional
public class NoteService {

	@Autowired
	private NoteRepository		noteRepository;

	@Autowired
	private RefereeService		refereeService;

	@Autowired
	private CustomerService		customerService;

	@Autowired
	private HandyWorkerService	handyWorkerService;

	@Autowired
	private ReportService		reportService;


	//36.4 (CARMEN) --> Write a note regarding any of the reports that he or shes written (as long as it was
	//saved in final mode).
	public Note create() {
		final Note note = new Note();

		final Date moment = LocalDate.now().toDate();
		final String commentCustomer = "";
		final String commentHandyWorker = "";
		final String commentReferee = "";

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

	//36.5 (CARMEN) --> Write a comment in a note regarding any of the reports that he or shes written.
	public Note updateRefereeComment(final Note note) {

		Assert.isTrue(note.getReport().getComplaint().getReferee() != null);
		return this.noteRepository.save(note);
	}
	//CARMEN
}
