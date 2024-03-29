import java.util.Random;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

/**
 * The chatbot class represents a response generator object.
 * It is used to generate an automatic response to an input string.
 * For the bored tasks have not been done here!
 *
 * @author  Michael Kölling and David J. Barnes
 * @author  n-c0de-r
 * @version 2023.02.06
 */
public class ChatBot {
    private Random rng; // rng - random number generator
    private ArrayList<String> responses;
    private HashMap<String, String> responseMap;
    private HashMap<String, String> alternativeMap;
    private int lastAnswer = -1; // Assignment 4

    /**
     * Construct a ChatBot
     */
    public ChatBot() {
        rng = new Random();
        // ArrayList is good here, as they are picked randomly
        responses = new ArrayList<>();
        // Sll other responses need to be picked by a keyword,
        // Maps are great for that
        responseMap = new HashMap<>();
        alternativeMap = new HashMap<>();
        // Without these test methods, there is no responses
        fillResponses();
        fillResponseMap();
        fillAlternativeMap();
    }

    /**
     * Fill the list of predefined default responses.
     */
    private void fillResponses() {
        responses.add("That sounds interesting. Tell me more...");
        responses.add("Christmas is coming, you should get a new computer.");
        responses.add("You should visit our service-point. We can help you there");
        responses.add("Have you updated the system?");
        responses.add("Have you read the manual?");
        responses.add("Some system files seem to be missing. Try to re-install.");
        responses.add("I checked the web and found no solution. Sorry for that.");
    }

    /**
     * Fill a response map, with keywords and responses
     */
    private void fillResponseMap() {
        responseMap.put("crash", "Our quality software never crashes, that is very odd!");
        responseMap.put("slow", "Your system probably doesn't meet our software's minimum requirements.");
        responseMap.put("bug", "Send us a bug report, so we can fix that right away.");
        responseMap.put("windows", "Well, that's Windows. Consider switching to some other OS.");
        responseMap.put("expensive", "A good quality product has it's price. We are sorry, it doesn't suits you.");
        responseMap.put("installation", "Try the guided installation, and re-install the system. It should work.");
        responseMap.put("memory", "Plese consider upgrading your memory, our system is demanding but powerful.");
    }

    /**
     * Fill a response map, with alternative searchwords and responses,
     * when none of the words from the first list were found.
     */
    private void fillAlternativeMap() {
        alternativeMap.put("why", "You tell me, you started the conversation.");
        alternativeMap.put("where", "We are located in Berlin, Schöneweide.");
        alternativeMap.put("how", "It's best to hit it a few times! ... Softly first! Else hard");
        alternativeMap.put("who", "Someone else might be able to help you. Not us.");
        alternativeMap.put("when", "We are not working during Christmas.");
        alternativeMap.put("what", "I can't tell you what to do.");
        alternativeMap.put("want", "MA-O-AM! MA-o-AM!!! :)");
    }

    /**
     * Generate a random response.
     * Assignment 1 & 6
     * @return A string that should be displayed as a response, chosen randomly
     *         from list of responses depending on it's size of contained elements.
     *         Including 0 but excluding the size number!
     */
    public String generateRandomResponse() {
        int index = 0;
        while (index == lastAnswer) { // compares the two variables
            index = rng.nextInt(responses.size()); // generates a new random int
        } // unless new index is the same as the one in the answer before, continue
        // Assignment 4, saving the last index ensures no repetition
        lastAnswer = index; // stores index of the the answer for later compares
        return responses.get(index); // returns that answer
    }

    /**
     * Generate a response from a map of responses otherwise chose a default.
     * Assignment 2
     * @param input The input String from ChatSystem.
     */
    public String generateResponse(String input) {
        // Generation according to the book;
        // Assignment 3: \\W deals with punctuation marks & trim() with spaces
        HashSet<String> words = new HashSet<>(Arrays.asList(input.trim().split("\\W")));
        
         // generate a random response in case nothing final is found
        String response = generateRandomResponse();
        
        //See if any word is found.
        for (String word : words) {
            // Pick a most suitable answer from the list
            if (responseMap.get(word) != null) {
                response = responseMap.get(word);
            }else if (alternativeMap.get(word) != null) {
                // Otherwise pick a less suitable, bit general answer
                response = alternativeMap.get(word);
            }
        }
        // Return what ever got picked in the process.
        return response;
    }
}
