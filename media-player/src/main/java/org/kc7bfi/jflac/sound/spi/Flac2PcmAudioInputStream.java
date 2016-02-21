package org.kc7bfi.jflac.sound.spi;

/**
 * libFLAC - Free Lossless Audio Codec library
 * Copyright (C) 2001,2002,2003  Josh Coalson
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Library General Public
 * License as published by the Free Software Foundation; either
 * version 2 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Library General Public License for more details.
 *
 * You should have received a copy of the GNU Library General Public
 * License along with this library; if not, write to the
 * Free Software Foundation, Inc., 59 Temple Place - Suite 330,
 * Boston, MA  02111-1307, USA.
 */
import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.AudioFormat;

import org.kc7bfi.jflac.PCMProcessor;
import org.kc7bfi.jflac.FLACDecoder;
import org.kc7bfi.jflac.PropertiesContainer;
import org.kc7bfi.jflac.frame.Frame;
import org.kc7bfi.jflac.metadata.Metadata;
import org.kc7bfi.jflac.metadata.StreamInfo;
import org.kc7bfi.jflac.util.ByteData;
import org.tritonus.share.sampled.convert.TAsynchronousFilteredAudioInputStream;

/**
 * Converts an Flac bitstream into a PCM 16bits/sample audio stream.
 * 
 * @author Marc Gimpel, Wimba S.A. (marc@wimba.com)
 * @author Florian Bomers
 * @version $Revision: 1.6 $
 */
public class Flac2PcmAudioInputStream extends TAsynchronousFilteredAudioInputStream
        implements PropertiesContainer, PCMProcessor {

    /** The underlying inputStream. */
    private InputStream in;
    /** Flac Decoder. */
    private FLACDecoder decoder;
    private ByteData pcmData;
    /** StreamInfo MetaData. */
    private StreamInfo streamInfo;
    /** the meta data from the stream */
    private Metadata[] metaData;

    /**
     * Constructor.
     *
     * @param in the underlying input stream.
     * @param format the target format of this stream's audio data.
     * @param length the length in sample frames of the data in this stream.
     */
    public Flac2PcmAudioInputStream(InputStream in, AudioFormat format) {
        super(format, -1);

        this.in = in;
    }

    /**
     * Initialize the Flac Decoder after reading the Header.
     *
     * @exception IOException
     */
    protected void initDecoder() throws IOException {
        decoder = new FLACDecoder(in);
        decoder.addPCMProcessor(this);
        metaData = decoder.readMetadata();
    }

    /**
     * Process the StreamInfo block.
     *
     * @param streamInfo the StreamInfo block
     * @see org.kc7bfi.jflac.PCMProcessor#processStreamInfo(org.kc7bfi.jflac.metadata.StreamInfo)
     */
    public void processStreamInfo(StreamInfo streamInfo) {
        this.streamInfo = streamInfo;
    }

    /**
     * Process the decoded PCM bytes. This is called synchronously from the
     * fill() method.
     *
     * @param pcm The decoded PCM data
     * @see org.kc7bfi.jflac.PCMProcessor#processPCM(ByteData)
     */
    public void processPCM(ByteData pcm) {
        getCircularBuffer().write(pcm.getData(), 0, pcm.getLen());
    }

    /**
     * @return the streamInfo
     */
    public StreamInfo getStreamInfo() {
        return streamInfo;
    }

    /**
     * @return the metaData
     */
    public Metadata[] getMetaData() {
        return metaData;
    }

    @Override
    public long skip(long bytes) throws IOException {
        if (decoder == null) {
            initDecoder();
        }
        if (decoder.isEOF()) {
            getCircularBuffer().close();
        } else {
//            long sync = bytes % streamInfo.getMaxBlockSize();
//            long bytesRead = super.skip(bytes - sync + 982);
            long bytesRead = 0L;
            bytes -= decoder.getTotalBytesRead();
            while (bytesRead < bytes) {
                Frame frame = decoder.readNextFrame();
                if (frame != null) {
                    bytesRead = decoder.getTotalBytesRead();
                }
            }
            return bytesRead;
        }
        return -1;
    }

    public void execute() {
        try {
            if (decoder == null) {
                initDecoder();
            }
            if (decoder.isEOF()) {
                getCircularBuffer().close();
                return;
            } else {
                Frame frame = decoder.readNextFrame();
                if (frame != null) {
                    pcmData = decoder.decodeFrame(frame, pcmData);
                    processPCM(pcmData);
                }
            }
        } catch (IOException ex) {
        }
    }
}