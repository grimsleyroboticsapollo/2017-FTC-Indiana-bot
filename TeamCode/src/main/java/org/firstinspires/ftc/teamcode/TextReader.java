package org.firstinspires.ftc.teamcode;

import android.content.Context;
import android.speech.tts.TextToSpeech;


import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Set;
import java.util.logging.Logger;

/**
 * Helper around text-to-speech methods
 */
public class TextReader extends Thread {
    private static Logger log = Logger.getLogger(TextReader.class.getCanonicalName());

    private static Set<String> messages = new LinkedHashSet<String>();
    private static final long LOOP_INTERVAL = 1000;
    private static boolean ttsIsInitialized = false;

    private static Context context;
    private static android.speech.tts.TextToSpeech textToSpeech = null;
    private final static String speakMutex = "only one at a time";
    private final static String isSpeakingMutex = "only one at a time";

    /**
     * Put message on the queue to be spoken aloud.
     */
    public static void speak(Context context, String message) {

        log.fine("speak: '" + message + "'");

        synchronized (speakMutex) {
            messages.add(message);
            if (TextReader.context == null) {
                log.info("TextReader initial call (with first word: '" + message + "')");

                // first-time call; kick it off
                TextReader.context = context;
                TextReader textReader = new TextReader();
                textReader.start();
            }
        }
    }

    public static boolean isSpeaking() {
        boolean returnVal;

        synchronized (isSpeakingMutex) {
            if (!ttsIsInitialized) {
                returnVal = false;
            } else if (textToSpeech == null) {
                returnVal = false;
            } else {
                returnVal = textToSpeech.isSpeaking();
            }
        }

        log.fine("isSpeaking() returns: " + returnVal);
        return returnVal;
    }

    @Override
    public synchronized void run() {
        log.info("TextReader.run()");

        try {

            while (true) {

                synchronized (isSpeakingMutex) {

                    if ((textToSpeech == null) && (!messages.isEmpty())) {
                        log.fine("TextToSpeech is null but messages are on the queue; initializing");
                        ttsIsInitialized = false;
                        textToSpeech = new android.speech.tts.TextToSpeech(context, new android.speech.tts.TextToSpeech.OnInitListener() {
                            @Override
                            public void onInit(int i) {
                                log.fine("TextToSpeech initialized OK");
                                ttsIsInitialized = true;
                            }
                        });
                        textToSpeech.setLanguage(Locale.US);

                        // wait until the next loop
                    }

                    if (textToSpeech == null) {
                        // textToSpeech became unavailable (orientation change? bug?)
                        // attempt restart

                        ttsIsInitialized = false;
                    }

                    if (ttsIsInitialized) {
                        // ready to talk

                        if (!textToSpeech.isSpeaking()) {
                            log.fine("not speaking, check next message");

                            Iterator<String> iterator = messages.iterator();
                            if (iterator.hasNext()) {

                                String sayTheWord = iterator.next();
                                log.fine("say: '" + sayTheWord + "'");
                                textToSpeech.speak(sayTheWord, TextToSpeech.QUEUE_ADD, null);
                                iterator.remove();

                            } else {
                                log.fine("all done talking, shutting down");

                                textToSpeech.shutdown();
                                textToSpeech = null;
                                ttsIsInitialized = false;
                            }

                        }

                    }

                }

                // loop
                if (this.isInterrupted()) {
                    TextReader.context = null;
                    return; // stop doing what you're doing
                }

                try {
                    Thread.sleep(LOOP_INTERVAL);
                } catch (InterruptedException e) {
                    // interrupted
                }

                if (this.isInterrupted()) {
                    TextReader.context = null;
                    return; // stop doing what you're doing
                }
            }

        } catch (Exception e) {
            log.severe("An unexpected error has occurred.");

        } finally {

            // unload everything
            textToSpeech = null;
            ttsIsInitialized = false;
            TextReader.context = null;
        }

    }

}
