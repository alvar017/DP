
package domain;

public class Curriculum extends DomainEntity {

	private String				ticker;
	///////////////////////////
	private MiscellaneousRecord	misrec;
	private EndorserRecord		endrec;
	private PersonalRecord		perrec;
	private ProfessionalRecord	prorec;
	private EducationalRecord	edurec;
	private HandyWorker			owner;


	public String getTicker() {
		return this.ticker;
	}

	public void setTicker(final String ticker) {
		this.ticker = ticker;
	}

	public MiscellaneousRecord getMisrec() {
		return this.misrec;
	}

	public void setMisrec(final MiscellaneousRecord misrec) {
		this.misrec = misrec;
	}

	public EndorserRecord getEndrec() {
		return this.endrec;
	}

	public void setEndrec(final EndorserRecord endrec) {
		this.endrec = endrec;
	}

	public PersonalRecord getPerrec() {
		return this.perrec;
	}

	public void setPerrec(final PersonalRecord perrec) {
		this.perrec = perrec;
	}

	public ProfessionalRecord getProrec() {
		return this.prorec;
	}

	public void setProrec(final ProfessionalRecord prorec) {
		this.prorec = prorec;
	}

	public EducationalRecord getEdurec() {
		return this.edurec;
	}

	public void setEdurec(final EducationalRecord edurec) {
		this.edurec = edurec;
	}

	public HandyWorker getOwner() {
		return this.owner;
	}

	public void setOwner(final HandyWorker owner) {
		this.owner = owner;
	}
}
