interface Props {
  trackId: string;
}

export default function AudioPlayer({ trackId }: Props) {
  return (
    <audio controls className="w-full">
      <source
        src={`http://localhost:8080/api/tracks/${trackId}/stream`}
        type="audio/mpeg"
      />
    </audio>
  );
}
