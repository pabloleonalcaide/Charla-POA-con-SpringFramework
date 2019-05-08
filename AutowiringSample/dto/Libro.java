public class Libro {

    private String titulo = "Spring en el Google IO";

    @Autowired
    private Autor autor;

    private String genero = "Terror";
    private String editorial = "GDG";
    private int edicion = 1;
    private int paginas = 257;

}