package mtrzepacz.quizgame;



public class History {
    public String contentQuestionsHIS[] =
            {
                    "W którym roku odbyła się bitwa pod Grunwaldem?",
                    "Kto odkrył Amerykę?",
                    "Kto był pierwszym królem Polski?",
                    "W którym roku przeniesiono stolicę Polski z Gniezna do Krakowa?",
                    "W którym roku upadł Konstantynopol?",
                    "Kto sprowadził zakon krzyżacki na tereny Polskie?",
                    "Skąd pochodził Leonardo da Vinci?",
                    "Kto wynalazł żarówkę?",
                    "W którym roku bracia Wright odbyli pierwszy lot samolotem?",
                    "W którym roku odbył się pierwszy lot balonem z udziałem ludzi?",
                    "Który wiek był tzw. złotym wiekiem w histori polski?",
                    "Kto z 'Wielkiej 3' w czasie II wojny światowej był na wszystkich spotkaniach?",
                    "Jak nazywała się wyspa na którą zesłano Naploena Bonaparte po klęsce w bitwie narodów?",
                    "Jak nazywał się pierwszy prezydent Polski?",
                    "Kim według legendy był Dratweka który pokonał smoka?",
                    "Jak na imie na Polski prezydent Duda?",
                    "Ile było rycerskich krucjat do ziemi swiętej?",
                    "Skąd pochodził Aleksander Wielki?",
                    "W którym roku rozpoczął się rozpad Jugosławi?",
                    "Jak nazywał się wynalazca dynamitu?",
                    "W którym roku odbyła się rewolucja październikowa?",
                    "W których latach trwała I wojna światowa?",
                    "Kto był naczelnikiem państwa w czasie powstania II RP?",
                    "Z jakiego kraju pochodzi papież Franciszek?",
                    "Który kraj nie nalezy do grupy Wyszehradzkiej?",
                    "W którym roku Polska wstąpiła do NATO?",
                    "Z kim Polska zawarła pokój w 1693 w Karłowicach?",
                    "Który z prezydentów USA był w Polsce dwukrotnie",
                    "Za panowania którego Cesarza spłonął Rzym?",
                    "Ile dni trwało Powstanie Warszawskie?"








            };
    private String answersHIS[][] =
            {
                    {"1366", "1426", "1410", "1000"},
                    {"Krzysztof \n Kolumb", "Pablo \n Pisasso", "Ferdynand \n Magellan", "Vasco \n da Gama"},
                    {"Kazimierz \n Odnowiciel", "Mieszko I", "Bolesław \n Chrobry", "Zygmunt \n III Waza"},
                    {"1078","1029","1038","1112"},
                    {"1452","1453","1498","1492"},
                    {"Mieszko \n III Stary","Henryk \n Brodaty", "Przemysł II", "Konrad \n Mazowiecki"},
                    {"Włochy","Hiszpania","Rumunia","Francja"},
                    {"Nikola Tesla", "Thomas Edison", "Albert Einstein", "Sebastian Bach"},
                    {"1900","1905","1903","1907"},
                    {"1712","1836","1783","1812"},
                    {"X","XIII","XV","XVI"},
                    {"Winston Churchill","Harry Truman","Józef Stalin", "Królowa Elżbieta"},
                    {"Majorka","Elba","Sycylia","Wyspa \n św Heleny"},
                    {"Lech Wałęsa","Aleksander Kwaśniewski","Gabriel \n Narutowicz","Ignacy Mościcki"},
                    {"Kowalem","Szewcem","Czołgistą","Kucharzem"},
                    {"Janusz","Andrzej","Cezary","Tomasz"},
                    {"4","6","7","9"},
                    {"Włochy","Macedonia","brak informacji", "Egipt"},
                    {"1991","1989","2003","1997"},
                    {"Alfred Nobel", "Nikola Tesla", "John Ericsson", "Victor Hugo"},
                    {"1918","1904","1910","1917"},
                    {"1904-1908","1914-1920","1914-1927","1914-1918"},
                    {"Józef \n Piłsudski","Roman Dmowski","Edward \n Rydz-Smigły","Piotr Wysocki"},
                    {"Argentyna","Brazylia","Meksyk","Urugwaj"},
                    {"Polska", "Węgry", "Słowacja","Austria"},
                    {"1999","2004","1994","2007"},
                    {"Szwecja","Turcja","Rosja","Prusy"},
                    {"Bill Clinton", "Ronald Reagan", "Richard Nixon", "John Carter"},
                    {"Kaligula","Neron","Julisz Cezar","Klaudiusz"},
                    {"65","63","60","58"}



            };

    private String correctAnswersHIS [] =
            {
                    "1410",
                    "Krzysztof \n Kolumb",
                    "Bolesław \n Chrobry",
                    "1038",
                    "1453",
                    "Konrad \n Mazowiecki",
                    "Włochy",
                    "Thomas Edison",
                    "1903",
                    "1783",
                    "XVI",
                    "Józef Stalin",
                    "Elba",
                    "Gabriel \n Narutowicz",
                    "Szewcem",
                    "Andrzej",
                    "9",
                    "Macedonia",
                    "1991",
                    "Alfred Nobel",
                    "1917",
                    "1914-1918",
                    "Józef \n Piłsudski",
                    "Argentyna",
                    "Austria",
                    "1999",
                    "Turcja",
                    "Bill Clinton",
                    "Neron",
                    "63"

                    //30

            };

    public String getQuestionHIS(int a)
    {
        String question = contentQuestionsHIS[a];
        return question;
    }

    public String getChoice1(int a )
    {
        String choice = answersHIS[a][0];
        return choice;
    }

    public String getChoice2(int a )
    {
        String choice = answersHIS[a][1];
        return choice;
    }
    public String getChoice3(int a )
    {
        String choice = answersHIS[a][2];
        return choice;
    }
    public String getChoice4(int a )
    {
        String choice = answersHIS[a][3];
        return choice;
    }

    public String getCorrectAnswer(int a)
    {
        String answer = correctAnswersHIS[a];
        return answer;
    }



}

