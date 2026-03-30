package com.hyperdondon.networklib.api.payload;

import io.netty.buffer.ByteBuf;

public abstract class ServerboundPayload {
    /**
     * Reads the data. Executes when a payload is received.
     * @param data The data in a {@link ByteBuf}
     */
    public abstract void read(ByteBuf data);
}
