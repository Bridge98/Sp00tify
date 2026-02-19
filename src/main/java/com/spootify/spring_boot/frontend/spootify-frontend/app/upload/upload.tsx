"use client";

import { useState } from "react";
import { uploadTrack } from "@/services/trackService";

export default function UploadPage() {
  const [file, setFile] = useState<File | null>(null);
  const [cover, setCover] = useState<File | null>(null);
  const [title, setTitle] = useState("");
  const [genre, setGenre] = useState("");

  const handleUpload = async () => {
    if (!file || !cover) return;

    await uploadTrack(file, cover, title, genre);
    alert("Upload completed");
  };

  return (
    <div className="p-10 space-y-4">
      <h1 className="text-2xl font-bold">Upload Track</h1>

      <input type="text" placeholder="Title"
        onChange={(e) => setTitle(e.target.value)} />

      <input type="text" placeholder="Genre"
        onChange={(e) => setGenre(e.target.value)} />

      <input type="file" onChange={(e) => setFile(e.target.files![0])} />
      <input type="file" onChange={(e) => setCover(e.target.files![0])} />

      <button
        onClick={handleUpload}
        className="bg-green-500 text-white px-4 py-2 rounded"
      >
        Upload
      </button>
    </div>
  );
}
