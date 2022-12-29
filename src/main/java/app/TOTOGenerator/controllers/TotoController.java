package app.TOTOGenerator.controllers;

import java.util.HashSet;
import java.util.Random;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TotoController {

    // Generate numbers between 1 and 49
    private int[] generateTotoNumbers() {
        // Total of 6 numbers
        int[] totoNumbers = new int[6];
        HashSet<Integer> set = new HashSet<>();
        Random rand = new Random();
        for (int i = 0; i < 6; i++) {
            totoNumbers[i] = rand.nextInt(49) + 1;
            // prevent same number from appearing
            if (set.contains(totoNumbers[i])) {
            } else {
                set.add(totoNumbers[i]);
            }
        }

        int[] newArray = set.stream().mapToInt(i -> i).toArray();
        for (int i = 0; i < newArray.length; i++) {
            System.out.print(newArray[i] + " ");
        }

        return totoNumbers;
    }

    // Ordinary function
    @GetMapping("/toto/ordinary")
    public String getTotoNumbers(Model model) {
        int[] totoNumbers = generateTotoNumbers();
        model.addAttribute("totoNumbers", totoNumbers);
        return "ordinary";
    }

    // System 7 function
    private int[] generateSystemSeven() {
        int[] systemSeven = new int[7];
        Random rand = new Random();
        for (int i = 0; i < 7; i++) {
            systemSeven[i] = rand.nextInt(49) + 1;
        }
        return systemSeven;
    }

    @GetMapping("/toto/system7")
    public String getSystemSeven(Model model) {
        int[] systemSevenNumbers = generateSystemSeven();
        model.addAttribute("systemSeven", systemSevenNumbers);
        return "system7";
    }

    // Exception handler for any exceptions thrown during the request handling
    @ExceptionHandler(Exception.class)
    public String handleError(Model model, Exception e) {
        model.addAttribute("errorMessage", e.getMessage());
        return "error";
    }

}