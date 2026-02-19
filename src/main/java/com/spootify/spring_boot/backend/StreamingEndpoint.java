@GetMapping("/{id}/stream")
public ResponseEntity<Resource> stream(
        @PathVariable UUID id,
        @RequestHeader HttpHeaders headers
) throws IOException {

    Track track = trackRepository.findById(id).orElseThrow();

    File file = new File(track.getFilePath());
    Resource resource = new FileSystemResource(file);

    long contentLength = file.length();
    List<HttpRange> ranges = headers.getRange();

    if (ranges.isEmpty()) {
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .contentLength(contentLength)
                .body(resource);
    }

    HttpRange range = ranges.get(0);
    long start = range.getRangeStart(contentLength);
    long end = range.getRangeEnd(contentLength);

    long rangeLength = end - start + 1;

    InputStream inputStream = new FileInputStream(file);
    inputStream.skip(start);

    InputStreamResource inputStreamResource = new InputStreamResource(inputStream);

    return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT)
            .contentType(MediaType.APPLICATION_OCTET_STREAM)
            .contentLength(rangeLength)
            .header(HttpHeaders.CONTENT_RANGE,
                    "bytes " + start + "-" + end + "/" + contentLength)
            .body(inputStreamResource);
}
