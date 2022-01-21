package mtrzepacz.quizgame;


public class MathQuestions {

    public String contentQuestionsMath[] =
            {
                    "Ile wynosi suma miar kątów trójkąta ?",
                    " 1 + (2 * 2) + 2 * 2 - 1 = ?",
                    "Wzór na pole trójkąta to",
                    "Ile wynosi w przybliżeniu liczba PI ?",
                    "Wzór na pole koła to ",
                    "Ile wynosi tzw. złota propocja ?",
                    " Ile wynosi wyznacznik macierzy \n  1 2 \n 2 3",
                    "Ile ścian ma ostrosłup czworokątny?",
                    "Ile krawędzi ma graniastosłup trójkątny?",
                    "Ile wynosi pochodna z liczby 2x?",
                    "Ile wynosi pochona z sin(x)?",
                    "Jak nazywa się zdanie złożone z symbolem '∨' ",
                    "Zaprzeczenie alternatywy dwóch zdań jest równoważne koninkcji zaprzeczeń to?",
                    "Ile wynosi 6 wyraz ciągu Fibbonacciego?",
                    "Ile jest tzw 'Milenijnych problemów' matematycznych ?",
                    "Co ile lat przyznawane są medale Fieldsa?",
                    "Do jakiego wielku można otrzymać medal Fieldsa?",
                    "Jakie jest prawdopodobieństwo wyrzucenia 3 orłów pod rząd rzucając zwykłą monetą?",
                    "Ile w przybliżeniu wynosi liczba Eulera?",
                    "12 / 50 to ?",
                    "Który z podanych operatorów ma najwyższy priorytet?",
                    "Która z podanych liczb jest liczbą pierwszą?",
                    "W liczbach zespolonych i*i = ?",
                    "Jeżeli kolejne wyrazy ciągu to 1,2,4,8,16 to kolejnym jest?",
                    "Odwoływanie się funkcji do samej siebie to?",
                    "proces wyznaczania pochodnej to?",
                    "Powszechnie stosowane cyfry do zapisywania liczb to cyfry?",
                    "Liczba 'D' w zapisie Rzymskim oznacza?",
                    "Cenę produktu zmniejszono o 10% a następnie zwiększono 10%, jak zmieniła się cena po obu zmianach?",
                    "Która z liczb posiada największą wartość bezwzględną?"


            };
    private String answersMath[][] =
            {
                    {"90", "180", "360", "270"},
                    {"15", "9", "8", "7"},
                    {"P = (a * h)/2", "P = a * b * c", "P = a * h", "P = a/2 * h/3"},
                    {"1,23", "1,61", "3,14", "4,75"},
                    {"P = 2*PI*R", "P = 2*PI", "P = PI * 4/3", "P = PI*(R^2)"},
                    {"0.5", "0.66", "2.44", "1.61"},
                    {"-1", "7", "8", "1"},
                    {"4", "8", "5", "6"},
                    {"12", "9", "6", "15"},
                    {"x", "2x", "0", "2"},
                    {"cos(x)", "sin(x)", "-cos(x)", "-sin(x)"},
                    {"kombinacja", "alternatywa", "negacja", "implikacja"},
                    {"I prawo \n De'Morgana", "II prawo \n De'Morgana", "prawo \n Dunsa Szkota", "prawo \n symplifikacji"},
                    {"0", "1", "6", "8"},
                    {"3", "5", "7", "9"},
                    {"1", "2", "4", "5"},
                    {"30", "40", "50", "60"},
                    {"50%", "25%", "12.5%", "75%"},
                    {"3.14", "2.71", "1.61", "0.89"},

                    {"12%", "24%", "6%", "18%"},
                    {"+", "-", "*", "()"},
                    {"1", "4", "7", "25"},
                    {"1", "-1", "2i", "-2i"},
                    {"32", "24", "18", "128"},
                    {"optymalizacja", "kombinatoryka", "aproksymacja", "rekurencja"},

                    {"całkowanie", "różniczkowanie", "rekursja", "proces \n wienera"},
                    {"arabskie", "rzymskie", "greckie", "babilońskie"},
                    {"50", "100", "500", "1000"},
                    {"wzrosła \n o 1%", "zmalała \n o 1%", "jest \n taka sama", "wzrosła \n o 0,1%"},
                    {"-1", "3", "-5", "4"}


            };

    private String correctAnswersMath[] =
            {
                    "180",
                    "8",
                    "P = (a * h)/2",
                    "3,14",
                    "P = PI*(R^2)",
                    "1.61",
                    "-1",
                    //7
                    "5",
                    "9",
                    "2",
                    "cos(x)",
                    "alternatywa",
                    "I prawo \n De'Morgana",
                    "8",
                    "7",
                    "4",
                    "40",
                    "12.5%",
                    "2.71",
                    //19
                    "24%",
                    "()",
                    "7",
                    "-1",
                    "32",
                    "rekurencja",
                    //25
                    "różniczkowanie",
                    "arabskie",
                    "500",
                    "zmalała \n o 1%",
                    "-5"


            };

    public String getQuestionMath(int a) {
        String question = contentQuestionsMath[a];
        return question;
    }

    public String getChoice1(int a) {
        String choice = answersMath[a][0];
        return choice;
    }

    public String getChoice2(int a) {
        String choice = answersMath[a][1];
        return choice;
    }

    public String getChoice3(int a) {
        String choice = answersMath[a][2];
        return choice;
    }

    public String getChoice4(int a) {
        String choice = answersMath[a][3];
        return choice;
    }

    public String getCorrectAnswer(int a) {
        String answer = correctAnswersMath[a];
        return answer;
    }


};
