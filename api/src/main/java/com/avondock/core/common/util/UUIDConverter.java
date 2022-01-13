package com.avondock.core.common.util;

import org.apache.commons.codec.binary.Base64;

import java.nio.ByteBuffer;
import java.util.UUID;

public class UUIDConverter {

    public static String toBase64() {
        UUID       uuid = UUID.randomUUID();
        ByteBuffer bb   = ByteBuffer.wrap(new byte[16]);
        bb.putLong(uuid.getMostSignificantBits());
        bb.putLong(uuid.getLeastSignificantBits());
        return Base64.encodeBase64URLSafeString(bb.array());
    }
}
