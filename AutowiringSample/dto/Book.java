public class Book {

    private String title = "Spring en el Google IO";

    @Autowired
    private Author author;

    private String genre = "Terror";
    private String editorial = "GDG";
    private int edition = 1;
    private int pages = 257;

}