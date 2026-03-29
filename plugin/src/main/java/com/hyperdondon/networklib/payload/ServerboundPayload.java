package com.hyperdondon.networklib.payload;

import io.netty.buffer.ByteBuf;

public abstract class ServerboundPayload {
    public abstract void read(ByteBuf data);
}
