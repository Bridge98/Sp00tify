@PostMapping("/upload")
public ResponseEntity<TrackResponse> uploadTrack(
    @RequestParam("file") MultipartFile file,
    @RequestParam("cover") MultipartFile cover,
    @RequestParam("title") String title,
    @RequestParam("genre") String genre,
    @AuthenticationPrincipal User user
) {

    String audioUrl = storageService.upload(file);
    String coverUrl = storageService.upload(cover);

    Track track = trackService.createTrack(
        title,
        genre,
        audioUrl,
        coverUrl,
        user
    );

    return ResponseEntity.ok(mapper.toResponse(track));
}
