@Entity
public class Track {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String title;
    private String artistName;

    private String genre;
    private Integer duration;

    private String fileUrl;
    private String coverUrl;

    @ManyToOne
    private User uploadedBy;

    private LocalDateTime createdAt;
}
