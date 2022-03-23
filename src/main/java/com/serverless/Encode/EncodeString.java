package com.serverless.Encode;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CodingErrorAction;
import java.nio.charset.StandardCharsets;

public class EncodeString {
    public static byte[] toByteArray(String str) throws CharacterCodingException {
        CharsetEncoder encoder = StandardCharsets.UTF_8.newEncoder();
        encoder.onMalformedInput(CodingErrorAction.IGNORE)
          .onUnmappableCharacter(CodingErrorAction.REPLACE)
          .replaceWith(new byte[] { 0 });
    
        byte[] byteArrray = encoder.encode(CharBuffer.wrap(str)).array();
        return byteArrray;
    }

    public static ByteBuffer toByteBuffer(String str) throws IOException {
        byte[] byteArray = toByteArray(str);

        InputStream initialStream = new ByteArrayInputStream(byteArray);
        ByteBuffer byteBuffer = ByteBuffer.allocate(byteArray.length);
        while (initialStream.available() > 0) {
            byteBuffer.put((byte) initialStream.read());
        }
        return byteBuffer;
    }
}
