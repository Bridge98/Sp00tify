"use client";

import { useEffect, useState } from "react";
import { getAllTracks } from "@/services/trackService";
import AudioPlayer from "@/components/AudioPlayer";
import { Track } from "@/types/track";

export default function TracksPage() {
  const [tracks, setTracks] = useState<Track[]>([]);

  useEffect(() => {
    getAllTracks().then(setTracks);
  }, []);

  return (
    <div className="p-10">
      <h1 className="text-2xl font-bold mb-4">Tracks</h1>

      {tracks.map((track) => (
        <div key={track.id} className="mb-6">
          <h2>{track.title}</h2>
          <AudioPlayer trackId={track.id} />
        </div>
      ))}
    </div>
  );
}
