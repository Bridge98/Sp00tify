@RestController
@RequestMapping("/api/tracks")
@RequiredArgsConstructor
public class TrackController {

    private final TrackRepository trackRepository;
    private final UserRepository userRepository;

    private final String UPLOAD_DIR = "uploads/";

    @PostMapping("/upload")
    public ResponseEntity<?> upload(
            @RequestParam("file") MultipartFile file,
            @RequestParam("cover") MultipartFile cover,
            @RequestParam("title") String title,
            @RequestParam("genre") String genre,
            @RequestHeader("Authorization") String authHeader
    ) throws IOException {

        String email = extractEmail(authHeader);

        User user = userRepository.findByEmail(email).orElseThrow();

        String audioPath = UPLOAD_DIR + UUID.randomUUID() + "_" + file.getOriginalFilename();
        String coverPath = UPLOAD_DIR + UUID.randomUUID() + "_" + cover.getOriginalFilename();

        Files.createDirectories(Paths.get(UPLOAD_DIR));

        Files.write(Paths.get(audioPath), file.getBytes());
        Files.write(Paths.get(coverPath), cover.getBytes());

        Track track = new Track();
        track.setTitle(title);
        track.setGenre(genre);
        track.setFilePath(audioPath);
        track.setCoverPath(coverPath);
        track.setUploadedBy(user);

        trackRepository.save(track);

        return ResponseEntity.ok(track.getId());
    }

    private String extractEmail(String header) {
        String token = header.replace("Bearer ", "");
        return jwtService.extractEmail(token);
    }
}
