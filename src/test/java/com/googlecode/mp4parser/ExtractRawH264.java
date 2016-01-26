package com.googlecode.mp4parser;

import com.coremedia.iso.IsoFile;
import com.coremedia.iso.IsoTypeReaderVariable;
import com.coremedia.iso.boxes.Container;
import com.coremedia.iso.boxes.TrackBox;
import com.coremedia.iso.boxes.h264.AvcConfigurationBox;
import com.coremedia.iso.boxes.mdat.SampleList;
import com.googlecode.mp4parser.authoring.Movie;
import com.googlecode.mp4parser.authoring.Sample;
import com.googlecode.mp4parser.authoring.builder.DefaultMp4Builder;
import com.googlecode.mp4parser.authoring.container.mp4.MovieCreator;
import com.googlecode.mp4parser.authoring.tracks.H264TrackImpl;
import com.googlecode.mp4parser.util.Path;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;


public class ExtractRawH264 {
    public static void main(String[] args) throws IOException {
        IsoFile isoFile = new IsoFile("D:\\downloads\\cracked.s01e01.hdtv.x264-2hd.mp4");
        TrackBox trackBox = (TrackBox) Path.getPath(isoFile, "/moov/trak/mdia/minf/stbl/stsd/avc1/../../../../../");
        SampleList sl = new SampleList(trackBox);


        FileChannel fc = new FileOutputStream("out.h264").getChannel();
        ByteBuffer separator = ByteBuffer.wrap(new byte[]{0, 0, 0, 1});

        fc.write((ByteBuffer) separator.rewind());
        // Write SPS
        fc.write(ByteBuffer.wrap(
                ((AvcConfigurationBox) Path.getPath(trackBox, "mdia/minf/stbl/stsd/avc1/avcC")
                ).getSequenceParameterSets().get(0)));
        // Warning:
        // There might be more than one SPS (I've never seen that but it is possible)

        fc.write((ByteBuffer) separator.rewind());
        // Write PPS
        fc.write(ByteBuffer.wrap(
                ((AvcConfigurationBox) Path.getPath(trackBox, "mdia/minf/stbl/stsd/avc1/avcC")
                ).getPictureParameterSets().get(0)));
        // Warning:
        // There might be more than one PPS (I've never seen that but it is possible)

        int lengthSize = ((AvcConfigurationBox) Path.getPath(trackBox, "mdia/minf/stbl/stsd/avc1/avcC")).getLengthSizeMinusOne() + 1;
        for (Sample sample : sl) {
            while (sample.remaining() > 0) {
                ByteBuffer bb = sample.asByteBuffer();
                int length = (int) IsoTypeReaderVariable.read(bb, lengthSize);
                fc.write((ByteBuffer) separator.rewind());
                fc.write((ByteBuffer) bb.slice().limit(length));
                bb.position(bb.position() + length);
            }


        }
        fc.close();

    }


}
