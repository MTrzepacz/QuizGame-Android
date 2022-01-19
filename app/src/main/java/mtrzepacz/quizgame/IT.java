package mtrzepacz.quizgame;


public class IT {

    public String contentQuestionsIT[] =
            {
                    "Ile bitów ma bajt?",
                    "Jakiego modelu barw używa się w ekranach?",
                    "Który z systemów operacyjnych Windows jest najstarszy?",
                    "Jak nazywa się jeżyk do operacji na relacyjnych bazach danych?",
                    "Który z podanych języków programowania jest najstarszy?",
                    "Jakim rodzajem języka programowania jest java?",
                    "Jakie słowo kluczowe w języku sql zapewnia brak powtórzeń w wyniku zapytania?",
                    "Który z podanych programów posługuje się grafiką wektorową?",
                    "Które polecenie w systemie Linux zmieni właściciela pliku?",
                    "Które polecenie w systemie Linux jest związane z procesami?",
                    "Który z programów NIE wchodzi w skład podstawowego pakietu office?",
                    "Który z podanych wersji systemu android jest najnowszy?",
                    "Jak nazywa się system operacyjny uzywany na urządzeniach firmy Apple?",
                    "Który format grafiki rastrowej używa kompresji stratnej?",
                    "Który ze skrótów klawiszowych kopiuje zaznaczony tekst?",
                    "Który z procesorów firmy intel jest najwydajniejszy?",
                    "Jaka jest złożoność obliczeniowa sortowania bąbelkowego?",
                    "Sposób tworzenia grafiki rastrowej na poziomie edycji pixeli to?",
                    "Która z przeglądarek internetowych została stworzona przez firmę Apple?",
                    "Który z programów pakietu MS Office służy do tworzenia prezentacji",
                    "Jak nazywa się oprogramowanie do zautomatyzowanego składu tekstu?",
                    "Co widnieje na logo Linux'a?",
                    "Jak nazywa się format w którym zapisywane są dokumenty w Adobe Photoshop?",
                    "Jaka jest maksymalna ilość pamięci ram, którą obsłuży 32 bitowy system Windows?",
                    "Jak nazywany jest dysk na którym przechowywane są dane w komputerze?",
                    "W jakim systemie liczbowm zapisywane są kolory?",
                    "Jak nazywa się dział informatyki zajmujący się tworzeniem oprogramowania?",
                    "Bezpieczeństwo komputerowe to połączenie informatyki z?",
                    "Jak popularnie nazywa się urządzenie wskazujące do pracy na interfejsach graficznych?",
                    "Jak nazywa się rozszerzalny język znaczników przeznaczony do strukturalnej reprezentacji danych?"
            };

    private String answersIT[][] =
            {
                    {"4", "8", "16", "32"},
                    {"CMYK", "HSV", "RGB", "CMY"},
                    {"Windows 98", "Windows Milenium", "Windows Vista", "Windows XP"},
                    {"C++", "python", "javascript", "sql"},
                    {"python", "c", "swift", "java"},
                    {"strukturalnym", "skryptowym", "obiektowym", "żadnym z wymienionych"},
                    {"asc", "distinct", "once", "unique"},
                    {"adobe \n photoshop", "inkscape", "gimp", "ms paint"},
                    {"owncng", "cngown", "chown", "ownch"},
                    {"mkdir", "help", "ps", "wall"},
                    {"excel", "one note", "outlook", "word"},
                    {"gingerbread", "lolipop", "nougat", "cupcake"},
                    {"aos", "ios", "osp", "osi"},
                    {"jpeg", "png", "xcf", "tiff"},
                    {"ctrl + alt", "ctrl + x", "ctrl + c", "ctrl + d"},
                    {"pentium", "celeron", "i5", "i7"},
                    {"O(n^2)", "O(n)", "O(1)", "O(n^3)"},
                    {"Pixel draw", "Pixel Art", "Pixel Edit", "Pixel Paint"},
                    {"Safari", "Google Chrome", "Opera", "Firefox"},
                    {"Excel", "Access", "Word", "Powerpoint"},
                    {"JDK", "Latex", "html", "Kile"},
                    {"Krokodyl", "Żółw", "Pingwin", "Wąż"},
                    {"xcf", "psd", "psa", "gif"},
                    {"4gb", "8gb", "2gb", "16gb"},
                    {"dysk \n miękki", "dysk \n twardy", "dysk \n danych", "dysk \n numeryczny"},
                    {"dwójkowym", "szesnastkowym", "dziesiętnym", "ósemkowym"},
                    {"języki \n programowania", "inżynieria \n oprogramowania", "algorytmika", "webmastering"},
                    {"mechaniką", "telekomunikacją", "termodynamiką", "grafiką"},
                    {"klawiatura", "myszka", "ekran", "mikrofon"},
                    {"xml", "c++", "jpg", "std"}
            };

    private String correctAnswersIT[] =
            {
                    "8",
                    "RGB",
                    "Windows 98",
                    "sql",
                    "c",
                    "obiektowym",
                    "distinct",
                    "inkscape",
                    "chown",
                    "ps",
                    "outlook",
                    "nougat",
                    "ios",
                    "jpeg",
                    "ctrl + c",
                    "i7",
                    "O(n^2)",
                    "Pixel Art",
                    "Safari",
                    "Powerpoint",
                    "Latex",
                    "Pingwin",
                    "psd",
                    "4gb",
                    "dysk \n twardy",
                    "szesnastkowym",
                    "inżynieria \n oprogramowania",
                    "telekomunikacją",
                    "myszka",
                    "xml"
            };

    public String getQuestionIT(int a) {
        String question = contentQuestionsIT[a];
        return question;
    }

    public String getChoice1(int a) {
        return answersIT[a][0];
    }

    public String getChoice2(int a) {
        return answersIT[a][1];
    }

    public String getChoice3(int a) {
        return answersIT[a][2];
    }

    public String getChoice4(int a) {
        return answersIT[a][3];
    }

    public String getCorrectAnswer(int a) {
        return correctAnswersIT[a];
    }
};
