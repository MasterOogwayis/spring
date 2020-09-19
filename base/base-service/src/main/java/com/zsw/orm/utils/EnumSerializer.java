package com.zsw.orm.utils;


import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.Objects;

/**
 * @author ZhangShaowei on 2019/8/22 12:25
 **/
public class EnumSerializer extends JsonSerializer<TypeE> {
    @Override
    public void serialize(TypeE value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        String fieldName = gen.getOutputContext().getCurrentName();
        if (Objects.isNull(value)) {
            gen.writeNull();
            gen.writeStringField(fieldName, null);
        } else {
            gen.writeNumber(value.ordinal());
            gen.writeStringField(fieldName, value.name());
        }
    }

    @Override
    public Class<TypeE> handledType() {
        return TypeE.class;
    }
}
