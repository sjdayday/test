package com.googlecode.mp4parser;

import com.coremedia.iso.IsoFile;

import java.io.IOException;

/**
 * Gets the duration of a video.
 */
public class GetDuration {
    public static void main(String[] args) throws IOException {
        String filename = GetDuration.class.getProtectionDomain().getCodeSource().getLocation().getFile() + "/count-video.mp4";
        IsoFile isoFile = new IsoFile(filename);
        double lengthInSeconds = (double)
                isoFile.getMovieBox().getMovieHeaderBox().getDuration() /
                isoFile.getMovieBox().getMovieHeaderBox().getTimescale();
        System.err.println(lengthInSeconds);

    }

}
