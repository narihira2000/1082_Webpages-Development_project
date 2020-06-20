package project.model;

public interface EmailService {

	public void validationLink(Account account);

	public void failedRegistration(String username, String email);

	public void passwordResetLink(Account account);

}
