package com.pocketedDays.controller;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.googleapis.json.GoogleJsonError;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.GmailScopes;
import com.google.api.services.gmail.model.Message;
import com.pocketedDays.utilities.PropertiesLoader;
import org.apache.commons.codec.binary.Base64;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Paths;
import java.security.GeneralSecurityException;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Logger;

import static javax.mail.Message.RecipientType.TO;

/**
 * The type G mailer.
 *
 * Source from : https://cloud.google.com/appengine/docs/legacy/standard/java/mail/sending-mail-with-mail-api
 */
public class GMailer implements PropertiesLoader {
    private final Logger logger = Logger.getLogger(String.valueOf(PropertiesLoader.class));
    private final Gmail service;

    /**
     * Instantiates a new G mailer.
     *
     * @throws GeneralSecurityException the general security exception
     * @throws IOException              the io exception
     */
    public GMailer() throws GeneralSecurityException, IOException {
        NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        GsonFactory jsonFactory = GsonFactory.getDefaultInstance();
        service = new Gmail.Builder(httpTransport, jsonFactory, getCredentials(httpTransport, jsonFactory))
                .setApplicationName("Test Mailer")
                .build();
    }
    private static Credential getCredentials(final NetHttpTransport httpTransport, GsonFactory jsonFactory)
            throws IOException {
        // Load client secrets.
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(jsonFactory, new InputStreamReader(GMailer.class.getResourceAsStream("/client_secret_472113439088-4rjgd1mija43favjf1h6d63odmujcf50.apps.googleusercontent.com.json")));

        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                httpTransport, jsonFactory, clientSecrets, Set.of(GmailScopes.GMAIL_SEND))
                .setDataStoreFactory(new FileDataStoreFactory(Paths.get("tokens").toFile()))
                .setAccessType("offline")
                .build();

        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();

        //returns an authorized Credential object.
        return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
    }

    /**
     * Send mail.
     *
     * @param subject  the subject
     * @param message  the message
     * @param receiver the receiver
     * @throws GeneralSecurityException the general security exception
     * @throws IOException              the io exception
     * @throws MessagingException       the messaging exception
     */
    public void sendMail(String subject, String message, String receiver) throws GeneralSecurityException, IOException, MessagingException {
        // Encode as MIME message
        Properties props = new Properties();
        Properties properties = null;
        try {
            properties = loadProperties("/gmail.properties");
        } catch (Exception e) {
            logger.info("Somthing is wrong.");
            throw new RuntimeException(e);
        }
        String myEmail = properties.getProperty("my.email");
        Session session = Session.getDefaultInstance(props, null);
        MimeMessage email = new MimeMessage(session);
        email.setFrom(new InternetAddress(myEmail));
        email.addRecipient(TO, new InternetAddress(receiver));
        email.setSubject(subject);
        email.setText(message);

        // Encode and wrap the MIME message into a gmail message
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        email.writeTo(buffer);
        byte[] rawMessageBytes = buffer.toByteArray();
        String encodedEmail = Base64.encodeBase64URLSafeString(rawMessageBytes);
        Message msg = new Message();
        msg.setRaw(encodedEmail);

        try {
            // Create send message
            msg = service.users().messages().send("me", msg).execute();
        } catch (GoogleJsonResponseException e) {
            GoogleJsonError error = e.getDetails();
            if (error.getCode() == 403) {
                logger.info("Unable to send message: " + e.getDetails());
            } else {
                logger.info("Somthing is wrong.");
                throw e;
            }
        }
    }
}
