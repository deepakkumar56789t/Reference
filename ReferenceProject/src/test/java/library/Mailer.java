package library;

import java.io.IOException;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import org.apache.log4j.Logger;

public class Mailer {
	
	/**********************************************************************************************
	 /*Script Developed by :- Deepak Kumar
     *Script Name :- Dropnet List and Search.
     *Script Type :- Testng
     *Platform:- Minimum requirement java SE7 to run this script
     */
	 //*********************************************************************************************/
	static Logger logger = Logger.getLogger(Mailer.class);

	public static void sendEmail(String zippedReport) throws IOException {
		String host = "smtp.mail.fedex.com";
		String sender = BackendCommonFunctionality.getProperty("MailSender");
		String recipient = BackendCommonFunctionality.getProperty("MailRecipients");

		Properties properties = System.getProperties();

		properties.setProperty("mail.smtp.host", host);
		Session session = Session.getDefaultInstance(properties);
		try {
			String cid = BackendCommonFunctionality.currentDateTime;
			String extentReportName = "execution-report_" + BackendCommonFunctionality.currentDateTime + ".zip";
			Message message = new MimeMessage(session);
			Multipart multiPart = new MimeMultipart();

			message.setFrom(new InternetAddress(sender));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));

			message.setSubject("Dropnet Automation Execution Status ");

//			MimeBodyPart extentReport = new MimeBodyPart();
//			String ZippedFilename = zippedReport + ".zip";
//			DataSource ZippedFileSource = new FileDataSource(ZippedFilename);
//			extentReport.setDataHandler(new DataHandler(ZippedFileSource));
//			extentReport.setFileName(extentReportName);

			MimeBodyPart reportDashView = new MimeBodyPart();
			DataSource reportDashViewSource = new FileDataSource("Resources\\report-dashview.png");
			reportDashView.setDataHandler(new DataHandler(reportDashViewSource));
			reportDashView.setFileName("report-dashview.png");

			MimeBodyPart policyGridLogger = new MimeBodyPart();
			DataSource policyGridLoggerSource = new FileDataSource("Resources\\logger.log");
			policyGridLogger.setDataHandler(new DataHandler(policyGridLoggerSource));
			policyGridLogger.setFileName("logger.log");

			MimeBodyPart imageInBody = new MimeBodyPart();
			imageInBody.attachFile("Resources\\report-dashview.png");
			imageInBody.setContentID("<" + cid + ">");
			imageInBody.setDisposition(MimeBodyPart.INLINE);
			multiPart.addBodyPart(imageInBody);

			MimeBodyPart textPart = new MimeBodyPart();
			textPart.setText("<html>" + " <body>" + "  <p style='font-family:Calibri;'>Hi Team,</p>"
					+ "<p style='font-family:Calibri;'> Please find below report for TestView, StepsView and PassPercentage of Today's Dropnet Automation Execution. </p>" + "  <img src=\"cid:" + cid + "\"style='border:50 solid black'/>"

					+ "<p style='font-family:Calibri;'>For detailed execution report of the Dropnet, please find the attached  <b>" + extentReportName
					+ "</b> and <b> logger.log </b>file.</p>"
					+ "<p style='font-family:Calibri;'><b>Note:-</b> If image is not diplayed in the mail body then, please find the <b>report-dashview.png</b> file in the attachements.</p>"

					+ "<p style='font-family:Calibri;'>Thanks and Regards,<br>Dropnet_Team</p>" + "<p><br></p>"

					+ "<p style='font-family:Calibri;'><font size='1'><center><<------------------------------------------------This is an automated email triggered from Dropnet Automation framework-------------------------------------------->></center></font></p>"

					+ "</body>" + "</html>", "US-ASCII", "html");

			//multiPart.addBodyPart(extentReport);
			multiPart.addBodyPart(reportDashView);
			multiPart.addBodyPart(policyGridLogger);
			multiPart.addBodyPart(textPart);

			message.setContent(multiPart);
			Transport.send(message);

			logger.info("Email Sent Succesfully");
		} catch (MessagingException mex) {
			mex.printStackTrace();
		}
	}
}