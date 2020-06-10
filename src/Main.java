import java.util.*;

//bugs:
// wanneer er 4 juist zijn en wanneer de letter 2x in de code voorkomt
// maar incorrect posities hebben wordt er 3 aangegeven

public class Main {
    public static void main (String[] args){
        Mastermind game1 = new Mastermind();
        game1.player();
    }
}

class Mastermind {
    boolean playerWin;
    boolean exit;

    void player () {
        //menu
        System.out.println("Welkom bij het spel Mastermind. Raad de 4 letter code (a,b,c,d,e,f):");

        //De computer maakt de code aan
        Computer computer1 = new Computer();
        computer1.makeRandomCode();

        //zet de de randomcode om in een String
        String codeString = "";
        for (Character s: computer1.code){
            codeString += s+"";
        }

        do {
            Scanner input = new Scanner(System.in);
            String userInput = input.nextLine();

            if (userInput.equals("q")){
                exit = true;
                break;
            }

            if(userInput.length() < 4){
                userInput= userInput+"___";
            }

            //correct en incorrect ArrayList
            Set<Character> incorrectP = new HashSet<>();
            List<Character> correctP = new ArrayList<>();

            System.out.println("RESET CODE"+computer1.code);

            //zet de userInput on in een character ArrayList
            List<Character> userCode = new ArrayList<>();
            for (int i=0;i<4; i++){
                userCode.add(userInput.charAt(i));
            }

            for(int i =0;i<4;i++) {
                if ((computer1.code).contains(userCode.get(i))) {           //bevat de code het input? ja
                        if(userCode.get(i)==(computer1.code.get(i))){       //staat het op de juiste plek?
                    correctP.add(computer1.code.get(i));
                    computer1.code.set(i,null);                             //Als het correct is wordt de waarde vervangen door een null
                    }
                    else if(userCode.contains(computer1.code.get(i))) {    //als niet op de juiste positie
                        incorrectP.add(computer1.code.get(i));
                    }
                }
            }

            /*// Voor tests
            System.out.println("ANSWER AFTER "+computer1.code);
            System.out.println("ANSWER "+ computer1.code);
            System.out.println("Print correct "+correctP);
            System.out.println("Print incorrect "+incorrectP);*/

            System.out.println(correctP.size()+" zijn correct en staan op de juiste positie.");
            System.out.println(incorrectP.size()+" zijn correct maar staan op een onjuiste positie.");

            if (correctP.size() == 4){
                playerWin = true;
                break;
            }

            computer1.code.clear();                         //haalt alle waarde in de code ArrayList weg
            for (int i=0;i<4; i++){
                computer1.code.add(codeString.charAt(i));   //Voegt alle originele waarde weer toe
            }
            System.out.println("Probeer het opnieuw:");
        }
        while (!exit || !playerWin);
        if(playerWin == true) {
            System.out.println("Je hebt het spel gewonnen!");}
            else if (exit == true){
                System.out.println("SPEL AFSLUITEN");
            }
    }
}

class Computer {
ArrayList<Character>code = new ArrayList<>();
        void makeRandomCode(){
        code.add(randomLetter());
        code.add(randomLetter());
        code.add(randomLetter());
        code.add(randomLetter());

//System.out.println("Answer "+code)
    }

    char randomLetter() {
        char letterDatabase[] = {'a', 'b', 'c', 'd', 'e', 'f'};
        Random rnd = new Random();
        int x = rnd.nextInt(6);
        return letterDatabase[x];
    }
}